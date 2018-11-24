package com.example.mj.fragment;

import android.app.Application;
import android.support.v4.app.Fragment;

/**
 * Created by 우상훈 on 2017-10-21.
 */

public class FragmentApplication extends Application {
    Fragment[] fragmentArray;
    Fragment fr1,fr2,fr3;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Fragment[] getFragmentArray(){
        if(fragmentArray != null && fragmentArray.length > 0){
            return fragmentArray;
        }
        else{
            initFragmentArray();
            return fragmentArray;
        }
    }

    private void initFragmentArray(){
        fr1 = new StudentMenuFragment();
        fr2 = new TeacherMenuFragment();
        fr3 = new BuyListFragment();
        fragmentArray = new Fragment[] {fr1, fr2, fr3};
    }
}
