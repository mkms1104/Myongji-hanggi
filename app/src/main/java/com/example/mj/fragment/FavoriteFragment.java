package com.example.mj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mj.R;

/**
 * Created by 우상훈 on 2017-11-19.
 */

public class FavoriteFragment extends Fragment implements View.OnClickListener {
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    View mRootView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cb1 = (CheckBox)getView().findViewById(R.id.favo_star);
        cb2 = (CheckBox)getView().findViewById(R.id.favo_star2);
        cb3 = (CheckBox)getView().findViewById(R.id.favo_star3);
        cb4 = (CheckBox)getView().findViewById(R.id.favo_star4);
        cb5 = (CheckBox)getView().findViewById(R.id.favo_star5);
        cb6 = (CheckBox)getView().findViewById(R.id.favo_star6);
        cb1.setOnClickListener(this);
        cb2.setOnClickListener(this);
        cb3.setOnClickListener(this);
        cb4.setOnClickListener(this);
        cb5.setOnClickListener(this);
        cb6.setOnClickListener(this);
        cb1.setChecked(true);
        cb2.setChecked(true);
        cb3.setChecked(true);
        cb4.setChecked(true);
        cb5.setChecked(true);
        cb6.setChecked(true);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null)
            mRootView = inflater.inflate(R.layout.favorite_fragment, container, false);

        return mRootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.favo_star:
                if(cb1.isChecked()==true){
                    Toast.makeText(getActivity(), "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "즐겨찾기가 해제되었습니다!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favo_star2:
                if(cb2.isChecked()==true){
                    Toast.makeText(getActivity(), "즐겨찾기가 해제되었습니다!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favo_star3:
                if(cb3.isChecked()==true){
                    Toast.makeText(getActivity(), "즐겨찾기가 해제되었습니다!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favo_star4:
                if(cb4.isChecked()==true){
                    Toast.makeText(getActivity(), "즐겨찾기가 해제되었습니다!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favo_star5:
                if(cb5.isChecked()==true){
                    Toast.makeText(getActivity(), "즐겨찾기가 해제되었습니다!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favo_star6:
                if(cb6.isChecked()==true){
                    Toast.makeText(getActivity(), "즐겨찾기가 해제되었습니다!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}