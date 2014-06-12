package com.nikohapa.icountyke.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nikohapa.icountyke.R;
import com.nikohapa.icountyke.constant.Constants;
import com.nikohapa.icountyke.ui.frags.ForwardMessageFragment;

public class ComposeMessageActivity extends ActionBarActivity {

    static final String LOG_TAG = "ComposeMessageActivity";
    static final String ACTIVE_FRAG = "active_frag";
    private ForwardMessageFragment fragment;

    private View section_media;
    private Button attach_photo;
    private Button attach_audio;
    private Button attach_video;
    private ImageView media_pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initUI();

    }


    void initUI(){
        section_media = (LinearLayout)findViewById(R.id.section_media);
        attach_photo = (Button)findViewById(R.id.attach_photo);
        attach_audio = (Button)findViewById(R.id.attach_audio);
        attach_video = (Button)findViewById(R.id.attach_video);
        media_pic = (ImageView)findViewById(R.id.media_pic);

        attach_photo.setOnClickListener(clickListener);
        attach_video.setOnClickListener(clickListener);
        attach_audio.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.attach_photo:
                    //TODO attach pic
                    toast("add pic");
                    section_media.setVisibility(View.VISIBLE);
                    break;
                case R.id.attach_audio:
                    //TODO attach audio
                    toast("add audio");
                    break;
                case R.id.attach_video:
                    //TODO attach video
                    toast("add video");
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compose_message, menu);
        return true;
    }

    void toast(String text){
        Toast.makeText(ComposeMessageActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.home:
                onBackPressed();
                break;

            case R.id.action_broadcast: //TODO broadcast msg
                toast("broadcast msg");
                break;

            case R.id.action_forward:   //TODO forward to jodongo
                toast("forwarding");
                forwardMessage("");
                break;

            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    void forwardMessage(String messageID){
        fragment = ForwardMessageFragment.newInstance(messageID);
        fragment.setOnClickListener(ForwardClickListener);
        fragment.show(getSupportFragmentManager(), ACTIVE_FRAG);
    }

    AdapterView.OnItemClickListener ForwardClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(ComposeMessageActivity.this, "forwarding to: "+
                    Constants.INHOUSE_CHAT_MEMBERS[position], Toast.LENGTH_SHORT).show();
            //TODO forward message

            if(fragment != null) fragment.dismiss();
            finish();
        }
    };
}
