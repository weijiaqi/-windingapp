package app.winding.com.windingapp.popuwindow;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.util.AnimationUtils;

/**
 * Created by Administrator on 2019/3/28.
 */

public class InvitationPopwindow extends PopupWindow {

    private View view;
    private LinearLayout ll_invite_WX_chat;
    private LinearLayout ll_invite_WX_friend;
    private LinearLayout ll_invite_qq;
    private LinearLayout ll_invite_wb;
    private LinearLayout ll_invite_qq_space;
    private LinearLayout ll_off;

    public InvitationPopwindow(Context mContext, Activity activity, View.OnClickListener itemsOnClick) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.invitation_popwindow, null);
        ll_invite_WX_chat = view.findViewById(R.id.ll_invite_WX_chat);
        ll_invite_WX_friend = view.findViewById(R.id.ll_invite_WX_friend);
        ll_invite_qq = view.findViewById(R.id.ll_invite_qq);
        ll_invite_wb = view.findViewById(R.id.ll_invite_wb);
        ll_invite_qq_space = view.findViewById(R.id.ll_invite_qq_space);

        ll_off = view.findViewById(R.id.ll_off);
        ll_invite_WX_chat.setOnClickListener(itemsOnClick);
        ll_invite_WX_friend.setOnClickListener(itemsOnClick);
        ll_invite_qq.setOnClickListener(itemsOnClick);
        ll_invite_wb.setOnClickListener(itemsOnClick);
        ll_invite_qq_space.setOnClickListener(itemsOnClick);

        ll_off.setOnClickListener(itemsOnClick);
        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener((v, event) -> {

            int height = view.findViewById(R.id.ll_recordsdk).getTop();

            int y = (int) event.getY();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss();
                }
            }
            return true;
        });

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
        this.setOnDismissListener(() -> AnimationUtils.darkenBackground(activity, 1f));


    }
}
