package com.ljg.guiderecycleview.nouprv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ljg.guiderecycleview.R;
import com.ljg.guiderecycleview.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VideoControlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<VideoControlBean.DataBean> mDatas = new ArrayList<>();
    private VideoControlAdapter.IBaseItemClickListener itemClickListener;

    private RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.video_control_online)  //加载成功之前占位图
            .error(R.mipmap.video_control_online)    //加载错误之后的错误图
            //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
            .fitCenter()
            .skipMemoryCache(true)  //跳过内存缓存
            .diskCacheStrategy(DiskCacheStrategy.ALL)   //缓存所有版本的图像
            .diskCacheStrategy(DiskCacheStrategy.NONE)  //跳过磁盘缓存
            .diskCacheStrategy(DiskCacheStrategy.DATA)  //只缓存原来分辨率的图片
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)  //只缓存最终的图片
            ;

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_EMPTY = 1;
    public static final int VIEW_TYPE_ERROR = 2;

    private int view_type = 1;

    public VideoControlAdapter(Context context) {
        this.mContext = context;
    }

    public void setmDatas(List<VideoControlBean.DataBean> mDatas) {
        view_type = VIEW_TYPE_ITEM;
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void setEmptyView() {
        this.mDatas.clear();
        view_type = VIEW_TYPE_EMPTY;
        notifyDataSetChanged();
    }
    public void setErrorView() {
        this.mDatas.clear();
        view_type = VIEW_TYPE_ERROR;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_EMPTY) {
            View emptyView = LayoutInflater.from(mContext).inflate(R.layout.rv_empty_view_default, parent, false);
            return new EmptyViewHolder(emptyView);
        }

        if (viewType == VIEW_TYPE_ERROR) {
            View emptyView = LayoutInflater.from(mContext).inflate(R.layout.rv_error_view_default, parent, false);
            return new ErrorViewHolder(emptyView);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.video_control_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(mDatas!=null&&mDatas.size()<1){
            return;
        }
        if (holder instanceof MyViewHolder) {
            MyViewHolder hd = (MyViewHolder) holder;
            VideoControlBean.DataBean beanTmp = mDatas.get(position);

            hd.tv_station_name.setText(beanTmp.getStationName());
            hd.tv_video_name.setText(beanTmp.getDeviceName()+"-("+beanTmp.getDeviceId()+")");

            //0离线 1在线
            if (beanTmp.getStatus()==0) {
                hd.tv_station_line_status.setSelected(false);
                hd.tv_station_line_status.setText("离线");
                hd.iv_station_icon.setImageResource(R.mipmap.video_control_offline);
                hd.iv_station_icon_bg.setVisibility(View.VISIBLE);
            } else {
                hd.tv_station_line_status.setSelected(true);
                hd.tv_station_line_status.setText("在线");

                hd.iv_station_icon_bg.setVisibility(View.VISIBLE);
                if (StringUtils.isNotNull(beanTmp.getPicUrl())) {
                    hd.iv_station_icon_bg.setVisibility(View.INVISIBLE);
                    Glide.with(mContext)
                            .load(beanTmp.getPicUrl())
                            .apply(options)
                            .into(hd.iv_station_icon);
                } else {
                    hd.iv_station_icon.setImageResource(R.mipmap.video_control_online);
                }
            }


            hd.root_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, position, false);
                }
            });
        }


        if (holder instanceof ErrorViewHolder) {
            ErrorViewHolder hd = (ErrorViewHolder) holder;
            hd.mErrorTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onErrorClick();
                }
            });

        }
    }


    @Override
    public int getItemViewType(int position) {
        return view_type;
    }


    @Override
    public int getItemCount() {
        if (mDatas.size() == 0) {
            return 1;
        }
        //如果不为0，按正常的流程跑
        return mDatas.size();

    }

    /**
     * 设置监听点击的item中ListView的位置position
     */
    public void setOnViewItemClickListener(VideoControlAdapter.IBaseItemClickListener listener) {
        itemClickListener = listener;
    }

    public interface IBaseItemClickListener {
        /**
         * Item 普通点击
         */
        void onItemClick(View view, int position, Boolean isSelect);

        void onItemInnerViewClick(View view, int position, Boolean isSelect);

        void onErrorClick();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout root_view;
        ImageView iv_station_icon;
        TextView iv_station_icon_bg;
        TextView tv_station_name;
        TextView tv_station_line_status;
        TextView tv_video_name;

        public MyViewHolder(View itemView) {
            super(itemView);

            root_view = itemView.findViewById(R.id.root_view);
            iv_station_icon = itemView.findViewById(R.id.iv_station_icon);
            iv_station_icon_bg = itemView.findViewById(R.id.iv_station_icon_bg);
            tv_station_name = itemView.findViewById(R.id.tv_station_name);
            tv_station_line_status = itemView.findViewById(R.id.tv_station_line_status);
            tv_video_name = itemView.findViewById(R.id.tv_video_name);

        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        TextView mEmptyTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            mEmptyTextView = itemView.findViewById(R.id.tv_no_date);
        }
    }

    class ErrorViewHolder extends RecyclerView.ViewHolder {
        TextView mErrorTextView;

        public ErrorViewHolder(View itemView) {
            super(itemView);
            mErrorTextView = itemView.findViewById(R.id.tv_error_view_refresh_btn);
        }
    }

}





