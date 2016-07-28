package com.zhiyicx.zhibo.zbsmartlivesdkdemo.adapter;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhiyicx.zhibo.zbsmartlivesdkdemo.R;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder.BaseHolder;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder.FooterView1Holder;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder.FooterView2Holder;
import com.zhiyicx.zhibo.zbsmartlivesdkdemo.holder.FooterView3Holder;

import java.util.List;

/**
 * Created by jess on 2015/11/27.
 */
public abstract class MoreAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {
    protected List<T> mInfos;
    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private BaseHolder<T> mHolder;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER1 = 1;
    private static final int TYPE_FOOTER2 = 2;
    private static final int TYPE_FOOTER3 = 3;

    private boolean mShowFooter = false;

    public MoreAdapter(List<T> infos) {
        super();
        this.mInfos = infos;
    }

    /**
     * 创建Hodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
            mHolder = getHolder(view);
            mHolder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
                @Override
                public void onViewClick(View view, int position) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, mInfos.get(position));
                    }
                }
            });

            return mHolder;
        }
        else if (viewType == TYPE_FOOTER1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer1, null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    140);
            view.setLayoutParams(params);
            ((AnimationDrawable) ((ImageView) view.findViewById(R.id.tv_load_more)).getDrawable()).start();

            return new FooterView1Holder(view);
        }
        else if (viewType == TYPE_FOOTER2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer2, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                  140));
            return new FooterView2Holder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
            return new FooterView3Holder(view);
        }

    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseHolder<T> holder, int position) {
        if (holder instanceof FooterView1Holder
                || holder instanceof FooterView2Holder
                || holder instanceof FooterView3Holder
                || position >= mInfos.size()) {
            return;
        }
        holder.setData(mInfos.get(position));
    }

    /**
     * 数据的个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 2 : 0;
        begin = mInfos.size() % 2 != 0 ? begin + 1 : begin;//总数为奇数就+1
        if (!mShowFooter) {//不显示加载更多view
            begin = 0;
        }
        if (mInfos == null) {
            return begin;
        }
        return mInfos.size() + begin;
    }


    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (!mShowFooter) {
            return TYPE_ITEM;
        }

        if (mInfos.size() % 2 != 0) {
            if (position + 3 == getItemCount()) {
                return TYPE_FOOTER3;
            }
        }

        if (position + 2 == getItemCount()) {
            return TYPE_FOOTER1;
        }
        else if (position + 1 == getItemCount()) {
            return TYPE_FOOTER2;
        }
        else {
            return TYPE_ITEM;
        }
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    /**
     * 获得item的数据
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mInfos == null ? null : mInfos.get(position);
    }

    /**
     * 子类实现提供holder
     *
     * @param v
     * @return
     */
    public abstract BaseHolder<T> getHolder(View v);


    /**
     * 提供Item的布局
     *
     * @return
     */
    public abstract int getLayoutId();


    public interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(View view, T data);
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
