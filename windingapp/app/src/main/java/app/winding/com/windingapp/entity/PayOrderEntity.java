package app.winding.com.windingapp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/3/20.
 */

public class PayOrderEntity {

    /**
     * code : 200
     * message : 请求成功
     * result : {"appid":"wx0a1222d5324260b1","noncestr":"aoNrpDSSE9cB1TuogSjk9AOBGkzGqTw0","package":"Sign=WXPay","partnerid":"1529118271","prepayid":"wx20112741410869d0dfd34cba4157433979","timestamp":1553052459,"sign":"6E7583677A3E8B1851B859E6C660AF42"}
     */

    private int code;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * appid : wx0a1222d5324260b1
         * noncestr : aoNrpDSSE9cB1TuogSjk9AOBGkzGqTw0
         * package : Sign=WXPay
         * partnerid : 1529118271
         * prepayid : wx20112741410869d0dfd34cba4157433979
         * timestamp : 1553052459
         * sign : 6E7583677A3E8B1851B859E6C660AF42
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
