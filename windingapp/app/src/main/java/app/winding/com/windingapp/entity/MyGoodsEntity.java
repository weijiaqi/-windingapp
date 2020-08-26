package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/12.
 */

public class MyGoodsEntity {

    /**
     * code : 200
     * result : [{"id":2,"good_name":"20元电信充值卡","brief":"","time":"","user_id":1,"price":"19.68","money":"20.00","num":1,"good_img":["http://test.kulian100.com/static/quanquan/20190213/029fde2d99b5efa3356132d05904aa6c.jpeg","http://test.kulian100.com/static/quanquan/20190213/e00a30eff324c11f3755d1fbc4620caa.jpg"],"memo":"","details":[{"no":1,"pass":2},{"no":1,"pass":3}],"classify_id":6,"type_id":2,"config_id":1,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_hot":2,"status":1,"create_time":"2019-02-21 12:17:20","update_time":"2019-02-21 12:17:20","user_name":"℡      小武  wym。","mobile":"","classify_name":"充值卡回收","type_name":"电信充值卡","config_discount":"9.84","supplier_name":"卷卷通"},{"id":1,"good_name":"20元电信充值卡","brief":"","time":"","user_id":1,"price":"19.68","money":"20.00","num":1,"good_img":["http://test.kulian100.com/static/quanquan/20190213/029fde2d99b5efa3356132d05904aa6c.jpeg","http://test.kulian100.com/static/quanquan/20190213/e00a30eff324c11f3755d1fbc4620caa.jpg"],"memo":"aa","details":[{"no":1,"pass":2},{"no":1,"pass":3}],"classify_id":6,"type_id":2,"config_id":1,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_hot":2,"status":3,"create_time":"2019-02-20 16:18:33","update_time":"2019-02-20 18:48:30","user_name":"℡      小武  wym。","mobile":"","classify_name":"充值卡回收","type_name":"电信充值卡","config_discount":"9.84","supplier_name":"卷卷通"}]
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
         * id : 2
         * good_name : 20元电信充值卡
         * brief :
         * time :
         * user_id : 1
         * price : 19.68
         * money : 20.00
         * num : 1
         * good_img : ["http://test.kulian100.com/static/quanquan/20190213/029fde2d99b5efa3356132d05904aa6c.jpeg","http://test.kulian100.com/static/quanquan/20190213/e00a30eff324c11f3755d1fbc4620caa.jpg"]
         * memo :
         * details : [{"no":1,"pass":2},{"no":1,"pass":3}]
         * classify_id : 6
         * type_id : 2
         * config_id : 1
         * supplier_id : 1
         * is_inside : 1
         * is_shelf : 1
         * is_hot : 2
         * status : 1
         * create_time : 2019-02-21 12:17:20
         * update_time : 2019-02-21 12:17:20
         * user_name : ℡      小武  wym。
         * mobile :
         * classify_name : 充值卡回收
         * type_name : 电信充值卡
         * config_discount : 9.84
         * supplier_name : 卷卷通
         */

        private int id;
        private String good_name;
        private String brief;
        private String time;
        private int user_id;
        private String price;
        private String money;
        private int num;
        private String memo;
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
        private String user_name;
        private String mobile;
        private String classify_name;
        private String type_name;
        private String config_discount;
        private String supplier_name;
        private List<String> good_img;
        private List<DetailsBean> details;

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

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
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

        public String getConfig_discount() {
            return config_discount;
        }

        public void setConfig_discount(String config_discount) {
            this.config_discount = config_discount;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public List<String> getGood_img() {
            return good_img;
        }

        public void setGood_img(List<String> good_img) {
            this.good_img = good_img;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean {
            /**
             * no : 1
             * pass : 2
             */

            private int no;
            private int pass;

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public int getPass() {
                return pass;
            }

            public void setPass(int pass) {
                this.pass = pass;
            }
        }
    }
}
