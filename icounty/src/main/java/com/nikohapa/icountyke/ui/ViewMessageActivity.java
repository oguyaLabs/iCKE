package com.nikohapa.icountyke.ui;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nikohapa.icountyke.R;
import com.nikohapa.icountyke.adapter.ChatAdapter;
import com.nikohapa.icountyke.adapter.ViewInflaterBaseAdapter;
import com.nikohapa.icountyke.constant.Constants;
import com.nikohapa.icountyke.widget.EllipsizedTextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class ViewMessageActivity extends ActionBarActivity {

    private static final String LOG_TAG = "ViewMessageActivity";
    private ListView chatListView;
    private ArrayList<Integer> messagesList = new ArrayList<Integer>();
    private ChatListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        chatListView = (ListView)findViewById(R.id.ChatListView);

        messagesList.add(1);
        messagesList.add(0);
        messagesList.add(1);
        messagesList.add(0);
        messagesList.add(1);
        messagesList.add(0);
        messagesList.add(1);

        ChatAdapter chatAdapter = new ChatAdapter(ViewMessageActivity.this);
        for(int i=0; i<20; i++){
            chatAdapter.addItem(i);
            if(i % 2 == 0)
                chatAdapter.addSeparatorItem(i);
        }
        chatListView.setAdapter(chatAdapter);


//        listAdapter = new ChatListAdapter(new Inflater(), messagesList);
//        chatListView.setAdapter(listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_message, menu);
        return true;
    }

    void toast(String text){
        Toast.makeText(ViewMessageActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    void showPopUpMenu(View view){
        PopupMenu popupMenu = new PopupMenu(ViewMessageActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.attach_popup_menu, popupMenu.getMenu());
//        popupMenu.setOnMenuItemClickListener(attachMenuListener);
        popupMenu.show();
    }

    void showPopupWindow(View view){
        PopupWindow popupWindow = new PopupWindow(ViewMessageActivity.this);
        View layout = getLayoutInflater().inflate(R.layout.attach_media, null);

        popupWindow.setContentView(layout);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view);

    }

    public void attachMenuListener(View view){
        switch (view.getId()){
            case R.id.attach_photo:
            case R.id.sect_take_pic:
                toast("take pic");
                break;

            case R.id.attach_audio:
            case R.id.sect_record_audio:
                toast("record audio");
                break;

            case R.id.attach_video:
            case R.id.sect_record_video:
                toast("record video");
                break;

            default: break;
        }
    }

    View.OnClickListener attachMenuListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.attach_photo:
                case R.id.sect_take_pic:
                    toast("take pic");
                    break;

                case R.id.attach_audio:
                case R.id.sect_record_audio:
                    toast("record audio");
                    break;

                case R.id.attach_video:
                case R.id.sect_record_video:
                    toast("record video");
                    break;

                default: break;
            }
        }
    };

    PopupMenu.OnMenuItemClickListener popUpMenuListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_attach_photo:
                    //TODO attach pic
                    toast("attach pic");
                    break;

                case R.id.action_attach_audio:
                    toast("attach audio recording");
                    //TODO attach audio recording
                    break;

                case R.id.action_attach_video:
                    toast("attach video");
                    //TODO attach video
                    break;

                default: return false;
            }
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.action_attach:    //attach item
                toast("show popup menu");
                View attach = findViewById(R.id.action_attach);
//                showPopUpMenu(attach);
                showPopupWindow(attach);
                break;

            case R.id.action_settings:
                break;

            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    //inflater
    private class Inflater implements ViewInflaterBaseAdapter.ViewInflater{

        boolean SOURCE_TYPE_SENDER = true;

        private class ViewHolder{
            int SOURCE_TYPE; //1 for sender 2 for my response
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

        @Override
        public View inflate(ViewInflaterBaseAdapter adapter, int position, View ConvertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View rowView = ConvertView;
            View mRowView = ConvertView;

            if(rowView == null){

                //TODO REMOVE THIS
                SOURCE_TYPE_SENDER = (messagesList.get(position) % 2 == 0);


                if(messagesList.get(position) == 1){
                    ViewHolder viewHolder = new ViewHolder();
                    //load senders view
                    rowView = inflater.inflate(R.layout.item_sender_message, parent, false);

                    viewHolder.section_media = (LinearLayout)rowView.findViewById(R.id.section_media);
                    viewHolder.img_profpic = (ImageView) rowView.findViewById(R.id.img_profpic);
                    viewHolder.img_mediaPic = (ImageView)rowView.findViewById(R.id.img_mediaPic);
                    viewHolder.img_MarkText = (ImageView)rowView.findViewById(R.id.img_MarkText);
                    viewHolder.txt_sender_message = (EllipsizedTextView)rowView.findViewById(R.id.txt_sender_message);
                    viewHolder.txt_sender_name = (TextView)rowView.findViewById(R.id.txt_sender_name);
                    viewHolder.txt_sender_phone = (TextView)rowView.findViewById(R.id.txt_sender_phone);
                    viewHolder.txt_sender_time = (TextView)rowView.findViewById(R.id.txt_sender_time);
                    viewHolder.txt_comment_count = (TextView)rowView.findViewById(R.id.txt_comment_count);
                    viewHolder.txt_message_type = (TextView)rowView.findViewById(R.id.txt_message_type);

                    rowView.setTag(viewHolder);

                }else if(messagesList.get(position) == 0){
                    ViewHolder viewHolder = new ViewHolder();

                    //load my response view
                    rowView = inflater.inflate(R.layout.item_my_message, parent, false);

                    viewHolder.txt_my_response = (TextView)rowView.findViewById(R.id.txt_my_response);
                    viewHolder.txt_response_time = (TextView)rowView.findViewById(R.id.txt_response_time);

                    rowView.setTag(viewHolder);

                }
            }

            ViewHolder viewHolder = (ViewHolder)rowView.getTag();


            if(messagesList.get(position) == 1){
                //TODO set data :: for sender
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
                if( messagesList.get(position) == 1){
                    Log.e(LOG_TAG, "showing pic");
                    viewHolder.section_media.setVisibility(View.VISIBLE);
                    viewHolder.txt_message_type.setText(
                            Constants.MSG_TYPES[new Random().nextInt(Constants.MSG_TYPES.length)]);
                }else{
                    viewHolder.section_media.setVisibility(View.GONE);
                }


            }else{
                //TODO set data :: for my(mp/governor) response
//                viewHolder.txt_my_response;
//                viewHolder.txt_response_time;
            }





            //TODO format dates, set Fonts

            return rowView;
        }

        int getRandomNo(){
            return new Random(200719293).nextInt();
        }


    }

    //list adapter
    public class ChatListAdapter extends ViewInflaterBaseAdapter<Integer>{

        public ChatListAdapter(ViewInflater inflater, ArrayList<Integer> data) {
            super(inflater, data);
            super.setInflater(inflater);
        }
    }

}
