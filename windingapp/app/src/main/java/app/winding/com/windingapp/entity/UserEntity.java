package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/7.
 */

public class UserEntity {


    /**
     * code : 200
     * result : {"id":19,"username":"卷卷X4KZNM","password":null,"saft":null,"headimgurl":"","mobile":"17600737159","email":"","money":"0.00","lock_money":"0.00","score":0,"sex":0,"openid":"","pid":0,"last_login_ip":"","last_login_time":"","status":1,"regtime":"2019-03-07 12:11:32","code":null,"level":0,"qr_code":null,"alipay_account":null,"real_name":null,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LndoaHlrai5jbiIsImF1ZCI6Imh0dHBzOlwvXC93d3cud2hoeWtqLmNuIiwiaWF0IjoxNTUxOTMxODkyLCJleHAiOjE1NTMzNzE4OTIsInVpZCI6MTl9.Afa64PE4c_DDSnPhHKzEjqNHpDZug0__vsZmV7k9HlI"}
     * message : 获取用户信息成功
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
         * id : 19
         * username : 卷卷X4KZNM
         * password : null
         * saft : null
         * headimgurl :
         * mobile : 17600737159
         * email :
         * money : 0.00
         * lock_money : 0.00
         * score : 0
         * sex : 0
         * openid :
         * pid : 0
         * last_login_ip :
         * last_login_time :
         * status : 1
         * regtime : 2019-03-07 12:11:32
         * code : null
         * level : 0
         * qr_code : null
         * alipay_account : null
         * real_name : null
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LndoaHlrai5jbiIsImF1ZCI6Imh0dHBzOlwvXC93d3cud2hoeWtqLmNuIiwiaWF0IjoxNTUxOTMxODkyLCJleHAiOjE1NTMzNzE4OTIsInVpZCI6MTl9.Afa64PE4c_DDSnPhHKzEjqNHpDZug0__vsZmV7k9HlI
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
        private String token;

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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
