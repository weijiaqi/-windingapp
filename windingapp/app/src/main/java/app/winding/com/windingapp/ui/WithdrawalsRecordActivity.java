package app.winding.com.windingapp.ui;

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
import app.winding.com.windingapp.adapter.PayAdapter;
import app.winding.com.windingapp.adapter.WithdrawalsRecordAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;

import app.winding.com.windingapp.entity.UserPresentListEntity;

import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import butterknife.Bind;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//提现记录
public class WithdrawalsRecordActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.rcyCashWithdrawal)
    RecyclerView rcyCashWithdrawal;
    WithdrawalsRecordAdapter withdrawalsRecordAdapter;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
   int page=1;
    List<UserPresentListEntity.ResultBean> resultBeanList = new ArrayList<>();
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_withdrawals_record;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("提现记录");
        rcyCashWithdrawal.setLayoutManager(new LinearLayoutManager(this));
        user_present_list(0,page);

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            user_present_list(0,page);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            user_present_list(0,page);
            if (resultBeanList.size() >= 0) {
                rcyCashWithdrawal.smoothScrollToPosition(resultBeanList.size());
            }
            refreshLayout.finishLoadMore();
        });
    }

    public void user_present_list(int status,int page) {

        ApiInterface.ApiFactory.createApi().user_present_list(status,page).enqueue(new Callback<UserPresentListEntity>() {
            @Override
            public void onResponse(Call<UserPresentListEntity> call, Response<UserPresentListEntity> response) {
                if (response.body().getCode() == 200) {

                    if (page == 1) {
                        resultBeanList.clear();
                    }

                    if (response.body().getResult().size() > 0) {
                        resultBeanList.addAll(response.body().getResult());
                        if (withdrawalsRecordAdapter == null) {
                            withdrawalsRecordAdapter = new WithdrawalsRecordAdapter(WithdrawalsRecordActivity.this, R.layout.item_withdrawalsrecord, resultBeanList);
                            rcyCashWithdrawal.setAdapter(withdrawalsRecordAdapter);
                        } else {
                            withdrawalsRecordAdapter.notifyDataSetChanged();
                        }
                    }

                }


            }

            @Override
            public void onFailure(Call<UserPresentListEntity> call, Throwable t) {

            }
        });
    }
}
