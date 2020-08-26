package app.winding.com.windingapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.MyReleaseAdapter;
import app.winding.com.windingapp.adapter.PayallAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.MysGoodsEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyReleaseActivity extends BaseActivity implements NoticeObserver.Observer {

    @Bind(R.id.rcyRelease)
    RecyclerView rcyRelease;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    MyReleaseAdapter myReleaseAdapter;
    List<MysGoodsEntity.ResultBean> resultBeanList = new ArrayList<>();
    int page = 1;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_release;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        NoticeObserver.getInstance().addObserver(this);
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("我发布的");
        rcyRelease.setLayoutManager(new LinearLayoutManager(this));
        my_goods(0, 0, 0, "", "", 0, page);

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            my_goods(0, 0, 0, "", "", 0, page);
            refreshLayout.finishRefresh();
        });



        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            my_goods(0, 0, 0, "", "", 0, page);
            if (resultBeanList.size() >= 0) {
                rcyRelease.smoothScrollToPosition(resultBeanList.size());
            }
            refreshLayout.finishLoadMore();
        });

    }


    public void my_goods(int classify_id, int type_id, int supplier_id, String good_name, String brief, int status, int page) {
        ApiInterface.ApiFactory.createApi().my_goods(classify_id, type_id, supplier_id, good_name, brief, status, page).enqueue(new Callback<MysGoodsEntity>() {
            @Override
            public void onResponse(Call<MysGoodsEntity> call, Response<MysGoodsEntity> response) {
                if (response.body().getCode() == 200) {
                    if (page == 1) {
                        resultBeanList.clear();
                    }
                    if (response.body().getResult().size() > 0) {
                        resultBeanList.addAll(response.body().getResult());
                        if (myReleaseAdapter == null) {
                            myReleaseAdapter = new MyReleaseAdapter(MyReleaseActivity.this, R.layout.item_myrelease, resultBeanList);
                            rcyRelease.setAdapter(myReleaseAdapter);
                        } else {
                            myReleaseAdapter.notifyDataSetChanged();
                        }
                    } else {
                        if (page == 1 || page == 0) {
                            myReleaseAdapter = new MyReleaseAdapter(MyReleaseActivity.this, R.layout.item_myrelease, resultBeanList);
                            rcyRelease.setAdapter(myReleaseAdapter);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<MysGoodsEntity> call, Throwable t) {

            }
        });
    }

    @Override
    public <T> void update(int what, T t) {
        if (what == Constants.ADDCUUENCY) {

            my_goods(0, 0, 0, "", "", 0, page);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NoticeObserver.getInstance().removeObserver(this);
    }


}
