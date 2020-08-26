package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/4/1.
 */

public class GetInfoEntity {

    /**
     * code : 200
     * result : {"title":"测试","desc":"测试","link":"http://test.kulian100.com/admins/index/pay.html","imgUrl":"http://qqt.ruimofang.com/static/admin/images/logo.png"}
     * message : 数据获取成功
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
         * title : 测试
         * desc : 测试
         * link : http://test.kulian100.com/admins/index/pay.html
         * imgUrl : http://qqt.ruimofang.com/static/admin/images/logo.png
         */

        private String title;
        private String desc;
        private String link;
        private String imgUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
