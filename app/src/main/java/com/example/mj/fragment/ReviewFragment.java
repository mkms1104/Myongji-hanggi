package com.example.mj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mj.R;
import com.example.mj.popUp.PopupActivity_1;
import com.example.mj.popUp.PopupActivity_3;

/**
 * Created by 우상훈 on 2017-11-19.
 */

public class ReviewFragment extends Fragment implements View.OnClickListener{
    View mRootView;
    Button review_btn;
    Intent intent;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        review_btn = (Button) getView().findViewById(R.id.review_btn);
        review_btn.setOnClickListener(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null)
            mRootView = inflater.inflate(R.layout.review_fragment, container, false);

        return mRootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.review_btn: // 리뷰쓰기 버튼
                intent = new Intent(getActivity(), PopupActivity_3.class);
                startActivityForResult(intent, 3); // requestCode 3

                break;

        }

    }
}
