package app.winding.com.windingapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.MypointsAdapter;
import app.winding.com.windingapp.adapter.PayAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseFragment;
import app.winding.com.windingapp.entity.InviteListEntity;
import app.winding.com.windingapp.entity.MyInviteEntity;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 邀请列表
 */

public class PayFragment extends BaseFragment {
    @Bind(R.id.sum)
    TextView sum;
    @Bind(R.id.ryinvation)
    RecyclerView ryinvation;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int page = 1;
    List<InviteListEntity.ResultBean.ListBean> resultBeanList=new ArrayList<>();
    MypointsAdapter mypointsAdapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.pay_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) throws IOException {

        my_invite();
        ryinvation.setLayoutManager(new LinearLayoutManager(mContext));
        invite_list(page);

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            invite_list(page);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            invite_list(page);
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
                    sum.setText(String.format(UiUtils.getText(R.string.invation_sum), response.body().getResult().getData().getCount()));

                }
            }

            @Override
            public void onFailure(Call<MyInviteEntity> call, Throwable t) {

            }
        });
    }





    public void invite_list(int page) {
        ApiInterface.ApiFactory.createApi().invite_list(page).enqueue(new Callback<InviteListEntity>() {
            @Override
            public void onResponse(Call<InviteListEntity> call, Response<InviteListEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    if (page == 1) {
                        resultBeanList.clear();
                    }
                    if (response.body().getResult().getList().size() > 0) {
                        resultBeanList.addAll(response.body().getResult().getList());
                        if (mypointsAdapter == null) {
                            mypointsAdapter = new MypointsAdapter(mContext, R.layout.item_mypoints, resultBeanList);
                            ryinvation.setAdapter(mypointsAdapter);
                        } else {
                            mypointsAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<InviteListEntity> call, Throwable t) {

            }
        });
    }
}
