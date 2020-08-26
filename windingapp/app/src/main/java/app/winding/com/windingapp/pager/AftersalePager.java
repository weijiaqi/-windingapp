package app.winding.com.windingapp.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.OrderDetailsActivity;
import app.winding.com.windingapp.adapter.PayallAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.entity.MyGoodsOrderEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.NoticeObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 售后中
 */

public class AftersalePager extends BasePager implements NoticeObserver.Observer {

    private RecyclerView rcypayall;
    private PayallAdapter payallAdapter;
    int page = 1;
    int id=0;
    private SmartRefreshLayout refreshLayout;
    List<MyGoodsOrderEntity.ResultBean> resultBeanList = new ArrayList<>();

    public AftersalePager(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater layoutInflater) {
        NoticeObserver.getInstance().addObserver(this);
        View view = layoutInflater.inflate(R.layout.aftersale_pager, null);
        rcypayall = view.findViewById(R.id.rcypayall);
        rcypayall.setLayoutManager(new LinearLayoutManager(context));
        refreshLayout = view.findViewById(R.id.refreshLayout);


        my_goods_order(0, 0, 0, "", 4, page);


        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;

            if (id==0){
                my_goods_order(0, 0, 0, "", 4, page);
            }else {
                my_goods_order(id, 0, 0, "", 4, page);
            }
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            if (id==0){
                my_goods_order(0, 0, 0, "", 4, page);
            }else {
                my_goods_order(id, 0, 0, "", 4, page);
            }
            if (resultBeanList.size() >= 0) {
                rcypayall.smoothScrollToPosition(resultBeanList.size());
            }
            refreshLayout.finishLoadMore();
        });
        return view;
    }

    @Override
    public <DATA> void initData(int type, DATA data) {

    }


    public void my_goods_order(int classify_id, int type_id, int supplier_id, String good_name, int status, int page) {
        ApiInterface.ApiFactory.createApi().my_goods_order(classify_id, type_id, supplier_id, good_name, status, page).enqueue(new Callback<MyGoodsOrderEntity>() {
            @Override
            public void onResponse(Call<MyGoodsOrderEntity> call, Response<MyGoodsOrderEntity> response) {
                if (response.body().getCode() == 200) {
                    if (page == 1) {
                        resultBeanList.clear();
                    }
                    if (response.body().getResult().size() > 0) {
                        resultBeanList.addAll(response.body().getResult());
                        if (payallAdapter == null) {
                            payallAdapter = new PayallAdapter(context, R.layout.item_payall, resultBeanList);
                            rcypayall.setAdapter(payallAdapter);
                        } else {
                            payallAdapter.notifyDataSetChanged();
                        }
                    }else {
                        if (page==1||page==0){
                            payallAdapter = new PayallAdapter(context, R.layout.item_payall, resultBeanList);
                            rcypayall.setAdapter(payallAdapter);
                        }
                    }



                    payallAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                            Bundle bundle = new Bundle();
                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                            JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                        }

                        @Override
                        public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                            return false;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MyGoodsOrderEntity> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoticeObserver.getInstance().removeObserver(this);
    }

    @Override
    public <T> void update(int what, T t) {
        if (what == Constants.UPDATEPAYALL) {
            page = 1;
            id=0;
            my_goods_order(0, 0, 0, "", 4, page);
        } else if (what == Constants.SELECTORDERTYPE) {
            String result = (String) t;
            page = 1;
            id=Integer.parseInt(result);
            my_goods_order(id, 0, 0, "", 4, page);
        }
    }
}
