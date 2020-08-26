package app.winding.com.windingapp.pager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.angmarch.views.NiceSpinner;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.adapter.TelephoneRecoveryAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.entity.FareOrderListEntity;
import app.winding.com.windingapp.entity.FareRobOrdersEntity;
import app.winding.com.windingapp.entity.GoodsOrdersEntity;
import app.winding.com.windingapp.entity.UserfareorderEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.ui.ElectricityDetailActivity;
import app.winding.com.windingapp.ui.TelephoneActivity;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 话费回收
 */

public class TelephoneRecoveryPager extends BasePager {

    RecyclerView rcyrecovery;
    LoadingDialog loadingDialog;
    RelativeLayout rlstrategy;
    TextView content;
    private Handler handler = new Handler();

    public TelephoneRecoveryPager(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater layoutInflater) {
        View view = layoutInflater.inflate(R.layout.telephone_recovery_pager, null);
        rlstrategy = view.findViewById(R.id.rlstrategy);
        content = view.findViewById(R.id.content);
        rlstrategy.setOnClickListener(v -> {
            DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_strategy, R.style.dialog, (contentView, utils) -> {
                utils.setCancelable(false);
                TextView contents = contentView.findViewById(R.id.content);
                ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
                    @Override
                    public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                        if (response.body().getCode() == 200) {

                            contents.setText(response.body().getResult().getFare_grab_sheet());

                        }
                    }

                    @Override
                    public void onFailure(Call<WordsListEntity> call, Throwable t) {

                    }
                });

                TextView cancel = contentView.findViewById(R.id.cancel);
                cancel.setOnClickListener(v2 -> {
                    utils.close();
                });

            });

        });
        rcyrecovery = view.findViewById(R.id.rcyrecovery);
        rcyrecovery.setLayoutManager(new GridLayoutManager(context, 3));

        //话费回收列表
        fare_order_list();
        //正在进行中话费订单
        get_fare_order();

        get_words_list();
        return view;
    }

    @Override
    public <DATA> void initData(int type, DATA data) {

    }

    public void get_words_list() {
        ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
            @Override
            public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                if (response.body().getCode() == 200) {
                    content.setText(response.body().getResult().getFare_home().toString());

                }
            }

            @Override
            public void onFailure(Call<WordsListEntity> call, Throwable t) {

            }
        });
    }

    public void fare_order_list() {
        ApiInterface.ApiFactory.createApi().fare_order_list().enqueue(new Callback<FareOrderListEntity>() {
            @Override
            public void onResponse(Call<FareOrderListEntity> call, Response<FareOrderListEntity> response) {
                if (response.body().getCode() == 200) {
                    List<FareOrderListEntity.ResultBean> resultBeanList = response.body().getResult();
                    TelephoneRecoveryAdapter telephoneRecoveryAdapter = new TelephoneRecoveryAdapter(context, resultBeanList);
                    rcyrecovery.setAdapter(telephoneRecoveryAdapter);
                    telephoneRecoveryAdapter.setOnItemClickListener((view, position) -> {

                        DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_telephone_order, R.style.dialog, (contentView, utils) -> {
                            utils.setCancelable(false);
                            TextView classcontent = contentView.findViewById(R.id.classcontent);
                            TextView cancel = contentView.findViewById(R.id.cancel);
                            TextView submit = contentView.findViewById(R.id.submit);
                            ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
                                @Override
                                public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                                    if (response.body().getCode() == 200) {
                                        classcontent.setText(response.body().getResult().getFare_tips());
                                    }
                                }

                                @Override
                                public void onFailure(Call<WordsListEntity> call, Throwable t) {

                                }
                            });


                            cancel.setOnClickListener(v -> {
                                utils.close();
                            });
                            submit.setOnClickListener(v -> {
                                utils.close();
                                fare_rob_order(resultBeanList.get(position).getConfig_money());
                            });
                        });


                    });
                }else if (response.body().getCode() == 21818) {
                    SharedPreferencesUtils.putString(Constants.TOKEN,"");
                    ToastUtil.show("登录失效,请重新登录!",200);
                    JumpActivityUtil.launchActivity(context, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<FareOrderListEntity> call, Throwable t) {

            }
        });
    }


    //话费抢单
    public void fare_rob_order(String money) {
        ApiInterface.ApiFactory.createApi().fare_rob_order(money).enqueue(new Callback<FareRobOrdersEntity>() {
            @Override
            public void onResponse(Call<FareRobOrdersEntity> call, Response<FareRobOrdersEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult() != null && !TextUtils.isEmpty(response.body().getResult().getCash_money())) {
                        loadingDialog = new LoadingDialog(context, context.getString(R.string.loading));
                        loadingDialog.show();
                        handler.postDelayed(() -> get_user_fare_order(), 1000);
                    } else {
                        ToastUtil.show("库存不足!", 200);
                    }
                } else if (response.body().getCode() == 500) {
                    ToastUtil.show("库存不足!", 200);
                }
            }

            @Override
            public void onFailure(Call<FareRobOrdersEntity> call, Throwable t) {
                ToastUtil.show("库存不足!", 200);
            }
        });
    }

    public void get_user_fare_order() {
        ApiInterface.ApiFactory.createApi().get_user_fare_order().enqueue(new Callback<UserfareorderEntity>() {
            @Override
            public void onResponse(Call<UserfareorderEntity> call, Response<UserfareorderEntity> response) {
                if (response.body().getCode() == 200) {
                    loadingDialog.dismiss();
                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", response.body().getResult().getOrder_id());
                    bundle.putInt("id", 1);
                    JumpActivityUtil.launchActivity(context, ElectricityDetailActivity.class, bundle);
                    TelephoneActivity.instance.finish();
                }
            }

            @Override
            public void onFailure(Call<UserfareorderEntity> call, Throwable t) {

            }
        });
    }


    public void get_fare_order() {
        String token= SharedPreferencesUtils.getString(Constants.TOKEN,"");
        ApiInterface.ApiFactory.createApi().get_fare_order().enqueue(new Callback<GoodsOrdersEntity>() {
            @Override
            public void onResponse(Call<GoodsOrdersEntity> call, Response<GoodsOrdersEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult().getOrder_id() != null &&!TextUtils.isEmpty(response.body().getResult().getOrder_id())) {
                        DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_electricityorder, R.style.dialog, (contentView, utils) -> {
                            utils.setCancelable(false);
                            Button btn_click = contentView.findViewById(R.id.Getinto);
                            btn_click.setOnClickListener(v2 -> {
                                utils.close();
                                Bundle bundle = new Bundle();
                                bundle.putString("order_id", response.body().getResult().getOrder_id());
                                bundle.putInt("id", 1);
                                JumpActivityUtil.launchActivity(context, ElectricityDetailActivity.class, bundle);
                                TelephoneActivity.instance.finish();
                            });
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<GoodsOrdersEntity> call, Throwable t) {

            }
        });

    }
}
