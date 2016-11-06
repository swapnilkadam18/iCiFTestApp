package com.android.swapnil.iciftestapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private String fragTitle;

    private TextView titleText;

    private View bottomContainer;

    private Drawable drawable;

    static ProfileFragment init(String val) {
        ProfileFragment profileFragment = new ProfileFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        String imgName = "";
        if (Integer.valueOf(val)%2 == 0) {
            imgName = "sample_one";
        }else{
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
        titleText = (TextView) view.findViewById(R.id.title);
        bottomContainer = view.findViewById(R.id.bottomView);
        titleText.setText("Page " + fragTitle);
        titleText.setBackground(drawable);
        bottomContainer.setBackground(resize(drawable));
        return view;
    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 50, 50, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

}