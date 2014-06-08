package com.nikohapa.icountyke.ui;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.nikohapa.icountyke.R;

public class ViewMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
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
}
