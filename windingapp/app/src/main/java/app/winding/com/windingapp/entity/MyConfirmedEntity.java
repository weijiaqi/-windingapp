package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 */

public class MyConfirmedEntity {

    /**
     * code : 200
     * result : [{"id":38,"user_id":1,"order_id":"HF2019031815528898444TMT7P","goods_id":54,"pay_price":"19.84","cash_money":"19.60","create_time":"2019-03-18 14:17:24","pay_time":"2019-03-18 14:23:37","finish_time":null,"goods_name":"20元话费充值","voucher":null,"classify_id":1,"type_id":24,"is_shelf":1,"supplier_id":1,"status":2,"t_user_id":9,"locking_time":"","order_details":"13802223824","order_err":null,"is_sell":1,"pay_type":1,"city":null,"money":"20.00","new_voucher":null,"mobile":"","classify_name":"话费交易","type_name":"内部话费交易","supplier_name":"卷卷通","icon":1}]
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
         * id : 38
         * user_id : 1
         * order_id : HF2019031815528898444TMT7P
         * goods_id : 54
         * pay_price : 19.84
         * cash_money : 19.60
         * create_time : 2019-03-18 14:17:24
         * pay_time : 2019-03-18 14:23:37
         * finish_time : null
         * goods_name : 20元话费充值
         * voucher : null
         * classify_id : 1
         * type_id : 24
         * is_shelf : 1
         * supplier_id : 1
         * status : 2
         * t_user_id : 9
         * locking_time :
         * order_details : 13802223824
         * order_err : null
         * is_sell : 1
         * pay_type : 1
         * city : null
         * money : 20.00
         * new_voucher : null
         * mobile :
         * classify_name : 话费交易
         * type_name : 内部话费交易
         * supplier_name : 卷卷通
         * icon : 1
         */

        private int id;
        private int user_id;
        private String order_id;
        private int goods_id;
        private String pay_price;
        private String cash_money;
        private String create_time;
        private String pay_time;
        private Object finish_time;
        private String goods_name;
        private String voucher;
        private int classify_id;
        private int type_id;
        private int is_shelf;
        private int supplier_id;
        private int status;
        private int t_user_id;
        private String locking_time;
        private String order_details;
        private Object order_err;
        private int is_sell;
        private int pay_type;
        private Object city;
        private String money;
        private String new_voucher;
        private String mobile;
        private String classify_name;
        private String type_name;
        private String supplier_name;
        private int icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public String getCash_money() {
            return cash_money;
        }

        public void setCash_money(String cash_money) {
            this.cash_money = cash_money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public Object getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(Object finish_time) {
            this.finish_time = finish_time;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getVoucher() {
            return voucher;
        }

        public void setVoucher(String voucher) {
            this.voucher = voucher;
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

        public int getIs_shelf() {
            return is_shelf;
        }

        public void setIs_shelf(int is_shelf) {
            this.is_shelf = is_shelf;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getT_user_id() {
            return t_user_id;
        }

        public void setT_user_id(int t_user_id) {
            this.t_user_id = t_user_id;
        }

        public String getLocking_time() {
            return locking_time;
        }

        public void setLocking_time(String locking_time) {
            this.locking_time = locking_time;
        }

        public String getOrder_details() {
            return order_details;
        }

        public void setOrder_details(String order_details) {
            this.order_details = order_details;
        }

        public Object getOrder_err() {
            return order_err;
        }

        public void setOrder_err(Object order_err) {
            this.order_err = order_err;
        }

        public int getIs_sell() {
            return is_sell;
        }

        public void setIs_sell(int is_sell) {
            this.is_sell = is_sell;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNew_voucher() {
            return new_voucher;
        }

        public void setNew_voucher(String new_voucher) {
            this.new_voucher = new_voucher;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getClassify_name() {
            return classify_name;
        }

        public void setClassify_name(String classify_name) {
            this.classify_name = classify_name;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }
    }
}
