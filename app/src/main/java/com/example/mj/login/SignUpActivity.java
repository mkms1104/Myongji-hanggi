package com.example.mj.login;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.mj.R;
import com.example.mj.db.Database;
import com.example.mj.popUp.PopupActivity_1;
import com.example.mj.popUp.PopupActivity_2;
import com.example.mj.spinnerAdapter.NothingSelectedSpinnerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 우상훈 on 2017-11-01.
 */

public class SignUpActivity extends Activity implements View.OnClickListener{
    SQLiteDatabase database;
    EditText id, pw, pwConfirm, birth, name, phoneNum;
    String tid, tpw, tpwConfirm, tname, tbirth, tgender, tphoneNum;
    CheckBox inform_check;
    Intent intent;
    Cursor cursor;

    boolean pwCheck;
    Spinner spinner;
    ArrayAdapter adapterSpinner;

    private final String closePopup_1 = "Close Popup_1"; // 이용약관 팝업
    private final String closePopup_2 = "Close Popup_2"; // 정보제공 팝업
    String result_1, result_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        database = Database.getInstance().open(this);
        Log.d("minsu", "데이터베이스 사용 가능");

        id = (EditText) findViewById(R.id.InputId);
        pw = (EditText) findViewById(R.id.InputPw);
        pwConfirm = (EditText) findViewById(R.id.InputConfirmPw);
        name = (EditText) findViewById(R.id.InputName);
        birth = (EditText) findViewById(R.id.InputBirth);
        phoneNum = (EditText) findViewById(R.id.PhoneNumber);
        inform_check = (CheckBox) findViewById(R.id.inform_check); // 이용약관 및 정보 동의
        inform_check.setOnClickListener(this);

        Button joinBtn = (Button) findViewById(R.id.Joinbtn); // 회원가입
        Button watchBtn1 = (Button) findViewById(R.id.watch_btn1); // 이용약관 보기
        Button watchBtn2 = (Button) findViewById(R.id.watch_btn2); // 개인정보제공 보기
        joinBtn.setOnClickListener(this);
        watchBtn1.setOnClickListener(this);
        watchBtn2.setOnClickListener(this);

        List<String> data = new ArrayList<>();
        data.add("남자");
        data.add("여자");

        spinner = (Spinner)findViewById(R.id.spinner);
        adapterSpinner = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, data);
        adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(new NothingSelectedSpinnerAdapter(adapterSpinner, R.layout.spinner_row_nothing_selected, this));

        // 이벤트 처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(SignUpActivity.this, "선택된 아이템 : " + spinner.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                if(spinner.getItemAtPosition(position) != null) { // 처음 기본 텍스트(성별) 상태이면 if 문 실행X
                    tgender = spinner.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    } //onCreate() 종료

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                result_1 = data.getStringExtra("result_1"); // 이용약관
                result_1.toString();
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                result_2 = data.getStringExtra("result_2");
                result_2.toString();
            }
        }

        if(result_1 != null && result_2 != null ) {
            if (result_1.equals(closePopup_1) && result_2.equals(closePopup_2)) { // 정보 제공, 이용 약관 모두 확인 시
                inform_check.setChecked(true); // 체크 박스 체크
                inform_check.setEnabled(false); // 체크 박스 사용 불가 상태
            }

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Joinbtn: // 회원가입 버튼

                tid = id.getText().toString();
                tpw = pw.getText().toString();
                tpwConfirm = pwConfirm.getText().toString();
                tname = name.getText().toString();
                tbirth = birth.getText().toString();
                tphoneNum = phoneNum.getText().toString();
                pwCheck = Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{6,16}$", tpw);

                if(tid.trim().length() == 0 || tpw.trim().length() == 0 || tpwConfirm.trim().length() == 0 || tbirth.trim().length() == 0 || tname.trim().length() == 0 || tphoneNum.trim().length() == 0){
                    Toast.makeText(this, "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "공백 발생");
                    return;
                }

                if(tgender == null){
                    Toast.makeText(this, "성별을 선택해주세요!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "성별 미 선택");
                    return;
                }


                cursor = Database.getInstance().searchId(tid);
                if(cursor.getCount() != 0){
                    Toast.makeText(this, "존재하는 아이디입니다!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "아이디 중복");

                }

                else if(!tpw.equals(tpwConfirm)){
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();


                }

                else if(!pwCheck){
                    Toast.makeText(this, "비밀번호는 6~16자 영문 대 소문자, 숫자, 특수문자의 조합을 사용하세요!", Toast.LENGTH_SHORT).show();

                }

                else if(spaceCheck(tpw)){
                    Toast.makeText(this, "비밀번호에 공백을 사용할 수 없습니다!", Toast.LENGTH_SHORT).show();

                }

                else if(inform_check.isChecked() == false){
                    Toast.makeText(this, "이용약관 및 사용자 정보제공 \n동의는 필수입니다!", Toast.LENGTH_SHORT).show();

                }

                else{
                    Database.getInstance().insert(database, tid, tpw, tname, tbirth, tgender, tphoneNum);
                    Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show();
                    finish();
                    Log.d("minsu", "회원가입 완료");

                }

                cursor.close(); // 꼭 닫아주어야 함
                break;

            case R.id.watch_btn1: // 이용약관 보기 버튼
                intent = new Intent(this, PopupActivity_1.class);
                startActivityForResult(intent, 1); // requestCode 1

                break;

            case R.id.watch_btn2: // 정보제공 보기 버튼
                intent = new Intent(this, PopupActivity_2.class);
                startActivityForResult(intent, 2); // requestCode 2

                break;

            case R.id.inform_check:
                if(result_1 == null || result_2 == null){ // 내용 미 확인 후 동의 체크 시
                    inform_check.setChecked(false);
                    Toast.makeText(this, "이용약관 및 개인정보정책 내용을 \n확인해주세요!", Toast.LENGTH_SHORT).show();

                }

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
