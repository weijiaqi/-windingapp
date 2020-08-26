package app.winding.com.windingapp.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.PayallAdapter;
import app.winding.com.windingapp.adapter.PushInfoAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.PushEntity;
import app.winding.com.windingapp.util.JumpActivityUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//消息通知列表
public class PushInfoActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.rcypayall)
    RecyclerView rcypayall;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    PushInfoAdapter pushInfoAdapter;
    List<PushEntity.ResultBean> resultBeanList=new ArrayList<>();
    int page=1;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_push_info;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v->{finish();});
        tvTitles.setText("消息通知");
        rcypayall.setLayoutManager(new LinearLayoutManager(this));
        get_list(page);

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            get_list(page);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            get_list(page);
            if (resultBeanList.size() >= 0) {
                rcypayall.smoothScrollToPosition(resultBeanList.size());
            }
            refreshLayout.finishLoadMore();
        });
    }


     public void get_list(int page){
         ApiInterface.ApiFactory.createApi().get_list(page).enqueue(new Callback<PushEntity>() {
             @Override
             public void onResponse(Call<PushEntity> call, Response<PushEntity> response) {
                 if (response.body().getCode()==200){
                     if (response.body().getResult().size()>0){
                         if (pushInfoAdapter==null){
                             resultBeanList.addAll(response.body().getResult());
                             pushInfoAdapter=new PushInfoAdapter(PushInfoActivity.this,R.layout.item_pushinfo,resultBeanList);
                             rcypayall.setAdapter(pushInfoAdapter);
                         }else {
                             pushInfoAdapter.notifyDataSetChanged();
                         }

                     } else {
                         if (page == 1 || page == 0) {
                             pushInfoAdapter=new PushInfoAdapter(PushInfoActivity.this,R.layout.item_pushinfo,resultBeanList);
                             rcypayall.setAdapter(pushInfoAdapter);
                         }
                     }
                     pushInfoAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                         @Override
                         public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                             Bundle bundle=new Bundle();
                             bundle.putInt("id",resultBeanList.get(position).getId());
                             JumpActivityUtil.launchActivity(PushInfoActivity.this,PushInfoDetailActvity.class,bundle);
                         }

                         @Override
                         public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                             return false;
                         }
                     });

                 }
             }

             @Override
             public void onFailure(Call<PushEntity> call, Throwable t) {

             }
         });
     }
}
