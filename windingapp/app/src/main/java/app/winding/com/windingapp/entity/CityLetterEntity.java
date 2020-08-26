package app.winding.com.windingapp.entity;

import android.support.annotation.NonNull;

import java.util.List;

import app.winding.com.windingapp.util.code.PinyinUtil;
import app.winding.com.windingapp.util.code.PyEntity;

/**
 * Created by Administrator on 2019/3/7.
 */

public class CityLetterEntity  {




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
         * letter : A
         * list : [{"id":134,"city":"阿拉善盟","pid":7,"letter":"A","type":0},{"id":144,"city":"鞍山","pid":8,"letter":"A","type":0},{"id":206,"city":"安徽","pid":13,"letter":"A","type":0},{"id":213,"city":"安庆","pid":13,"letter":"A","type":0},{"id":269,"city":"安阳","pid":17,"letter":"A","type":0},{"id":364,"city":"广安","pid":23,"letter":"A","type":0},{"id":375,"city":"安顺","pid":24,"letter":"A","type":0},{"id":404,"city":"阿里","pid":26,"letter":"A","type":0},{"id":415,"city":"安康","pid":27,"letter":"A","type":0},{"id":459,"city":"阿克苏 ","pid":31,"letter":"A","type":0},{"id":462,"city":"澳门","pid":33,"letter":"A","type":0},{"id":463,"city":"澳门特别行政区 ","pid":33,"letter":"A","type":0},{"id":538,"city":"阿坝藏族羌族","pid":23,"letter":"A","type":0}]
         */

        private String letter;
        private List<ListBean> list;

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean  implements PyEntity{
            /**
             * id : 134
             * city : 阿拉善盟
             * pid : 7
             * letter : A
             * type : 0
             */

            private int id;
            private String city;
            private int pid;
            private String letter;
            private int type;
            public String  pinyin;
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

            @NonNull
            @Override
            public String getPinyin() {
                if(pinyin == null) {
                    pinyin = PinyinUtil.getPingYin(city);
                }
                return pinyin;
            }
        }
    }
}
