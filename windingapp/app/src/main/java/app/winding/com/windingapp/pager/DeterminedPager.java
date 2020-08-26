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
import app.winding.com.windingapp.adapter.MyConfirmedAdapter;
import app.winding.com.windingapp.adapter.PayallAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.entity.MyConfirmedEntity;
import app.winding.com.windingapp.entity.MyGoodsOrderEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.ui.AfterSaleTreatmentActivity;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 待确定
 */

public class DeterminedPager extends BasePager implements NoticeObserver.Observer {
    private RecyclerView rcypayall;
    private MyConfirmedAdapter payallAdapter;
    int page = 1;
    int id = 0;
    private SmartRefreshLayout refreshLayout;
    List<MyConfirmedEntity.ResultBean> resultBeanList = new ArrayList<>();

    public DeterminedPager(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater layoutInflater) {
        NoticeObserver.getInstance().addObserver(this);
        View view = layoutInflater.inflate(R.layout.determined_pager, null);
        rcypayall = view.findViewById(R.id.rcypayall);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        rcypayall.setLayoutManager(new LinearLayoutManager(context));
        my_confirmed_goods_order(page,0);
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            if (id == 0) {
                my_confirmed_goods_order(page,0);
            } else {
                my_confirmed_goods_order(page,id);
            }
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            if (id == 0) {
                my_confirmed_goods_order(page,0);
            } else {
                my_confirmed_goods_order(page,id);
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


    public void my_confirmed_goods_order(int page,int id) {
        ApiInterface.ApiFactory.createApi().my_confirmed_goods_order(page,id).enqueue(new Callback<MyConfirmedEntity>() {
            @Override
            public void onResponse(Call<MyConfirmedEntity> call, Response<MyConfirmedEntity> response) {
                if (response.body().getCode() == 200) {
                    if (page == 1) {
                        resultBeanList.clear();
                    }
                    if (response.body().getResult().size() > 0) {
                        resultBeanList.addAll(response.body().getResult());
                        if (payallAdapter == null) {
                            payallAdapter = new MyConfirmedAdapter(context, R.layout.item_payall, resultBeanList);
                            rcypayall.setAdapter(payallAdapter);
                        } else {
                            payallAdapter.notifyDataSetChanged();
                        }
                    } else {
                        if (page == 1 || page == 0) {
                            payallAdapter = new MyConfirmedAdapter(context, R.layout.item_payall, resultBeanList);
                            rcypayall.setAdapter(payallAdapter);
                        }
                    }


                    payallAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                            int id = SharedPreferencesUtils.getInt(Constants.ID, 0);

                            if (resultBeanList.get(position).getStatus() == 1) {

                                if (resultBeanList.get(position).getClassify_id() == 3) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                    JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                } else {
                                    if (id==resultBeanList.get(position).getT_user_id()){
                                        //待支付
                                        Bundle bundle = new Bundle();
                                        bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                        bundle.putInt("t_userid", resultBeanList.get(position).getT_user_id());
                                        bundle.putInt("id", 2);
                                        JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                    }else {
                                        //待支付
                                        Bundle bundle = new Bundle();
                                        bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                        bundle.putInt("t_userid", resultBeanList.get(position).getT_user_id());
                                        bundle.putInt("id", 6);
                                        JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                    }

                                }
                            } else if (resultBeanList.get(position).getStatus() == 3) {
                                if (resultBeanList.get(position).getClassify_id() == 1 || resultBeanList.get(position).getClassify_id() == 2) {
                                    if (resultBeanList.get(position).getIcon() == 1 ) {
                                        if (resultBeanList.get(position).getNew_voucher() == null){
                                            Bundle bundle = new Bundle();
                                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                            bundle.putString("voucher", resultBeanList.get(position).getVoucher());
                                            bundle.putInt("id",1);
                                            JumpActivityUtil.launchActivity(context, AfterSaleTreatmentActivity.class, bundle);
                                        }else {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                            bundle.putString("voucher", resultBeanList.get(position).getVoucher());
                                            bundle.putInt("id",2);
                                            JumpActivityUtil.launchActivity(context, AfterSaleTreatmentActivity.class, bundle);
                                        }

                                    } else {
                                        if (resultBeanList.get(position).getClassify_id() == 1 || resultBeanList.get(position).getClassify_id() == 2) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                            bundle.putInt("id", 4);
                                            bundle.putInt("Classify_id", resultBeanList.get(position).getClassify_id());
                                            JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                        } else {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                            JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                        }

                                    }
                                } else {
                                    if (resultBeanList.get(position).getClassify_id() == 1 || resultBeanList.get(position).getClassify_id() == 2) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                        bundle.putInt("id", 4);
                                        bundle.putInt("Classify_id", resultBeanList.get(position).getClassify_id());
                                        JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                    } else {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                        JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                    }
                                }
                            } else if (resultBeanList.get(position).getStatus() == 5) {
                                if (id == resultBeanList.get(position).getT_user_id()) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                    bundle.putInt("id", 3);
                                    JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                } else {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                    JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                }
                            } else if (resultBeanList.get(position).getStatus() == 2) {
                                if (id == resultBeanList.get(position).getUser_id()) {
                                    if (resultBeanList.get(position).getClassify_id() != 3) {

                                        if (resultBeanList.get(position).getClassify_id() == 6&&resultBeanList.get(position).getIs_sell()==2){
                                            Bundle bundle = new Bundle();
                                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                            bundle.putInt("id",5);
                                            JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                        }else {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                            bundle.putInt("id", 1);
                                            JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                        }
//                                        if (resultBeanList.get(position).getIcon()==1){
//                                            Bundle bundle = new Bundle();
//                                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
//                                            bundle.putInt("id",5);
//                                            JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
//                                        }else {
//                                            Bundle bundle = new Bundle();
//                                            bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
//                                            bundle.putInt("id", 1);
//                                            JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
//                                        }

                                    } else {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                        JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                    }
                                } else {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                    JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                                }
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putString("order_id", resultBeanList.get(position).getOrder_id());
                                JumpActivityUtil.launchActivity(context, OrderDetailsActivity.class, bundle);
                            }
                        }

                        @Override
                        public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                            return false;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MyConfirmedEntity> call, Throwable t) {

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
            id=0;
            page = 1;
            my_confirmed_goods_order(page,0);
        } else if (what == Constants.SELECTORDERTYPE) {
            String result = (String) t;
            id = Integer.parseInt(result);
            page = 1;
            my_confirmed_goods_order(page,id);
        }
    }
}
