package com.android.swapnil.iciftestapp;

import android.support.v4.app.FragmentManager;

import com.commonsware.cwac.pager.PageDescriptor;
import com.commonsware.cwac.pager.v4.ArrayPagerAdapter;

import java.util.ArrayList;

/**
 * Created by swapnil on 04/11/2016.
 */

public class ProfileAdapter extends ArrayPagerAdapter<ProfileFragment> {

    public ProfileAdapter(FragmentManager fragmentManager,
                          ArrayList<PageDescriptor> descriptors) {
        super(fragmentManager, descriptors);
    }

    @Override
    protected ProfileFragment createFragment(PageDescriptor desc) {
        return(ProfileFragment.init(desc.getTitle()));
    }

}
