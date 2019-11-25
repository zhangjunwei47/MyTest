package com.kaolafm.module.update.modle;

/**
 * 升级信息
 */
public class PluginInfo {
    /**
     * 强制升级
     */
    public static final int MANDATORY_UPDATE = 1;

    /**
     * 显示提示框
     */
    public static final int SHOW_TOAST = 0;

    /**
     * 升级
     */
    public static final int UPDATE = 1;

    /**
     * apkUrl : http://image.kaolafm.net/mz/apk/201911/2d17f515-3c32-4608-946d-d57da8ecbeb2.apk
     * apkName : Android_KaolaFM-release.apk
     * versionNum : 1.1.2
     * isPrompt : 1 (是否提示升级  0提示  1不提示)
     * upgradeNotes : 这是测试静默升级1的升级说明
     * isUpgrade : 0, 1 (0 不升级, 1升级)
     * upgradeSituation : 0 (升级形势 0非强制 1强制)
     */

    private String apkUrl;
    private String apkName;
    private String versionNum;
    private int isPrompt;
    private String upgradeNotes;
    private int isUpgrade;
    private int upgradeSituation;
    private String apkMd5;

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public int getIsPrompt() {
        return isPrompt;
    }

    public void setIsPrompt(int isPrompt) {
        this.isPrompt = isPrompt;
    }

    public String getUpgradeNotes() {
        return upgradeNotes;
    }

    public void setUpgradeNotes(String upgradeNotes) {
        this.upgradeNotes = upgradeNotes;
    }

    public int isIsUpgrade() {
        return isUpgrade;
    }

    public void setIsUpgrade(int isUpgrade) {
        this.isUpgrade = isUpgrade;
    }

    public int getUpgradeSituation() {
        return upgradeSituation;
    }

    public void setUpgradeSituation(int upgradeSituation) {
        this.upgradeSituation = upgradeSituation;
    }

    public String getApkMd5() {
        return apkMd5;
    }

    public void setApkMd5(String apkMd5) {
        this.apkMd5 = apkMd5;
    }

    /**
     * 是否需要升级
     *
     * @return
     */
    public boolean isNeedUpdate() {
        return isUpgrade == UPDATE;
    }

    /**
     * 是否显示提示框
     *
     * @return
     */
    public boolean isShowToast() {
        return isPrompt == SHOW_TOAST;
    }

    /**
     * 是否强制升级
     *
     * @return
     */
    public boolean isMandatoryUpdate() {
        return upgradeSituation == MANDATORY_UPDATE;
    }
}
