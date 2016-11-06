package com.android.swapnil.iciftestapp.view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.android.swapnil.iciftestapp.R;
import com.android.swapnil.iciftestapp.model.BlurBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProfileFragment extends Fragment {

    private String fragTitle;

    @InjectView(R.id.title)
    TextView titleText;

    @InjectView(R.id.bottomView)
    View bottomContainer;
    int bottomContainerWidth;
    int bottomContainerHeight;

    private Drawable drawable;

    static ProfileFragment init(String val) {
        ProfileFragment profileFragment = new ProfileFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        String imgName = "";
        if (Integer.valueOf(val) % 2 == 0) {
            imgName = "sample_one";
        } else {
            imgName = "sample_two";
        }
        args.putString("img", imgName);
        args.putString("title", val);
        profileFragment.setArguments(args);
        return profileFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragTitle = getArguments() != null ? getArguments().getString("title") : "";

        drawable = getResources().getDrawable(getResources()
                .getIdentifier(getArguments() != null ? getArguments().getString("img") : "sample_two", "drawable", getActivity().getPackageName()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titleText.setText("Page " + fragTitle);
        titleText.setBackground(drawable);
        //we could create custom view also and fetch the width and height
        bottomContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bottomContainerWidth = bottomContainer.getWidth();
                bottomContainerHeight = bottomContainer.getHeight();

                bottomContainer.setBackground(resize(drawable));

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                    bottomContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    bottomContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);


            }
        });


    }

    private Drawable resize(Drawable drawable) {

        Bitmap originalBitmap = ((BitmapDrawable) drawable).getBitmap();
        int ogBmWidth = originalBitmap.getWidth();
        int ogBmHeight = originalBitmap.getHeight();

        Bitmap cropImg = Bitmap.createBitmap(originalBitmap, 0, (ogBmHeight - 1) - bottomContainerHeight, ogBmWidth, bottomContainerHeight);

        Bitmap blurredBitmap = BlurBuilder.blur( getActivity(), cropImg );

        return new BitmapDrawable(getResources(), blurredBitmap);
    }
}