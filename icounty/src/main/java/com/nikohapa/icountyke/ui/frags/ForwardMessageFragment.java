package com.nikohapa.icountyke.ui.frags;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nikohapa.icountyke.R;
import com.nikohapa.icountyke.constant.Constants;

import java.util.ArrayList;
import java.util.List;

public class ForwardMessageFragment extends DialogFragment {

    static final String LOG_TAG = "ForwardMessageFragment";
    private static final String ARG_MESSAGE_ID = "mesageID";

    private String messageID;
    private AdapterView.OnItemClickListener clickListener;

    public static ForwardMessageFragment newInstance(String messageID) {
        ForwardMessageFragment fragment = new ForwardMessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE_ID, messageID);
        fragment.setArguments(args);
        return fragment;
    }

    public ForwardMessageFragment() {
        // Required empty public constructor
    }

    public void setOnClickListener(AdapterView.OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            messageID = getArguments().getString(ARG_MESSAGE_ID);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);
        if (getArguments() != null) {
            messageID = getArguments().getString(ARG_MESSAGE_ID);
        }

        View rootView = getActivity().getLayoutInflater().inflate(R.layout.fragment_forward_message, null);
        ListView listView = (ListView)rootView.findViewById(R.id.memberList);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, Constants.INHOUSE_CHAT_MEMBERS);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(clickListener);

        builder.setView(rootView);
        builder.setTitle("Forward Message");
        builder.setIcon(getActivity().getResources().getDrawable(R.drawable.ic_forward_dark));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }




}
