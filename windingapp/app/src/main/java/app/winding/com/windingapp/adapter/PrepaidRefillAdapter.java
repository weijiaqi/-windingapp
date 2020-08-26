package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.FareEntity;
import app.winding.com.windingapp.entity.FareOrderListEntity;
import app.winding.com.windingapp.util.UiUtils;

/**
 * Created by Administrator on 2019/3/8.
 */

public class PrepaidRefillAdapter  extends RecyclerView.Adapter<PrepaidRefillAdapter.ViewHolder> {
    //1、定义一个集合，用来记录选中
    private List<Boolean> isClicks;
    private Context context;
    private List<FareEntity.ResultBean> list;
    //2、定义监听并设set方法
    private PrepaidRefillAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(PrepaidRefillAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public PrepaidRefillAdapter(Context context, List<FareEntity.ResultBean> list) {
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
    public PrepaidRefillAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_prepaid, viewGroup, false);
        PrepaidRefillAdapter.ViewHolder vh = new PrepaidRefillAdapter.ViewHolder(view);

        return vh;
    }

    //将数据与界面进行绑定的操作
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final PrepaidRefillAdapter.ViewHolder viewHolder, int position) {
        viewHolder.price.setText(list.get(position).getMoney());
        viewHolder.Settlementprice.setText( String.format(UiUtils.getText(R.string.price), list.get(position).getPrice()));

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
        viewHolder.itemView.setTag(viewHolder.lly);
        viewHolder.itemView.setTag(viewHolder.price);
        viewHolder.itemView.setTag(viewHolder.Settlementprice);
        //6、判断改变属性
        if (isClicks.get(position)) {
            viewHolder.lly.setBackgroundResource( R.drawable.electris_border);
            viewHolder.price.setTextColor(context.getColor(R.color.white));
            viewHolder.Settlementprice.setTextColor(context.getColor(R.color.white));
        } else {
            viewHolder.lly.setBackgroundResource(R.drawable.electri_border);
            viewHolder.price.setTextColor(context.getColor(R.color.color_ff0));
            viewHolder.Settlementprice.setTextColor(context.getColor(R.color.color_ff0));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView price;
        public TextView Settlementprice;
        public RelativeLayout lly;

        public ViewHolder(View view) {
            super(view);
            price = view.findViewById(R.id.price);
            Settlementprice = view.findViewById(R.id.Settlementprice);

            lly = (RelativeLayout) view.findViewById(R.id.lly);
        }
    }

    //7、定义点击事件回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}



