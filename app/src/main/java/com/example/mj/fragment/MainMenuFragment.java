package com.example.mj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.mj.R;

/**
 * Created by 우상훈 on 2017-11-19.
 */

public class MainMenuFragment extends DialogFragment{
    ViewPager vp;
    FragmentApplication mApp;
    View mRootView;
    TextView tv1;
    FragmentManager mFragmentManager;
    Button btn_first, btn_second, btn_third;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_first = (Button)getActivity().findViewById(R.id.btn_first);
        btn_second = (Button)getActivity().findViewById(R.id.btn_second);
        btn_third = (Button)getActivity().findViewById(R.id.btn_third);
        tv1 = (TextView)getActivity().findViewById(R.id.mainText);
        mFragmentManager = getActivity().getSupportFragmentManager();

        btn_first.setOnClickListener(movePageListener);
        btn_first.setTag(0);
        btn_second.setOnClickListener(movePageListener);
        btn_second.setTag(1);
        btn_third.setOnClickListener(movePageListener);
        btn_third.setTag(2);
        if(vp == null){
            vp = (ViewPager)getActivity().findViewById(R.id.vp);
            vp.setAdapter(new PagerAdapter(mFragmentManager));
            vp.setCurrentItem(0);
        }
        mApp = (FragmentApplication)getActivity().getApplicationContext(); // Context = 안드로이드 컴포넌트들의 정보
    }


    public void setCurrentPage(final int pageNum){
        Log.d("fff","set page = "+pageNum);
        vp.post(new Runnable() {
            @Override
            public void run() {
                vp.setCurrentItem(pageNum, false);
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null)
            mRootView = inflater.inflate(R.layout.main_menu_fragment, container, false);

        return mRootView;
    }

    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            if(tag == 0) {
                selectStudent();
            }
            else if(tag == 1) {
                selectTeacher();
            }
            else if(tag == 2) {
                selectBuylist();
            }
            vp.setCurrentItem(tag);
        }
    };

    private class PagerAdapter extends FragmentStatePagerAdapter
    {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Log.d("by_SH", "position = " + position);
            return mApp.getFragmentArray()[position];
        }

        @Override
        public int getItemPosition(Object object) {
            notifyDataSetChanged();
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

    public void selectStudent() {
        tv1.setText("학생");
        btn_first.setBackgroundResource(R.color.btnColor);
        btn_second.setBackgroundResource(R.color.btnColor2);
        btn_third.setBackgroundResource(R.color.btnColor2);
    }
    public void selectTeacher(){
        tv1.setText("교직원");
        btn_second.setBackgroundResource(R.color.btnColor);
        btn_first.setBackgroundResource(R.color.btnColor2);
        btn_third.setBackgroundResource(R.color.btnColor2);
    }
    public void selectBuylist(){
        tv1.setText("구매내역");
        btn_third.setBackgroundResource(R.color.btnColor);
        btn_first.setBackgroundResource(R.color.btnColor2);
        btn_second.setBackgroundResource(R.color.btnColor2);
    }

}
