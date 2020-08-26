package app.winding.com.windingapp.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.activity.TaskDetailActivity;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.adapter.TaskListAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseFragment;
import app.winding.com.windingapp.entity.GoodsListEntity;
import app.winding.com.windingapp.entity.TaskClassifylistEntity;
import app.winding.com.windingapp.entity.UserinfoEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.popuwindow.TaskPopuWindow;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaskFragment extends BaseFragment implements NoticeObserver.Observer {


    private TaskListAdapter taskListAdapter;
    @Bind(R.id.rcytask)
    RecyclerView rcytask;
    @Bind(R.id.lyc)
    LinearLayout lyc;
    @Bind(R.id.rlbase)
    RelativeLayout rlbase;
    @Bind(R.id.search)
    EditText search;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    int page = 1;
    int type = 0;
    int id;
    List<GoodsListEntity.ResultBean> goodsConfigEntityList = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_task;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) throws IOException {
        NoticeObserver.getInstance().addObserver(this);
        rcytask.setLayoutManager(new LinearLayoutManager(mContext));
        goods_list(0, 0, 0, "", page);
        ivRight.setBackgroundResource(R.mipmap.alltype);
        ivRight.setOnClickListener(v -> {
            goods_classify();

        });
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            switch (type) {
                case 0:
                    goods_list(0, 0, 0, "", page);
                    break;
                case 4:
                    goods_list(4, 0, 0, "", page);
                    break;
                case 5:
                    goods_list(5, 0, 0, "", page);
                    break;
                case 6:
                    goods_list(6, 0, 0, "", page);
                    break;
                case 7:
                    goods_list(id,0 , 0, "", page);
                    break;
                default:
                    goods_list(0, 0, 0, "", page);
                    break;
            }

            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            switch (type) {
                case 0:
                    goods_list(0, 0, 0, "", page);
                    break;
                case 4:
                    goods_list(4, 0, 0, "", page);
                    break;
                case 5:
                    goods_list(5, 0, 0, "", page);
                    break;
                case 6:
                    goods_list(6, 0, 0, "", page);
                    break;

                case 7:
                    goods_list(id,0 , 0, "", page);
                    break;
                default:
                    goods_list(0, 0, 0, "", page);
                    break;
            }
            if (goodsConfigEntityList.size() >= 0) {
                rcytask.smoothScrollToPosition(goodsConfigEntityList.size());
            }
            refreshLayout.finishLoadMore();
        });
        search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                page = 1;

                switch (type) {

                    case 4:
                        goods_list(4, 0, 0, search.getText().toString(), page);
                        break;
                    case 5:
                        goods_list(5, 0, 0, search.getText().toString(), page);
                        break;
                    case 6:
                        goods_list(6, 0, 0, search.getText().toString(), page);
                        break;
                    case 7:
                        goods_list(id, 0, 0, "", page);
                        break;
                    default:
                        goods_list(0, 0, 0, search.getText().toString(), page);
                        break;
                }

                UiUtils.hideSoftKeyboard(getActivity());

            }
            return false;
        });
    }


    public void goods_classify() {
        ApiInterface.ApiFactory.createApi().task_classify_list().enqueue(new Callback<TaskClassifylistEntity>() {
            @Override
            public void onResponse(Call<TaskClassifylistEntity> call, Response<TaskClassifylistEntity> response) {
                if (response.body().getCode() == 200) {
                    List<TaskClassifylistEntity.ResultBean> resultBeanList = response.body().getResult();
                    if (resultBeanList.size() > 0) {
                        new TaskPopuWindow(getActivity(), resultBeanList).showPopupWindow(rlbase);
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskClassifylistEntity> call, Throwable t) {

            }
        });
    }

    //提示消息
    public void showToast(int str) {
        type = str;
        goods_list(str, 0, 0, "", page);
    }


    //任务列表
    public void goods_list(int classify_id, int type_id, int supplier_id, String good_name, int page) {
        ApiInterface.ApiFactory.createApi().goods_list(classify_id, type_id, supplier_id, good_name, page).enqueue(new Callback<GoodsListEntity>() {
            @Override
            public void onResponse(Call<GoodsListEntity> call, Response<GoodsListEntity> response) {
                if (response.body().getCode() == 200) {
                    if (page == 0 || page == 1) {
                        goodsConfigEntityList.clear();
                    }
                    if (response.body().getResult().size() > 0) {
                        goodsConfigEntityList.addAll(response.body().getResult());
                        if (taskListAdapter == null) {
                            taskListAdapter = new TaskListAdapter(mContext, R.layout.item_tasklist, goodsConfigEntityList);
                            rcytask.setAdapter(taskListAdapter);
                        }else {
                            taskListAdapter.notifyDataSetChanged();
                        }
                    } else {
                        if (page == 0 || page == 1) {
                            taskListAdapter = new TaskListAdapter(mContext, R.layout.item_tasklist, goodsConfigEntityList);
                            rcytask.setAdapter(taskListAdapter);
                            taskListAdapter.notifyDataSetChanged();
                        }
                    }
                    taskListAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", goodsConfigEntityList.get(position).getId());
                            JumpActivityUtil.launchActivity(mContext, TaskDetailActivity.class, bundle);
                        }

                        @Override
                        public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                            return false;
                        }
                    });
                } else if (response.body().getCode() == 21818) {
                    SharedPreferencesUtils.putString(Constants.TOKEN, "");
                    ToastUtil.show("登录失效,请重新登录!", 200);
                    JumpActivityUtil.launchActivity(mContext, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<GoodsListEntity> call, Throwable t) {

            }
        });

    }


    @Override
    public <T> void update(int what, T t) {
        if (what == Constants.UPDATETASKSTATUS) {
            page = 1;
            id = 0;
            goods_list(0, 0, 0, "", page);
        } else if (what == Constants.UPDATETYPE) {
            String result = (String) t;
            id = Integer.parseInt(result);
            page = 1;
            type = 7;
            goods_list(id, 0, 0, "", page);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoticeObserver.getInstance().removeObserver(this);
    }
}


