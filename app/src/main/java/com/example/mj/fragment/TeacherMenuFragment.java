package com.example.mj.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mj.R;
import com.example.mj.main.MainActivity;

/**
 * Created by 우상훈 on 2017-10-21.
 */

public class TeacherMenuFragment extends Fragment implements View.OnClickListener {
    Button cart1, cart2, buy1, buy2;
    CheckBox ck_1, ck_2, st_1, st_2;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cart1 = (Button)getActivity().findViewById(R.id.teacher_cart);
        cart2 = (Button)getActivity().findViewById(R.id.teacher_cart2);
        buy1 = (Button)getActivity().findViewById(R.id.teacher_buy);
        buy2 = (Button)getActivity().findViewById(R.id.teacher_buy2);
        ck_1 = (CheckBox)getActivity().findViewById(R.id.cb1);
        ck_2 = (CheckBox)getActivity().findViewById(R.id.cb2);
        st_1 = (CheckBox)getActivity().findViewById(R.id.tea_star1);
        st_2 = (CheckBox)getActivity().findViewById(R.id.tea_star2);
        ck_1.setOnClickListener(this);
        ck_2.setOnClickListener(this);
        st_1.setOnClickListener(this);
        st_2.setOnClickListener(this);

        cart1.setOnClickListener(this);
        cart2.setOnClickListener(this);
        buy1.setOnClickListener(this);
        buy2.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Log.d("by_debug", "onCreateView");
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_2, container, false);

        return layout;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.teacher_cart:
                new AlertDialog.Builder(getActivity())
                        .setTitle("장바구니").setMessage("선택한 항목을 장바구니 담으시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity activity = (MainActivity)getActivity();
                                activity.openCartFragment();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                break;
            case R.id.teacher_buy:
                new AlertDialog.Builder(getActivity())
                        .setTitle("구매하기").setMessage("정말로 구매하시겠습니까?")
                        .setPositiveButton("구매하기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity activity = (MainActivity)getActivity();
                                activity.openBuyFragment();

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                break;
            case R.id.teacher_cart2:
                new AlertDialog.Builder(getActivity())
                        .setTitle("장바구니").setMessage("선택한 항목을 장바구니 담으시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity activity = (MainActivity)getActivity();
                                activity.openCartFragment();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                break;
            case R.id.teacher_buy2:
                new AlertDialog.Builder(getActivity())
                        .setTitle("구매하기").setMessage("정말로 구매하시겠습니까?")
                        .setPositiveButton("구매하기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity activity = (MainActivity)getActivity();
                                activity.openBuyFragment();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                break;
            case R.id.tea_star1:
                Favorite(st_1);
                break;
            case R.id.tea_star2:
                Favorite(st_2);
                break;
        }
    }

    public void Favorite(CheckBox cb){
        if(cb.isChecked() == true)
            Toast.makeText(getActivity(), "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "즐겨찾기가 해제되었습니다!", Toast.LENGTH_SHORT).show();
    }
}
