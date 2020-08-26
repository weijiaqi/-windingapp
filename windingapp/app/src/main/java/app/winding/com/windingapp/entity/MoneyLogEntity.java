package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/22.
 */

public class MoneyLogEntity {

    /**
     * code : 00000
     * result : {"list":[{"id":2,"user_id":1,"money":"-10.00","balance":"0.00","create_time":"2018-11-09 16:23:32","remark":"消费","admin_id":null,"type":null,"username":"小雨","mobile":"13802222824"},{"id":1,"user_id":1,"money":"10.00","balance":"10.00","create_time":"2018-11-09 16:22:32","remark":"充值","admin_id":null,"type":null,"username":"小雨","mobile":"13802222824"}],"page":"1","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LndoaHlrai5jbiIsImF1ZCI6Imh0dHBzOlwvXC93d3cud2hoeWtqLmNuIiwiaWF0IjoxNTQyMDg4NDYzLCJleHAiOjE1NDM1Mjg0NjMsInVpZCI6MX0.1QpbmlMDScvHDMhRzey3CzMwPxZCqxySk1BTXtIBdiM"}
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
         * list : [{"id":2,"user_id":1,"money":"-10.00","balance":"0.00","create_time":"2018-11-09 16:23:32","remark":"消费","admin_id":null,"type":null,"username":"小雨","mobile":"13802222824"},{"id":1,"user_id":1,"money":"10.00","balance":"10.00","create_time":"2018-11-09 16:22:32","remark":"充值","admin_id":null,"type":null,"username":"小雨","mobile":"13802222824"}]
         * page : 1
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LndoaHlrai5jbiIsImF1ZCI6Imh0dHBzOlwvXC93d3cud2hoeWtqLmNuIiwiaWF0IjoxNTQyMDg4NDYzLCJleHAiOjE1NDM1Mjg0NjMsInVpZCI6MX0.1QpbmlMDScvHDMhRzey3CzMwPxZCqxySk1BTXtIBdiM
         */

        private String page;
        private String token;
        private List<ListBean> list;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * user_id : 1
             * money : -10.00
             * balance : 0.00
             * create_time : 2018-11-09 16:23:32
             * remark : 消费
             * admin_id : null
             * type : null
             * username : 小雨
             * mobile : 13802222824
             */

            private int id;
            private int user_id;
            private String money;
            private String balance;
            private String create_time;
            private String remark;
            private Object admin_id;
            private Object type;
            private String username;
            private String mobile;

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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public Object getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(Object admin_id) {
                this.admin_id = admin_id;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }
        }
    }
}
