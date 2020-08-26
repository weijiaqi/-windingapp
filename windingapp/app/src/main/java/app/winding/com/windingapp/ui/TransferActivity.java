package app.winding.com.windingapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.GoodsAddEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import app.winding.com.windingapp.util.code.PickActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 转让电影票
 */
public class TransferActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.moviename)
    EditText moviename;
    @Bind(R.id.remark)
    EditText remark;
    @Bind(R.id.city)
    TextView city;
    @Bind(R.id.next)
    ImageView next;
    @Bind(R.id.writemoview)
    EditText writemoview;
    @Bind(R.id.Actualsell)
    EditText actualse;
    @Bind(R.id.cofimOrder)
    TextView cofimOrder;
    int id;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_transfer;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("卖电影票");
    }


    @OnClick({R.id.cofimOrder, R.id.rlcity})
    public void onlick(View view) {
        switch (view.getId()) {
            case R.id.cofimOrder:

                if (moviename.getText().toString().equals("") || TextUtils.isEmpty(moviename.getText().toString())) {
                    ToastUtil.show("请输入电影名称!", 200);
                    return;
                }
                if (remark.getText().toString().equals("") || TextUtils.isEmpty(remark.getText().toString())) {
                    ToastUtil.show("请详细的描述您需要备注的需求!", 200);
                    return;
                }
                if (writemoview.getText().toString().equals("") || TextUtils.isEmpty(writemoview.getText().toString())) {
                    ToastUtil.show("输入影院名称!", 200);
                    return;
                }

                if (actualse.getText().toString().equals("") || TextUtils.isEmpty(actualse.getText().toString())) {
                    ToastUtil.show("输入实际售价!", 200);
                    return;
                }

                if (city.getText().toString().equals("") || TextUtils.isEmpty(city.getText().toString())) {
                    ToastUtil.show("请选择城市!", 200);
                    return;
                }
                if (UiUtils.isFastClick()) {
                    goods_add(moviename.getText().toString(), writemoview.getText().toString(), "", 1, "", actualse.getText().toString(), actualse.getText().toString(), "", 6, 21, 42, 1, 1, 1, 2, remark.getText().toString(), "", "", "", id);
                }

                break;
            case R.id.rlcity:
                startActivityForResult(new Intent(getApplicationContext(), PickActivity.class), 111);
                break;

        }
    }


    public void goods_add(String good_name, String brief, String time, int num, String good_img, String price, String money, String details, int classify_id, int type_id, int config_id, int is_inside, int is_shelf, int supplier_id, int is_sell, String memo, String order_mobile, String order_name, String order_address, int city) {
        ApiInterface.ApiFactory.createApi().goods_add(good_name, brief, time, num, good_img, price, money, details, classify_id, type_id, config_id, is_inside, is_shelf, supplier_id, is_sell, memo, order_mobile, order_name, order_address, city).enqueue(new Callback<GoodsAddEntity>() {
            @Override
            public void onResponse(Call<GoodsAddEntity> call, Response<GoodsAddEntity> response) {
                if (response.body().getCode() == 200) {
                    DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_cofimorder, R.style.dialog, (contentView, utils) -> {
                        utils.setCancelable(false);
                        TextView btn_click = contentView.findViewById(R.id.btn_click);
                        btn_click.setOnClickListener(v -> {
                            finish();
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            utils.close();
                        });
                    });
                }
            }

            @Override
            public void onFailure(Call<GoodsAddEntity> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            city.setText(data.getStringExtra("name"));
            id = Integer.parseInt(data.getStringExtra("id"));

        }

    }

}
