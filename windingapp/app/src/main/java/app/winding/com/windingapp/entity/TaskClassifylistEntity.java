package app.winding.com.windingapp.entity;


import java.util.List;

/**
 * Created by Administrator on 2019/3/29.
 */

public class TaskClassifylistEntity {

    /**
     * code : 200
     * result : [{"classify_id":6,"classify_name":"电影票"},{"classify_id":5,"classify_name":"实物代购"},{"classify_id":4,"classify_name":"优惠券"}]
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
         * classify_id : 6
         * classify_name : 电影票
         */

        private int classify_id;
        private String classify_name;

        public int getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(int classify_id) {
            this.classify_id = classify_id;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }
    }
}
