package com.android.swapnil.iciftestapp.presenter;

import com.android.swapnil.iciftestapp.interfaces.MVP_Main;
import com.android.swapnil.iciftestapp.model.MainModel;
import com.commonsware.cwac.pager.PageDescriptor;

import java.util.ArrayList;

/**
 * Created by swapnil on 06/11/2016.
 */

public class MainPresenter implements MVP_Main.viewToPresenter,MVP_Main.modelToPresenter {

    private MVP_Main.presenterToView presenterToView;
    private MVP_Main.presenterToModel presenterToModel;

    public MainPresenter(MVP_Main.presenterToView mPresenterToView) {
        presenterToView = mPresenterToView;
    }

    @Override
    public void getBuildAdapter() {
        presenterToModel = new MainModel(this);
        presenterToModel.getPagerAdapter();
    }

    @Override
    public void readyViewPagerAdapter(ArrayList<PageDescriptor> viewPagerAdapter) {
        presenterToView.readyBuildAdapter(viewPagerAdapter);
    }
}
