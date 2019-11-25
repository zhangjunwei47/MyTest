package com.example.kaola.myapplication.util;

import com.kaolafm.report.ReportHelper;
import com.kaolafm.report.event.EndListenReportEvent;
import com.kaolafm.report.event.StartListenReportEvent;

public class ReportUtil {

    boolean isPlaying = false;
    private void testReport() {
        if (isPlaying) {
            isPlaying = false;
            //上报结束
            //ReportHelper.getInstance().addEndListenReport("1", true);
            EndListenReportEvent endListenReportEvent = new EndListenReportEvent();
            endListenReportEvent.setAlbumid("11111111");
            endListenReportEvent.setAudioid("11122211111");
            endListenReportEvent.setPlayid("11111111111111111112");
            ReportHelper.getInstance().addEvent(endListenReportEvent);
        } else {
            isPlaying = true;
            //播放开始
            StartListenReportEvent startListenReportEvent =new StartListenReportEvent();
            startListenReportEvent.setAlbumid("11111111");
            startListenReportEvent.setAudioid("222222");
            startListenReportEvent.setPosition("10000");
            ReportHelper.getInstance().addEvent(startListenReportEvent);
        }
    }
}
