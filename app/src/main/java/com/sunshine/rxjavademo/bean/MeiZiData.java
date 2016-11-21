package com.sunshine.rxjavademo.bean;

import java.util.List;

/**
 * 作者：Lizhao
 * 时间：2016/11/7 16:56
 * 邮箱：44493547@qq.com
 * 备注：妹子实体类
 */
public class MeiZiData {

    /**
     * error : false
     * results : [{"_id":"580e9c74421aa90e799ec1fa","createdAt":"2016-10-25T07:42:44.254Z","desc":"10-25","publishedAt":"2016-10-25T11:35:01.586Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9469eoojtj20u011hdjy.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsEntity> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity {
        /**
         * _id : 580e9c74421aa90e799ec1fa
         * createdAt : 2016-10-25T07:42:44.254Z
         * desc : 10-25
         * publishedAt : 2016-10-25T11:35:01.586Z
         * source : chrome
         * type : 福利
         * url : http://ww4.sinaimg.cn/large/610dc034jw1f9469eoojtj20u011hdjy.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String get_id() {
            return _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getSource() {
            return source;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean getUsed() {
            return used;
        }

        public String getWho() {
            return who;
        }
    }
}
