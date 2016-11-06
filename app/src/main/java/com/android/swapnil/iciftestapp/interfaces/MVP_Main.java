package com.android.swapnil.iciftestapp.interfaces;

import com.commonsware.cwac.pager.PageDescriptor;

import java.util.ArrayList;

/**
 * Created by swapnil on 06/11/2016.
 */

public interface MVP_Main {

    interface viewToPresenter {
        void getBuildAdapter();
    }

    interface presenterToModel {
        void getPagerAdapter();
    }

    interface modelToPresenter {
        void readyViewPagerAdapter(ArrayList<PageDescriptor> viewPagerAdapter);
    }

    interface presenterToView {
        void readyBuildAdapter(ArrayList<PageDescriptor> pages);
    }
}
