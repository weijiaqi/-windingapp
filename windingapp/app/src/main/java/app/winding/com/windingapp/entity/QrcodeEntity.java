package app.winding.com.windingapp.entity;



public class QrcodeEntity {

    /**
     * code : 00000
     * result : {"data":{"id":1,"code":"7ltpOD","qr_code":"uploads/qrcode/20181112/7ltpOD.png"},"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LndoaHlrai5jbiIsImF1ZCI6Imh0dHBzOlwvXC93d3cud2hoeWtqLmNuIiwiaWF0IjoxNTQyMDE4MTk0LCJleHAiOjE1NDM0NTgxOTQsInVpZCI6MX0.CJbAj9GSkdI8sRccNp0NGiC1ATCqRaXWCgv5aURumZ0"}
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
        /**
         * data : {"id":1,"code":"7ltpOD","qr_code":"uploads/qrcode/20181112/7ltpOD.png"}
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3LndoaHlrai5jbiIsImF1ZCI6Imh0dHBzOlwvXC93d3cud2hoeWtqLmNuIiwiaWF0IjoxNTQyMDE4MTk0LCJleHAiOjE1NDM0NTgxOTQsInVpZCI6MX0.CJbAj9GSkdI8sRccNp0NGiC1ATCqRaXWCgv5aURumZ0
         */

        private DataBean data;
        private String token;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class DataBean {
            /**
             * id : 1
             * code : 7ltpOD
             * qr_code : uploads/qrcode/20181112/7ltpOD.png
             */

            private int id;
            private String code;
            private String qr_code;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getQr_code() {
                return qr_code;
            }

            public void setQr_code(String qr_code) {
                this.qr_code = qr_code;
            }
        }
    }
}
