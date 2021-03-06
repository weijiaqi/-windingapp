package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/8.
 */

public class GoodsOrderEntity {


    /**
     * code : 200
     * result : {"id":413,"user_id":19,"order_id":"YH201904021554193303BMDZH8","goods_id":228,"pay_price":"10.00","cash_money":"9.00","create_time":"2019-04-02 16:21:43","pay_time":"2019-04-02 16:21:51","finish_time":"2019-04-02 16:21:51","goods_name":"卫佳琪","voucher":null,"classify_id":4,"type_id":15,"is_shelf":1,"supplier_id":23,"status":3,"t_user_id":18,"locking_time":null,"order_details":null,"order_err":null,"is_sell":2,"pay_type":1,"city":0,"money":"0.00","new_voucher":null,"mobile":"17600737159","user_info":{"id":19,"username":"ttt","password":null,"saft":null,"headimgurl":"http://test.kulian100.com/static/quanquan/20190316/23627af81a4631efd786b88e7d1245bd.jpg","mobile":"17600737159","email":"","money":"276.75","lock_money":"109.00","score":0,"sex":0,"openid":"","pid":7,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2019-03-07 12:11:32","code":"635045","level":0,"qr_code":"http://test.kulian100.com/uploads/qrcode/20190313/635045.png","alipay_account":"8436211@qq.com","real_name":"卫佳琪"},"t_user_info":{"id":18,"username":"","password":null,"saft":null,"headimgurl":"http://test.kulian100.com/static/quanquan/20190325/b4b1e75bb12a2a0429ec7b2e9dcdc239.png","mobile":"15022660794","email":"","money":"81.95","lock_money":"405.40","score":0,"sex":0,"openid":"","pid":0,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2019-03-04 14:42:08","code":"387857","level":0,"qr_code":"http://test.kulian100.com/uploads/qrcode/20190315/387857.png","alipay_account":"22222333","real_name":"liu"},"kalman":null,"classify_name":"优惠券","type_name":"购物券","supplier_name":"优酷会员","goods":{"id":228,"good_name":"卫佳琪","brief":"","time":"","user_id":19,"price":"10.00","money":"15.00","num":0,"good_img":"","memo":"呵呵呵呵","details":"[\"http://test.kulian100.com/static/quanquan/20190402/a3c9cbc044f07d2b38d67deb3a7d1a19.jpg\"]","classify_id":4,"type_id":15,"config_id":39,"supplier_id":23,"is_inside":1,"is_shelf":1,"is_sell":2,"is_hot":2,"status":3,"create_time":"2019-04-02 16:14:53","update_time":"2019-04-02 16:14:53","order_mobile":"","order_name":"","order_address":"","city":0},"icon":1}
     * message : 数据获取成功
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
         * user_info : {"id":19,"username":"ttt","password":null,"saft":null,"headimgurl":"http://test.kulian100.com/static/quanquan/20190316/23627af81a4631efd786b88e7d1245bd.jpg","mobile":"17600737159","email":"","money":"276.75","lock_money":"109.00","score":0,"sex":0,"openid":"","pid":7,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2019-03-07 12:11:32","code":"635045","level":0,"qr_code":"http://test.kulian100.com/uploads/qrcode/20190313/635045.png","alipay_account":"8436211@qq.com","real_name":"卫佳琪"}
         * t_user_info : {"id":18,"username":"","password":null,"saft":null,"headimgurl":"http://test.kulian100.com/static/quanquan/20190325/b4b1e75bb12a2a0429ec7b2e9dcdc239.png","mobile":"15022660794","email":"","money":"81.95","lock_money":"405.40","score":0,"sex":0,"openid":"","pid":0,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2019-03-04 14:42:08","code":"387857","level":0,"qr_code":"http://test.kulian100.com/uploads/qrcode/20190315/387857.png","alipay_account":"22222333","real_name":"liu"}
         * kalman : null
         * classify_name : 优惠券
         * type_name : 购物券
         * supplier_name : 优酷会员
         * goods : {"id":228,"good_name":"卫佳琪","brief":"","time":"","user_id":19,"price":"10.00","money":"15.00","num":0,"good_img":"","memo":"呵呵呵呵","details":"[\"http://test.kulian100.com/static/quanquan/20190402/a3c9cbc044f07d2b38d67deb3a7d1a19.jpg\"]","classify_id":4,"type_id":15,"config_id":39,"supplier_id":23,"is_inside":1,"is_shelf":1,"is_sell":2,"is_hot":2,"status":3,"create_time":"2019-04-02 16:14:53","update_time":"2019-04-02 16:14:53","order_mobile":"","order_name":"","order_address":"","city":0}
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
        private String locking_time;
        private String order_details;
        private String order_err;
        private int is_sell;
        private int pay_type;
        private int city;
        private String money;
        private String new_voucher;
        private String mobile;
        private UserInfoBean user_info;
        private TUserInfoBean t_user_info;
        private String kalman;
        private String classify_name;
        private String type_name;
        private String supplier_name;
        private GoodsBean goods;
        private int icon;

        public String getCity_name() {
            return City_name;
        }

        public void setCity_name(String city_name) {
            City_name = city_name;
        }

        private String City_name;
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

        public void setOrder_err(String order_err) {
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

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public TUserInfoBean getT_user_info() {
            return t_user_info;
        }

        public void setT_user_info(TUserInfoBean t_user_info) {
            this.t_user_info = t_user_info;
        }

        public String getKalman() {
            return kalman;
        }

        public void setKalman(String kalman) {
            this.kalman = kalman;
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

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public static class UserInfoBean {
            /**
             * id : 19
             * username : ttt
             * password : null
             * saft : null
             * headimgurl : http://test.kulian100.com/static/quanquan/20190316/23627af81a4631efd786b88e7d1245bd.jpg
             * mobile : 17600737159
             * email :
             * money : 276.75
             * lock_money : 109.00
             * score : 0
             * sex : 0
             * openid :
             * pid : 7
             * last_login_ip :
             * last_login_time :
             * status : 1
             * regtime : 2019-03-07 12:11:32
             * code : 635045
             * level : 0
             * qr_code : http://test.kulian100.com/uploads/qrcode/20190313/635045.png
             * alipay_account : 8436211@qq.com
             * real_name : 卫佳琪
             */

            private int id;
            private String username;
            private String password;
            private Object saft;
            private String headimgurl;
            private String mobile;
            private String email;
            private String money;
            private String lock_money;
            private int score;
            private int sex;
            private String openid;
            private int pid;
            private String last_login_ip;
            private String last_login_time;
            private int status;
            private String regtime;
            private String code;
            private int level;
            private String qr_code;
            private String alipay_account;
            private String real_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Object getSaft() {
                return saft;
            }

            public void setSaft(Object saft) {
                this.saft = saft;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getLock_money() {
                return lock_money;
            }

            public void setLock_money(String lock_money) {
                this.lock_money = lock_money;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getLast_login_ip() {
                return last_login_ip;
            }

            public void setLast_login_ip(String last_login_ip) {
                this.last_login_ip = last_login_ip;
            }

            public String getLast_login_time() {
                return last_login_time;
            }

            public void setLast_login_time(String last_login_time) {
                this.last_login_time = last_login_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRegtime() {
                return regtime;
            }

            public void setRegtime(String regtime) {
                this.regtime = regtime;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getQr_code() {
                return qr_code;
            }

            public void setQr_code(String qr_code) {
                this.qr_code = qr_code;
            }

            public String getAlipay_account() {
                return alipay_account;
            }

            public void setAlipay_account(String alipay_account) {
                this.alipay_account = alipay_account;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }
        }

        public static class TUserInfoBean {
            /**
             * id : 18
             * username : 
             * password : null
             * saft : null
             * headimgurl : http://test.kulian100.com/static/quanquan/20190325/b4b1e75bb12a2a0429ec7b2e9dcdc239.png
             * mobile : 15022660794
             * email :
             * money : 81.95
             * lock_money : 405.40
             * score : 0
             * sex : 0
             * openid :
             * pid : 0
             * last_login_ip :
             * last_login_time :
             * status : 1
             * regtime : 2019-03-04 14:42:08
             * code : 387857
             * level : 0
             * qr_code : http://test.kulian100.com/uploads/qrcode/20190315/387857.png
             * alipay_account : 22222333
             * real_name : liu
             */

            private int id;
            private String username;
            private String password;
            private String saft;
            private String headimgurl;
            private String mobile;
            private String email;
            private String money;
            private String lock_money;
            private int score;
            private int sex;
            private String openid;
            private int pid;
            private String last_login_ip;
            private String last_login_time;
            private int status;
            private String regtime;
            private String code;
            private int level;
            private String qr_code;
            private String alipay_account;
            private String real_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getSaft() {
                return saft;
            }

            public void setSaft(String saft) {
                this.saft = saft;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getLock_money() {
                return lock_money;
            }

            public void setLock_money(String lock_money) {
                this.lock_money = lock_money;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getLast_login_ip() {
                return last_login_ip;
            }

            public void setLast_login_ip(String last_login_ip) {
                this.last_login_ip = last_login_ip;
            }

            public String getLast_login_time() {
                return last_login_time;
            }

            public void setLast_login_time(String last_login_time) {
                this.last_login_time = last_login_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRegtime() {
                return regtime;
            }

            public void setRegtime(String regtime) {
                this.regtime = regtime;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getQr_code() {
                return qr_code;
            }

            public void setQr_code(String qr_code) {
                this.qr_code = qr_code;
            }

            public String getAlipay_account() {
                return alipay_account;
            }

            public void setAlipay_account(String alipay_account) {
                this.alipay_account = alipay_account;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }
        }

        public static class GoodsBean {
            /**
             * id : 228
             * good_name : 卫佳琪
             * brief :
             * time :
             * user_id : 19
             * price : 10.00
             * money : 15.00
             * num : 0
             * good_img :
             * memo : 呵呵呵呵
             * details : ["http://test.kulian100.com/static/quanquan/20190402/a3c9cbc044f07d2b38d67deb3a7d1a19.jpg"]
             * classify_id : 4
             * type_id : 15
             * config_id : 39
             * supplier_id : 23
             * is_inside : 1
             * is_shelf : 1
             * is_sell : 2
             * is_hot : 2
             * status : 3
             * create_time : 2019-04-02 16:14:53
             * update_time : 2019-04-02 16:14:53
             * order_mobile :
             * order_name :
             * order_address :
             * city : 0
             */

            private int id;
            private String good_name;
            private String brief;
            private String time;
            private int user_id;
            private String price;
            private String money;
            private int num;
            private String good_img;
            private String memo;
            private String details;
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

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            private String city_name;
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

            public String getGood_img() {
                return good_img;
            }

            public void setGood_img(String good_img) {
                this.good_img = good_img;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
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
        }
    }
}
