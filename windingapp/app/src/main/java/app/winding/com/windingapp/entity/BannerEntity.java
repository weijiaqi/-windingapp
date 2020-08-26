package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/2/22.
 */

public class BannerEntity {




    /**
     * code : 200
     * result : [{"id":1,"title":"开业啦","link":"http://www.baidu,comn","img":"http://test.kulian100.com/uploads/banner/20190131/8f79c332bb077694c1f8ec8663cc1645.jpeg","type":2,"create_time":"2018-11-06 15:01:44","update_time":"2019-01-31 13:03:11","is_delete":0},{"id":2,"title":"开业啦","link":"http://www.baidu,comn","img":"http://test.kulian100.com/uploads/banner/20190215/e0d05ae4341f00e28c844059432e4208.jpeg","type":2,"create_time":"2018-11-06 15:01:44","update_time":"2019-02-15 20:56:47","is_delete":0},{"id":3,"title":"开业啦","link":"http://www.baidu,comn","img":"http://test.kulian100.com/uploads/banner/20190131/11f17c5fe9c71e9d7d2c62c4d6d69976.jpeg","type":2,"create_time":"2018-11-06 15:01:44","update_time":"2019-01-31 13:03:37","is_delete":0}]
     * message : 数据获取成功
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * title : 开业啦
         * link : http://www.baidu,comn
         * img : http://test.kulian100.com/uploads/banner/20190131/8f79c332bb077694c1f8ec8663cc1645.jpeg
         * type : 2
         * create_time : 2018-11-06 15:01:44
         * update_time : 2019-01-31 13:03:11
         * is_delete : 0
         */

        private int id;
        private String title;
        private String link;
        private String img;
        private int type;
        private String create_time;
        private String update_time;
        private int is_delete;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
        }
    }
}
