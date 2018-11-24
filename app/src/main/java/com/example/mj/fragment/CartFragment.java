package com.example.mj.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.mj.R;
import com.example.mj.db.Database;

import java.util.ArrayList;
import java.util.List;

import static com.example.mj.fragment.StudentMenuFragment.checkArray;

/**
 * Created by 우상훈 on 2017-11-19.
 */

public class CartFragment extends Fragment implements View.OnClickListener{
    Cursor cursor_name, cursor_price, cursor_img;
    SQLiteDatabase database;
    Integer price, img;
    String menu = null;
    CheckBox all_Ck;
    Button all_Del;
    View mRootView;

    private LinearLayout dynamicLayout;
    private final int DYNAMIC_VIEW_CHECK_ID = 7000;
    private final int DYNAMIC_VIEW_ID = 8000;
    // private final int DYNAMIC_VIEW_BUTTON_ID = 9000;
    private int numCheck;
    private int numLinear;

    String code[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",};
    // List<Integer> arrayButtonId = new ArrayList<Integer>();
    List<Integer> arrayLayoutId = new ArrayList<Integer>();
    List<Integer> arrayCheckId = new ArrayList<Integer>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        all_Ck = (CheckBox) getView().findViewById(R.id.all_Check); // 전부 체크
        all_Del = (Button) getView().findViewById(R.id.all_Delete); // 전부 삭제
        all_Ck.setOnClickListener(this);
        all_Del.setOnClickListener(this);

        database = Database.getInstance().open(getActivity());
        dynamicLayout = (LinearLayout) getView().findViewById(R.id.dynamicView);

        // 체크된 학생 메뉴의 이름과 가격을 장바구니에 보여준다.
        for(int i=0; i<checkArray.length; i++) {
            studentMenu(checkArray[i], code[i]);

        }


        // 장바구니 프레그먼트로 넘어오면 학생 메뉴의 체크박스 상태를 초기화
        for(int i=0; i<checkArray.length; i++){
            final int index;
            index = i;
            checkArray[index].setChecked(false);
        }
    }


    private void addView(int imgSrc, String menu, String price){ // 장바구니에 담은 메뉴
        LinearLayout linearLayout = new LinearLayout(getActivity());

        numLinear++;
        linearLayout.setId(DYNAMIC_VIEW_ID + numLinear);
        arrayLayoutId.add(linearLayout.getId());
        Log.d("minsu", "레이아웃 아이디" + linearLayout.getId());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(0,50,0,50);
        LinearLayout.LayoutParams param_margin = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        param_margin.setMargins(0, 0, 0, 50);

        linearLayout.setLayoutParams(param_margin);
        // 레이아웃 마진

        linearLayout.setBackgroundResource(R.drawable.line);
        dynamicLayout.addView(linearLayout);
        // 뷰를 담을 레이아웃 생성



        // <LinearLayout>
        LinearLayout ck_img_ll = new LinearLayout(getActivity());
        LinearLayout.LayoutParams param_ck_img = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        param_ck_img.weight = 1;
        ck_img_ll.setLayoutParams(param_ck_img);
        linearLayout.addView(ck_img_ll);

        CheckBox ck = new CheckBox(getActivity());

        numCheck++;
        ck.setId(DYNAMIC_VIEW_CHECK_ID + numCheck);
        arrayCheckId.add(ck.getId());
        // 체크박스

        ImageView imgView = new ImageView(getActivity());
        ViewGroup.LayoutParams param_imgSize = new ViewGroup.LayoutParams(300, 300);
        imgView.setBackgroundResource(imgSrc);
        imgView.setLayoutParams(param_imgSize);
        // 이미지 뷰

        ck_img_ll.addView(ck);
        ck_img_ll.addView(imgView);
        // </LinearLayout>



        // <LinearLayout>
        LinearLayout tx_btn_ll = new LinearLayout(getActivity());
        tx_btn_ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams param_tx_btn = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        param_tx_btn.weight = 1;
        tx_btn_ll.setLayoutParams(param_tx_btn);
        tx_btn_ll.setGravity(Gravity.CENTER);
        linearLayout.addView(tx_btn_ll);

        TextView nameTx = new TextView(getActivity());
        nameTx.setGravity(Gravity.CENTER);
        nameTx.setText(menu);
        // 메뉴 이름 텍스트

        TextView priceTx = new TextView(getActivity());
        priceTx.setGravity(Gravity.CENTER);
        priceTx.setText(price + "원");
        // 메뉴 가격 텍스트

        tx_btn_ll.addView(nameTx);
        tx_btn_ll.addView(priceTx);
        // </LinearLayout>



        // <LinearLayout>
        LinearLayout count_ll = new LinearLayout(getActivity());
        count_ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams param_count = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        param_count.weight = 1;
        count_ll.setLayoutParams(param_count);
        count_ll.setGravity(Gravity.CENTER);
        linearLayout.addView(count_ll);

        TextView countTx = new TextView(getActivity());
        countTx.setGravity(Gravity.CENTER);
        countTx.setText("수량");
        count_ll.addView(countTx);
        // 수량

        TextView count = new TextView(getActivity());
        count.setGravity(Gravity.CENTER);
        count.setText("1");
        count_ll.addView(count);

        /*
        Button btn = new Button(getActivity());
        LinearLayout.LayoutParams param_btn = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(param_btn);

        numButton++;
        btn.setId(DYNAMIC_VIEW_BUTTON_ID + numButton); // 설정된 id를 기준으로 삭제
        Log.d("minsu", "버튼 아이디" + btn.getId());

        arrayButtonId.add(btn.getId());
        btn.setText("삭제하기");
        btn.setOnClickListener(this);
        // 삭제 버튼

        //count_ll.addView(btn);
        // </LinearLayout>
        */


    }

    private void deleteView(int i){ // 장바구니 삭제
        LinearLayout linearLayout;

        linearLayout = (LinearLayout) getView().findViewById(i);
        dynamicLayout.removeView(linearLayout);


    }

    private void checkAllView(int i){ // 장바구니 전부 체크
        CheckBox ck;
        ck = (CheckBox) getView().findViewById(i);
        if(ck != null) {
            if (all_Ck.isChecked()) {
                ck.setChecked(true);

            }
        }

    }

    private void checkedView(int i, int j){ // 각각의 장바구니 메뉴 체크
        CheckBox ck;
        ck = (CheckBox) getView().findViewById(i);

        if(ck != null) {
            if (ck.isChecked()) {
                deleteView(j);

            }
        }

    }


    private void studentMenu(CheckBox ck, String code){
        cursor_name = Database.getInstance().searchStudentMenuName(code); // 메뉴 이름
        cursor_price = Database.getInstance().searchStudentMenuPrice(code); // 메뉴 가격
        cursor_img = Database.getInstance().searchStudentMenuImage(code); // 메뉴 이미지

        if(ck.isChecked()) {
            Log.d("minsu", "장바구니에 " + cursor_name.getString(0) + " 추가, 가격은 " + cursor_price.getInt(0) + "원");
            menu = cursor_name.getString(0);
            price = cursor_price.getInt(0);
            img = cursor_img.getInt(0);
            addView(img, menu, price.toString());
            // 체크된 메뉴의 이미지와 이름과 가격을 selectedCart 함수의 매게변수로 저장

        }

        cursor_name.close();
        cursor_price.close();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null)
            mRootView = inflater.inflate(R.layout.cart_fragment, container, false);

        return mRootView;
    }


    @Override
    public void onClick(View v) { // 삭제하기 버튼
        /*
        Log.d("minsu", "size " + arrayButtonId.size());
        for(int i=0; i<arrayButtonId.size(); i++) {
            if(v.getId() == arrayButtonId.get(i)){
                Log.d("minsu", "View Id " + v.getId() + " " + arrayButtonId.get(i));

                deleteView(arrayLayoutId.get(i));
                arrayLayoutId.remove(i);
                arrayButtonId.remove(i);
                arrayCheckId.remove(i);

            }
        }// for문 종료
        */

        switch (v.getId()){
            case R.id.all_Check: // 전체 체크
                for(int i=0; i<arrayCheckId.size(); i++) {
                    checkAllView(arrayCheckId.get(i));

                }

                break;

            case R.id.all_Delete: // 전체삭제
                for(int i=0; i<arrayCheckId.size(); i++) {
                    checkedView(arrayCheckId.get(i), arrayLayoutId.get(i));
                }

                break;

        }

    }
}
