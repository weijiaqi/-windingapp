package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/12.
 */

public class TemporaryEntity {


    /**
     * code : 200
     * result : {"order_id":"DY201904021554207615M6E2U","name":"我信","cname":"的","city":134,"memo":"额","create_time":"2019-04-02 20:20:23","update_time":"2019-04-02 20:20:23","city_name":"阿拉善盟","remark":"额"}
     * message : 获取信息成功
     */

    private int code;
    private ResultBean result;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        /**
         * order_id : DY201904021554207615M6E2U
         * name : 我信
         * cname : 的
         * city : 134
         * memo : 额
         * create_time : 2019-04-02 20:20:23
         * update_time : 2019-04-02 20:20:23
         * city_name : 阿拉善盟
         * remark : 额
         */

        private String order_id;
        private String name;
        private String cname;
        private int city;
        private String memo;
        private String create_time;
        private String update_time;
        private String city_name;
        private String remark;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
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

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
