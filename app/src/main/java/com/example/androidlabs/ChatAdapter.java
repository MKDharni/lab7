package com.example.androidlabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class ChatAdapter extends BaseAdapter {

    private List<Message> messages;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<Message> messages, Context context)
    {
        this.messages = messages;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null){
            if (messages.get(position).isGone()){
                v = inflater.inflate(R.layout.left_layout, null);
}
            else
                {
                v = inflater.inflate(R.layout.right_layout, null);
                }
            TextView  messageTyped = (TextView)v.findViewById(R.id.textOnly);
            messageTyped.setText(messages.get(position).message);
        }
        return v;
    }
    @Override
    public int getCount()
    {
        return messages.size();
    }

    @Override
    public Object getItem(int position)
    {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }
}
