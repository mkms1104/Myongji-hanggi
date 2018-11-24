package com.example.mj.login;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mj.R;
import com.example.mj.db.Database;

/**
 * Created by 우상훈 on 2017-11-01.
 */

public class SearchActivity extends Activity implements View.OnClickListener{
    EditText searchName, searchBirth, searchId, searchNamePw, searchBirthPw;
    String tsearchName, tsearchBirth, tsearchId, tsearchNamePw, tsearchBirthPw;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchName = (EditText) findViewById(R.id.SearchName); // 아이디 찾기의 이름 입력 란
        searchBirth = (EditText) findViewById(R.id.SearchBirth); // 아아디 찾기의 생년월일 입력 란
        searchId = (EditText) findViewById(R.id.SearchId); // 비밀번호 찾기의 아이디 입력 란
        searchNamePw = (EditText) findViewById(R.id.SearchNamePw); // 비밀번호 찾기의 이름 입력 란
        searchBirthPw = (EditText) findViewById(R.id.SearchBirthPw); // 비밀번호 찾기의 생년월일 입력 란
        Button searchIdbtn = (Button) findViewById(R.id.SearchIdbtn);
        Button searchPwbtn = (Button) findViewById(R.id.SearchPwbtn);
        searchIdbtn.setOnClickListener(this);
        searchPwbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SearchIdbtn: // 아이디 찾기 버튼
                tsearchName = searchName.getText().toString();
                tsearchBirth = searchBirth.getText().toString();

                if(tsearchName.length() == 0 || tsearchBirth.length() == 0){
                    Toast.makeText(this, "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "아이디 찾기 공백 발생");
                    return;
                }

                cursor = Database.getInstance().findId(tsearchName, tsearchBirth);
                if(cursor.getCount() != 1){
                    Toast.makeText(this, "입력한 정보가 존재하지 않습니다!", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    Toast.makeText(this, "아이디는 " + cursor.getString(0) + " 입니다!", Toast.LENGTH_SHORT).show();

                }
                cursor.close(); // 꼭 닫아주어야 함
                break;

            case R.id.SearchPwbtn: // 비밀번호 찾기 버튼
                tsearchId = searchId.getText().toString();
                tsearchNamePw = searchNamePw.getText().toString();
                tsearchBirthPw = searchBirthPw.getText().toString();

                if(tsearchId.length() == 0 || tsearchNamePw.length() == 0 || tsearchBirthPw.length() == 0){
                    Toast.makeText(this, "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "비밀번호 찾기 공백 발생");
                    return;
                }

                cursor = Database.getInstance().findPw(tsearchId, tsearchNamePw, tsearchBirthPw);
                if(cursor.getCount() != 1){
                    Toast.makeText(this, "입력한 정보가 존재하지 않습니다!", Toast.LENGTH_SHORT).show();
                    return;

                }
                else {
                    Toast.makeText(this, "비밀번호는 " + cursor.getString(0) + " 입니다!", Toast.LENGTH_SHORT).show();

                }

                cursor.close(); // 꼭 닫아주어야 함
                break;

        }

    }
}
