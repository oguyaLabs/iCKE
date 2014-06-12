package com.nikohapa.icountyke.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.nikohapa.icountyke.R;
import com.nikohapa.icountyke.constant.Constants;
import com.nikohapa.icountyke.ui.frags.ForwardMessageFragment;

public class ComposeMessageActivity extends ActionBarActivity {

    static final String LOG_TAG = "ComposeMessageActivity";
    static final String ACTIVE_FRAG = "active_frag";
    private ForwardMessageFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


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
