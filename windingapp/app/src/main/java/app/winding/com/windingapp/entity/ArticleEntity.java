package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/2/22.
 */

public class ArticleEntity {

    /**
     * code : 200
     * result : [{"id":3,"type_id":3,"title":"活动2","content":"<p>活动2<\/p>","create_time":"2018-11-24 19:51:10","update_time":"2019-02-15 20:53:25","is_delete":0,"brief":"活动2","img":"http://test.kulian100.com/uploads/article/20190215/b5499e3ea31f1b9670f8b833d4609e4a.jpeg","is_recom":2},{"id":2,"type_id":3,"title":"活动1","content":"<p>活动1<\/p>","create_time":"2018-11-24 19:50:54","update_time":"2019-02-15 20:53:43","is_delete":0,"brief":"活动1","img":"http://test.kulian100.com/uploads/article/20190215/f8a9b0833560b5b7576a58d655f55352.jpeg","is_recom":2}]
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
         * id : 3
         * type_id : 3
         * title : 活动2
         * content : <p>活动2</p>
         * create_time : 2018-11-24 19:51:10
         * update_time : 2019-02-15 20:53:25
         * is_delete : 0
         * brief : 活动2
         * img : http://test.kulian100.com/uploads/article/20190215/b5499e3ea31f1b9670f8b833d4609e4a.jpeg
         * is_recom : 2
         */

        private int id;
        private int type_id;
        private String title;
        private String content;
        private String create_time;
        private String update_time;
        private int is_delete;
        private String brief;
        private String img;
        private int is_recom;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getIs_recom() {
            return is_recom;
        }

        public void setIs_recom(int is_recom) {
            this.is_recom = is_recom;
        }
    }
}
