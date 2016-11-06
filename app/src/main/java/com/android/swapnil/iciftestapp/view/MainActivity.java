package com.android.swapnil.iciftestapp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.android.swapnil.iciftestapp.R;
import com.android.swapnil.iciftestapp.interfaces.MVP_Main;
import com.android.swapnil.iciftestapp.presenter.MainPresenter;
import com.commonsware.cwac.pager.PageDescriptor;
import com.commonsware.cwac.pager.v4.ArrayPagerAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity implements MVP_Main.presenterToView{

    @InjectView(R.id.pager)
    ViewPager mPager;
    @InjectView(R.id.like)
    Button like;
    private ArrayPagerAdapter<ProfileFragment> adapter = null;
//    private int pageNumber = 1;

    private MVP_Main.viewToPresenter viewToPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        viewToPresenter = new MainPresenter(this);

        viewToPresenter.getBuildAdapter();

        //adapter = buildAdapter();
//        mPager.setAdapter(adapter);

    }

    @OnClick(R.id.like)
    public void likeButtonClick() {
        remove();
    }

    @Override
    public void readyBuildAdapter(ArrayList<PageDescriptor> pages) {
        adapter = new ProfileAdapter(getSupportFragmentManager(), pages);
        mPager.setAdapter(adapter);
    }

//    private ArrayPagerAdapter<ProfileFragment> buildAdapter() {
//        ArrayList<PageDescriptor> pages = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            pages.add(new SimplePageDescriptor(buildTag(i), buildTitle(i)));
//        }
//
//        return (new ProfileAdapter(getSupportFragmentManager(), pages));
//    }

//    private String buildTag(int position) {
//        return ("editor" + String.valueOf(pageNumber++));
//    }
//
//    private String buildTitle(int position) {
//        return (String.format(getString(R.string.hint), position + 1));
//    }

    private void remove() {
        if (adapter.getCount() > 1) {
            ProfileFragment currentFragment = adapter.getCurrentFragment();
            final View v = currentFragment.getView();
            Animation removeFragAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out_up);
            removeFragAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    adapter.remove(mPager.getCurrentItem());
                }
            });
            v.startAnimation(removeFragAnimation);
        }
    }
}
