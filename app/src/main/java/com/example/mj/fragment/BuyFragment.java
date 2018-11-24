package com.example.mj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mj.R;

/**
 * Created by minsu on 2017-12-12.
 */

public class BuyFragment extends Fragment{
    View mRootView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null)
            mRootView = inflater.inflate(R.layout.buy_fragment, container, false);

        return mRootView;
    }


}
