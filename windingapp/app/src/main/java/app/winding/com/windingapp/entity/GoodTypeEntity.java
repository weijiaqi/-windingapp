package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 */

public class GoodTypeEntity {

    /**
     * code : 200
     * result : [{"id":14,"classify_id":4,"parent_id":13,"type_name":"美食券","type_img":"","create_time":"2019-02-25 15:42:07","update_time":"2019-02-25 15:42:07","is_delete":0},{"id":15,"classify_id":4,"parent_id":13,"type_name":"购物券","type_img":"","create_time":"2019-02-25 15:42:19","update_time":"2019-02-25 15:42:19","is_delete":0},{"id":16,"classify_id":4,"parent_id":13,"type_name":"观影券","type_img":"","create_time":"2019-02-25 15:42:28","update_time":"2019-02-25 15:42:28","is_delete":0},{"id":17,"classify_id":4,"parent_id":13,"type_name":"视频券","type_img":"","create_time":"2019-02-25 15:42:40","update_time":"2019-02-25 15:42:40","is_delete":0}]
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
         * id : 14
         * classify_id : 4
         * parent_id : 13
         * type_name : 美食券
         * type_img :
         * create_time : 2019-02-25 15:42:07
         * update_time : 2019-02-25 15:42:07
         * is_delete : 0
         */

        private int id;
        private int classify_id;
        private int parent_id;
        private String type_name;
        private String type_img;
        private String create_time;
        private String update_time;
        private int is_delete;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(int classify_id) {
            this.classify_id = classify_id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getType_img() {
            return type_img;
        }

        public void setType_img(String type_img) {
            this.type_img = type_img;
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
