package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/13.
 */

public class RewardListEntity {

    /**
     * code : 00000
     * result : {"list":[{"id":1,"user_id":2,"pid":1,"money":"10.00","order_id":"201811131542101568X","type_id":1,"create_time":"2018-11-13 17:10:26","mobile":"18806656554","u_mobile":"13802222824","type_name":"话费订单"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * user_id : 2
             * pid : 1
             * money : 10.00
             * order_id : 201811131542101568X
             * type_id : 1
             * create_time : 2018-11-13 17:10:26
             * mobile : 18806656554
             * u_mobile : 13802222824
             * type_name : 话费订单
             */

            private int id;
            private int user_id;
            private int pid;
            private String money;
            private String order_id;
            private int type_id;
            private String create_time;
            private String mobile;
            private String u_mobile;
            private String type_name;

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

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getU_mobile() {
                return u_mobile;
            }

            public void setU_mobile(String u_mobile) {
                this.u_mobile = u_mobile;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }
        }
    }
}
