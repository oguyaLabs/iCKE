package com.nikohapa.icountyke.ui.frags;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikohapa.icountyke.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class InHouseFragment extends Fragment {


    public InHouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inhouse, container, false);
    }


}
