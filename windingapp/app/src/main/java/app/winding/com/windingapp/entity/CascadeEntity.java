package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/7.
 */

public class CascadeEntity {


    /**
     * code : 00000
     * result : [{"id":5,"city":"北京","pid":1,"letter":"B","type":0},{"id":24,"city":"上海","pid":2,"letter":"S","type":0},{"id":63,"city":"重庆","pid":4,"letter":"C","type":0},{"id":195,"city":"杭州","pid":12,"letter":"H","type":0},{"id":284,"city":"武汉","pid":18,"letter":"W","type":0},{"id":316,"city":"广州","pid":20,"letter":"G","type":0},{"id":317,"city":"深圳","pid":20,"letter":"S","type":0},{"id":354,"city":"成都","pid":23,"letter":"C","type":0}]
     * message : 获取信息成功
     */

    private String code;
    private String message;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * id : 5
         * city : 北京
         * pid : 1
         * letter : B
         * type : 0
         */

        private int id;
        private String city;
        private int pid;
        private String letter;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
