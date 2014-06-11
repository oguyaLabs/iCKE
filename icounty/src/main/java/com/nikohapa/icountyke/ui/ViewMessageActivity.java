package com.nikohapa.icountyke.ui;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
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

    private EditText new_response;
    private ImageView send_message;

    private ActionMode currentActionMode;
    private int currentListItemIndex;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();

        //TODO dummy data
        messagesList.add(1);
        messagesList.add(0);
        messagesList.add(1);
        messagesList.add(0);
        messagesList.add(1);
        messagesList.add(0);
        messagesList.add(1);

        chatAdapter = new ChatAdapter(ViewMessageActivity.this);
        for(int i=0; i<20; i++){
            chatAdapter.addItem(i);
            if(i % 2 == 0)
                chatAdapter.addSeparatorItem(i);
        }
        chatListView.setAdapter(chatAdapter);
        chatListView.requestFocus();

        chatListView.setOnItemLongClickListener(longClickListener);

//        listAdapter = new ChatListAdapter(new Inflater(), messagesList);
//        chatListView.setAdapter(listAdapter);
    }

    void initUI(){
        chatListView = (ListView)findViewById(R.id.ChatListView);
        new_response = (EditText)findViewById(R.id.edit_new_response);
        send_message = (ImageView)findViewById(R.id.btn_send_message);
    }

    AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            if(currentActionMode != null) {return false;}
            currentListItemIndex = position;
            currentActionMode = startSupportActionMode(modeCallBack);
            view.setSelected(true);
            return true;
        }
    };

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
            case R.id.home: //nav back
                onBackPressed();
                break;

            case R.id.action_attach:    //attach item
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

    //callback action mode for forward/delete messages
    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Actions");
            actionMode.getMenuInflater().inflate(R.menu.chat_quick_actions, menu);
            return true;
        }

        // Called each time the action mode is shown.
        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.quick_forward:    //forward message
                    toast("forwarding message");
                    actionMode.finish();
                    return true;

                case R.id.quick_delete:     //delete message
                    toast("message deleted");
                    chatAdapter.removeItem(currentListItemIndex);
                    chatAdapter.notifyDataSetChanged();
                    actionMode.finish();
                    return true;

                default: return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            currentActionMode = null;
        }
    };

}
