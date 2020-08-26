package app.winding.com.windingapp.wxapi;

/**
 * Created by wjq91 on 2017/9/15.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;

import app.winding.com.windingapp.MainActivity;
import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.OrderDetailsActivity;
import app.winding.com.windingapp.activity.TaskDetailActivity;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.fragment.MarginBuyingFragment;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.ui.GenerateOrderActivity;
import app.winding.com.windingapp.ui.PurchaseFilmActivity;
import app.winding.com.windingapp.ui.ReplaceTasksActivity;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import butterknife.Bind;
import butterknife.ButterKnife;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.paypic)
    ImageView paypic;
    @Bind(R.id.paystatus)
    TextView paystatus;
    @Bind(R.id.paydetail)
    TextView paydetail;

    private IWXAPI api;

    //TODO　这里需要替换你的APP_ID
    private String APP_ID = "wx0a1222d5324260b1"; //这里需要替换你的APP_ID

    @Override
    protected int getLayoutResource() {
        return R.layout.pay_result;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ButterKnife.bind(this);
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("支付结果");

        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("支付结果");
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
            int type = SharedPreferencesUtils.getInt(Constants.PAYTYPE, 0);
            switch (resp.errCode) {
                case -2:
                    switch (type) {
                        case 1: //话费充值
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;
                        case 2: //生成订单的支付
                            if (GenerateOrderActivity.instance != null) {//给一下判空
                                GenerateOrderActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            break;
                        case 3:  //订单支付
                            if (OrderDetailsActivity.instance != null) {//给一下判空
                                OrderDetailsActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;
                        case 4:  //任务支付订单
                            if (TaskDetailActivity.instance != null) {//给一下判空
                                TaskDetailActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            break;
                        case 5:  // 买电影票
                            if (PurchaseFilmActivity.instance != null) {//给一下判空
                                PurchaseFilmActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;
                        case 6:   //代下任务
                            if (ReplaceTasksActivity.instance != null) {//给一下判空
                                ReplaceTasksActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;
                        case 7: //电费充值
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;

                    }

                    paypic.setImageResource(R.mipmap.payerror);
                    paystatus.setText("支付失败");
                    paydetail.setText("您的订单支付失败");
                    break;
                case 0:

                    switch (type) {
                        case 1: //话费充值
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;
                        case 2: //生成订单的支付
                            if (GenerateOrderActivity.instance != null) {//给一下判空
                                GenerateOrderActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            break;
                        case 3:  //订单支付
                            if (OrderDetailsActivity.instance != null) {//给一下判空
                                OrderDetailsActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;
                        case 4:  //任务支付订单
                            if (TaskDetailActivity.instance != null) {//给一下判空
                                TaskDetailActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            break;
                        case 5:  // 买电影票
                            if (PurchaseFilmActivity.instance != null) {//给一下判空
                                PurchaseFilmActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;
                        case 6:   //代下任务
                            if (ReplaceTasksActivity.instance != null) {//给一下判空
                                ReplaceTasksActivity.instance.finish();//在其它的activity里面使用
                            }
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;
                        case 7: //电费充值
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                            break;

                    }
                    paypic.setImageResource(R.mipmap.paysuccess);
                    paystatus.setText("支付成功");
                    paydetail.setText("您的订单支付成功");
                    break;
            }


        }
    }
}
