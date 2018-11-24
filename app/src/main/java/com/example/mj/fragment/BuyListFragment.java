package com.example.mj.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.mj.R;



/**
 * Created by 우상훈 on 2017-10-21.
 */

public class BuyListFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Log.d("by_debug", "onCreateView");
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_3, container, false);
        return layout;
    }

    @Override
    public void onClick(View v) {

    }
}
