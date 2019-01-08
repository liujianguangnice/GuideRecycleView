package com.ljg.guiderecycleview.labellistviewrv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ljg.guiderecycleview.R;
import com.ljg.guiderecycleview.labellistviewrv.entities.CommonBaseBean;
import com.ljg.guiderecycleview.labellistviewrv.entities.ProfessionItemModel;
import com.ljg.guiderecycleview.labellistviewrv.entities.RecycleItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ljg on @time 2018/10/16 17:59
 * @email : liujga@enn.cn
 * @desscribe :
 */

public class ProfessionRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;
    private List<RecycleItemModel> mData;
    private static final int TYPE_HEADER = 0; //正常item
    private static final int TYPE_FOTTER = 1; //footer item(最后一个item)


    public ProfessionRecycleAdapter(Context mContext, List<RecycleItemModel> datas) {
        this.mContext = mContext;
        this.mData = datas;
    }

    public void updateData(List<RecycleItemModel> datas) {
        mData.clear();
        if (datas != null) {
           mData = datas;
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        int s = mData.size()-1;
        if (position == s){
            return TYPE_FOTTER;
        }else{
            return TYPE_HEADER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate((viewType == TYPE_HEADER ? R.layout.item_my_tools_recycleview : R.layout.item_my_tools_foot_recycleview),parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        RecycleItemModel model = mData.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        if (model != null){
            if (model.titleName!=null){
                itemViewHolder.item_title.setText(model.titleName);
                itemViewHolder.item_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recycleSubViewAdapterListener.onRecycleItem(position);
                    }
                });
            }
            if (model.modelList!=null && model.modelList.size()>0){
                List<CommonBaseBean> listCommonBean= new ArrayList<>();
                CommonBaseBean bean;
                for (ProfessionItemModel mProfessionItemModel : model.modelList) {
                    bean = new CommonBaseBean(mProfessionItemModel.name, mProfessionItemModel.value, mProfessionItemModel.valueUnit, mProfessionItemModel.isSelected, mProfessionItemModel.isHaveDivider);
                    listCommonBean.add(bean);
                }
                InnerContentAdapter lvAdapter = new InnerContentAdapter(mContext,listCommonBean);
                itemViewHolder.lv_content.setAdapter(lvAdapter);
                lvAdapter.setOnListViewItemClickListener(new InnerContentAdapter.IBaseItemClickListener() {
                    @Override
                    public void onItemClick(View view, int postion, Boolean isSelect) {
                        recycleSubViewAdapterListener.onRecycleSubViewClick(position,postion);
                    }
                });
            }
        }
        return;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView item_title;
        private ListView lv_content;

        public ItemViewHolder(View view){
            super(view);
            item_title = view.findViewById(R.id.item_title);
            lv_content = view.findViewById(R.id.lv_content);
        }
    }




    private OnRecycleSubViewAdapterListener recycleSubViewAdapterListener;

    /** 设置监听点击RecycleView的item中GridView的位置position */
    public void setOnRecycleSubViewAdapterListener(OnRecycleSubViewAdapterListener listener) {
        recycleSubViewAdapterListener = listener;
    }

    public interface OnRecycleSubViewAdapterListener {

        /** 点击item跳转 */
        void onRecycleItem(int recyclePosition);

        /** 点击item的listview */
        void onRecycleSubViewClick(int recyclePosition, int lvPosition);

    }
}
