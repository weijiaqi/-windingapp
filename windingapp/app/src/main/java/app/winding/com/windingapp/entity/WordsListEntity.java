package app.winding.com.windingapp.entity;

/**
 * Created by Administrator on 2019/4/1.
 */

public class WordsListEntity {


    /**
     * code : 200
     * result : {"fare_home":"温馨提示：<\/br>1.未完成的话费回收订单，可以在我的订单>话费订单里继续进行凭证上传和订单确认。<\/br>2.话费回收订单完成后，订单收入自动进入冻结账户，24小时后解冻并可以进行提现。<\/br>3.上传截图需含充值手机号+金额+成功状态，特殊情况可以先上传 然后联系在线客服或电话客服说明情况。<\/br>4.凭证上传成功后，又发现充值失败退款的，请自行点申请售后放弃订单，否则将有一定处罚。<\/br>5.抢单技巧，点本页面右上角三个点然后点击刷新，有库存即可获取相应订单。","fare_grab_sheet":"抢单攻略：<\/br>1.库存有显示即可点击获取订单，请使用快充渠道充值，禁止慢充，注意规则；<\/br>2.复制手机号去相应渠道充值相应的金额，注意核对手机号，充值成功截图！<\/br>3.回到卷卷通上传截图并且点我已充值，15分钟内完成充值并上报！","fare_tips":"1.请在15分钟内充值并上传凭证报单！禁止多充、禁止少充、禁止拆分充值！<\/br>2.请使用快充渠道，禁止使用慢充渠道！24小时未到账直接失败处理！话费充值有15天售后期！<\/br>3.对于假充（实际没充值点我已充值的）、伪造凭证等违规行为平台将永久封号处理！<\/br>4.未完成订单点我的\u2014我的卖单\u2014话费订单处理；获取订单15分钟未充值及点我已充值处理的订单将自动释放！<\/br>5.订单成功后结算金额自动进入冻结金额，24小时后可直接体现！<\/br>以上规则我能读懂并自愿遵守，若有异议，请勿接单，若有违反，造成资金损失由自己承担！","fare_rechage":"温馨提示：<\/br>1.充值后30分钟话费未到账，请联系卷卷通客服","electricity_home":"电费做单说明：<\/br>1.获取订单后请按指定户号充值，允许多次充值，但充值总金额不能低于订单金额；<\/br>2.上传截图需含户号，金额，状态；分次充值的每笔均需上传凭证，充值失败的请自行点击订单详情点击充值异常，否则将有一定处罚；<\/br>3.假充，伪造凭证，低于订单金额充值的均封号处理；<\/br>4.充值后金额自动进入冻结金额，解冻后方可提现，如有疑问可咨询在线客服或电话客服处理；","electricity_rechage":"电费充值说明：<\/br>1.电费充值必须选择正确的智能电表所在城市，北京以外的城市必须缴清欠费再充值；<\/br>2.本平台仅支持智能电表充值，不知道户号的点上面如何知道户号查询；<\/br>3.电费会在24小时内到账，可能存在分笔到账情况，总额为客户充值金额；如有疑问可联系卷卷通在线客服或者电话客服处理；<\/br>4.电费查询方式（请在购买后24小时再查询）：国家电网电话95598转人工查询最近的几笔电费充值，或者关注智能电表余额是否增加；","electricity_rechage_tips":"如何获取户号：<\/br>1.缴费单上查看户号。<\/br>2.拨打95598可报户名和地址索要户号。<\/br>3.以上都不行，清查看家中电表编号，然后拨打95598索要户号。","user_present":"温馨提示：<\/br>1.可提现金额满100可申请提现。<\/br>2.当天20：00之前的提现当天24点之前到账，20：00之后的提现次日到账；节假日可能有延迟。<\/br>3.如有疑问请添加客服QQ:2982862或拨打客服电话。","present_page":"1.账户余额需大于等于100元才可提现<\/br>2.提现前请核对支付账户和提现金额<\/br>3.当天20点前提现当天到账，20点之后提现次天到账"}
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
         * fare_home : 温馨提示：</br>1.未完成的话费回收订单，可以在我的订单>话费订单里继续进行凭证上传和订单确认。</br>2.话费回收订单完成后，订单收入自动进入冻结账户，24小时后解冻并可以进行提现。</br>3.上传截图需含充值手机号+金额+成功状态，特殊情况可以先上传 然后联系在线客服或电话客服说明情况。</br>4.凭证上传成功后，又发现充值失败退款的，请自行点申请售后放弃订单，否则将有一定处罚。</br>5.抢单技巧，点本页面右上角三个点然后点击刷新，有库存即可获取相应订单。
         * fare_grab_sheet : 抢单攻略：</br>1.库存有显示即可点击获取订单，请使用快充渠道充值，禁止慢充，注意规则；</br>2.复制手机号去相应渠道充值相应的金额，注意核对手机号，充值成功截图！</br>3.回到卷卷通上传截图并且点我已充值，15分钟内完成充值并上报！
         * fare_tips : 1.请在15分钟内充值并上传凭证报单！禁止多充、禁止少充、禁止拆分充值！</br>2.请使用快充渠道，禁止使用慢充渠道！24小时未到账直接失败处理！话费充值有15天售后期！</br>3.对于假充（实际没充值点我已充值的）、伪造凭证等违规行为平台将永久封号处理！</br>4.未完成订单点我的—我的卖单—话费订单处理；获取订单15分钟未充值及点我已充值处理的订单将自动释放！</br>5.订单成功后结算金额自动进入冻结金额，24小时后可直接体现！</br>以上规则我能读懂并自愿遵守，若有异议，请勿接单，若有违反，造成资金损失由自己承担！
         * fare_rechage : 温馨提示：</br>1.充值后30分钟话费未到账，请联系卷卷通客服
         * electricity_home : 电费做单说明：</br>1.获取订单后请按指定户号充值，允许多次充值，但充值总金额不能低于订单金额；</br>2.上传截图需含户号，金额，状态；分次充值的每笔均需上传凭证，充值失败的请自行点击订单详情点击充值异常，否则将有一定处罚；</br>3.假充，伪造凭证，低于订单金额充值的均封号处理；</br>4.充值后金额自动进入冻结金额，解冻后方可提现，如有疑问可咨询在线客服或电话客服处理；
         * electricity_rechage : 电费充值说明：</br>1.电费充值必须选择正确的智能电表所在城市，北京以外的城市必须缴清欠费再充值；</br>2.本平台仅支持智能电表充值，不知道户号的点上面如何知道户号查询；</br>3.电费会在24小时内到账，可能存在分笔到账情况，总额为客户充值金额；如有疑问可联系卷卷通在线客服或者电话客服处理；</br>4.电费查询方式（请在购买后24小时再查询）：国家电网电话95598转人工查询最近的几笔电费充值，或者关注智能电表余额是否增加；
         * electricity_rechage_tips : 如何获取户号：</br>1.缴费单上查看户号。</br>2.拨打95598可报户名和地址索要户号。</br>3.以上都不行，清查看家中电表编号，然后拨打95598索要户号。
         * user_present : 温馨提示：</br>1.可提现金额满100可申请提现。</br>2.当天20：00之前的提现当天24点之前到账，20：00之后的提现次日到账；节假日可能有延迟。</br>3.如有疑问请添加客服QQ:2982862或拨打客服电话。
         * present_page : 1.账户余额需大于等于100元才可提现</br>2.提现前请核对支付账户和提现金额</br>3.当天20点前提现当天到账，20点之后提现次天到账
         */

        private String fare_home;
        private String fare_grab_sheet;
        private String fare_tips;
        private String fare_rechage;
        private String electricity_home;
        private String electricity_rechage;
        private String electricity_rechage_tips;
        private String user_present;
        private String present_page;

        public String getContact_word() {
            return contact_word;
        }

        public void setContact_word(String contact_word) {
            this.contact_word = contact_word;
        }

        public String getInvite_word() {
            return invite_word;
        }

        public void setInvite_word(String invite_word) {
            this.invite_word = invite_word;
        }

        private String contact_word;
        private String invite_word;
        public String getFare_home() {
            return fare_home;
        }

        public void setFare_home(String fare_home) {
            this.fare_home = fare_home;
        }

        public String getFare_grab_sheet() {
            return fare_grab_sheet;
        }

        public void setFare_grab_sheet(String fare_grab_sheet) {
            this.fare_grab_sheet = fare_grab_sheet;
        }

        public String getFare_tips() {
            return fare_tips;
        }

        public void setFare_tips(String fare_tips) {
            this.fare_tips = fare_tips;
        }

        public String getFare_rechage() {
            return fare_rechage;
        }

        public void setFare_rechage(String fare_rechage) {
            this.fare_rechage = fare_rechage;
        }

        public String getElectricity_home() {
            return electricity_home;
        }

        public void setElectricity_home(String electricity_home) {
            this.electricity_home = electricity_home;
        }

        public String getElectricity_rechage() {
            return electricity_rechage;
        }

        public void setElectricity_rechage(String electricity_rechage) {
            this.electricity_rechage = electricity_rechage;
        }

        public String getElectricity_rechage_tips() {
            return electricity_rechage_tips;
        }

        public void setElectricity_rechage_tips(String electricity_rechage_tips) {
            this.electricity_rechage_tips = electricity_rechage_tips;
        }

        public String getUser_present() {
            return user_present;
        }

        public void setUser_present(String user_present) {
            this.user_present = user_present;
        }

        public String getPresent_page() {
            return present_page;
        }

        public void setPresent_page(String present_page) {
            this.present_page = present_page;
        }
    }
}
