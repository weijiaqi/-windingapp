package app.winding.com.windingapp.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.PushInfoEntity;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PushInfoDetailActvity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.title)
    TextView title;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_push_info_detail_actvity;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("消息内容");
        get_info(getIntent().getExtras().getInt("id"));
    }


    public void get_info(int id) {
        ApiInterface.ApiFactory.createApi().get_info(id).enqueue(new Callback<PushInfoEntity>() {
            @Override
            public void onResponse(Call<PushInfoEntity> call, Response<PushInfoEntity> response) {
                if (response.body().getCode() == 200) {
                    title.setText(response.body().getResult().getTitle());
                    content.setText(response.body().getResult().getDetail());
                }
            }

            @Override
            public void onFailure(Call<PushInfoEntity> call, Throwable t) {

            }
        });
    }

}
