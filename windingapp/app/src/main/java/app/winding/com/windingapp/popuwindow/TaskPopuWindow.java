package app.winding.com.windingapp.popuwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.CheckAddrAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.entity.TaskClassifylistEntity;
import app.winding.com.windingapp.entity.UserinfoEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.NoticeObserver;


public class TaskPopuWindow extends PopupWindow {
    private View conentView, viewDismiss;
    private RecyclerView rvAddr;
    private TextView all;
    private CheckAddrAdapter addrAdapter;

    public TaskPopuWindow(Activity context, List<TaskClassifylistEntity.ResultBean> list) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.layout_type_view, null);
        rvAddr = conentView.findViewById(R.id.recycler_view);
        all = conentView.findViewById(R.id.all);
        all.setOnClickListener(v -> {
            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
            dismiss();
        });

        addrAdapter = new CheckAddrAdapter(context, R.layout.item_task, list);
        rvAddr.setLayoutManager(new GridLayoutManager(context,3));
        rvAddr.setAdapter(addrAdapter);
        addrAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                NoticeObserver.getInstance().notifyObservers(Constants.UPDATETYPE, list.get(position).getClassify_id() + "");
                dismiss();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = h / 3;
        rvAddr.setLayoutParams(layoutParams);

        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

}