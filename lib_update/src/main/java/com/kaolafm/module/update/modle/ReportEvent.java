package com.kaolafm.module.update.modle;

public class ReportEvent {
    private String appid;
    private String udid;
    private String imsi;
    private String uid;
    private String eventcode;
    private String timestamp;
    private String lon;
    private String lat;
    private String openid;
    private String page;
    private String os;
    private String screen_height;
    private String screen_width;
    private String osversion;
    private String app_version;
    private String app_version2;
    private String lib_version;

    /**
     * 应用每启动一次，生成一个新的sessionid
     */
    private String sessionid;

    /**
     * 当前应用每发生一次事件上报，action_id自增1
     */
    private String action_id;
    /**
     * 0：否；1：真
     */
    private String wifi;

    /**
     * t
     * -1：未知，0：无网，1：wifi，2：2g，3：3g，4：4g
     */
    private String network;

    /**
     * 0、未知，1、移动，2、联通，3、电信
     */
    private String carrier;

    /**
     * 车载唯一标识码 	车厂账号
     */
    private String car_id;

    /**
     * 渠道类型 0：后装，1：前装
     */
    private String market_type;

    /**
     * 合作方式 0：APK，1：SDK，2：API，3：控件
     */
    private String appid_type;

    /**
     * 产品直接落地车辆所属的车厂，后装则为方案商名称
     */
    private String oem;
    /**
     * 品牌
     */
    private String car_brand;
    /**
     * 车型
     */
    private String car_type;
    /**
     * 屏幕状态0：横屏；1：竖屏
     */
    private String screen_direction;
    /**
     * 设备制造商
     */
    private String manufacturer;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 播放行为发生时，每播放一个音频时，生成一个新的唯一的id，没有音频播放时，可以赋默认值0000 播放器拉取新的播放资源时更新playid，比如点播、播放开始时，playid均更新；选择搜索结果上报时playid不更新
     */
    private String playid;

    /**
     * 鉴权时使用的包名
     */
    private String channel;

    /**
     * 该应用所属的开发者id
     */
    private String developer;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEventcode() {
        return eventcode;
    }

    public void setEventcode(String eventcode) {
        this.eventcode = eventcode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getScreen_height() {
        return screen_height;
    }

    public void setScreen_height(String screen_height) {
        this.screen_height = screen_height;
    }

    public String getScreen_width() {
        return screen_width;
    }

    public void setScreen_width(String screen_width) {
        this.screen_width = screen_width;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getApp_version2() {
        return app_version2;
    }

    public void setApp_version2(String app_version2) {
        this.app_version2 = app_version2;
    }

    public String getLib_version() {
        return lib_version;
    }

    public void setLib_version(String lib_version) {
        this.lib_version = lib_version;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getMarket_type() {
        return market_type;
    }

    public void setMarket_type(String market_type) {
        this.market_type = market_type;
    }

    public String getAppid_type() {
        return appid_type;
    }

    public void setAppid_type(String appid_type) {
        this.appid_type = appid_type;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getScreen_direction() {
        return screen_direction;
    }

    public void setScreen_direction(String screen_direction) {
        this.screen_direction = screen_direction;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlayid() {
        return playid;
    }

    public void setPlayid(String playid) {
        this.playid = playid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
