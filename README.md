# [팀 프로젝트] 명지한끼
---
<div>
<img src="https://user-images.githubusercontent.com/19260410/49719247-f67a2600-fc9f-11e8-9e11-d0f7b1000c0d.PNG" width="250">
<img src="https://user-images.githubusercontent.com/19260410/49719192-c5015a80-fc9f-11e8-9ada-3ce0f329b4da.PNG" width="250">
<img src="https://user-images.githubusercontent.com/19260410/49719198-c92d7800-fc9f-11e8-8dc9-a34bd3a7dd3f.PNG" width="250">
</div>

## [ [시연영상](https://youtu.be/7F-69xNvecU) ]
<br></br>

## 설명
---
- 앱을 통해 당일 학생과 교직원 식당의 메뉴 정보를 알 수 있다.
- 원하는 메뉴를 선택 후 장바구니에 담아놓거나 결제할 수 있다. 
<br></br>

## 지원
---
- Git
- Android Emulator
<br></br>

## 사용 기술
---
- Java
- Sqlite
<br></br>

## 개발 환경
---
- Window OS
- Android Studio 2.3.3
<br></br>

## 개인 역할
---
- Sqlite를 이용한 로그인, 회원가입, 아이디/비밀번호 찾기 구현.
- 장바구니, 고객문의 페이지 구현.

### * 회원가입 유효성 검사
~~~java
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

                if(tid.trim().length() == 0 || tpw.trim().length() == 0 || tpwConfirm.trim().length() == 0 || tbirth.trim().length() == 			0 || tname.trim().length() == 0 || tphoneNum.trim().length() == 0){
                    Toast.makeText(this, "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "공백 발생");
                    return;
                }
	}
}
~~~
### * 아이디 검색  처리
~~~java
public Cursor searchId(String id){
     sql = "SELECT id FROM " + mDBopenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
     
     // 입력한 아이디를 조건으로 테이블에서 아이디를 검색
     c = mDB.rawQuery(sql, null);
     c.moveToNext();

     return c; // 커서 리턴
}
~~~
