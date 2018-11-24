package com.example.mj.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mj.R;
import com.example.mj.db.Database;
import com.example.mj.login.LoginActivity;
import java.util.regex.Pattern;

/**
 * Created by 우상훈 on 2017-11-19.
 */

public class ModifyFragment extends Fragment implements View.OnClickListener{
    View mRootView;
    SQLiteDatabase database;
    EditText modify_Id, modify_Name, modify_PhoneNum;
    Button modify_Name_Btn, modify_PhoneNum_Btn, modify_Pw_Btn;
    EditText modify_Currently_Pw_Edit, modify_New_Pw_Edit, modify_New_pwConfirm_Edit;
    String name, phoneNum, early_modify_name, early_modify_phoneNum, mCurrently_Pw, mNew_Pw, mNew_Confirm_Pw, mCurrently_DB_Pw, sql;
    Cursor cursor;
    Intent intent;

    boolean pwCheck;
    private final String id = LoginActivity.sloginId; // 기본키로 로그인 성공한 아이디를 사용
    private final String logout_Code = "t";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
        dbOpenHelper = new DBOpenHelper(getActivity()); // getActivity == context (화면 제어권자)
        database = dbOpenHelper.getWritableDatabase();
        */
        database = Database.getInstance().open(getActivity());

        modify_Id = (EditText) getView().findViewById(R.id.modify_id);
        modify_Id.setEnabled(false);
        modify_Name = (EditText) getView().findViewById(R.id.modify_name);
        modify_PhoneNum = (EditText) getView().findViewById(R.id.modify_phoneNum);
        modify_Currently_Pw_Edit = (EditText) getView().findViewById(R.id.modify_currently_pw_edit);
        modify_New_Pw_Edit = (EditText) getView().findViewById(R.id.modify_new_pw_edit);
        modify_New_pwConfirm_Edit = (EditText) getView().findViewById(R.id.modify_new_pwConfirm_edit);
        modify_Name_Btn = (Button) getView().findViewById(R.id.modify_name_btn);
        modify_PhoneNum_Btn = (Button) getView().findViewById(R.id.modify_phoneNum_btn);
        modify_Pw_Btn = (Button) getView().findViewById(R.id.modify_pw_btn);
        modify_Name_Btn.setOnClickListener(this);
        modify_PhoneNum_Btn.setOnClickListener(this);
        modify_Pw_Btn.setOnClickListener(this);


        /*
        sql = "SELECT id FROM " + dbOpenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        */



        cursor = Database.getInstance().searchId(id);
        modify_Id.setText(cursor.getString(0));

        Log.d("Modify_Id= ", cursor.getString(0));
        cursor.close();




        /*
        sql = "SELECT name FROM " + dbOpenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        */
        cursor = Database.getInstance().searchName(id);
        modify_Name.setText(cursor.getString(0));
        Log.d("Modify_name= ", cursor.getString(0));
        early_modify_name = cursor.getString(0);
        cursor.close();

        /*
        sql = "SELECT phone FROM " + dbOpenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        */

        cursor = Database.getInstance().searchPhone(id);
        modify_PhoneNum.setText(cursor.getString(0));
        Log.d("Modify_phone= ", cursor.getString(0));
        early_modify_phoneNum = cursor.getString(0);
        cursor.close();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(mRootView == null)
            mRootView = inflater.inflate(R.layout.modify_fragment, container, false);

        return mRootView;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modify_name_btn: // 이름 수정
                name = modify_Name.getText().toString();

                if(name.trim().length() == 0){
                    Toast.makeText(getActivity(), "변경하실 이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    modify_Name.setText(early_modify_name); // 기존 이름으로 텍스트 초기화
                    modify_Name.setSelection(modify_Name.length()); // 커서를 텍스트 끝으로 이동
                    return;
                }

                if(spaceCheck(name)){
                    Toast.makeText(getActivity(), "이름에 공백을 사용할 수 없습니다!", Toast.LENGTH_SHORT).show();
                    modify_Name.setText(early_modify_name); // 기존 이름으로 텍스트 초기화
                    modify_Name.setSelection(modify_Name.length()); // 커서를 텍스트 끝으로 이동
                }

                else{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("이름 수정").setMessage("이름을 수정하시겠습니까?")
                            .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Database.getInstance().updateName(database, name, id);
                                    modify_Name.setText(name);
                                    modify_Name.setSelection(modify_Name.length()); // 커서를 텍스트 끝으로 이동
                                    Toast.makeText(getActivity(), "이름이 '" + name + "' 으로\n 수정되었습니다!", Toast.LENGTH_SHORT).show();

                                }

                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();

                }

                break;

            case R.id.modify_phoneNum_btn: // 핸드폰번호 수정
                phoneNum = modify_PhoneNum.getText().toString();

                if(phoneNum.trim().length() == 0){
                    Toast.makeText(getActivity(), "변경하실 핸드폰번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    modify_PhoneNum.setText(early_modify_phoneNum); // 기존 핸드폰번호로 텍스트 초기화
                    modify_PhoneNum.setSelection(modify_PhoneNum.length()); // 커서를 텍스트 끝으로 이동
                    return;
                }

                if(spaceCheck(phoneNum)){
                    Toast.makeText(getActivity(), "핸드폰번호에 공백을 사용할 수 \n없습니다!", Toast.LENGTH_SHORT).show();
                    modify_PhoneNum.setText(early_modify_phoneNum); // 기존 핸드폰번호로 텍스트 초기화
                    modify_PhoneNum.setSelection(modify_PhoneNum.length()); // 커서를 텍스트 끝으로 이동
                }

                else{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("핸드폰번호 수정").setMessage("핸드폰번호를 수정하시겠습니까?")
                            .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Database.getInstance().updatePhone(database, phoneNum, id);
                                    modify_PhoneNum.setText(phoneNum);
                                    modify_PhoneNum.setSelection(modify_PhoneNum.length()); // 커서를 텍스트 끝으로 이동
                                    Toast.makeText(getActivity(), "핸드폰번호가 '" + phoneNum + "' 으로\n 수정되었습니다!", Toast.LENGTH_SHORT).show();

                                }

                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();

                }

                break;

            case R.id.modify_pw_btn: // 비밀번호 변경 버튼
                mCurrently_Pw = modify_Currently_Pw_Edit.getText().toString();
                mNew_Pw = modify_New_Pw_Edit.getText().toString();
                mNew_Confirm_Pw = modify_New_pwConfirm_Edit.getText().toString();
                pwCheck = Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{6,16}$", mNew_Pw);

                if(mCurrently_Pw.trim().length() == 0 || mNew_Pw.trim().length() == 0 || mNew_Confirm_Pw.trim().length() == 0){
                    Toast.makeText(getActivity(), "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*
                sql = "SELECT pw FROM " + dbOpenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
                cursor = database.rawQuery(sql, null);
                cursor.moveToNext();
                */
                cursor = Database.getInstance().searchPw(id);
                mCurrently_DB_Pw = cursor.getString(0);


                if(!mCurrently_Pw.equals(mCurrently_DB_Pw)){
                    Toast.makeText(getActivity(), "현재 비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    // 현재 비밀번호 불일치


                }


                else if(spaceCheck(mNew_Pw)) {
                    Toast.makeText(getActivity(), "새 비밀번호에 공백을 사용할 수 없습니다!", Toast.LENGTH_SHORT).show();
                    // 비밀번호 스페이스 발생
                }

                else if(!pwCheck){
                    Toast.makeText(getActivity(), "비밀번호는 6~16자 영문 대 소문자, 숫자, 특수문자의 조합을 사용하세요!", Toast.LENGTH_SHORT).show();
                    // 정규식 불일치
                }

                else if(!mNew_Pw.equals(mNew_Confirm_Pw)){
                    Toast.makeText(getActivity(), "새 비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    // 새 비밀번호, 새 비밀번호 확인 불일치
                }

                else{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("비밀번호 수정").setMessage("비밀번호를 수정하시겠습니까?")
                            .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Database.getInstance().updatePw(database, mNew_Pw, id);
                                    intent = new Intent(getActivity(), LoginActivity.class);
                                    intent.putExtra("Logout_Code", logout_Code);
                                    startActivity(intent);
                                    getActivity().finish();
                                    Toast.makeText(getActivity(), "비밀번호가 '" + mNew_Pw + "' 으로\n 수정되었습니다!", Toast.LENGTH_SHORT).show();

                                }

                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();


                }
                cursor.close();
                break;
        }



    }

    public boolean spaceCheck(String spaceCheck) // 문자열 안에 스페이스 체크
    {
        for(int i = 0; i < spaceCheck.length(); i++)
        {

            if(spaceCheck.charAt(i) == ' ')
                return true;

        }
        return false;
    }

}
