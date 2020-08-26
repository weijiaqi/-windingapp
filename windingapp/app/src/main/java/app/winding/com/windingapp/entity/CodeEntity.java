package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/7.
 */

public class CodeEntity {

    /**
     * code : 200
     * result : {"code":"0","msgId":"19022210582629529","time":"20190222105826","errorMsg":""}
     * message : 短信发送成功
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
         * code : 0
         * msgId : 19022210582629529
         * time : 20190222105826
         * errorMsg :
         */

        private String code;
        private String msgId;
        private String time;
        private String errorMsg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
}
