package app.winding.com.windingapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.ArticleDetailEntity;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDetailsActivity extends BaseActivity {

    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_notice_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v->{finish();});
        setDetail(Integer.parseInt(getIntent().getExtras().getString("id")));

    }

    public void setDetail(int id) {
        ApiInterface.ApiFactory.createApi().article_details(id).enqueue(new Callback<ArticleDetailEntity>() {
                                                                            @Override
                                                                            public void onResponse(Call<ArticleDetailEntity> call, Response<ArticleDetailEntity> response) {
                                                                                if (response.body().getCode() == 200) {
                                                                                    tvTitles.setText(response.body().getResult().getTitle());
                                                                                    content.setText(response.body().getResult().getContent());
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onFailure(Call<ArticleDetailEntity> call, Throwable t) {

                                                                            }
                                                                        }
        );
    }


}
