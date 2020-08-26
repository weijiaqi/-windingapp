package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/12.
 */

public class GoodsDetailsEntity {


    /**
     * code : 200
     * result : {"id":99,"good_name":"腾讯视频年会卡","brief":"","time":"","user_id":1379,"price":"120.00","money":"198.00","num":1,"good_img":["http://qqt.ruimofang.com//uploads/supplier/20181124/f8c2ec91b6dd131d3ed294ee2ebf18f8.jpg"],"memo":"买下后联系qq 3180598287。无需提供腾讯视频年卡账号，直接自己用卡密激活到账号上。\n\n发布时间：2019-03-30 19:38:12","details":["http://qqt.ruimofang.com/static/quanquan/20190401/5083e608fb658f4ab9861b7aae992b74.jpg"],"classify_id":4,"type_id":17,"config_id":39,"supplier_id":8,"is_inside":1,"is_shelf":1,"is_sell":2,"is_hot":2,"status":1,"create_time":"2019-04-01 05:43:09","update_time":"2019-04-01 05:43:09","order_mobile":null,"order_name":null,"order_address":null,"city":null,"view":0,"config_discount":"9.5","user_info":{"id":1379,"username":"越来越努力w°","password":null,"saft":null,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/VzQsdzsGScMuZsHfWfANPqKtEM3zlIKR46V8gKmyHKdtAo70ECVZV2We0aADS4ARHdPvlkfMn0Jl6D17l5Ctzeicf8icK4dT4j/132","mobile":"17607121207","email":"","money":"1034.25","lock_money":"59.10","score":0,"sex":1,"openid":"oEcnY1TO-ebo4ea0kokQXlvMToFE","pid":0,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2019-03-08 01:01:56","code":null,"level":0,"qr_code":null,"alipay_account":"321313980@qq.com","real_name":"张旺"},"classify_name":"优惠券","type_name":"视频券","supplier_name":"腾讯视频","prefix":"YH"}
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
         * id : 99
         * good_name : 腾讯视频年会卡
         * brief :
         * time :
         * user_id : 1379
         * price : 120.00
         * money : 198.00
         * num : 1
         * good_img : ["http://qqt.ruimofang.com//uploads/supplier/20181124/f8c2ec91b6dd131d3ed294ee2ebf18f8.jpg"]
         * memo : 买下后联系qq 3180598287。无需提供腾讯视频年卡账号，直接自己用卡密激活到账号上。

         发布时间：2019-03-30 19:38:12
         * details : ["http://qqt.ruimofang.com/static/quanquan/20190401/5083e608fb658f4ab9861b7aae992b74.jpg"]
         * classify_id : 4
         * type_id : 17
         * config_id : 39
         * supplier_id : 8
         * is_inside : 1
         * is_shelf : 1
         * is_sell : 2
         * is_hot : 2
         * status : 1
         * create_time : 2019-04-01 05:43:09
         * update_time : 2019-04-01 05:43:09
         * order_mobile : null
         * order_name : null
         * order_address : null
         * city : null
         * view : 0
         * config_discount : 9.5
         * user_info : {"id":1379,"username":"越来越努力w°","password":null,"saft":null,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/VzQsdzsGScMuZsHfWfANPqKtEM3zlIKR46V8gKmyHKdtAo70ECVZV2We0aADS4ARHdPvlkfMn0Jl6D17l5Ctzeicf8icK4dT4j/132","mobile":"17607121207","email":"","money":"1034.25","lock_money":"59.10","score":0,"sex":1,"openid":"oEcnY1TO-ebo4ea0kokQXlvMToFE","pid":0,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2019-03-08 01:01:56","code":null,"level":0,"qr_code":null,"alipay_account":"321313980@qq.com","real_name":"张旺"}
         * classify_name : 优惠券
         * type_name : 视频券
         * supplier_name : 腾讯视频
         * prefix : YH
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
        private int is_sell;
        private int is_hot;
        private int status;
        private String create_time;
        private String update_time;
        private Object order_mobile;
        private Object order_name;
        private Object order_address;
        private Object city;
        private int view;
        private String config_discount;
        private UserInfoBean user_info;
        private String classify_name;
        private String type_name;
        private String supplier_name;
        private String prefix;

        public String getCity_name() {
            return City_name;
        }

        public void setCity_name(String city_name) {
            City_name = city_name;
        }

        private String City_name;
        private List<String> good_img;
        private List<String> details;

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

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
        }

        public String getConfig_discount() {
            return config_discount;
        }

        public void setConfig_discount(String config_discount) {
            this.config_discount = config_discount;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
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

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public List<String> getGood_img() {
            return good_img;
        }

        public void setGood_img(List<String> good_img) {
            this.good_img = good_img;
        }

        public List<String> getDetails() {
            return details;
        }

        public void setDetails(List<String> details) {
            this.details = details;
        }

        public static class UserInfoBean {
            /**
             * id : 1379
             * username : 越来越努力w°
             * password : null
             * saft : null
             * headimgurl : http://thirdwx.qlogo.cn/mmopen/VzQsdzsGScMuZsHfWfANPqKtEM3zlIKR46V8gKmyHKdtAo70ECVZV2We0aADS4ARHdPvlkfMn0Jl6D17l5Ctzeicf8icK4dT4j/132
             * mobile : 17607121207
             * email :
             * money : 1034.25
             * lock_money : 59.10
             * score : 0
             * sex : 1
             * openid : oEcnY1TO-ebo4ea0kokQXlvMToFE
             * pid : 0
             * last_login_ip :
             * last_login_time :
             * status : 1
             * regtime : 2019-03-08 01:01:56
             * code : null
             * level : 0
             * qr_code : null
             * alipay_account : 321313980@qq.com
             * real_name : 张旺
             */

            private int id;
            private String username;
            private Object password;
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
            private Object code;
            private int level;
            private Object qr_code;
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

            public void setPassword(Object password) {
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

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public Object getQr_code() {
                return qr_code;
            }

            public void setQr_code(Object qr_code) {
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
    }
}
