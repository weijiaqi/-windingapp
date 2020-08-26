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
import app.winding.com.windingapp.adapter.TransactionDetailsAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.MoneyLogEntity;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//交易明细
public class TransactionDetailsActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.rcytrans)
    RecyclerView rcytrans;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    int page = 1;
    List<MoneyLogEntity.ResultBean.ListBean> resultBeanList = new ArrayList<>();
    private TransactionDetailsAdapter transactionDetailsAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_transaction_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v->{finish();});
        tvTitles.setText("交易明细");
        rcytrans.setLayoutManager(new LinearLayoutManager(this));
        money_log(page, 10);


        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            money_log(page, 10);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            money_log(page, 10);
            if (resultBeanList.size() >= 0) {
                rcytrans.smoothScrollToPosition(resultBeanList.size());
            }
            refreshLayout.finishLoadMore();
        });
    }


    public void money_log(int page, int limt) {
        ApiInterface.ApiFactory.createApi().money_log(page, limt).enqueue(new Callback<MoneyLogEntity>() {
            @Override
            public void onResponse(Call<MoneyLogEntity> call, Response<MoneyLogEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    if (page == 1) {
                        resultBeanList.clear();
                    }
                    if (response.body().getResult().getList().size() > 0) {
                        resultBeanList.addAll(response.body().getResult().getList());
                        if (transactionDetailsAdapter==null){
                            transactionDetailsAdapter=new TransactionDetailsAdapter(TransactionDetailsActivity.this,R.layout.item_transactiondetails,resultBeanList);
                          rcytrans.setAdapter(transactionDetailsAdapter);
                        }else {
                            transactionDetailsAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MoneyLogEntity> call, Throwable t) {

            }
        });
    }
}
