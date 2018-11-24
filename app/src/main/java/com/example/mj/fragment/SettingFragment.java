package com.example.mj.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mj.R;
import com.example.mj.db.Database;

/**
 * Created by 우상훈 on 2017-11-19.
 */

public class SettingFragment extends Fragment implements View.OnClickListener{
    View mRootView;
    Button menu_btn;
    SQLiteDatabase database;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        database = Database.getInstance().open(getActivity());
        menu_btn = (Button) getView().findViewById(R.id.insertMenu_btn);
        menu_btn.setOnClickListener(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null)
            mRootView = inflater.inflate(R.layout.setting_fragment, container, false);

        return mRootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insertMenu_btn:

                // 양식
                Database.getInstance().studentMenu(database, "A", "미트볼 스파게티", R.drawable.mit_spa, 3900, 0);
                Database.getInstance().studentMenu(database, "B", "등심 돈까스", R.drawable.don, 3900, 0);
                Database.getInstance().studentMenu(database, "C", "피자 돈까스", R.drawable.p_don, 4300, 0);

                // 중식
                Database.getInstance().studentMenu(database, "D", "신화 짜장", R.drawable.jja, 3000, 0);
                Database.getInstance().studentMenu(database, "E", "신화 짬뽕", R.drawable.jjam, 4000, 0);
                Database.getInstance().studentMenu(database, "F", "미니 탕수육", R.drawable.tang, 5500, 0);

                // 한식식
                Database.getInstance().studentMenu(database, "G", "보쌈 정식", R.drawable.bo, 4000, 0);
                Database.getInstance().studentMenu(database, "H", "차돌된장찌개", R.drawable.cha, 4000, 0);
                Database.getInstance().studentMenu(database, "I", "부대찌개", R.drawable.boo, 4000, 0);

                //츄밥
                Database.getInstance().studentMenu(database, "J", "제육 츄밥", R.drawable.coo1, 3000, 0);
                Database.getInstance().studentMenu(database, "K", "곱창 츄밥", R.drawable.coo2, 3000, 0);
                Database.getInstance().studentMenu(database, "L", "참치마요 츄밥", R.drawable.coo3, 3000, 0);

                //분식
                Database.getInstance().studentMenu(database, "M", "치즈라면", R.drawable.la, 2500, 0);
                Database.getInstance().studentMenu(database, "N", "라볶이", R.drawable.bokki, 3000, 0);
                Database.getInstance().studentMenu(database, "O", "참치김밥", R.drawable.gimbab, 3000, 0);

                Log.d("minsu","메뉴 튜플 생성");
                break;

        }

    }
}
