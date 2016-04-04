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
public class StaggerAdapter extends RecyclerViewAdapter {

    private ArrayList<Integer> mDelta = null;

    public StaggerAdapter(Context context, ArrayList<String> data, int layout) {
        super(context,data,layout);
        mDelta = new ArrayList<Integer>();
        int len = data.size();
        for (int i = 0; i < len; i++) {
            mDelta.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mDelta.get(position);
        holder.itemView.setLayoutParams(lp);
        holder.tv.setText(mData.get(position));
        setOnItemClickListenerInSub(holder);
        setOnItemLongClickListenerInSub(holder);
    }
}