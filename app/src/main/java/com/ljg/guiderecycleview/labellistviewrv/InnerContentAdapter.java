package com.ljg.guiderecycleview.labellistviewrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ljg.guiderecycleview.R;
import com.ljg.guiderecycleview.labellistviewrv.entities.CommonBaseBean;

import java.util.List;


/**
 * @author : ljg on @time 2018/10/16 17:59
 * @email : liujga@enn.cn
 * @desscribe :
 */
public class InnerContentAdapter extends BaseAdapter {
    private Context context;
    private List<CommonBaseBean> datalist = null;

    private IBaseItemClickListener itemClickListener;

    public InnerContentAdapter(Context context, List<CommonBaseBean> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    public void setList(List<CommonBaseBean> list) {
        this.datalist = list;
        notifyDataSetChanged();
    }

    public List<CommonBaseBean> getList() {
        return datalist;
    }

    public void addlist(List<CommonBaseBean> list){
        this.datalist.addAll(list);
        notifyDataSetChanged();

    }

    public void clearList() {
        this.datalist.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.month_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.rv_item= convertView.findViewById(R.id.rv_item);
            viewHolder.tv_name=convertView.findViewById(R.id.tv_name);
            viewHolder.tv_tools_item_value_unit=convertView.findViewById(R.id.tv_tools_item_value_unit);
            viewHolder.tv_tools_item_value=convertView.findViewById(R.id.tv_tools_item_value);
            viewHolder.tv_select=convertView.findViewById(R.id.tv_select);
            viewHolder.v_divider=convertView.findViewById(R.id.v_divider);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final CommonBaseBean baseBean = datalist.get(position);

        if(baseBean.name == null ||"".equals(baseBean.name.trim())) {
            viewHolder.tv_name.setText("");
        } else {
            viewHolder.tv_name.setText(baseBean.name);
        }
        if(baseBean.value== null ||"".equals(baseBean.value.trim())) {
            viewHolder.tv_tools_item_value.setText("");
        } else {
            viewHolder.tv_tools_item_value.setText(baseBean.value);
        }

        if(baseBean.unit == null ||"".equals(baseBean.unit.trim())) {
            viewHolder.tv_tools_item_value_unit.setText("");
        } else {
            viewHolder.tv_tools_item_value_unit.setText(baseBean.unit);
        }

        if(baseBean.isHaveDivide){
            viewHolder.v_divider.setVisibility(View.VISIBLE);
        }else{
            viewHolder.v_divider.setVisibility(View.INVISIBLE);
        }

        /*if(position==datalist.size()-1){
            viewHolder.v_divider.setVisibility(View.VISIBLE);
        }else{
            viewHolder.v_divider.setVisibility(View.INVISIBLE);
        }*/

        viewHolder.rv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v,position,baseBean.isSelect);
            }
        });


        return convertView;
    }

    class ViewHolder {
        public RelativeLayout rv_item;
        public TextView tv_name;
        public TextView tv_tools_item_value_unit;
        public TextView tv_tools_item_value;
        public ImageView tv_select;
        public View v_divider;
    }


    /** 设置监听点击的item中ListView的位置position */
    public void setOnListViewItemClickListener(IBaseItemClickListener listener) {
        itemClickListener = listener;
    }

    public interface IBaseItemClickListener {
        /**
         * Item 普通点击
         */
        void onItemClick(View view, int postion, Boolean isSelect);
    }


}
