package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/13.
 */

public class UserInfosEntity {

    /**
     * code : 00000
     * result : {"data":{"id":1,"username":"小雨","password":null,"saft":null,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/knotjtucWtgiaQ7oSMq6Yyw2vzjWnJNeibDVoqDD2z65eJWXHicPDQiaORxM9kJib75AiaxF6b1uRRBt1uibHfd2Uxw6hhAibWiazAGHP/132","mobile":"13802222824","email":"","money":"59.76","lock_money":"0.00","score":0,"sex":2,"openid":"oEcnY1fxzF4Lv-xR300eU4HcOG2g","pid":2,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2018-11-09 16:22:32","code":"548781","level":0,"qr_code":"uploads/qrcode/20181112/7ltpOD.png","alipay_account":"smalleiyu@163.com"},"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LndoaHlrai5jbiIsImF1ZCI6Imh0dHBzOlwvXC93d3cud2hoeWtqLmNuIiwiaWF0IjoxNTQyMTc3OTYxLCJleHAiOjE1NDM2MTc5NjEsInVpZCI6MX0.Efa9nT_d4DTJfBsL7TirZwRSBA-Go7YnT4cmSr44BUg"}
     * message : 数据获取成功
     */

    private String code;
    private ResultBean result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * data : {"id":1,"username":"小雨","password":null,"saft":null,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/knotjtucWtgiaQ7oSMq6Yyw2vzjWnJNeibDVoqDD2z65eJWXHicPDQiaORxM9kJib75AiaxF6b1uRRBt1uibHfd2Uxw6hhAibWiazAGHP/132","mobile":"13802222824","email":"","money":"59.76","lock_money":"0.00","score":0,"sex":2,"openid":"oEcnY1fxzF4Lv-xR300eU4HcOG2g","pid":2,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2018-11-09 16:22:32","code":"548781","level":0,"qr_code":"uploads/qrcode/20181112/7ltpOD.png","alipay_account":"smalleiyu@163.com"}
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LndoaHlrai5jbiIsImF1ZCI6Imh0dHBzOlwvXC93d3cud2hoeWtqLmNuIiwiaWF0IjoxNTQyMTc3OTYxLCJleHAiOjE1NDM2MTc5NjEsInVpZCI6MX0.Efa9nT_d4DTJfBsL7TirZwRSBA-Go7YnT4cmSr44BUg
         */

        private DataBean data;
        private String token;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class DataBean {
            /**
             * id : 1
             * username : 小雨
             * password : null
             * saft : null
             * headimgurl : http://thirdwx.qlogo.cn/mmopen/knotjtucWtgiaQ7oSMq6Yyw2vzjWnJNeibDVoqDD2z65eJWXHicPDQiaORxM9kJib75AiaxF6b1uRRBt1uibHfd2Uxw6hhAibWiazAGHP/132
             * mobile : 13802222824
             * email :
             * money : 59.76
             * lock_money : 0.00
             * score : 0
             * sex : 2
             * openid : oEcnY1fxzF4Lv-xR300eU4HcOG2g
             * pid : 2
             * last_login_ip :
             * last_login_time :
             * status : 1
             * regtime : 2018-11-09 16:22:32
             * code : 548781
             * level : 0
             * qr_code : uploads/qrcode/20181112/7ltpOD.png
             * alipay_account : smalleiyu@163.com
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
            private String code;
            private int level;
            private String qr_code;
            private String alipay_account;

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
        }
    }
}
