package com.example.mj.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mj.R;
import com.example.mj.db.Database;
import com.example.mj.main.MainActivity;

/**
 * Created by 우상훈 on 2017-10-21.
 */

public class StudentMenuFragment extends Fragment implements View.OnClickListener{
    Cursor cursor_name, cursor_price;

    public static CheckBox checkArray[] = new CheckBox[15];
    final private int checkId[] = {R.id.a_cb1, R.id.a_cb2, R.id.a_cb3,
            R.id.b_cb1, R.id.b_cb2, R.id.b_cb3,
            R.id.c_cb1, R.id.c_cb2, R.id.c_cb3,
            R.id.d_cb1, R.id.d_cb2, R.id.d_cb3,
            R.id.e_cb1, R.id.e_cb2, R.id.e_cb3};
    public static CheckBox checkArray2[] = new CheckBox[15];
    final private int checkStar[] = {R.id.A_star1, R.id.A_star2, R.id.A_star3,
            R.id.B_star1, R.id.B_star2, R.id.B_star3,
            R.id.C_star1, R.id.C_star2, R.id.C_star3,
            R.id.D_star1, R.id.D_star2, R.id.D_star3,
            R.id.E_star1, R.id.E_star2, R.id.E_star3};

    public static Integer price = 0;
    public static String menu = "";

    Button mainBuy, mainCart; // 학생 메뉴의 구매하기, 장바구니 버튼


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("by_debug", "onActivityCreated");

        mainBuy = (Button)getActivity().findViewById(R.id.mainBuy);
        mainCart = (Button)getActivity().findViewById(R.id.mainCart);
        mainBuy.setOnClickListener(this);
        mainCart.setOnClickListener(this);

        for(int i=0; i<checkArray.length; i++){
            final int index;
            index = i;
            checkArray[index] = (CheckBox) getView().findViewById(checkId[i]);
            checkArray[index].setOnClickListener(this);

        }

        for(int i=0; i<checkArray2.length; i++){
            final int index;
            index = i;
            checkArray2[index] = (CheckBox) getView().findViewById(checkStar[i]);
            checkArray2[index].setOnClickListener(this);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_1, container, false);

        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mainBuy: // 구매하기 버튼
                if (price == 0) {
                    Toast.makeText(getActivity(), "메뉴를 선택해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                new AlertDialog.Builder(getActivity())
                        .setTitle("구매하기").setMessage("총 금액은 " + price + "원 입니다. 구매하시겠습니까?")
                        .setPositiveButton("구매하기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity activity = (MainActivity) getActivity();
                                activity.openBuyFragment();

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                break;

            case R.id.mainCart: // 장바구니 버튼
                if (price == 0) {
                    Toast.makeText(getActivity(), "메뉴를 선택해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                new AlertDialog.Builder(getActivity())
                        .setTitle("장바구니").setMessage("선택한 항목을 장바구니에 담으시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity activity = (MainActivity) getActivity();
                                activity.openCartFragment();
                                price = 0;

                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                break;

            case R.id.a_cb1: // 미트볼 스파게티 체크
                studentMenu(checkArray[0], "A");

                break;

            case R.id.a_cb2: // 등심 돈까스
                studentMenu(checkArray[1], "B");

                break;

            case R.id.a_cb3: // 피자 돈까스
                studentMenu(checkArray[2], "C");

                break;

            case R.id.b_cb1: // 신화 짜장
                studentMenu(checkArray[3], "D");

                break;

            case R.id.b_cb2: // 신화 짬뽕
                studentMenu(checkArray[4], "E");

                break;

            case R.id.b_cb3: // 미니 탕수육
                studentMenu(checkArray[5], "F");

                break;

            case R.id.c_cb1: // 보쌈 정식
                studentMenu(checkArray[6], "G");

                break;

            case R.id.c_cb2: // 차돌 된장 찌개
                studentMenu(checkArray[7], "H");

                break;

            case R.id.c_cb3: // 부대 찌개
                studentMenu(checkArray[8], "I");

                break;

            case R.id.d_cb1: // 제육 츄밥
                studentMenu(checkArray[9], "J");

                break;

            case R.id.d_cb2: // 곱창 츄밥
                studentMenu(checkArray[10], "K");

                break;

            case R.id.d_cb3: // 참치마요 츄밥
                studentMenu(checkArray[11], "L");

                break;

            case R.id.e_cb1: // 치즈라면
                studentMenu(checkArray[12], "M");

                break;

            case R.id.e_cb2: // 떡볶이
                studentMenu(checkArray[13], "N");

                break;

            case R.id.e_cb3: // 참치김밥
                studentMenu(checkArray[14], "O");

                break;

            case R.id.A_star1:
                favorite(checkArray2[0]);
                break;
            case R.id.A_star2:
                favorite(checkArray2[1]);
                break;
            case R.id.A_star3:
                favorite(checkArray2[2]);
                break;
            case R.id.B_star1:
                favorite(checkArray2[3]);
                break;
            case R.id.B_star2:
                favorite(checkArray2[4]);
                break;
            case R.id.B_star3:
                favorite(checkArray2[5]);
                break;
            case R.id.C_star1:
                favorite(checkArray2[6]);
                break;
            case R.id.C_star2:
                favorite(checkArray2[7]);
                break;
            case R.id.C_star3:
                favorite(checkArray2[8]);
                break;
            case R.id.D_star1:
                favorite(checkArray2[9]);
                break;
            case R.id.D_star2:
                favorite(checkArray2[10]);
                break;
            case R.id.D_star3:
                favorite(checkArray2[11]);
                break;
            case R.id.E_star1:
                favorite(checkArray2[12]);
                break;
            case R.id.E_star2:
                favorite(checkArray2[13]);
                break;
            case R.id.E_star3:
                favorite(checkArray2[14]);
                break;
        }
    }
    private void favorite(CheckBox cb){
        if(cb.isChecked()==true)
            Toast.makeText(getActivity(), "즐겨찾기가 등록되었습니다!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "즐겨찾기가 해제되었습니다!", Toast.LENGTH_SHORT).show();
        }


    private void studentMenu(CheckBox ck, String code) { // 학생 메뉴 체크 함수
        cursor_name = Database.getInstance().searchStudentMenuName(code); // 메뉴 이름
        cursor_price = Database.getInstance().searchStudentMenuPrice(code); // 메뉴 가격

        if (ck.isChecked()) {
            Log.d("minsu", cursor_name.getString(0) + " 선택 " + cursor_price.getInt(0) + "원");
            price = price + cursor_price.getInt(0);
            menu = cursor_name.getString(0);
            Log.d("minsu", "총 금액= " + price + "원");

        } else {
            Log.d("minsu", cursor_name.getString(0) + " 취소 " + cursor_price.getInt(0) + "원");
            price = price - cursor_price.getInt(0);
            menu = "";
            Log.d("minsu", "총 금액= " + price + "원");

        }

        cursor_name.close();
        cursor_price.close();

    }
}
