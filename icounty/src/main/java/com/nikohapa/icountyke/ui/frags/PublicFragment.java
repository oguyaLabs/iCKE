package com.nikohapa.icountyke.ui.frags;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nikohapa.icountyke.R;
import com.nikohapa.icountyke.adapter.ViewInflaterBaseAdapter;
import com.nikohapa.icountyke.constant.Constants;
import com.nikohapa.icountyke.ui.ViewMessageActivity;
import com.nikohapa.icountyke.widget.EllipsizedTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class PublicFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String LOG_TAG = "PublicFragment";

    private Activity activity;
    private ListView listView;


    public PublicFragment() {
        // Required empty public constructor
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activity = activity;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_public, container, false);
        listView = (ListView)rootView.findViewById(R.id.publicListView);

        //TODO frag view loaded init view components

        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();

        //TODO frag is live set data

        //dummy data
        ArrayList<String> data = new ArrayList<String>();
        for(int i=0; i<10; i++) data.add("");

        PublicListAdapter adapter = new PublicListAdapter(new Inflater(), data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    public void onViewStateRestored(Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this.activity, ViewMessageActivity.class));
    }

    //inflater
    public class Inflater implements ViewInflaterBaseAdapter.ViewInflater{

        private class ViewHolder{
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
        }

        @Override
        public View inflate(ViewInflaterBaseAdapter adapter, int pos, View ConvertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View rowView = ConvertView;

            if(rowView == null){
                rowView = inflater.inflate(R.layout.list_item_public, parent, false);

                ViewHolder viewHolder = new ViewHolder();
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
            }

            ViewHolder viewHolder = (ViewHolder)rowView.getTag();

            //TODO set data
//            viewHolder.img_profpic;
//            viewHolder.img_mediaPic;
//            viewHolder.img_MarkText;
//            viewHolder.txt_sender_message;
//            viewHolder.txt_sender_name;
//            viewHolder.txt_sender_phone;
//            viewHolder.txt_sender_time;
//            viewHolder.txt_comment_count;
//            viewHolder.txt_message_type;

            //dummy stuff TODO remove this
            if( getRandomNo() % 2 == 0){
                Log.e(LOG_TAG, "showing pic");
                viewHolder.section_media.setVisibility(View.VISIBLE);
                viewHolder.txt_message_type.setText(
                        Constants.MSG_TYPES[new Random().nextInt(Constants.MSG_TYPES.length)]);
            }else{
                viewHolder.section_media.setVisibility(View.GONE);
            }

            //TODO format dates, set Fonts

            return rowView;
        }

        int getRandomNo(){
            return new Random(200719293).nextInt();
        }
    }

    //list adapter
    public class PublicListAdapter extends ViewInflaterBaseAdapter<String>{

        public PublicListAdapter(ViewInflater inflater, ArrayList<String> data) {
            super(inflater, data);
            super.setInflater(inflater);
        }
    }
}
