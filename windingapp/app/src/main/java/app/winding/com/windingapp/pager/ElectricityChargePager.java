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
import android.widget.TextView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.adapter.FenleiLeftAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.entity.ElectricOrderEntity;
import app.winding.com.windingapp.entity.ElectricRobEntity;
import app.winding.com.windingapp.entity.ElectricityEntity;
import app.winding.com.windingapp.entity.UserelectricorderEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.ui.ElectricityDetailActivity;
import app.winding.com.windingapp.ui.TariffTransactionActivity;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 电费订单
 */

public class ElectricityChargePager extends BasePager implements NoticeObserver.Observer {
    RecyclerView rcycharge;
    private LoadingDialog loadingDialog;
    private Handler handler = new Handler();
    private TextView content;

    public ElectricityChargePager(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater layoutInflater) {
        NoticeObserver.getInstance().addObserver(this);
        View view = layoutInflater.inflate(R.layout.electricitycharge_pager, null);
        rcycharge = view.findViewById(R.id.rcycharge);
        content = view.findViewById(R.id.content);
        rcycharge.setLayoutManager(new GridLayoutManager(context, 3));
        get_words_list();

        //电费回收列表
        ApiInterface.ApiFactory.createApi().electric_order_list().enqueue(new Callback<ElectricityEntity>() {
            @Override
            public void onResponse(Call<ElectricityEntity> call, Response<ElectricityEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult().size() > 0) {
                        List<ElectricityEntity.ResultBean> resultBeanList = response.body().getResult();
                        FenleiLeftAdapter fenleiLeftAdapter = new FenleiLeftAdapter(context, resultBeanList);
                        rcycharge.setAdapter(fenleiLeftAdapter);
                        fenleiLeftAdapter.setOnItemClickListener((view, position) -> {
                            electric_rob_order(resultBeanList.get(position).getId());

                        });
                    }
                }else if (response.body().getCode() == 21818) {
                    SharedPreferencesUtils.putString(Constants.TOKEN,"");
                    ToastUtil.show("登录失效,请重新登录!",200);
                    JumpActivityUtil.launchActivity(context, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<ElectricityEntity> call, Throwable t) {

            }
        });

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
                    content.setText(response.body().getResult().getElectricity_home().toString());
                }
            }
            @Override
            public void onFailure(Call<WordsListEntity> call, Throwable t) {

            }
        });
    }

    //电费抢单
    public void electric_rob_order(int id) {
        ApiInterface.ApiFactory.createApi().electric_rob_order(id).enqueue(new Callback<ElectricRobEntity>() {
            @Override
            public void onResponse(Call<ElectricRobEntity> call, Response<ElectricRobEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult() != null && !TextUtils.isEmpty(response.body().getResult().getCash_money())) {
                        loadingDialog = new LoadingDialog(context, context.getString(R.string.loading));
                        loadingDialog.show();
                        handler.postDelayed(() -> get_user_electric_order(), 1000);
                    } else {
                        ToastUtil.show("库存不足!", 200);
                    }
                }
            }

            @Override
            public void onFailure(Call<ElectricRobEntity> call, Throwable t) {

            }
        });
    }

    public void get_user_electric_order() {
        ApiInterface.ApiFactory.createApi().get_user_electric_order().enqueue(new Callback<UserelectricorderEntity>() {
            @Override
            public void onResponse(Call<UserelectricorderEntity> call, Response<UserelectricorderEntity> response) {
                if (response.body().getCode() == 200) {
                    loadingDialog.dismiss();
                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", response.body().getResult().getOrder_id());
                    bundle.putInt("id", 2);
                    JumpActivityUtil.launchActivity(context, ElectricityDetailActivity.class, bundle);
                    TariffTransactionActivity.instance.finish();
                } else if (response.body().getCode() == 500) {
                    loadingDialog.dismiss();
                    ToastUtil.show(response.body().getMessage(), 200);
                }
            }

            @Override
            public void onFailure(Call<UserelectricorderEntity> call, Throwable t) {

            }
        });
    }


    //查看是否有过往订单
    public void get_electric_order() {
        ApiInterface.ApiFactory.createApi().get_electric_order().enqueue(new Callback<ElectricOrderEntity>() {
            @Override
            public void onResponse(Call<ElectricOrderEntity> call, Response<ElectricOrderEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult().getOrder_id() != null &&!TextUtils.isEmpty(response.body().getResult().getOrder_id())) {
                        DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_electricityorder, R.style.dialog, (contentView, utils) -> {
                            utils.setCancelable(false);
                            Button btn_click = contentView.findViewById(R.id.Getinto);
                            btn_click.setOnClickListener(v2 -> {
                                utils.close();
                                Bundle bundle = new Bundle();
                                bundle.putString("order_id", response.body().getResult().getOrder_id());
                                bundle.putInt("id", 2);
                                JumpActivityUtil.launchActivity(context, ElectricityDetailActivity.class, bundle);
                                TariffTransactionActivity.instance.finish();
                            });
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ElectricOrderEntity> call, Throwable t) {

            }
        });
    }

    @Override
    public <T> void update(int what, T t) {
        if (what == Constants.HangOrder) {
            get_electric_order();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoticeObserver.getInstance().removeObserver(this);
    }
}
