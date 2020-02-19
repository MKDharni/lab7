package com.example.androidlabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends BaseAdapter {
    private List<MessageModel> listMessage;
    private Context context;
    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return listMessage.size();
    }
    public ChatAdapter(List<MessageModel> listMessage, Context context) {
        this.listMessage =listMessage;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public Object getItem(int position) {
        return listMessage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            if (listMessage.get(position).isSend()){
                view = inflater.inflate(R.layout.left_layout, null);

            }else {
                view = inflater.inflate(R.layout.right_layout, null);
            }
            TextView messageText = (TextView)view.findViewById(R.id.msgRight);
            messageText.setText(listMessage.get(position).message);
        }
        return view;
    }
}