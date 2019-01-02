package com.ljg.guiderecycleview.nouprv;


import java.util.List;

public class VideoControlBean extends BaseResponseEntity {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * accessToken : string
         * channelName : string
         * channelNo : 0
         * deviceId : string
         * deviceName : string
         * isEncrypt : 0
         * picUrl : string
         * stationId : string
         * stationName : string
         * status : 0
         * videoLevel : 0
         */

        private String accessToken;
        private String channelName;
        private int channelNo;
        private String deviceId;
        private String deviceName;
        private int isEncrypt;
        private String picUrl;
        private String stationId;
        private String stationName;
        private int status;
        private int videoLevel;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public int getChannelNo() {
            return channelNo;
        }

        public void setChannelNo(int channelNo) {
            this.channelNo = channelNo;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public int getIsEncrypt() {
            return isEncrypt;
        }

        public void setIsEncrypt(int isEncrypt) {
            this.isEncrypt = isEncrypt;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getVideoLevel() {
            return videoLevel;
        }

        public void setVideoLevel(int videoLevel) {
            this.videoLevel = videoLevel;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "accessToken='" + accessToken + '\'' +
                    ", channelName='" + channelName + '\'' +
                    ", channelNo=" + channelNo +
                    ", deviceId='" + deviceId + '\'' +
                    ", deviceName='" + deviceName + '\'' +
                    ", isEncrypt=" + isEncrypt +
                    ", picUrl='" + picUrl + '\'' +
                    ", stationId='" + stationId + '\'' +
                    ", stationName='" + stationName + '\'' +
                    ", status=" + status +
                    ", videoLevel=" + videoLevel +
                    '}';
        }
    }
}
