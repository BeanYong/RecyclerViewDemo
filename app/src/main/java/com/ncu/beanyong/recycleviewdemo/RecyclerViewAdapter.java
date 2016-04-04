package com.ncu.beanyong.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BeanYong on 2015/11/6.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater mInflater = null;
    protected ArrayList<String> mData = null;
    private int mLayoutId = 0;

    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;

    public interface OnItemClickListener {
        void onClick(View itemView, int position);
    }

    public interface OnItemLongClickListener {
        void onLongClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public RecyclerViewAdapter(Context context, ArrayList<String> data, int layout) {
        mInflater = LayoutInflater.from(context);
        mData = data;
        mLayoutId = layout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        setOnItemClickListenerInSub(holder);
        setOnItemLongClickListenerInSub(holder);
        holder.tv.setText(mData.get(position));
    }

    public void setOnItemClickListenerInSub(final RecyclerView.ViewHolder holder){
        if(mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onClick(v, layoutPosition);
                }
            });
        }
    }

    public void setOnItemLongClickListenerInSub(final RecyclerView.ViewHolder holder){
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemLongClickListener.onLongClick(v, layoutPosition);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(int pos) {
        mData.add(pos, "insert one");
        notifyItemInserted(pos);
    }

    public void deleteItem(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}