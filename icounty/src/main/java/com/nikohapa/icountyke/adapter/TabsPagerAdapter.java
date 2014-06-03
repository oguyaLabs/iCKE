package com.nikohapa.icountyke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nikohapa.icountyke.constant.Constants;
import com.nikohapa.icountyke.ui.frags.InHouseFragment;
import com.nikohapa.icountyke.ui.frags.ProfileFragment;
import com.nikohapa.icountyke.ui.frags.PublicFragment;

/**
 * Created by james on 3/06/14.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index){
            case 0: //show public frag
                return new PublicFragment();
            case 1: //show inhouse chat
                return new InHouseFragment();
            case 2: //show profile frag
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        //no of tabs
        return Constants.TABS_TITLE.length;
    }
}
