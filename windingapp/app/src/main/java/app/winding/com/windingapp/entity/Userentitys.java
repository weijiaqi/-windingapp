package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/20.
 */

public class Userentitys {


    /**
     * code : 500
     * result : {}
     * message : 不能抢自己发布的商品
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
    }
}
