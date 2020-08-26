package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.ElectricityEntity;
import app.winding.com.windingapp.entity.GoodsListEntity;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.UiUtils;

/**
 * Created by Administrator on 2019/3/30.
 */

public class tasksAdapter extends RecyclerView.Adapter<tasksAdapter.ViewHolder> {
    //1、定义一个集合，用来记录选中
    private List<Boolean> isClicks;
    private Context context;
    private List<GoodsListEntity.ResultBean> list;
    //2、定义监听并设set方法
    private tasksAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(tasksAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public tasksAdapter(Context context, List<GoodsListEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
        //3、为集合添加值
        isClicks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            isClicks.add(false);
        }
    }

    //创建新View，被LayoutManager所调用
    @Override
    public tasksAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tasklist, viewGroup, false);
        tasksAdapter.ViewHolder vh = new tasksAdapter.ViewHolder(view);

        return vh;
    }

    //将数据与界面进行绑定的操作
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final tasksAdapter.ViewHolder viewHolder, int position) {
        Spannable spannable = new SpannableString("￥" + list.get(position).getPrice().toString());
        spannable.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        viewHolder.price.setText(spannable);
        if (list.get(position).getGood_name().toString() != null && !TextUtils.isEmpty(list.get(position).getGood_name().toString())) {
            viewHolder.title.setText(list.get(position).getGood_name().toString());
        }

        if (list.get(position).getGood_img().size() > 0) {
            GlideUtils.loadImgWithGlide(list.get(position).getGood_img().get(0).toString(), viewHolder.pic);
        } else {
            viewHolder.pic.setImageResource(R.mipmap.bitmaplogo);
        }
        viewHolder.data.setText(list.get(position).getCreate_time());
        viewHolder.type.setText(list.get(position).getClassify_name());

        switch (list.get(position).getIs_sell()){
            case 1:
                viewHolder.typestatus.setImageResource(R.mipmap.wantbuy);
                if (list.get(position).getClassify_id()==6){
                    viewHolder. num.setText("数量:"+list.get(position).getNum());
                }else {
                    viewHolder. num.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
                    viewHolder. num.setText(list.get(position).getMoney());
                }
                break;
            case 2:
                viewHolder.typestatus.setImageResource(R.mipmap.possession);
                if (list.get(position).getClassify_id()==6){
                    viewHolder. num.setVisibility(View.GONE);
                }else {
                    viewHolder. num.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
                    viewHolder. num.setText(list.get(position).getMoney());
                }
                break;
            default:
                viewHolder. num.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
                viewHolder. num.setText(list.get(position).getMoney());
                break;
        }



        //4：设置点击事件
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(v -> {
                int position1 = viewHolder.getLayoutPosition(); // 1
                for (int i = 0; i < isClicks.size(); i++) {
                    isClicks.set(i, false);
                }
                isClicks.set(position1, true);
                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(viewHolder.itemView, position1); // 2
            });
        }
        //5、记录要更改属性的控件
        viewHolder.itemView.setTag(viewHolder.num);
        viewHolder.itemView.setTag(viewHolder.data);
        viewHolder.itemView.setTag(viewHolder.type);
        viewHolder.itemView.setTag(viewHolder.title);
        viewHolder.itemView.setTag(viewHolder.typestatus);
        viewHolder.itemView.setTag(viewHolder.pic);
        viewHolder.itemView.setTag(viewHolder.price);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView num,data,type;
        public TextView title;
        public ImageView typestatus, pic;
        public TextView price;

        public ViewHolder(View view) {
            super(view);
            num = view.findViewById(R.id.num);
            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            typestatus = view.findViewById(R.id.typestatus);
            pic = view.findViewById(R.id.pic);
            data=view.findViewById(R.id.date);
            type=view.findViewById(R.id.type);
        }
    }

    //7、定义点击事件回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}

