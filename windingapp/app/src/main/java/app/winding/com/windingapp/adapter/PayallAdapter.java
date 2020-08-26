package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.MyGoodsOrderEntity;
import app.winding.com.windingapp.model.Constants;

import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.UiUtils;

/**
 * Created by Administrator on 2019/2/28.
 */

public class PayallAdapter extends RecycleAdapter<MyGoodsOrderEntity.ResultBean> {
    public PayallAdapter(Context context, int layoutId, List<MyGoodsOrderEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyGoodsOrderEntity.ResultBean resultBean) {
        holder.setText(R.id.titlename, resultBean.getType_name());
        holder.setText(R.id.detail, resultBean.getGoods_name());

        TextView date = holder.getConvertView().findViewById(R.id.date);
        date.setText(String.format(UiUtils.getText(R.string.Order_date), resultBean.getCreate_time()));
        TextView price = holder.getConvertView().findViewById(R.id.price);
        TextView des = holder.getConvertView().findViewById(R.id.Denomination);
        if (resultBean.getClassify_id() == 1 || resultBean.getClassify_id() == 2) {
            des.setText("充值号码:" + resultBean.getOrder_details());
        } else {
            des.setVisibility(View.GONE);
        }
        TextView status = holder.getConvertView().findViewById(R.id.status);
        int id = SharedPreferencesUtils.getInt(Constants.ID, 0);
        TextView selectdetails = holder.getConvertView().findViewById(R.id.selectdetails);
        ImageView pic = holder.getConvertView().findViewById(R.id.pic);
        if (resultBean.getIcon() == 1) {
            //转让
            price.setText(String.format(UiUtils.getText(R.string.prices), resultBean.getCash_money()));

            pic.setImageResource(R.mipmap.possession);
        } else {

            //求购
            pic.setImageResource(R.mipmap.wantbuy);
            price.setText(String.format(UiUtils.getText(R.string.prices), resultBean.getPay_price()));

        }

        switch (resultBean.getStatus()) {
            case 1:
                status.setText("待支付");
                if (resultBean.getClassify_id() == 3) {
                    status.setText("待审核");
                    selectdetails.setText("查看详情");
                } else {
                    selectdetails.setText("取消订单");
                }
                break;
            case 2:
                status.setText("已支付");
                if (id == resultBean.getUser_id()) {
                    if (resultBean.getClassify_id() != 3) {
                        selectdetails.setText("上传凭证");
                    } else {
                        status.setText("待确定");
                        selectdetails.setText("查看详情");
                    }
                } else {
                    selectdetails.setText("查看详情");
                }
                break;
            case 3:
                status.setText("已完成");
                if (resultBean.getClassify_id() == 1 || resultBean.getClassify_id() == 2) {
                    if (resultBean.getIcon() == 1) {
                        selectdetails.setText("售后处理");
                    } else {
                        selectdetails.setText("查看详情");
                    }
                } else {
                    selectdetails.setText("查看详情");
                }
                break;
            case 4:
                status.setText("已退款");
                selectdetails.setText("查看详情");
                break;
            case 5:
                status.setText("待确认");
                if (resultBean.getClassify_id() == 3) {
                    status.setText("审核失败");
                }
                if (id == resultBean.getT_user_id()) {
                    selectdetails.setText("确认完成");
                }
                break;
        }


    }
}

