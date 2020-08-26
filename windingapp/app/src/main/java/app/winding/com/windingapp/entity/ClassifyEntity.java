package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/2/22.
 */

public class ClassifyEntity {

    /**
     * code : 200
     * result : [{"id":1,"classify_name":"话费交易","classify_img":"20190222/42767032f29adffb40c914ad2088abf2.png","create_time":"2019-02-19 17:57:31","update_time":"2019-02-22 12:07:56","is_delete":0,"is_home":1},{"id":2,"classify_name":"优惠券交易","classify_img":"20190222/3cb48cd5876bfbb5de4d4be190018e51.png","create_time":"2019-02-19 17:58:42","update_time":"2019-02-22 12:08:05","is_delete":0,"is_home":1},{"id":3,"classify_name":"电影票交易","classify_img":"20190222/6b440df7dbd90bed4d75286203880413.png","create_time":"2019-02-19 17:58:56","update_time":"2019-02-22 12:08:13","is_delete":0,"is_home":1},{"id":4,"classify_name":"电费交易","classify_img":"20190222/976417023786c64515c156804afc75c4.png","create_time":"2019-02-19 17:59:08","update_time":"2019-02-22 12:08:20","is_delete":0,"is_home":1},{"id":5,"classify_name":"代下任务","classify_img":"20190222/c3c713b878d8368806582970926f5a4c.png","create_time":"2019-02-19 17:59:23","update_time":"2019-02-22 12:08:28","is_delete":0,"is_home":1},{"id":6,"classify_name":"充值卡回收","classify_img":"20190222/30cb85c05084721eebe5e6017126e4c6.png","create_time":"2019-02-19 18:13:07","update_time":"2019-02-22 12:08:50","is_delete":0,"is_home":1}]
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
         * classify_name : 话费交易
         * classify_img : 20190222/42767032f29adffb40c914ad2088abf2.png
         * create_time : 2019-02-19 17:57:31
         * update_time : 2019-02-22 12:07:56
         * is_delete : 0
         * is_home : 1
         */

        private int id;
        private String classify_name;
        private String classify_img;
        private String create_time;
        private String update_time;
        private int is_delete;
        private int is_home;

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

        public int getIs_home() {
            return is_home;
        }

        public void setIs_home(int is_home) {
            this.is_home = is_home;
        }
    }
}
