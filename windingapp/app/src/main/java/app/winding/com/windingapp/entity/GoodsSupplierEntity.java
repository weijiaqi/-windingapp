package app.winding.com.windingapp.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 */

public class GoodsSupplierEntity {

    /**
     * code : 200
     * result : [{"id":1,"supplier_name":"卷卷通","supplier_img":"20190215/ed1aed856d444cf3c8066831244957fe.jpg","remark":"卷卷通","create_time":"2018-11-24 13:38:32","update_time":"2019-02-15 22:24:24","is_delete":0},{"id":2,"supplier_name":"必胜客","supplier_img":"20181124/48ea4fac5163dc35a17cc1c4e07d2dea.jpg","remark":"必胜客","create_time":"2018-11-24 13:39:00","update_time":"2018-11-24 14:56:04","is_delete":0},{"id":3,"supplier_name":"呷哺呷哺","supplier_img":"20181124/efaca45027779cfc0f107155091a92db.jpg","remark":"呷哺呷哺","create_time":"2018-11-24 13:39:09","update_time":"2018-11-24 14:56:12","is_delete":0},{"id":4,"supplier_name":"哈根达斯","supplier_img":"20181124/d9680abd51904aec4585c862c95e9692.jpg","remark":"哈根达斯","create_time":"2018-11-24 13:39:22","update_time":"2018-11-24 14:56:19","is_delete":0},{"id":6,"supplier_name":"costa咖啡","supplier_img":"20181124/ae4e0a2172fb368671836ed56d38562d.jpg","remark":"costa咖啡","create_time":"2018-11-24 14:56:58","update_time":"2018-11-24 14:56:58","is_delete":0},{"id":7,"supplier_name":"瑞幸咖啡","supplier_img":"20181124/edfb3229f879b721e867db1a4e58698a.jpg","remark":"瑞幸咖啡","create_time":"2018-11-24 14:57:55","update_time":"2018-11-24 14:57:55","is_delete":0},{"id":8,"supplier_name":"腾讯视频","supplier_img":"20181124/f8c2ec91b6dd131d3ed294ee2ebf18f8.jpg","remark":"腾讯视频","create_time":"2018-11-24 14:58:12","update_time":"2018-11-24 14:58:12","is_delete":0},{"id":9,"supplier_name":"爱奇艺","supplier_img":"20181124/44b36f2db9e3c29ec61826e7c465788d.jpg","remark":"爱奇艺","create_time":"2018-11-24 14:58:30","update_time":"2018-11-24 14:58:30","is_delete":0},{"id":10,"supplier_name":"大众点评","supplier_img":"20181124/23c73b47da16eedb3f618bdfe32251fb.jpg","remark":"大众点评","create_time":"2018-11-24 14:58:47","update_time":"2018-11-24 14:58:47","is_delete":0},{"id":11,"supplier_name":"猫眼","supplier_img":"20181124/8b7e48c9213ce9ac89d9162315579cb6.jpg","remark":"猫眼","create_time":"2018-11-24 14:59:04","update_time":"2018-11-24 14:59:04","is_delete":0},{"id":12,"supplier_name":"家乐福","supplier_img":"20181124/163e22b23012f4b90a2f0797b34412c0.jpg","remark":"家乐福","create_time":"2018-11-24 14:59:23","update_time":"2018-11-24 14:59:23","is_delete":0},{"id":13,"supplier_name":"123","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"123","create_time":"2018-12-06 10:58:59","update_time":"2018-12-06 10:58:59","is_delete":0},{"id":14,"supplier_name":"麦当劳","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"麦当劳","create_time":"2018-12-06 11:01:27","update_time":"2018-12-06 11:01:27","is_delete":0},{"id":15,"supplier_name":"掌上生活","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"掌上生活","create_time":"2018-12-07 10:25:12","update_time":"2018-12-07 10:25:12","is_delete":0},{"id":16,"supplier_name":"招商银行","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"招商银行","create_time":"2018-12-07 23:55:02","update_time":"2018-12-07 23:55:02","is_delete":0},{"id":17,"supplier_name":"芒果视频VIP","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"芒果视频VIP","create_time":"2018-12-08 10:54:17","update_time":"2018-12-08 10:54:17","is_delete":0},{"id":18,"supplier_name":"芒果视频","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"芒果视频","create_time":"2018-12-08 10:54:23","update_time":"2018-12-08 10:54:23","is_delete":0},{"id":19,"supplier_name":"星巴克","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"星巴克","create_time":"2018-12-12 21:47:55","update_time":"2018-12-12 21:47:55","is_delete":0},{"id":20,"supplier_name":"优酷月卡","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"优酷月卡","create_time":"2018-12-18 17:47:31","update_time":"2018-12-18 17:47:31","is_delete":0},{"id":21,"supplier_name":"汉堡王","supplier_img":"20181205/bcb32a733c32052a607988d641e11488.jpg","remark":"汉堡王","create_time":"2018-12-27 08:37:05","update_time":"2018-12-27 08:37:05","is_delete":0},{"id":22,"supplier_name":"麦德龙","supplier_img":"2019022123b2152970f48631fe332b16ec38958a.jpg","remark":"麦德龙","create_time":"2018-12-28 09:50:53","update_time":"2019-02-21 15:33:12","is_delete":0}]
     * message : 数据获取成功
     */

    private int code;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * supplier_name : 卷卷通
         * supplier_img : 20190215/ed1aed856d444cf3c8066831244957fe.jpg
         * remark : 卷卷通
         * create_time : 2018-11-24 13:38:32
         * update_time : 2019-02-15 22:24:24
         * is_delete : 0
         */

        private int id;
        private String supplier_name;
        private String supplier_img;
        private String remark;
        private String create_time;
        private String update_time;
        private int is_delete;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public String getSupplier_img() {
            return supplier_img;
        }

        public void setSupplier_img(String supplier_img) {
            this.supplier_img = supplier_img;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
        }
    }
}
