package com.example.mj.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mj.fragment.StudentMenuFragment;
import com.example.mj.R;
import com.example.mj.fragment.TeacherMenuFragment;
import com.example.mj.fragment.BuyListFragment;

import org.w3c.dom.Text;

/**
 * Created by 우상훈 on 2017-10-21.
 */

public class TabViewActivity extends FragmentActivity implements View.OnClickListener{
    Fragment fr1, fr2, fr3;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_view_activity);

        Button btn_first = (Button)findViewById(R.id.btn_first);
        Button btn_second = (Button)findViewById(R.id.btn_second);
        Button btn_third = (Button)findViewById(R.id.btn_third);
        tv1 = (TextView)findViewById(R.id.mainText);

        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_third.setOnClickListener(this);

        fr1 = new StudentMenuFragment();
        fr2 = new TeacherMenuFragment();
        fr3 = new BuyListFragment();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_first:
               // setFragment(fr1);
                setFragmentForAnimation(fr1);
                tv1.setText("학생");
                break;
            case R.id.btn_second:
               // setFragment(fr2);
                setFragmentForAnimation(fr2);
                tv1.setText("교직원");
                break;
            case R.id.btn_third:
               // setFragment(fr3);
                setFragmentForAnimation(fr3);
                tv1.setText("구매내역");
                break;
        }
    }

    public void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

    public void setFragmentForAnimation(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_up, 0, 0, R.anim.slide_in_down);
        fragmentTransaction.replace(R.id.fragment_container,fragment);


    }


}
