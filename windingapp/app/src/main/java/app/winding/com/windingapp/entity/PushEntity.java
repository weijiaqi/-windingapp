package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/4/2.
 */

public class PushEntity {

    /**
     * code : 200
     * result : [{"id":3,"created_at":null,"updated_at":null,"deleted_at":null,"u_token":"19","m_type":2,"title":"3","alert":"3","detail":"33","has_read_msg":3,"time_stamp":33},{"id":1,"created_at":null,"updated_at":null,"deleted_at":null,"u_token":"","m_type":1,"title":"1","alert":"1","detail":"1","has_read_msg":1,"time_stamp":1}]
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
         * id : 3
         * created_at : null
         * updated_at : null
         * deleted_at : null
         * u_token : 19
         * m_type : 2
         * title : 3
         * alert : 3
         * detail : 33
         * has_read_msg : 3
         * time_stamp : 33
         */

        private int id;
        private Object created_at;
        private Object updated_at;
        private Object deleted_at;
        private String u_token;
        private int m_type;
        private String title;
        private String alert;
        private String detail;
        private int has_read_msg;
        private int time_stamp;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }

        public Object getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(Object deleted_at) {
            this.deleted_at = deleted_at;
        }

        public String getU_token() {
            return u_token;
        }

        public void setU_token(String u_token) {
            this.u_token = u_token;
        }

        public int getM_type() {
            return m_type;
        }

        public void setM_type(int m_type) {
            this.m_type = m_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlert() {
            return alert;
        }

        public void setAlert(String alert) {
            this.alert = alert;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getHas_read_msg() {
            return has_read_msg;
        }

        public void setHas_read_msg(int has_read_msg) {
            this.has_read_msg = has_read_msg;
        }

        public int getTime_stamp() {
            return time_stamp;
        }

        public void setTime_stamp(int time_stamp) {
            this.time_stamp = time_stamp;
        }
    }
}
