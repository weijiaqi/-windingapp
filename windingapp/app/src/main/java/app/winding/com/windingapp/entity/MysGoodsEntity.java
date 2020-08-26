package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/12.
 */

public class MysGoodsEntity {


    /**
     * code : 200
     * result : [{"id":88,"good_name":"啦啦","brief":"","time":"","user_id":19,"price":"2.00","money":"10.00","num":1,"good_img":null,"memo":"1把","details":null,"classify_id":5,"type_id":20,"config_id":40,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_sell":1,"is_hot":2,"status":1,"create_time":"2019-03-12 16:55:54","update_time":"2019-03-12 16:55:54","order_mobile":"18510507182","order_name":"啦啦","order_address":"吧","city":0,"user_name":"卷卷X4KZNM","mobile":"17600737159","classify_name":"代下任务","type_name":"黄金","config_discount":"9","supplier_name":"卷卷通"},{"id":87,"good_name":"煞笔","brief":"","time":"","user_id":19,"price":"2.00","money":"10.00","num":1,"good_img":[null],"memo":"1111","details":null,"classify_id":5,"type_id":20,"config_id":40,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_sell":1,"is_hot":2,"status":1,"create_time":"2019-03-12 16:46:47","update_time":"2019-03-12 16:46:47","order_mobile":"18510507182","order_name":"啦啦","order_address":"啦啦啦","city":0,"user_name":"卷卷X4KZNM","mobile":"17600737159","classify_name":"代下任务","type_name":"黄金","config_discount":"9","supplier_name":"卷卷通"},{"id":86,"good_name":"赶紧","brief":"","time":"","user_id":19,"price":"2.00","money":"10.00","num":1,"good_img":[null],"memo":"啦啦","details":null,"classify_id":5,"type_id":19,"config_id":40,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_sell":1,"is_hot":2,"status":1,"create_time":"2019-03-12 16:00:56","update_time":"2019-03-12 16:00:56","order_mobile":"18510507182","order_name":"啦啦","order_address":"把","city":0,"user_name":"卷卷X4KZNM","mobile":"17600737159","classify_name":"代下任务","type_name":"包包","config_discount":"9","supplier_name":"卷卷通"},{"id":85,"good_name":"啦啦啦","brief":"","time":"","user_id":19,"price":"2.00","money":"10.00","num":1,"good_img":null,"memo":"太累了","details":null,"classify_id":5,"type_id":19,"config_id":40,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_sell":1,"is_hot":2,"status":1,"create_time":"2019-03-12 15:57:42","update_time":"2019-03-12 15:57:42","order_mobile":"18510507182","order_name":"145","order_address":"啦啦啦","city":0,"user_name":"卷卷X4KZNM","mobile":"17600737159","classify_name":"代下任务","type_name":"包包","config_discount":"9","supplier_name":"卷卷通"},{"id":84,"good_name":"啦啦啦","brief":"","time":"","user_id":19,"price":"2.00","money":"10.00","num":1,"good_img":null,"memo":"太累了","details":null,"classify_id":5,"type_id":19,"config_id":40,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_sell":1,"is_hot":2,"status":1,"create_time":"2019-03-12 15:57:27","update_time":"2019-03-12 15:57:27","order_mobile":"18510507182","order_name":"145","order_address":"啦啦啦","city":0,"user_name":"卷卷X4KZNM","mobile":"17600737159","classify_name":"代下任务","type_name":"包包","config_discount":"9","supplier_name":"卷卷通"},{"id":83,"good_name":"啦啦啦","brief":"","time":"","user_id":19,"price":"2.00","money":"10.00","num":1,"good_img":[null],"memo":"啦啦啦啦","details":null,"classify_id":5,"type_id":19,"config_id":40,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_sell":1,"is_hot":2,"status":1,"create_time":"2019-03-12 15:55:50","update_time":"2019-03-12 15:55:50","order_mobile":"18510507182","order_name":"太累了","order_address":"啦啦啦","city":0,"user_name":"卷卷X4KZNM","mobile":"17600737159","classify_name":"代下任务","type_name":"包包","config_discount":"9","supplier_name":"卷卷通"},{"id":82,"good_name":"啦啦啦","brief":"","time":"","user_id":19,"price":"20.00","money":"100.00","num":1,"good_img":[null],"memo":"啦啦啦","details":null,"classify_id":5,"type_id":19,"config_id":40,"supplier_id":1,"is_inside":1,"is_shelf":1,"is_sell":1,"is_hot":2,"status":1,"create_time":"2019-03-12 15:43:18","update_time":"2019-03-12 15:43:18","order_mobile":"18510507182","order_name":"卡","order_address":"啦啦","city":0,"user_name":"卷卷X4KZNM","mobile":"17600737159","classify_name":"代下任务","type_name":"包包","config_discount":"9","supplier_name":"卷卷通"},{"id":80,"good_name":"把","brief":"","time":"","user_id":19,"price":"10.00","money":"10.98","num":0,"good_img":["http://test.kulian100.com/uploads/supplier/20190304/63cec270b7b3b57dc1dc6c45bc542458.jpg"],"memo":"把","details":null,"classify_id":4,"type_id":15,"config_id":39,"supplier_id":23,"is_inside":1,"is_shelf":1,"is_sell":2,"is_hot":1,"status":1,"create_time":"2019-03-12 12:22:51","update_time":"2019-03-12 15:55:39","order_mobile":"","order_name":"","order_address":"","city":0,"user_name":"卷卷X4KZNM","mobile":"17600737159","classify_name":"优惠券","type_name":"购物券","config_discount":"9","supplier_name":"优酷会员"}]
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
         * id : 88
         * good_name : 啦啦
         * brief :
         * time :
         * user_id : 19
         * price : 2.00
         * money : 10.00
         * num : 1
         * good_img : null
         * memo : 1把
         * details : null
         * classify_id : 5
         * type_id : 20
         * config_id : 40
         * supplier_id : 1
         * is_inside : 1
         * is_shelf : 1
         * is_sell : 1
         * is_hot : 2
         * status : 1
         * create_time : 2019-03-12 16:55:54
         * update_time : 2019-03-12 16:55:54
         * order_mobile : 18510507182
         * order_name : 啦啦
         * order_address : 吧
         * city : 0
         * user_name : 卷卷X4KZNM
         * mobile : 17600737159
         * classify_name : 代下任务
         * type_name : 黄金
         * config_discount : 9
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

        public String[] getGood_img() {
            return good_img;
        }

        public void setGood_img(String[] good_img) {
            this.good_img = good_img;
        }

        private String[] good_img;
        private String memo;
        private Object details;
        private int classify_id;
        private int type_id;
        private int config_id;
        private int supplier_id;
        private int is_inside;
        private int is_shelf;
        private int is_sell;
        private int is_hot;
        private int status;
        private String create_time;
        private String update_time;
        private String order_mobile;
        private String order_name;
        private String order_address;
        private int city;
        private String user_name;
        private String mobile;
        private String classify_name;
        private String type_name;
        private String config_discount;
        private String supplier_name;

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

        public int getIs_sell() {
            return is_sell;
        }

        public void setIs_sell(int is_sell) {
            this.is_sell = is_sell;
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

        public String getOrder_mobile() {
            return order_mobile;
        }

        public void setOrder_mobile(String order_mobile) {
            this.order_mobile = order_mobile;
        }

        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }

        public String getOrder_address() {
            return order_address;
        }

        public void setOrder_address(String order_address) {
            this.order_address = order_address;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
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
    }
}
