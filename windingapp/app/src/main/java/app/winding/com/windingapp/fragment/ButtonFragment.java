package app.winding.com.windingapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.PayAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseFragment;
import app.winding.com.windingapp.entity.MyInviteEntity;
import app.winding.com.windingapp.entity.RewardListEntity;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 邀请奖励
 */

public class ButtonFragment extends BaseFragment {
    @Bind(R.id.reward)
    TextView reward;
    @Bind(R.id.ryinvation)
    RecyclerView ryinvation;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int page = 1;
    List<RewardListEntity.ResultBean.ListBean> resultBeanList = new ArrayList<>();
    PayAdapter payAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.button_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) throws IOException {
        my_invite();
        ryinvation.setLayoutManager(new LinearLayoutManager(mContext));

        reward_list(page);
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            my_invite();
            reward_list(page);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            reward_list(page);
            if (resultBeanList.size() >= 0) {
                ryinvation.smoothScrollToPosition(resultBeanList.size());
            }
            refreshLayout.finishLoadMore();
        });
    }


    public void my_invite() {
        ApiInterface.ApiFactory.createApi().my_invite().enqueue(new Callback<MyInviteEntity>() {
            @Override
            public void onResponse(Call<MyInviteEntity> call, Response<MyInviteEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    if (response.body().getResult().getData().getMoney() != null && !TextUtils.isEmpty(response.body().getResult().getData().getMoney())) {
                        reward.setText(String.format(UiUtils.getText(R.string.reward), response.body().getResult().getData().getMoney()));
                    } else {
                        reward.setText(String.format(UiUtils.getText(R.string.reward), 0));
                    }

                }
            }

            @Override
            public void onFailure(Call<MyInviteEntity> call, Throwable t) {

            }
        });
    }


    public void reward_list(int page) {
        ApiInterface.ApiFactory.createApi().reward_list(page).enqueue(new Callback<RewardListEntity>() {
            @Override
            public void onResponse(Call<RewardListEntity> call, Response<RewardListEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    if (page == 1) {
                        resultBeanList.clear();
                    }
                    if (response.body().getResult().getList().size() > 0) {
                        resultBeanList.addAll(response.body().getResult().getList());
                        if (payAdapter == null) {
                            payAdapter = new PayAdapter(mContext, R.layout.item_mypoints, resultBeanList);
                            ryinvation.setAdapter(payAdapter);
                        } else {
                            payAdapter.notifyDataSetChanged();
                        }
                    }else {
                        payAdapter = new PayAdapter(mContext, R.layout.item_mypoints, resultBeanList);
                        ryinvation.setAdapter(payAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<RewardListEntity> call, Throwable t) {

            }
        });
    }
}
