package app.winding.com.windingapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.angmarch.views.NiceSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.GoodsConfigEntity;
import app.winding.com.windingapp.adapter.CommodityallocationAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.GoodsTypeEntity;
import app.winding.com.windingapp.entity.OrderAddEntity;
import app.winding.com.windingapp.entity.OrdersAddEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//充值卡回收
public class RechargeableRecoveryActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.Cardclass)
    NiceSpinner Cardclass;
    @Bind(R.id.CardType)
    NiceSpinner CardType;
    @Bind(R.id.rcycard)
    RecyclerView rcycard;
    @Bind(R.id.llcardtwo)
    LinearLayout llcardtwo;
    @Bind(R.id.cardone)
    EditText cardone;
    @Bind(R.id.pwdone)
    EditText pwdone;
    @Bind(R.id.cardtwo)
    EditText cardtwo;
    @Bind(R.id.pwdtwo)
    EditText pwdtwo;
    @Bind(R.id.delete)
    ImageView delete;
    private int goods_id;
    private String order_details;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_rechargeable_recovery;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });

        tvTitles.setText("卡回收");
        rcycard.setLayoutManager(new GridLayoutManager(this, 3));
        //卡类
        goods_type(3, 0);

    }

    public void goods_type(int classify_id, int parent_id) {
        ApiInterface.ApiFactory.createApi().goods_type(classify_id, parent_id).enqueue(new Callback<GoodsTypeEntity>() {
            @Override
            public void onResponse(Call<GoodsTypeEntity> call, Response<GoodsTypeEntity> response) {
                if (response.body().getCode() == 200) {
                    List<GoodsTypeEntity.ResultBean> resultBeanList = response.body().getResult();
                    if (resultBeanList.size() > 0) {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < resultBeanList.size(); i++) {
                            list.add(resultBeanList.get(i).getType_name());
                        }

                        Cardclass.attachDataSource(list);
                        goods2_type(3, resultBeanList.get(0).getId());

                        Cardclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                goods2_type(3, resultBeanList.get(position).getId());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<GoodsTypeEntity> call, Throwable t) {

            }
        });
    }


    public void goods2_type(int classify_id, int parent_id) {
        ApiInterface.ApiFactory.createApi().goods_type(classify_id, parent_id).enqueue(new Callback<GoodsTypeEntity>() {
            @Override
            public void onResponse(Call<GoodsTypeEntity> call, Response<GoodsTypeEntity> response) {
                if (response.body().getCode() == 200) {
                    List<GoodsTypeEntity.ResultBean> resultBeanList = response.body().getResult();
                    if (resultBeanList.size() > 0) {
                        List<String> list3 = new ArrayList<>();

                        for (int i = 0; i < resultBeanList.size(); i++) {
                            list3.add(resultBeanList.get(i).getType_name());
                        }
                        CardType.attachDataSource(list3);

                        goods_config(resultBeanList.get(0).getId());

                        CardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                goods_config(resultBeanList.get(position).getId());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

                    }


                }
            }

            @Override
            public void onFailure(Call<GoodsTypeEntity> call, Throwable t) {

            }
        });

    }


    //获取商品配置
    public void goods_config(int id) {
        ApiInterface.ApiFactory.createApi().goods_config(id).enqueue(new Callback<GoodsConfigEntity>() {
            @Override
            public void onResponse(Call<GoodsConfigEntity> call, Response<GoodsConfigEntity> response) {
                if (response.body().getCode() == 200) {
                    List<GoodsConfigEntity.ResultBean> resultBeanList = response.body().getResult();
                    if (resultBeanList.size() > 0) {
                        CommodityallocationAdapter commodityallocationAdapter = new CommodityallocationAdapter(RechargeableRecoveryActivity.this, resultBeanList);
                        rcycard.setAdapter(commodityallocationAdapter);
                        commodityallocationAdapter.setOnItemClickListener((view, position) -> {
                            goods_id = resultBeanList.get(position).getId();
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<GoodsConfigEntity> call, Throwable t) {

            }
        });
    }


    @SuppressLint("WrongConstant")
    @OnClick({R.id.cofimOrder, R.id.llcardtwo, R.id.lladd_more,R.id.delete})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.lladd_more:

                if (llcardtwo.getVisibility() == 0) {
                    ToastUtil.show("最多只允许添加两次", 200);
                    return;
                } else {
                    llcardtwo.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.cofimOrder:


                if (cardone.getText().toString() != null && TextUtils.isEmpty(cardone.getText().toString())) {
                    ToastUtil.show("请输入卡号", 200);
                    return;
                }
                if (pwdone.getText().toString() != null && TextUtils.isEmpty(pwdone.getText().toString())) {
                    ToastUtil.show("请输入密码", 200);
                    return;
                }
                if (llcardtwo.getVisibility() == View.VISIBLE) {


                    if (cardtwo.getText().toString() != null && TextUtils.isEmpty(cardtwo.getText().toString())) {
                        ToastUtil.show("请输入卡号", 200);
                        return;
                    }
                    if (pwdtwo.getText().toString() != null && TextUtils.isEmpty(pwdtwo.getText().toString())) {
                        ToastUtil.show("请输入密码", 200);
                        return;
                    }
                    if (UiUtils.isFastClick()){
                        order_details = "[{\"no\":" + cardone.getText().toString() + ",\"pass\":" + pwdone.getText().toString() + "},{\"no\":" + cardtwo.getText().toString() + ",\"pass\":" + pwdtwo.getText().toString() + "}]";
                    }
                } else {
                    if (UiUtils.isFastClick()){
                        order_details = "[{\"no\":" + cardone.getText().toString() + ",\"pass\":" + pwdone.getText().toString() + "}]";
                    }
                }

                order_add(goods_id, order_details, "CZ");
                break;
            case R.id.llcardtwo:
                llcardtwo.setVisibility(View.VISIBLE);
                break;
            case R.id.delete:
                llcardtwo.setVisibility(View.GONE);
                break;

        }


    }


    public void order_add(int goods_id, String order_details, String prefix) {
        ApiInterface.ApiFactory.createApi().orders_add(goods_id, order_details, prefix).enqueue(new Callback<OrdersAddEntity>() {
            @Override
            public void onResponse(Call<OrdersAddEntity> call, Response<OrdersAddEntity> response) {
                if (response.body().getCode() == 200) {
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                    NoticeObserver.getInstance().notifyObservers(Constants.HEADIMGURL);
                    ToastUtil.show("发布成功", 200);
                    finish();
                }else {
                    ToastUtil.show(response.body().getMessage().toString(),200);
                }
            }

            @Override
            public void onFailure(Call<OrdersAddEntity> call, Throwable t) {

            }
        });
    }
}



