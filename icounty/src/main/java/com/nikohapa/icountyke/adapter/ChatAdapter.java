package com.nikohapa.icountyke.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nikohapa.icountyke.R;
import com.nikohapa.icountyke.constant.Constants;
import com.nikohapa.icountyke.widget.EllipsizedTextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by james on 10/06/14.
 */
public class ChatAdapter extends BaseAdapter {

    private static final String LOG_TAG = "ChatAdapter";

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

    private ArrayList<Integer> chatList = new ArrayList<Integer>();
    private LayoutInflater inflater;

    private TreeSet separators = new TreeSet();
    private Activity activity;

    static class ViewHolder{
        //sender's text
        View section_sender;
        View section_my_response;
        View section_media;
        ImageView img_profpic;
        ImageView img_mediaPic;
        ImageView img_MarkText;
        EllipsizedTextView txt_sender_message;
        TextView txt_sender_name;
        TextView txt_sender_phone;
        TextView txt_sender_time;
        TextView txt_comment_count;
        TextView txt_message_type;

        //my response
        TextView txt_my_response;
        TextView txt_response_time;
    }

    public ChatAdapter(Activity activity){
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final int item){
        chatList.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(final int position){
        chatList.remove(position);
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
                case TYPE_ITEM: //load senders layout
                    convertView = inflater.inflate(R.layout.item_sender_message, null);

                    viewHolder.section_media = (LinearLayout)convertView.findViewById(R.id.section_media);
                    viewHolder.img_profpic = (ImageView) convertView.findViewById(R.id.img_profpic);
                    viewHolder.img_mediaPic = (ImageView)convertView.findViewById(R.id.img_mediaPic);
                    viewHolder.img_MarkText = (ImageView)convertView.findViewById(R.id.img_MarkText);
                    viewHolder.txt_sender_message = (EllipsizedTextView)convertView.findViewById(R.id.txt_sender_message);
                    viewHolder.txt_sender_name = (TextView)convertView.findViewById(R.id.txt_sender_name);
                    viewHolder.txt_sender_phone = (TextView)convertView.findViewById(R.id.txt_sender_phone);
                    viewHolder.txt_sender_time = (TextView)convertView.findViewById(R.id.txt_sender_time);
                    viewHolder.txt_comment_count = (TextView)convertView.findViewById(R.id.txt_comment_count);
                    viewHolder.txt_message_type = (TextView)convertView.findViewById(R.id.txt_message_type);

                    break;
                case TYPE_SEPARATOR: //load my response layout
                    convertView = inflater.inflate(R.layout.item_my_message, null);

                    viewHolder.txt_my_response = (TextView)convertView.findViewById(R.id.txt_my_response);
                    viewHolder.txt_response_time = (TextView)convertView.findViewById(R.id.txt_response_time);
                    break;
            }

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //TODO set data
        switch (type){
            case TYPE_ITEM: //TODO set data :: for sender
                //                viewHolder.img_profpic;
//                viewHolder.img_mediaPic;
//                viewHolder.img_MarkText;
//                viewHolder.txt_sender_message;
//                viewHolder.txt_sender_name;
//                viewHolder.txt_sender_phone;
//                viewHolder.txt_sender_time;
//                viewHolder.txt_comment_count;
//                viewHolder.txt_message_type;
                //TODO remove non relevant views
                try{
                    viewHolder.txt_sender_message.setMaxLines(10 * 10 * 10);
                    viewHolder.txt_comment_count.setVisibility(View.GONE);
                }catch (Exception ex){
                    Log.e(LOG_TAG, "exception in type_sender_layout::"+ex.getMessage(), ex.getCause());
                }


                //TODO remove this dummy image stuff
                if( chatList.get(position) % 2 == 0){
                    Log.e(LOG_TAG, "showing pic");
                    viewHolder.section_media.setVisibility(View.VISIBLE);
                    viewHolder.txt_message_type.setText(
                            Constants.MSG_TYPES[new Random().nextInt(Constants.MSG_TYPES.length)]);
                }else{
                    viewHolder.section_media.setVisibility(View.GONE);
                }

                break;

            case TYPE_SEPARATOR: //TODO set data :: for my(mp/governor) response
//                viewHolder.txt_my_response;
//                viewHolder.txt_response_time;
        }

        //TODO format dates, set Fonts

        return convertView;
    }
}
