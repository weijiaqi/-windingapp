package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/12.
 */

public class MyGoodsOrderEntity {


    /**
     * code : 200
     * result : [{"id":413,"user_id":19,"order_id":"YH201904021554193303BMDZH8","goods_id":228,"pay_price":"10.00","cash_money":"9.00","create_time":"2019-04-02 16:21:43","pay_time":"2019-04-02 16:21:51","finish_time":"2019-04-02 16:21:51","goods_name":"卫佳琪","voucher":null,"classify_id":4,"type_id":15,"is_shelf":1,"supplier_id":23,"status":3,"t_user_id":18,"locking_time":null,"order_details":null,"order_err":null,"is_sell":2,"pay_type":1,"city":0,"money":"0.00","new_voucher":null,"mobile":"17600737159","classify_name":"优惠券","type_name":"购物券","supplier_name":"优酷会员","icon":1},{"id":182,"user_id":18,"order_id":"HF201903271553684058YEN5VB","goods_id":54,"pay_price":"19.84","cash_money":"19.60","create_time":"2019-03-27 18:54:18","pay_time":"2019-03-27 18:54:43","finish_time":"2019-03-29 18:12:45","goods_name":"20元话费充值","voucher":"http://test.kulian100.com/static/quanquan/20190329/5d230ea33815303d118a1241b7625661.png","classify_id":1,"type_id":24,"is_shelf":1,"supplier_id":1,"status":3,"t_user_id":19,"locking_time":"2019-03-29 18:12:31","order_details":"18510507182","order_err":null,"is_sell":1,"pay_type":1,"city":0,"money":"20.00","new_voucher":null,"mobile":"15022660794","classify_name":"话费交易","type_name":"内部话费交易","supplier_name":"卷卷通","icon":0},{"id":192,"user_id":18,"order_id":"DF201903281553745217H3IP4D","goods_id":48,"pay_price":"4.75","cash_money":"4.50","create_time":"2019-03-28 11:53:37","pay_time":"2019-03-28 11:53:56","finish_time":"2019-03-29 14:21:06","goods_name":"5元电费配置","voucher":"http://test.kulian100.com/static/quanquan/20190329/ab17994e18d6d5b7cd5bf356e443ba7b.png","classify_id":2,"type_id":23,"is_shelf":1,"supplier_id":1,"status":3,"t_user_id":19,"locking_time":"2019-03-29 14:20:51","order_details":"11","order_err":null,"is_sell":1,"pay_type":1,"city":134,"money":"5.00","new_voucher":"http://test.kulian100.com/static/quanquan/20190329/54381bead36bdd7e55f9c0c9b6fc772a.png","mobile":"15022660794","classify_name":"电费交易","type_name":"电费充值","supplier_name":"卷卷通","icon":0},{"id":175,"user_id":22,"order_id":"HF20190327155367441733DU7H","goods_id":53,"pay_price":"9.92","cash_money":"9.80","create_time":"2019-03-27 16:13:37","pay_time":"2019-03-27 16:13:56","finish_time":"2019-03-29 12:46:53","goods_name":"10元话费充值","voucher":"http://test.kulian100.com/static/quanquan/20190329/ef22cf8a12342b72bc22f23224ff62ce.jpg","classify_id":1,"type_id":24,"is_shelf":1,"supplier_id":1,"status":3,"t_user_id":19,"locking_time":"2019-03-29 12:46:40","order_details":"18510507182","order_err":null,"is_sell":1,"pay_type":1,"city":0,"money":"10.00","new_voucher":null,"mobile":"18510507182","classify_name":"话费交易","type_name":"内部话费交易","supplier_name":"卷卷通","icon":0},{"id":213,"user_id":19,"order_id":"YH201903281553768362QXUYCK","goods_id":153,"pay_price":"1.00","cash_money":"0.90","create_time":"2019-03-28 18:19:22","pay_time":"2019-03-28 18:20:52","finish_time":"2019-03-28 18:20:52","goods_name":"uuiii","voucher":null,"classify_id":4,"type_id":15,"is_shelf":1,"supplier_id":23,"status":3,"t_user_id":23,"locking_time":null,"order_details":"","order_err":null,"is_sell":2,"pay_type":1,"city":0,"money":"0.00","new_voucher":null,"mobile":"17600737159","classify_name":"优惠券","type_name":"购物券","supplier_name":"优酷会员","icon":1},{"id":211,"user_id":19,"order_id":"DG201903281553766362PNGNPK","goods_id":152,"pay_price":"0.00","cash_money":"0.00","create_time":"2019-03-28 17:46:02","pay_time":"2019-03-28 17:46:09","finish_time":"2019-03-28 17:58:37","goods_name":"带一次","voucher":"http://test.kulian100.com/static/quanquan/20190328/d0ba1a2897c4e01f37ac6bb870f29d00.jpg","classify_id":5,"type_id":19,"is_shelf":1,"supplier_id":1,"status":3,"t_user_id":23,"locking_time":"2019-03-28 17:53:40","order_details":"","order_err":null,"is_sell":1,"pay_type":1,"city":0,"money":"0.00","new_voucher":null,"mobile":"17600737159","classify_name":"实物代购","type_name":"包包","supplier_name":"卷卷通","icon":1},{"id":208,"user_id":19,"order_id":"DY201903281553763302TYICSP","goods_id":150,"pay_price":"44.00","cash_money":"39.60","create_time":"2019-03-28 16:55:02","pay_time":"2019-03-28 16:55:08","finish_time":"2019-03-28 17:20:26","goods_name":"我思","voucher":"http://test.kulian100.com/static/quanquan/20190328/f55f63276ef818616d34e05b01be53e7.jpg","classify_id":6,"type_id":21,"is_shelf":1,"supplier_id":1,"status":3,"t_user_id":23,"locking_time":"2019-03-28 17:12:30","order_details":"","order_err":null,"is_sell":1,"pay_type":1,"city":0,"money":"0.00","new_voucher":null,"mobile":"17600737159","classify_name":"电影票","type_name":"购买电影票","supplier_name":"卷卷通","icon":1},{"id":206,"user_id":19,"order_id":"HF201903281553760381M7UUVB","goods_id":57,"pay_price":"99.20","cash_money":"98.00","create_time":"2019-03-28 16:06:21","pay_time":"2019-03-28 16:06:27","finish_time":"2019-03-28 16:07:02","goods_name":"100元话费充值","voucher":"http://test.kulian100.com/static/quanquan/20190328/2d89c5ba65330b1c4872f32443bf7609.jpg","classify_id":1,"type_id":24,"is_shelf":1,"supplier_id":1,"status":3,"t_user_id":23,"locking_time":"2019-03-28 16:06:51","order_details":"15826657921","order_err":null,"is_sell":1,"pay_type":1,"city":0,"money":"100.00","new_voucher":"http://test.kulian100.com/static/quanquan/20190328/ef01aa2551918aa7df93e1e7bf0cdbcb.jpg","mobile":"17600737159","classify_name":"话费交易","type_name":"内部话费交易","supplier_name":"卷卷通","icon":1},{"id":204,"user_id":19,"order_id":"DF2019032815537597298GFBE7","goods_id":52,"pay_price":"95.00","cash_money":"90.00","create_time":"2019-03-28 15:55:29","pay_time":"2019-03-28 15:55:35","finish_time":"2019-03-28 15:55:56","goods_name":"100元电费充值","voucher":"http://test.kulian100.com/static/quanquan/20190328/5d6c0d09070efc6264ace5df1a321400.jpg","classify_id":2,"type_id":23,"is_shelf":1,"supplier_id":1,"status":3,"t_user_id":23,"locking_time":"2019-03-28 15:55:42","order_details":"他0ajqgbgee+sz是gk我 的k确\n.wtrewcj","order_err":null,"is_sell":1,"pay_type":1,"city":354,"money":"100.00","new_voucher":"http://test.kulian100.com/static/quanquan/20190328/52dbc7ecd6de31430a2c317d2f66bf7f.jpg","mobile":"17600737159","classify_name":"电费交易","type_name":"电费充值","supplier_name":"卷卷通","icon":1},{"id":202,"user_id":19,"order_id":"DY201903281553758482AGK9G6","goods_id":149,"pay_price":"3.00","cash_money":"2.70","create_time":"2019-03-28 15:34:42","pay_time":"2019-03-28 15:34:53","finish_time":"2019-03-28 15:39:45","goods_name":"uuuuuuuuuuuu","voucher":"http://test.kulian100.com/static/quanquan/20190328/be459dabcb21faa784c486cb9369ae46.jpg","classify_id":6,"type_id":21,"is_shelf":1,"supplier_id":1,"status":3,"t_user_id":23,"locking_time":null,"order_details":"","order_err":null,"is_sell":2,"pay_type":1,"city":0,"money":"0.00","new_voucher":null,"mobile":"17600737159","classify_name":"电影票","type_name":"购买电影票","supplier_name":"卷卷通","icon":1}]
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
         * id : 413
         * user_id : 19
         * order_id : YH201904021554193303BMDZH8
         * goods_id : 228
         * pay_price : 10.00
         * cash_money : 9.00
         * create_time : 2019-04-02 16:21:43
         * pay_time : 2019-04-02 16:21:51
         * finish_time : 2019-04-02 16:21:51
         * goods_name : 卫佳琪
         * voucher : null
         * classify_id : 4
         * type_id : 15
         * is_shelf : 1
         * supplier_id : 23
         * status : 3
         * t_user_id : 18
         * locking_time : null
         * order_details : null
         * order_err : null
         * is_sell : 2
         * pay_type : 1
         * city : 0
         * money : 0.00
         * new_voucher : null
         * mobile : 17600737159
         * classify_name : 优惠券
         * type_name : 购物券
         * supplier_name : 优酷会员
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
        private String finish_time;
        private String goods_name;
        private String voucher;
        private int classify_id;
        private int type_id;
        private int is_shelf;
        private int supplier_id;
        private int status;
        private int t_user_id;
        private Object locking_time;
        private Object order_details;
        private Object order_err;
        private int is_sell;
        private int pay_type;
        private int city;
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

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
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

        public Object getLocking_time() {
            return locking_time;
        }

        public void setLocking_time(Object locking_time) {
            this.locking_time = locking_time;
        }

        public Object getOrder_details() {
            return order_details;
        }

        public void setOrder_details(Object order_details) {
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

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public Object getNew_voucher() {
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
