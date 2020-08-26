package app.winding.com.windingapp.api;

import app.winding.com.windingapp.activity.GoodsConfigEntity;
import app.winding.com.windingapp.entity.AfterSaleEntity;
import app.winding.com.windingapp.entity.ArticleDetailEntity;
import app.winding.com.windingapp.entity.ArticleDetailsEntity;
import app.winding.com.windingapp.entity.ArticleEntity;
import app.winding.com.windingapp.entity.BannerEntity;
import app.winding.com.windingapp.entity.BindUserAlipayEntity;
import app.winding.com.windingapp.entity.CancelEntity;
import app.winding.com.windingapp.entity.CascadeEntity;
import app.winding.com.windingapp.entity.CascadesEntity;
import app.winding.com.windingapp.entity.CheckStatusEntity;
import app.winding.com.windingapp.entity.CityLetterEntity;
import app.winding.com.windingapp.entity.ClassifyEntity;
import app.winding.com.windingapp.entity.CodeEntity;
import app.winding.com.windingapp.entity.CompleteEntity;
import app.winding.com.windingapp.entity.ConfirmOrderEntity;
import app.winding.com.windingapp.entity.ElectricEntity;
import app.winding.com.windingapp.entity.ElectricOrderEntity;
import app.winding.com.windingapp.entity.ElectricOrdersEntity;
import app.winding.com.windingapp.entity.ElectricRobEntity;
import app.winding.com.windingapp.entity.ElectricityEntity;
import app.winding.com.windingapp.entity.FareEntity;
import app.winding.com.windingapp.entity.FareNewEntity;
import app.winding.com.windingapp.entity.FareOrderEntity;
import app.winding.com.windingapp.entity.FareOrderListEntity;
import app.winding.com.windingapp.entity.FareRobOrdersEntity;
import app.winding.com.windingapp.entity.GetInfoEntity;
import app.winding.com.windingapp.entity.GoodTypeEntity;
import app.winding.com.windingapp.entity.GoodsAddEntity;
import app.winding.com.windingapp.entity.GoodsDetailEntity;
import app.winding.com.windingapp.entity.GoodsDetailsEntity;
import app.winding.com.windingapp.entity.GoodsListEntity;
import app.winding.com.windingapp.entity.GoodsOrderEntity;
import app.winding.com.windingapp.entity.GoodsOrdersEntity;
import app.winding.com.windingapp.entity.GoodsSupplierEntity;
import app.winding.com.windingapp.entity.GoodsTypeEntity;
import app.winding.com.windingapp.entity.InviteListEntity;
import app.winding.com.windingapp.entity.MoneyLogEntity;
import app.winding.com.windingapp.entity.MyConfirmedEntity;
import app.winding.com.windingapp.entity.MyGoodsOrderEntity;
import app.winding.com.windingapp.entity.MyInviteEntity;
import app.winding.com.windingapp.entity.MysGoodsEntity;
import app.winding.com.windingapp.entity.OrderAddEntity;
import app.winding.com.windingapp.entity.OrderDetailsEntity;
import app.winding.com.windingapp.entity.OrdersAddEntity;
import app.winding.com.windingapp.entity.PayOrderEntity;
import app.winding.com.windingapp.entity.PushEntity;
import app.winding.com.windingapp.entity.PushInfoEntity;
import app.winding.com.windingapp.entity.QrcodeEntity;
import app.winding.com.windingapp.entity.RecomEntity;
import app.winding.com.windingapp.entity.RewardListEntity;
import app.winding.com.windingapp.entity.RobOrderEntity;
import app.winding.com.windingapp.entity.TaskClassifylistEntity;
import app.winding.com.windingapp.entity.TemporaryEntity;
import app.winding.com.windingapp.entity.UpdateHeadEntity;
import app.winding.com.windingapp.entity.UpdateNameEntity;
import app.winding.com.windingapp.entity.UpdateTemporaryEntity;
import app.winding.com.windingapp.entity.UploadEntity;
import app.winding.com.windingapp.entity.UserAlipayEntity;
import app.winding.com.windingapp.entity.UserEntity;
import app.winding.com.windingapp.entity.UserInfosEntity;
import app.winding.com.windingapp.entity.UserPresentEntity;
import app.winding.com.windingapp.entity.UserPresentListEntity;
import app.winding.com.windingapp.entity.UserelectricorderEntity;
import app.winding.com.windingapp.entity.UserfareorderEntity;
import app.winding.com.windingapp.entity.UserinfoEntity;
import app.winding.com.windingapp.entity.VersionEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2019/2/22.
 */

public interface ApiInterface {
    //"http://qqt.ruimofang.com/" 整数域名

    String BASE_URL = "http://qqt.ruimofang.com/";
    String HEAD_IMG = "http://qqt.ruimofang.com/index/Goods/upload.html";
    String APP_VERSION = "http://qqt.ruimofang.com/api/Versions/get_versions.html";

//    String BASE_URL = "http://test.kulian100.com/";
//    String HEAD_IMG = "http://test.kulian100.com/index/Goods/upload.html";
//    String APP_VERSION = "http://test.kulian100.com/api/Versions/get_versions.html";
    class ApiFactory {
        private volatile static Object monitor = new Object();
        private static ApiInterface apiSingleton;

        public static ApiInterface createApi() {
            if (apiSingleton == null) {
                synchronized (monitor) {
                    if (apiSingleton == null) {
                        apiSingleton = RestApi.getInstance().create(BASE_URL, ApiInterface.class);
                    }
                }
            }
            return apiSingleton;
        }
    }


    /**
     * 全部商品类别
     */

    @POST("index/Goods_Config/goods_classify.html")
    Call<UserinfoEntity> goods_classify();


    /**
     * 首页类别
     */

    @POST("index/home/get_classify.html")
    Call<ClassifyEntity> get_classify();

    /**
     * 首页推荐商品
     */
    @FormUrlEncoded
    @POST("index/home/recom_goods.html")
    Call<RecomEntity> recom_goods(@Field("limit") int limit);

    /**
     * 首页热门活动
     */
    @FormUrlEncoded
    @POST("index/home/article_list.html")
    Call<ArticleEntity> article_list(@Field("type") int type, @Field("limit") int limit, @Field("is_recom") int is_recom);

    /**
     * 公告详情
     */
    @FormUrlEncoded
    @POST("index/home/article_details.html")
    Call<ArticleDetailEntity> article_details(@Field("id") int id);

    /**
     * 首页公告
     */
    @FormUrlEncoded
    @POST("index/home/article_list.html")
    Call<ArticleDetailsEntity> article_notice(@Field("type") int type);

    /**
     * 首页banner
     */
    @FormUrlEncoded
    @POST("index/home/banner.html")
    Call<BannerEntity> banner(@Field("type") int type);

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("index/member/regcode.html")
    Call<CodeEntity> regcode(@Field("mobile") String mobile);


    /**
     * 用户注册
     */
    @FormUrlEncoded
    @POST("index/member/user_reg.html")
    Call<UserEntity> user_reg(@Field("mobile") String mobile, @Field("code") String code,@Field("invite") String invite,@Field("versions") String versions);

    /**
     * 电费回收
     */
    @POST("index/Goods_order/electric_order_list.html")
    Call<ElectricityEntity> electric_order_list();

    /**
     * 字母排序城市
     */

    @POST("index/Cascade/city_letter.html")
    Call<CityLetterEntity> city_letter();

    /**
     * 热门城市
     */
    @POST("index/Cascade/city_hot.html")
    Call<CascadeEntity> city_hot();


    /**
     * 城市搜索
     */
    @FormUrlEncoded
    @POST("index/Cascade/city_screen.html")
    Call<CascadesEntity> city_screen(@Field("key") String key);

    /**
     * 电费充值列表
     */
    @POST("index/Goods/electric.html")
    Call<ElectricEntity> electric();

    /**
     * 获取电费历史信息
     */
    @POST("index/goods_order/get_electric_new.html")
    Call<FareNewEntity> get_electric_new();

    /**
     * 获取话费历史信息
     */
    @POST("index/goods_order/get_fare_new.html")
    Call<FareNewEntity> get_fare_new();

    /**
     * 正在进行钟电费订单
     */

    @POST("index/goods_order/get_electric_order.html")
    Call<ElectricOrderEntity> get_electric_order();

    /**
     * 电费抢单
     */
    @FormUrlEncoded
    @POST("index/Goods_order/electric_rob_order.html")
    Call<ElectricRobEntity> electric_rob_order(@Field("goods_id") int goods_id);

    /**
     * 订单详情
     */

    @FormUrlEncoded
    @POST("index/Goods_Order/goods_order_details.html")
    Call<OrderDetailsEntity> goods_order_details(@Field("order_id") String goods_id);


    @FormUrlEncoded
    @POST("index/Goods_Order/goods_order_details.html")
    Call<GoodsDetailEntity> goods_order_detail(@Field("order_id") String goods_id);


    /**
     * 取消订单
     */
    @FormUrlEncoded
    @POST("index/Goods_order/cancel_order.html")
    Call<CancelEntity> cancel_order(@Field("order_id") String goods_id);

    /**
     * 电费充值下单
     */
    @FormUrlEncoded
    @POST("index/Goods_Order/electric_order_add.html")
    Call<ElectricOrdersEntity> electric_order_add(@Field("goods_id") int goods_id, @Field("prefix") String prefix, @Field("number") String number, @Field("city") int city);

    /**
     * 话费回收列表
     */
    @POST("index/Goods_order/fare_order_list.html")
    Call<FareOrderListEntity> fare_order_list();

    /**
     * 话费充值列表
     */
    @POST("index/Goods/fare.html")
    Call<FareEntity> fare();

    /**
     * 正在进行中话费订单
     */
    @POST("index/goods_order/get_fare_order.html")
    Call<GoodsOrdersEntity> get_fare_order();

    /**
     * 话费充值下单
     */
    @FormUrlEncoded
    @POST("index/Goods_Order/fare_order_add.html")
    Call<FareOrderEntity> fare_order_add(@Field("goods_id") int goods_id, @Field("prefix") String prefix, @Field("mobile") String number);

    /**
     * 商品类型
     */
    @FormUrlEncoded
    @POST("index/Goods_Config/goods_type.html")
    Call<GoodsTypeEntity> goods_type(@Field("classify_id") int classify_id, @Field("parent_id") int parent_id);

    /**
     * 获取商品配置
     */
    @FormUrlEncoded
    @POST("index/Goods_Config/goods_config.html")
    Call<GoodsConfigEntity> goods_config(@Field("type_id") int type_id);


    /**
     * 添加订单
     */
    @FormUrlEncoded
    @POST("index/Goods_Order/order_add.html")
    Call<OrderAddEntity> order_add(@Field("goods_id") int goods_id, @Field("order_details") String order_details, @Field("prefix") String prefix);

    /**
     * 充值卡添加订单
     */
    @FormUrlEncoded
    @POST("index/Goods_Order/order_add.html")
    Call<OrdersAddEntity> orders_add(@Field("goods_id") int goods_id, @Field("order_details") String order_details, @Field("prefix") String prefix);


    /**
     * 模拟支付
     */
    @FormUrlEncoded
    @POST("index/Pay/complete.html")
    Call<CompleteEntity> complete(@Field("order_id") String type_id,@Field("pay_price") String pay_price,@Field("pay_type") int pay_type);

    /**
     * 任务列表
     */
    @FormUrlEncoded
    @POST("index/Goods/goods_list.html")
    Call<GoodsListEntity> goods_list(@Field("classify_id") int classify_id, @Field("type_id") int type_id, @Field("supplier_id") int supplier_id, @Field("good_name") String good_name, @Field("page") int page);

    /**
     * 商品发布
     */
    @FormUrlEncoded
    @POST("index/Goods/goods_add.html")
    Call<GoodsAddEntity> goods_add(@Field("good_name") String good_name, @Field("brief") String brief, @Field("time") String time, @Field("num") int num, @Field("good_img") String good_img, @Field("price") String price, @Field("money") String money, @Field("details") String details, @Field("classify_id") int classify_id, @Field("type_id") int type_id, @Field("config_id") int config_id, @Field("is_inside") int is_inside, @Field("is_shelf") int is_shelf, @Field("supplier_id") int supplier_id, @Field("is_sell") int is_sell, @Field("memo") String memo, @Field("order_mobile") String order_mobile, @Field("order_name") String order_name, @Field("order_address") String order_address, @Field("city") int city);


    /**
     * 优惠券类型
     */
    @FormUrlEncoded
    @POST("index/Goods_Config/goods_type.html")
    Call<GoodTypeEntity> goodss_type(@Field("classify_id") int classify_id, @Field("parent_id") int parent_id);

    /**
     * 全部商家
     */
    @FormUrlEncoded
    @POST("index/Goods_Config/goods_supplier.html")
    Call<GoodsSupplierEntity> goods_supplier(@Field("type_id") int type_id);


    /**
     * 我的订单
     */
    @FormUrlEncoded
    @POST("index/Goods_order/my_goods_order.html")
    Call<MyGoodsOrderEntity> my_goods_order(@Field("classify_id") int image, @Field("type_id") int type_id, @Field("supplier_id") int supplier_id, @Field("good_name") String good_name, @Field("status") int status, @Field("page") int page);

    /**
     * 我的发布
     */
    @FormUrlEncoded
    @POST("index/Goods/my_goods.html")
    Call<MysGoodsEntity> my_goods(@Field("classify_id") int classify_id, @Field("type_id") int type_id, @Field("supplier_id") int supplier_id, @Field("good_name") String good_name, @Field("brief") String brief, @Field("status") int status, @Field("page") int page);

    /**
     * 更新凭证
     */
    @FormUrlEncoded
    @POST("index/goods_order/confirm_new_voucher.html")
    Call<GoodsAddEntity> confirm_new_voucher(@Field("order_id") String type_id, @Field("new_voucher") String new_voucher);

    /**
     * 话费抢单
     */
    @FormUrlEncoded
    @POST("index/Goods_order/fare_rob_order.html")
    Call<FareRobOrdersEntity> fare_rob_order(@Field("money") String money);

    /**
     * 获取补充电影票信息
     */
    @FormUrlEncoded
    @POST("index/Goods/get_temporary.html")
    Call<TemporaryEntity> get_temporary(@Field("order_id") String order_id);


    /**
     * 更新电影票补票信息
     */
    @FormUrlEncoded
    @POST("index/Goods/update_temporary.html")
    Call<UpdateTemporaryEntity> update_temporary(@Field("name") String name, @Field("cname") String cname, @Field("city") int city, @Field("memo") String memo, @Field("order_id") String order_id);


    /**
     * 添加电影票补充信息
     */
    @FormUrlEncoded
    @POST("index/Goods/add_temporary.html")
    Call<UpdateTemporaryEntity> add_temporary(@Field("name") String name, @Field("cname") String cname, @Field("city") int city, @Field("memo") String memo, @Field("order_id") String order_id);

    /**
     * 抢单
     */
    @FormUrlEncoded
    @POST("index/Goods_Order/rob_order.html")
    Call<RobOrderEntity> rob_order(@Field("goods_id") int goods_id, @Field("config_id") int config_id, @Field("type_id") int type_id, @Field("classify_id") int classify_id);


    /**
     * 卖家上传凭证确认
     */
    @FormUrlEncoded
    @POST("index/Goods_order/confirm_goods_order.html")
    Call<ConfirmOrderEntity> confirm_goods_order(@Field("order_id") String order_id, @Field("voucher") String voucher);


    /**
     * 买家最后完成订单
     */
    @FormUrlEncoded
    @POST("index/Goods_order/complete_goods_order.html")
    Call<ConfirmOrderEntity> complete_goods_order(@Field("order_id") String order_id, @Field("voucher") String voucher);


    /**
     * 商品编辑
     */
    @FormUrlEncoded
    @POST("index/Goods/goods_edit.html")
    Call<UpdateTemporaryEntity> goods_edit(@Field("good_name") String good_name, @Field("brief") String brief, @Field("time") String time, @Field("num") int num, @Field("good_img") String good_img, @Field("price") String price, @Field("money") String money, @Field("details") String details, @Field("classify_id") int classify_id, @Field("type_id") int type_id, @Field("config_id") int config_id, @Field("is_inside") int is_inside, @Field("is_shelf") int is_shelf, @Field("supplier_id") int supplier_id, @Field("is_sell") int is_sell, @Field("memo") String memo, @Field("id") int id);


    /**
     * 商品上架
     */

    @FormUrlEncoded
    @POST("index/Goods/goods_shelf.html")
    Call<UpdateTemporaryEntity> goods_shelf(@Field("id") int id, @Field("shelf") int shelf);


    /**
     * 用户添加商家
     */
    @FormUrlEncoded
    @POST("index/Goods_Config/add_goods_supplier.html")
    Call<UpdateTemporaryEntity> add_goods_supplier(@Field("supplier_name") String supplier_name);


    /**
     * 商品详情
     */
    @FormUrlEncoded
    @POST("index/Goods/goods_details.html")
    Call<GoodsDetailsEntity> goods_details(@Field("id") int id);

    /**
     * 我的邀请奖励列表
     */
    @FormUrlEncoded
    @POST("index/Invite/reward_list.html")
    Call<RewardListEntity> reward_list(@Field("page") int page);

    /**
     * 我的邀请列表
     */

    @FormUrlEncoded
    @POST("index/Invite/invite_list.html")
    Call<InviteListEntity> invite_list(@Field("page") int page);

    /**
     * 我的邀请统计
     */

    @POST("index/Invite/my_invite.html")
    Call<MyInviteEntity> my_invite();

    /**
     * 生成邀请码
     */

    @POST("index/User/user_qrcode.html")
    Call<QrcodeEntity> user_qrcode();

    /**
     * 绑定支付宝账户
     */
    @FormUrlEncoded
    @POST("index/Alipay/bindUserAlipay.html")
    Call<BindUserAlipayEntity> bindUserAlipay(@Field("alipay_account") String alipay_account, @Field("real_name") String real_name);

    //获取用户基本信息
    @POST("index/user/get_user_info.html")
    Call<UserInfosEntity> get_user_info();

    /**
     * 修改支付宝账户
     */
    @FormUrlEncoded
    @POST("index/alipay/updateUserAlipay.html")
    Call<UserAlipayEntity> updateUserAlipay(@Field("mobile") String mobile, @Field("verify") String verify, @Field("alipay_account") String alipay_account, @Field("real_name") String real_name);

    /**
     * 获取话费抢单信息
     */
    @POST("index/Goods_order/get_user_fare_order.html")
    Call<UserfareorderEntity> get_user_fare_order();

    /**
     * 获取电费抢单信息
     */
    @POST("index/Goods_order/get_user_electric_order.html")
    Call<UserelectricorderEntity> get_user_electric_order();

    /**
     * 用户提现申请
     */
    @FormUrlEncoded
    @POST("index/user/user_present.html")
    Call<UserPresentEntity> user_present(@Field("money") String money);


    /**
     * 提现列表
     */
    @FormUrlEncoded
    @POST("api/User_Present_List/get_list.html")
    Call<UserPresentListEntity> user_present_list(@Field("status") int status,@Field("page") int page);

    /**
     * 修改头像
     */
    @FormUrlEncoded
    @POST("index/user/update_head.html")
    Call<UpdateHeadEntity> update_head(@Field("headimgurl") String headimgurl);

    /**
     * 修改昵称
     */

    @FormUrlEncoded
    @POST("index/user/update_name.html")
    Call<UpdateNameEntity> update_name(@Field("username") String username);


    /**
     * 待确定
     */
    @FormUrlEncoded
    @POST("index/goods_order/my_confirmed_goods_order.html")
    Call<MyConfirmedEntity> my_confirmed_goods_order(@Field("page") int page,@Field("classify_id") int classify_id);


    //支付
    @FormUrlEncoded
    @POST("index/pay/pay_order.html")
    Call<PayOrderEntity> pay_order(@Field("order_id") String order_id, @Field("money") String money, @Field("pay_type") int pay_type);

    /**
     * 检测是否支付
     */

    @FormUrlEncoded
    @POST("index/pay/check_status.html")
    Call<CheckStatusEntity> check_status(@Field("order_id") String order_id);


    /**
     * 用户资金日志
     */
    @FormUrlEncoded
    @POST("index/user/money_log.html")
    Call<MoneyLogEntity> money_log(@Field("page") int page, @Field("limit") int limit);


    /**
     * 任务列表类别列表
     */
    @POST("api/Goods_Class/task_classify_list.html")
    Call<TaskClassifylistEntity> task_classify_list();

    /**
     * 检测版本升级
     */
    @FormUrlEncoded
    @POST("api/Versions/get_versions.html")
    Call<VersionEntity> get_versions(@Field("version") String version, @Field("type") int type);


    /**
     * 所有任务需求
     */
    @POST("api/Words/get_words_list.html")
    Call<WordsListEntity> get_words_list();


    /**
     * 分享
     */
    @POST("api/Wechat_Share_info/get_info.html")
    Call<GetInfoEntity> get_info();


    //通知列表
    @FormUrlEncoded
    @POST("api/Push_List/get_list.html")
    Call<PushEntity> get_list(@Field("page") int page);

    //通知列表详细
    @FormUrlEncoded
    @POST("api/Push_info/get_info.html")
    Call<PushInfoEntity> get_info(@Field("id") int id);

    //确认申请充值异常
    @FormUrlEncoded
    @POST("index/goods_order/after_sale.html")
    Call<AfterSaleEntity> after_sale(@Field("order_id") String order_id);
}
