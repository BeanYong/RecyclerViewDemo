package com.ncu.beanyong.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class StaggerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView = null;
    private ArrayList<String> mData = null;
    private StaggerAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initViews();

        mAdapter = new StaggerAdapter(this, mData, R.layout.item);
        mRecyclerView.setAdapter(mAdapter);

        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(sglm);

        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View itemView, int position) {
                Toast.makeText(StaggerActivity.this, "click:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnItemLongClickListener(new RecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View itemView, int position) {
                mAdapter.deleteItem(position);
            }
        });

//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_rv);
    }

    private void initDatas() {
        mData = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mData.add((char) i + "");
        }
    }
}
