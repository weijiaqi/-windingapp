package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/13.
 */

public class BindUserAlipayEntity {

    /**
     * code : 00000
     * result :
     * message : 绑定成功
     */

    private String code;
    private String result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
