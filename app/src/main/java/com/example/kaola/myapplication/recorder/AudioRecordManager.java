package com.example.kaola.myapplication.recorder;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AudioRecordManager {

    private static final String TAG = "AudioRecordManager";

    private static final int SAMPLE_RATE_IN_HZ = 44100;

    private AudioRecord mRecorder;

    private final File mRecordTmpDir;

    private File mRecordTmpFile;
    private FileOutputStream mFileOutput;
    /**
     * log_id : 4472921410481957691
     * results : [{"name":"yangfei","score":0.5170800685882568},{"name":"huanglulu","score":0.48291996121406555}]
     */

    private long log_id;
    private List<ResultsBean> results;

    public String getmResultFilePath() {
        return mResultFilePath;
    }

    private String mResultFilePath;

    private final int mBufferSize;

    private Thread mRecordThread;
    private boolean isStart;

    private static volatile AudioRecordManager sInstance;

    private AudioRecordManager() {
        mBufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        mRecordTmpDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "tmp");
        if (!mRecordTmpDir.exists()) {
            mRecordTmpDir.mkdirs();
        }
    }

    public static AudioRecordManager getInstance() {
        if (sInstance == null) {
            synchronized (AudioRecordManager.class) {
                if (sInstance == null) {
                    sInstance = new AudioRecordManager();
                }
            }
        }
        return sInstance;
    }

    public void startRecord(String path) {
        Log.d(TAG, "startRecord: " + path);
        try {
            setResultFilePath(mRecordTmpDir.getAbsolutePath() + path);
            startRecordThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecord() {
        Log.d(TAG, "stopRecord: ");
        try {
            destroyThread();
            if (mRecorder != null) {
                if (mRecorder.getState() == AudioRecord.RECORDSTATE_RECORDING) {
                    mRecorder.stop();
                }
                if (mRecorder != null) {
                    mRecorder.release();
                }
            }
            if (mFileOutput != null) {
                mFileOutput.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (mFileOutput != null) {
                    mFileOutput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        convertWaveFile();
        if (mRecordTmpFile != null && mRecordTmpFile.exists()) {
            mRecordTmpFile.delete();
        }
    }

    private boolean createTmpFile() {
        mRecordTmpFile = new File(mRecordTmpDir, "tmp" + System.currentTimeMillis() + ".pcm");
        Log.d(TAG, "mRecordTmpFile: " + mRecordTmpFile.getAbsolutePath());
        if (mRecordTmpFile.exists()) {
            mRecordTmpFile.delete();
        }
        try {
            mFileOutput = new FileOutputStream(mRecordTmpFile);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    private Runnable recordRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO);
                boolean success = createTmpFile();
                Log.d(TAG, "recordRunnable createTmpFile: " + success);
                if (!success) {
                    return;
                }
                int bytesRecord;
                byte[] tempBuffer = new byte[mBufferSize];
                mRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ,
                        AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, mBufferSize * 2);
                if (mRecorder.getState() != AudioRecord.STATE_INITIALIZED) {
                    stopRecord();
                    return;
                }
                mRecorder.startRecording();
                Log.d(TAG, "recordRunnable startRecording ");
                while (isStart) {
                    if (null != mRecorder) {
                        bytesRecord = mRecorder.read(tempBuffer, 0, mBufferSize);
                        Log.d(TAG, "bytesRecord = " + bytesRecord);
                        if (bytesRecord == AudioRecord.ERROR_INVALID_OPERATION
                                || bytesRecord == AudioRecord.ERROR_BAD_VALUE) {
                            Log.d(TAG, "bytesRecord is bad");
                            continue;
                        }
                        if (bytesRecord > 0) {
                            mFileOutput.write(tempBuffer, 0, bytesRecord);
                        } else {
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };

    private void setResultFilePath(String path) {
        mResultFilePath = path;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    private void destroyThread() {
        Log.d(TAG, "destroyThread ");
        try {
            isStart = false;
            if (null != mRecordThread && Thread.State.RUNNABLE == mRecordThread.getState()) {
                try {
                    Thread.sleep(10);
                    mRecordThread.interrupt();
                } catch (Exception e) {
                    mRecordThread = null;
                }
            }
            mRecordThread = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mRecordThread = null;
        }
    }

    private void startRecordThread() {
        Log.d(TAG, "startRecordThread ");
        destroyThread();
        isStart = true;
        if (mRecordThread == null) {
            mRecordThread = new Thread(recordRunnable);
            mRecordThread.start();
        }
    }

    private void convertWaveFile() {
        Log.d(TAG, "recordRunnable convertWaveFile ");
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0L;
        long totalDataLen;
        int channels = 1;
        long byteRate = 16 * SAMPLE_RATE_IN_HZ * channels / 8;
        byte[] data = new byte[512];
        try {
            in = new FileInputStream(mRecordTmpFile);
            out = new FileOutputStream(mResultFilePath);
            totalAudioLen = in.getChannel().size();
            totalDataLen = totalAudioLen + 36;
            writeWaveFileHeader(out, totalAudioLen, totalDataLen, SAMPLE_RATE_IN_HZ, channels, byteRate);
            while (in.read(data) != -1) {
                out.write(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeWaveFileHeader(FileOutputStream out, long totalAudioLen,
                                     long totalDataLen, long longSampleRate, int channels, long byteRate)
            throws IOException {
        byte[] header = new byte[44];
        header[0] = 'R';
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f';
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16;
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1;
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (2 * 16 / 8); // block align
        header[33] = 0;
        header[34] = 16;
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * name : yangfei
         * score : 0.5170800685882568
         */

        private String name;
        private double score;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
}