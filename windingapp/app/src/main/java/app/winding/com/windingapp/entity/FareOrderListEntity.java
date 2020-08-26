package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/8.
 */

public class FareOrderListEntity {


    /**
     * code : 200
     * result : [{"id":54,"type_id":26,"config_name":"10元第三方外部电信话费充值","config_money":"10.00","config_price":"9.92","config_discount":"9.85","create_time":"2019-03-07 17:11:40","update_time":"2019-03-07 17:11:40","is_delete":0,"cash_money":9.85,"count":0},{"id":55,"type_id":26,"config_name":"20元第三方外部电信话费充值","config_money":"20.00","config_price":"19.84","config_discount":"9.85","create_time":"2019-03-07 17:12:35","update_time":"2019-03-07 17:12:35","is_delete":0,"cash_money":19.7,"count":0},{"id":56,"type_id":26,"config_name":"30元第三方外部电信话费充值","config_money":"30.00","config_price":"29.76","config_discount":"9.85","create_time":"2019-03-07 17:12:56","update_time":"2019-03-07 17:12:56","is_delete":0,"cash_money":29.55,"count":0},{"id":57,"type_id":26,"config_name":"50元第三方外部电信话费充值","config_money":"50.00","config_price":"49.60","config_discount":"9.85","create_time":"2019-03-07 17:13:10","update_time":"2019-03-07 17:13:10","is_delete":0,"cash_money":49.25,"count":0},{"id":58,"type_id":26,"config_name":"100元第三方外部电信话费充值","config_money":"100.00","config_price":"99.20","config_discount":"9.85","create_time":"2019-03-07 17:13:44","update_time":"2019-03-07 17:13:44","is_delete":0,"cash_money":98.5,"count":0},{"id":59,"type_id":26,"config_name":"200元第三方外部电信话费充值","config_money":"200.00","config_price":"198.40","config_discount":"9.85","create_time":"2019-03-07 17:14:01","update_time":"2019-03-07 17:14:01","is_delete":0,"cash_money":197,"count":0}]
     * message : 添加成功
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
         * id : 54
         * type_id : 26
         * config_name : 10元第三方外部电信话费充值
         * config_money : 10.00
         * config_price : 9.92
         * config_discount : 9.85
         * create_time : 2019-03-07 17:11:40
         * update_time : 2019-03-07 17:11:40
         * is_delete : 0
         * cash_money : 9.85
         * count : 0
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
        private double cash_money;
        private int count;

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

        public double getCash_money() {
            return cash_money;
        }

        public void setCash_money(double cash_money) {
            this.cash_money = cash_money;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
