package com.android.swapnil.iciftestapp.model;

        import android.app.Activity;
        import android.content.Context;

        import com.android.swapnil.iciftestapp.R;
        import com.android.swapnil.iciftestapp.interfaces.MVP_Main;
        import com.android.swapnil.iciftestapp.view.ProfileAdapter;
        import com.commonsware.cwac.pager.PageDescriptor;
        import com.commonsware.cwac.pager.SimplePageDescriptor;

        import java.util.ArrayList;
        import java.util.Locale;

/**
 * Created by swapnil on 06/11/2016.
 */

public class MainModel implements MVP_Main.presenterToModel {
    private MVP_Main.modelToPresenter modelToPresenter;
    private int pageNumber = 1;
    Context ctx;

    public MainModel(MVP_Main.modelToPresenter mModelToPresenter) {
        modelToPresenter = mModelToPresenter;
    }

    @Override
    public void getPagerAdapter() {
        ArrayList<PageDescriptor> pages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pages.add(new SimplePageDescriptor(buildTag(i), buildTitle(i)));
        }
        modelToPresenter.readyViewPagerAdapter(pages);
    }

    private String buildTag(int position) {
        return ("editor" + String.valueOf(pageNumber++));
    }

    private String buildTitle(int position) {
        return String.valueOf(position+1);
    }
}
