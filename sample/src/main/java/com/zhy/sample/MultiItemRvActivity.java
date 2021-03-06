package com.zhy.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.listener.EasyOnItemChildClickListener;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.sample.adapter.rv.ChatAdapterForRv;
import com.zhy.sample.bean.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class MultiItemRvActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;

    private LoadMoreWrapper mLoadMoreWrapper;
    private List<ChatMessage> mDatas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);


        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatas.addAll(ChatMessage.MOCK_DATAS);
        ChatAdapterForRv adapter = new ChatAdapterForRv(this, mDatas);

        mLoadMoreWrapper = new LoadMoreWrapper(adapter);
        mLoadMoreWrapper.setLoadMoreView(LayoutInflater.from(this).inflate(R.layout.default_loading, mRecyclerView, false));
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener()
        {
            @Override
            public void onLoadMoreRequested()
            {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        boolean coming = Math.random() > 0.5;
                        ChatMessage msg = null;
                        msg = new ChatMessage(coming ? R.drawable.renma : R.drawable.xiaohei, coming ? "人马" : "xiaohei", "where are you " + mDatas.size(),
                                null, coming?ChatMessage.RECIEVE_MSG:ChatMessage.SEND_MSG);
                        mDatas.add(msg);
                        mLoadMoreWrapper.notifyDataSetChanged();

                    }
                }, 1000);
            }
        });

        adapter.setEasyOnItemChildClickListener(new EasyOnItemChildClickListener()
        {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.chat_send_content:
                        Toast.makeText(MultiItemRvActivity.this, "Click Send:" + position , Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chat_from_content:
                        Toast.makeText(MultiItemRvActivity.this, "Click From:" + position , Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }


}
