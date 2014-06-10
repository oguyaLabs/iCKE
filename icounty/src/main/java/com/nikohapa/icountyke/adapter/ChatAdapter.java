package com.nikohapa.icountyke.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nikohapa.icountyke.R;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by james on 10/06/14.
 */
public class ChatAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

    private ArrayList<Integer> chatList = new ArrayList<Integer>();
    private LayoutInflater inflater;

    private TreeSet separators = new TreeSet();
    private Activity activity;

    static class ViewHolder{

    }

    public ChatAdapter(Activity activity){
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final int item){
        chatList.add(item);
        notifyDataSetChanged();
    }

    public void addSeparatorItem(final int item) {
        chatList.add(item);
        // save separator position
        separators.add(chatList.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position){
        return separators.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount(){
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return chatList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        int type = getItemViewType(position);

        if(convertView == null){
            viewHolder = new ViewHolder();
            switch (type){
                case TYPE_ITEM:
                    convertView = inflater.inflate(R.layout.item_sender_message, null);
                    break;
                case TYPE_SEPARATOR:
                    convertView = inflater.inflate(R.layout.item_my_message, null);
                    break;
            }

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //TODO set data

        return convertView;
    }
}
