package com.zhy.sample.adapter.rv;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.sample.R;
import com.zhy.sample.bean.ChatMessage;

/**
 * Created by zhy on 16/6/22.
 */
public class MsgTimeItemDelagate implements ItemViewDelegate<ChatMessage>
{

    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.main_chat_time_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position)
    {
        return ChatMessage.TIME_MSG==item.getTypeMsg();
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage, int position)
    {
        holder.setText(R.id.chat_time_content, chatMessage.getName());
    }
}
