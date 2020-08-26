package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/3/11.
 */

public class GoodsAddEntity {

    /**
     * code : 200
     * result :
     * message : 发布成功
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
