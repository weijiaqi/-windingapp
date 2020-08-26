package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/7.
 */

public class ElectricEntity {

    /**
     * code : 200
     * result : [{"id":48,"good_name":"5元电费配置","brief":null,"time":null,"user_id":0,"price":"4.75","money":"5.00","num":1,"good_img":null,"memo":null,"details":null,"classify_id":2,"type_id":23,"config_id":43,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_hot":2,"status":1,"create_time":"2019-03-06 15:57:02","update_time":"2019-03-06 15:57:02","is_sell":1,"order_mobile":null,"order_name":null,"order_address":null,"city":1}]
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
         * id : 48
         * good_name : 5元电费配置
         * brief : null
         * time : null
         * user_id : 0
         * price : 4.75
         * money : 5.00
         * num : 1
         * good_img : null
         * memo : null
         * details : null
         * classify_id : 2
         * type_id : 23
         * config_id : 43
         * supplier_id : 1
         * is_inside : 1
         * is_shelf : 1
         * is_hot : 2
         * status : 1
         * create_time : 2019-03-06 15:57:02
         * update_time : 2019-03-06 15:57:02
         * is_sell : 1
         * order_mobile : null
         * order_name : null
         * order_address : null
         * city : 1
         */

        private int id;
        private String good_name;
        private Object brief;
        private Object time;
        private int user_id;
        private String price;
        private String money;
        private int num;
        private Object good_img;
        private Object memo;
        private Object details;
        private int classify_id;
        private int type_id;
        private int config_id;
        private int supplier_id;
        private int is_inside;
        private int is_shelf;
        private int is_hot;
        private int status;
        private String create_time;
        private String update_time;
        private int is_sell;
        private Object order_mobile;
        private Object order_name;
        private Object order_address;
        private int city;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGood_name() {
            return good_name;
        }

        public void setGood_name(String good_name) {
            this.good_name = good_name;
        }

        public Object getBrief() {
            return brief;
        }

        public void setBrief(Object brief) {
            this.brief = brief;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Object getGood_img() {
            return good_img;
        }

        public void setGood_img(Object good_img) {
            this.good_img = good_img;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public Object getDetails() {
            return details;
        }

        public void setDetails(Object details) {
            this.details = details;
        }

        public int getClassify_id() {
            return classify_id;
        }

        public void setClassify_id(int classify_id) {
            this.classify_id = classify_id;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public int getConfig_id() {
            return config_id;
        }

        public void setConfig_id(int config_id) {
            this.config_id = config_id;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public int getIs_inside() {
            return is_inside;
        }

        public void setIs_inside(int is_inside) {
            this.is_inside = is_inside;
        }

        public int getIs_shelf() {
            return is_shelf;
        }

        public void setIs_shelf(int is_shelf) {
            this.is_shelf = is_shelf;
        }

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public int getIs_sell() {
            return is_sell;
        }

        public void setIs_sell(int is_sell) {
            this.is_sell = is_sell;
        }

        public Object getOrder_mobile() {
            return order_mobile;
        }

        public void setOrder_mobile(Object order_mobile) {
            this.order_mobile = order_mobile;
        }

        public Object getOrder_name() {
            return order_name;
        }

        public void setOrder_name(Object order_name) {
            this.order_name = order_name;
        }

        public Object getOrder_address() {
            return order_address;
        }

        public void setOrder_address(Object order_address) {
            this.order_address = order_address;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }
    }
}
