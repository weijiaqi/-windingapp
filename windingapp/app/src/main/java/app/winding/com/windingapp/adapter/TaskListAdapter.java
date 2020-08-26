package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.GoodsListEntity;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.LogUtil;
import app.winding.com.windingapp.util.UiUtils;


public class TaskListAdapter extends RecycleAdapter<GoodsListEntity.ResultBean> {
    public TaskListAdapter(Context context, int layoutId, List<GoodsListEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void convert(RecyclerViewHolder holder, GoodsListEntity.ResultBean resultBean) {
        if (resultBean.getGood_name().toString()!=null && !TextUtils.isEmpty(resultBean.getGood_name().toString())){
            holder.setText(R.id.title, resultBean.getGood_name().toString());
        }

        ImageView pic = holder.getConvertView().findViewById(R.id.pic);
        if (resultBean.getGood_img().size() > 0) {
            GlideUtils.loadImgWithGlide(resultBean.getGood_img().get(0).toString(), pic);
        }else{
            pic.setImageResource(R.mipmap.bitmaplogo);
        }



        holder.setText(R.id.date, resultBean.getCreate_time());
        holder.setText(R.id.type, resultBean.getClassify_name());
        TextView price = holder.getConvertView().findViewById(R.id.price);

        ImageView typestatus=holder.getConvertView().findViewById(R.id.typestatus);
        TextView num=holder.getConvertView().findViewById(R.id.num);

        if (resultBean.getIs_sell()==1){
            typestatus.setImageResource(R.mipmap.wantbuy);
            if (resultBean.getClassify_id()==6){
                num.setVisibility(View.VISIBLE);
                holder.setText(R.id.num, "数量:"+resultBean.getNum());
                Spannable spannable = new SpannableString("￥" + resultBean.getMoney().toString());
                spannable.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                price.setText(spannable);
            }else {
                num.setVisibility(View.VISIBLE);
                num.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
                holder.setText(R.id.num, resultBean.getMoney()+"");
                LogUtil.e("11111111111111",resultBean.getMoney());
                Spannable spannable = new SpannableString("￥" + resultBean.getPrice().toString());
                spannable.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                price.setText(spannable);
            }
        }else if (resultBean.getIs_sell()==2){
            typestatus.setImageResource(R.mipmap.possession);
            if (resultBean.getClassify_id()==6){
              num.setVisibility(View.GONE);
                Spannable spannable = new SpannableString("￥" + resultBean.getPrice().toString());
                spannable.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                price.setText(spannable);
            }else {
                num.setVisibility(View.VISIBLE);
                Spannable spannable = new SpannableString("￥" + resultBean.getPrice().toString());
                spannable.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                price.setText(spannable);
                num.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
                holder.setText(R.id.num, resultBean.getMoney()+"");
                LogUtil.e("11111111111111",resultBean.getMoney());
            }
        }
    }
}
