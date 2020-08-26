package app.winding.com.windingapp.activity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 */

public class GoodsConfigEntity {

    /**
     * code : 200
     * result : [{"id":7,"type_id":2,"config_name":"500元电信充值卡","config_money":"500.00","config_price":"475.00","config_discount":"9.5","create_time":"2019-02-20 15:20:12","update_time":"2019-02-20 15:20:12","is_delete":0,"goods_id":7},{"id":6,"type_id":2,"config_name":"300元电信充值卡","config_money":"300.00","config_price":"295.50","config_discount":"9.85","create_time":"2019-02-20 15:20:11","update_time":"2019-02-20 15:20:11","is_delete":0,"goods_id":6},{"id":5,"type_id":2,"config_name":"200元电信充值卡","config_money":"200.00","config_price":"197.00","config_discount":"9.85","create_time":"2019-02-20 15:20:10","update_time":"2019-02-20 15:20:10","is_delete":0,"goods_id":5},{"id":4,"type_id":2,"config_name":"100元电信充值卡","config_money":"100.00","config_price":"98.50","config_discount":"9.85","create_time":"2019-02-20 15:19:47","update_time":"2019-02-20 15:19:47","is_delete":0,"goods_id":4},{"id":3,"type_id":2,"config_name":"50元电信充值卡","config_money":"50.00","config_price":"49.25","config_discount":"9.85","create_time":"2019-02-20 15:19:25","update_time":"2019-02-20 15:19:25","is_delete":0,"goods_id":3},{"id":2,"type_id":2,"config_name":"30元电信充值卡","config_money":"30.00","config_price":"29.55","config_discount":"9.85","create_time":"2019-02-20 15:19:00","update_time":"2019-02-20 15:19:00","is_delete":0,"goods_id":2},{"id":1,"type_id":2,"config_name":"20元电信充值卡","config_money":"20.00","config_price":"19.68","config_discount":"9.84","create_time":"2019-02-20 14:55:02","update_time":"2019-02-20 15:48:20","is_delete":0,"goods_id":1}]
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
         * id : 7
         * type_id : 2
         * config_name : 500元电信充值卡
         * config_money : 500.00
         * config_price : 475.00
         * config_discount : 9.5
         * create_time : 2019-02-20 15:20:12
         * update_time : 2019-02-20 15:20:12
         * is_delete : 0
         * goods_id : 7
         */

        private int id;
        private int type_id;
        private String config_name;
        private String config_money;
        private String config_price;
        private String config_discount;
        private String create_time;
        private String update_time;
        private int is_delete;
        private int goods_id;

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

        public String getConfig_name() {
            return config_name;
        }

        public void setConfig_name(String config_name) {
            this.config_name = config_name;
        }

        public String getConfig_money() {
            return config_money;
        }

        public void setConfig_money(String config_money) {
            this.config_money = config_money;
        }

        public String getConfig_price() {
            return config_price;
        }

        public void setConfig_price(String config_price) {
            this.config_price = config_price;
        }

        public String getConfig_discount() {
            return config_discount;
        }

        public void setConfig_discount(String config_discount) {
            this.config_discount = config_discount;
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

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }
    }
}
