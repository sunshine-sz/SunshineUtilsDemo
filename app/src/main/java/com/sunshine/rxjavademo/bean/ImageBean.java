package com.sunshine.rxjavademo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图的Bean
 * Created by Sunshine on 2016/3/24.
 */
public class ImageBean implements Parcelable {

    /**
     * code : 0
     * data : {"list":[{"arid":50,"article":{"aid":26,"alname":"睡眠","aotime":"2016-03-23","aouname":"张三","apcount":47,"appath":"http://112.74.200.231:8088/FILE/PIC/26/7d59f81f-3d72-4eb7-a2d5-78d9bbc38d71.jpg","ascount":2,"atitle":"半夜被吵醒比熬夜不睡的危害大","aucount":14},"rid":"1","sindex":1}]}
     * legal : 0
     */

    private int code;
    private DataBean data;
    private int legal;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getLegal() {
        return legal;
    }

    public void setLegal(int legal) {
        this.legal = legal;
    }

    public static class DataBean implements Parcelable {
        /**
         * arid : 50
         * article : {"aid":26,"alname":"睡眠","aotime":"2016-03-23","aouname":"张三","apcount":47,"appath":"http://112.74.200.231:8088/FILE/PIC/26/7d59f81f-3d72-4eb7-a2d5-78d9bbc38d71.jpg","ascount":2,"atitle":"半夜被吵醒比熬夜不睡的危害大","aucount":14}
         * rid : 1
         * sindex : 1
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int arid;
            /**
             * aid : 26
             * alname : 睡眠
             * aotime : 2016-03-23
             * aouname : 张三
             * apcount : 47
             * appath : http://112.74.200.231:8088/FILE/PIC/26/7d59f81f-3d72-4eb7-a2d5-78d9bbc38d71.jpg
             * ascount : 2
             * atitle : 半夜被吵醒比熬夜不睡的危害大
             * aucount : 14
             */

            private ArticleBean article;
            private String rid;
            private int sindex;

            public int getArid() {
                return arid;
            }

            public void setArid(int arid) {
                this.arid = arid;
            }

            public ArticleBean getArticle() {
                return article;
            }

            public void setArticle(ArticleBean article) {
                this.article = article;
            }

            public String getRid() {
                return rid;
            }

            public void setRid(String rid) {
                this.rid = rid;
            }

            public int getSindex() {
                return sindex;
            }

            public void setSindex(int sindex) {
                this.sindex = sindex;
            }

            public static class ArticleBean {
                private int aid;
                private String alname;
                private String aotime;
                private String aouname;
                private int apcount;
                private String appath;
                private int ascount;
                private String atitle;
                private int aucount;

                public int getAid() {
                    return aid;
                }

                public void setAid(int aid) {
                    this.aid = aid;
                }

                public String getAlname() {
                    return alname;
                }

                public void setAlname(String alname) {
                    this.alname = alname;
                }

                public String getAotime() {
                    return aotime;
                }

                public void setAotime(String aotime) {
                    this.aotime = aotime;
                }

                public String getAouname() {
                    return aouname;
                }

                public void setAouname(String aouname) {
                    this.aouname = aouname;
                }

                public int getApcount() {
                    return apcount;
                }

                public void setApcount(int apcount) {
                    this.apcount = apcount;
                }

                public String getAppath() {
                    return appath;
                }

                public void setAppath(String appath) {
                    this.appath = appath;
                }

                public int getAscount() {
                    return ascount;
                }

                public void setAscount(int ascount) {
                    this.ascount = ascount;
                }

                public String getAtitle() {
                    return atitle;
                }

                public void setAtitle(String atitle) {
                    this.atitle = atitle;
                }

                public int getAucount() {
                    return aucount;
                }

                public void setAucount(int aucount) {
                    this.aucount = aucount;
                }
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.list);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.list = new ArrayList<ListBean>();
            in.readList(this.list, ListBean.class.getClassLoader());
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeParcelable(this.data, flags);
        dest.writeInt(this.legal);
    }

    public ImageBean() {
    }

    protected ImageBean(Parcel in) {
        this.code = in.readInt();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.legal = in.readInt();
    }

    public static final Creator<ImageBean> CREATOR = new Creator<ImageBean>() {
        @Override
        public ImageBean createFromParcel(Parcel source) {
            return new ImageBean(source);
        }

        @Override
        public ImageBean[] newArray(int size) {
            return new ImageBean[size];
        }
    };
}
