package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/15.
 */

public class UserPresentEntity {

    /**
     * code : 00000
     * result :
     * message : 提现申请成功
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
