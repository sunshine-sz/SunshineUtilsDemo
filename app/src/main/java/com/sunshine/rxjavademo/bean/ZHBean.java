package com.sunshine.rxjavademo.bean;

import java.util.List;

/**
 * 作者: Sunshine
 * 时间: 2016/11/3.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class ZHBean {

    /**
     * date : 20161103
     * stories : [{"ga_prefix":"110316","id":8944114,"images":["http://pic3.zhimg.com/d81452e0c470dfc956471805a47e417a.jpg"],"title":"仔细想想，现在春夏秋冬的划分很有问题啊","type":0}]
     * top_stories : [{"ga_prefix":"110315","id":8945666,"image":"http://pic3.zhimg.com/033f2f097c670ea515253ac0b7d135f6.jpg","title":"三五人围着一锅私房豆角焖面，吃到撑，想想都满足","type":0}]
     */

    private String date;
    private List<StoriesEntity> stories;
    private List<TopStoriesEntity> top_stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public static class StoriesEntity {
        /**
         * ga_prefix : 110316
         * id : 8944114
         * images : ["http://pic3.zhimg.com/d81452e0c470dfc956471805a47e417a.jpg"]
         * title : 仔细想想，现在春夏秋冬的划分很有问题啊
         * type : 0
         */

        private String ga_prefix;
        private int id;
        private String title;
        private int type;
        private List<String> images;

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }

        public List<String> getImages() {
            return images;
        }
    }

    public static class TopStoriesEntity {
        /**
         * ga_prefix : 110315
         * id : 8945666
         * image : http://pic3.zhimg.com/033f2f097c670ea515253ac0b7d135f6.jpg
         * title : 三五人围着一锅私房豆角焖面，吃到撑，想想都满足
         * type : 0
         */

        private String ga_prefix;
        private int id;
        private String image;
        private String title;
        private int type;

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public int getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }
    }
}
