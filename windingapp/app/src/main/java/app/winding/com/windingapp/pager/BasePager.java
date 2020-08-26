package app.winding.com.windingapp.pager;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;


import app.winding.com.windingapp.R;
import app.winding.com.windingapp.app.MainApplication;
import app.winding.com.windingapp.ui.MultipleStatusView;


public abstract class BasePager {

    protected MultipleStatusView mMultipleStatusView;

    protected Context context;
    private LayoutInflater layoutInflater;

    protected Fragment mFragment;
    public Gson gson;


    private View view;

    public BasePager(Context context) {
        this(context, null);
    }

    public BasePager(Context context, Fragment mFragment) {
        this.context = context;
        this.mFragment = mFragment;
        gson = MainApplication.getContext().getSimpleGson();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = initView(layoutInflater);
    }


    //初始化布局
    public abstract View initView(LayoutInflater layoutInflater);

    /**
     * 传递数据
     *
     * @param type   0代表传递网络请求的数据，1代表传入的其他输入，如id
     * @param data
     * @param <DATA>
     */
    //初始化数据
    public abstract <DATA> void initData(int type, DATA data);

    public void onDestroy() {
    }

    public View getView() {
        return view;
    }

    //显示内容
    public void showContent() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showContent();
        }
    }

    //空数据状态
    public void showEmpty() {
        showEmpty("");
    }

    public void showEmpty(@StringRes int strRes) {
        showEmpty(context.getResources().getString(strRes));
    }

    public void showEmpty(String str) {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showEmpty();
            TextView tv =  mMultipleStatusView.getEmptyView().findViewById(R.id.empty_view_tv);
            if (!TextUtils.isEmpty(str)) {
                tv.setText(str);
            }
        }
    }

    //加载错误状态
    public void showError() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showError();
        }
    }

    //加载中状态
    public void showLoading() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showLoading();
        }
    }

    //无网络状态
    public void showNoNetwork() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showNoNetwork();
        }
    }
}
