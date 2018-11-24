package com.example.mj.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mj.R;
import com.example.mj.db.Database;
import com.example.mj.main.MainActivity;

public class LoginActivity extends Activity implements View.OnClickListener {

    Button btnLogin;
    TextView btnSignup, btnSearch;
    EditText loginId, loginPw;
    String id, pw, Logout_Code;
    CheckBox checkBox;
    Intent intent;
    Cursor cursor;
    SharedPreferences autoLogin;
    SharedPreferences.Editor editor;

    public static String sloginId; // 기본키

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginId = (EditText) findViewById(R.id.LoginId);
        loginPw = (EditText) findViewById(R.id.LoginPw);

        btnLogin = (Button) findViewById(R.id.login);
        btnSignup = (TextView) findViewById(R.id.signup);
        btnSearch = (TextView) findViewById(R.id.search);
        checkBox = (CheckBox) findViewById(R.id.autoCheck);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        autoLogin = getSharedPreferences("autoLogin", 0);
        editor = autoLogin.edit();

        intent = getIntent();
        Logout_Code = intent.getStringExtra("Logout_Code");
        Log.d("minsu", Logout_Code);

        if(autoLogin.getBoolean("chk_auto", false)){
//             getBoolean은 Boolean타입의 데이터를 가져옴
            // 초기 실행 시에는 설정된 값이 없음
            // 그러므로 기본값인 false가 반환되어 처음 실행시에는 if문 동작하지 않음

            loginId.setText(autoLogin.getString("ID", ""));
            loginPw.setText(autoLogin.getString("PW", ""));
            // getString은 ID값과 PW값을 불러와서 EditText에 setText로 적용

            sloginId = autoLogin.getString("ID","");
            Log.d("minsu", sloginId);

            checkBox.setChecked(true);
            // 자동로그인이 활성화 되었으므로 CheckBox도 활성화 표시

            if(Logout_Code.equals("f")){ // 스플래쉬 액티비티에서 넘어올 때
                intent = new Intent(this, MainActivity.class);
                startActivity(intent); // 앱 실행 시 로그인 없이 메인 액티비티로 이동
                finish();
                // 자동로그인 후 로그인 액티비티를 종료하여 메인 액티비티에서 뒤로가기 버튼을 누를 시
                // 로그인 화면이 아닌 앱을 종료하게 된다.
            }

            else{ // 매인 액티비티에서 로그아웃 할 때
                loginId.setText(autoLogin.getString("", ""));
                loginPw.setText(autoLogin.getString("", ""));
                checkBox.setChecked(false);

                editor.clear();
                editor.commit();
                return;

            }

            //자동 로그인이 활성화 된 상태에서 메인 액티비티에서 다시 로그인 액티비티로 넘어오면
            //if 문에 의해 다시 메인 액티비티가 실행되기 때문에 오류가 생김.

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login: // 로그인 버튼
                id = loginId.getText().toString();
                pw = loginPw.getText().toString();

                if(id.length() == 0 || pw.length() == 0){ // 아이디 또는 비밀번호가 입력이 안된 경우
                    Toast.makeText(this, "아이디 또는 비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                cursor = Database.getInstance().searchId(id);
                if(cursor.getCount() != 1){
                    // 데이터베이스에 존재한다면 무조건 카운트가 1이 되야 하는데 그렇지 않을 경우(존재X)
                    Toast.makeText(this, "존재하지 않는 아이디입니다!", Toast.LENGTH_SHORT).show();
                    return;

                }

                cursor = Database.getInstance().searchPw(id);
                if(!pw.equals(cursor.getString(0))){
                    // 입력한 비밀번호와 데이터베이스에 저장된 비밀번호가 일치하지 않을 경우
                    Toast toast = Toast.makeText(this, "비밀번호가 틀렸습니다!", Toast.LENGTH_SHORT);
                    toast.show();

                }else{ // 로그인이 성공할 수 있는 조건이 만족하면서

                    if(checkBox.isChecked()){ // 체크박스가 체크되어 있으면


                        editor.putString("ID", id);
                        editor.putString("PW", pw);
                        editor.putBoolean("chk_auto", true);
                        editor.commit();
                        cursor = Database.getInstance().searchName(id);
                        String name = cursor.getString(0);
                        Toast.makeText(this, name + "님 환영합니다!", Toast.LENGTH_SHORT).show();
                    }else{ // 아니면

                        editor.clear();
                        editor.commit();

                        cursor = Database.getInstance().searchName(id);
                        String name = cursor.getString(0);
                        Toast.makeText(this, name + "님 환영합니다!", Toast.LENGTH_SHORT).show();

                    }

                    // 체크 박스 여부와 상관없이 로그인 성공 시 메인화면으로 엑티비티 이동

                    sloginId = id; // 로그인에 성공한 아이디를 저장한다.
                    // Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    // 로그인 후 로그인 액티비티티를종료하여 메인 액티비티에서 뒤로가기 버튼을 누를 시
                    // 로그인 화면이 아닌 앱을 종료하게 된다.


                }
                cursor.close(); // 꼭 닫아주어야 함
                break;
            case R.id.signup: // 회원가입 버튼
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.search: // 찾기 버튼
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
