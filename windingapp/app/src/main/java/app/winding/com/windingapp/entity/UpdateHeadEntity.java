package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/16.
 */

public class UpdateHeadEntity {

    /**
     * code : 200
     * result : http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEJmEXVe3y0ic6o3yO5ROLLbyRXvSQJ7fxHtRpbtn0WOPCtpgUrWBpcKFnGKhSjYlzEZ6LPxYCJjiaBA/132
     * message : 修改头像成功
     */

    private int code;
    private String result;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
