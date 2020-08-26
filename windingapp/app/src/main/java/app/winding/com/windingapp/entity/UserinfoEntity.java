package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/2/22.
 */

public class UserinfoEntity {


    /**
     * code : 200
     * result : [{"id":6,"classify_name":"充值卡回收","classify_img":"20190219\\f9a2e28189a59a76d63f03e1ed517a91.png","create_time":"2019-02-19 18:13:07","update_time":"2019-02-21 11:03:51","is_delete":0},{"id":5,"classify_name":"代下任务","classify_img":"20190219\\c5f6f265572510a20cf381b3c8720bd5.png","create_time":"2019-02-19 17:59:23","update_time":"2019-02-19 17:59:23","is_delete":0},{"id":4,"classify_name":"电费交易","classify_img":"20190219\\f59d65f4f9231c244d4af821042ff97c.png","create_time":"2019-02-19 17:59:08","update_time":"2019-02-19 17:59:08","is_delete":0},{"id":3,"classify_name":"电影票交易","classify_img":"20190219\\fc8dbe7ee50978d00d0d4e8d67731c0c.png","create_time":"2019-02-19 17:58:56","update_time":"2019-02-19 18:07:47","is_delete":0},{"id":2,"classify_name":"优惠券交易","classify_img":"20190219\\bc86bb6011a3d4c19c5a5bd3589b8047.png","create_time":"2019-02-19 17:58:42","update_time":"2019-02-19 18:07:39","is_delete":0},{"id":1,"classify_name":"话费交易","classify_img":"20190219\\c0329331cf785395550e02cd50b0b03a.png","create_time":"2019-02-19 17:57:31","update_time":"2019-02-19 18:31:01","is_delete":0}]
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
         * id : 6
         * classify_name : 充值卡回收
         * classify_img : 20190219\f9a2e28189a59a76d63f03e1ed517a91.png
         * create_time : 2019-02-19 18:13:07
         * update_time : 2019-02-21 11:03:51
         * is_delete : 0
         */

        private int id;
        private String classify_name;
        private String classify_img;
        private String create_time;
        private String update_time;
        private int is_delete;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getClassify_img() {
            return classify_img;
        }

        public void setClassify_img(String classify_img) {
            this.classify_img = classify_img;
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
