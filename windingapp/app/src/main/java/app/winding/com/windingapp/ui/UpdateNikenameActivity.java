package app.winding.com.windingapp.ui;

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
import app.winding.com.windingapp.entity.UpdateNameEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateNikenameActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.nikenmae)
    EditText nikenmae;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_update_nikename;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("修改昵称");
        tvRight.setText("保存");
        tvRight.setTextSize(12);
        tvRight.setTextColor(getResources().getColor(R.color.color_ff));
        nikenmae.setText(getIntent().getExtras().getString("name"));

    }


    @OnClick({R.id.error, R.id.tv_right})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.error:
                nikenmae.getText().clear();
                break;
            case R.id.tv_right:
                if (nikenmae.getText().toString().trim() == null || TextUtils.isEmpty(nikenmae.getText().toString().trim())) {
                    ToastUtil.show("昵称不能为空!", 200);
                    return;
                }
                update_name(nikenmae.getText().toString());
                break;
        }
    }


    public void update_name(String name) {
        ApiInterface.ApiFactory.createApi().update_name(name).enqueue(new Callback<UpdateNameEntity>() {
            @Override
            public void onResponse(Call<UpdateNameEntity> call, Response<UpdateNameEntity> response) {
                if (response.body().getCode() == 200) {
                     ToastUtil.show("修改成功",200);
                     finish();
                    NoticeObserver.getInstance().notifyObservers(Constants.HEADIMGURL);
                }
            }

            @Override
            public void onFailure(Call<UpdateNameEntity> call, Throwable t) {

            }
        });
    }
}
