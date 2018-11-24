package com.example.mj.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mj.R;

/**
 * Created by minsu on 2017-11-28.
 */

public class Database{
    SQLiteDatabase mDB = null;
    DBOpenHelper mDBopenHelper = null;
    Cursor c;
    String sql;

    private static Database singletonDB;

    private Database(){

    }

    public static Database getInstance(){
        if(singletonDB == null) {
            singletonDB = new Database();
        }
        return singletonDB;

    }

    // 수정가능한 데이터베이스를 생성
    public SQLiteDatabase open(Context context){
        if(mDBopenHelper == null){
            mDBopenHelper = new DBOpenHelper(context);
            mDB = mDBopenHelper.getWritableDatabase();

        }
        return mDB;

    }

    // 아이디 검색
    public Cursor searchId(String id){
        sql = "SELECT id FROM " + mDBopenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
        // 입력한 아이디를 조건으로 테이블에서 아이디를 검색
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c; // 커서 리턴
    }

    // 비밀번호 검색
    public Cursor searchPw(String id){
        sql = "SELECT pw FROM " + mDBopenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
        // 입력한 아이디를 조건으로 테이블에서 비밀번호를 검색
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c; // 커서 리턴

    }

    // 이름 검색
    public Cursor searchName(String id){
        sql = "SELECT name FROM " + mDBopenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
        // 입력한 아이디를 조건으로 테이블에서 이름을 검색
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c; // 커서 리턴

    }

    //핸드폰 번호 검색
    public Cursor searchPhone(String id){
        sql = "SELECT phone FROM " + mDBopenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'";
        // 입력한 아이디를 조건으로 테이블에서 핸드폰번호를 검색
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c; // 커서 리턴

    }


    // 아이디 찾기
    public Cursor findId(String name, String brith){
        sql = "SELECT id FROM " + mDBopenHelper.DB_TABLE_USER + " WHERE name = " + "'" + name + "'" + " and " + "birth = " + "'" + brith + "'";
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c;
    }


    // 비밀번호 찾기
    public Cursor findPw(String id, String name, String birth){
        sql = "SELECT pw FROM " + mDBopenHelper.DB_TABLE_USER + " WHERE id = " + "'" + id + "'" + " and " + "name = "+ "'" + name + "'" + " and " + "birth = " + "'" + birth + "'";
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c;
    }


    // 회원가입(데이터 추가)
    public void insert(SQLiteDatabase db, String id, String pw, String name, String birth, String gender, String phone){
        String sql = "INSERT INTO " + mDBopenHelper.DB_TABLE_USER + "(id, pw, name, birth, gender, phone)" + "values" + "(" + "'" + id + "'" + ",'" + pw + "'" + ",'" + name + "'" + ",'" + birth + "'" + ",'" + gender + "'" + ",'" + phone + "'" + ")";
        db.execSQL(sql);
        //db.close();
    }

    // 이름 수정
    public void updateName(SQLiteDatabase db, String name, String id){
        String sql = "UPDATE " + mDBopenHelper.DB_TABLE_USER + " SET name='" + name + "' WHERE id='" + id + "';" ;
        db.execSQL(sql);
        // db.close();


    }

    // 핸드폰 번호 수정
    public void updatePhone(SQLiteDatabase db, String phoneNum, String id){
        String sql = "UPDATE " + mDBopenHelper.DB_TABLE_USER + " SET phone='" + phoneNum + "' WHERE id='" + id + "';" ;
        db.execSQL(sql);
        // db.close();

    }

    // 비밀번호 수정
    public void updatePw(SQLiteDatabase db, String pw, String id){
        String sql = "UPDATE " + mDBopenHelper.DB_TABLE_USER + " SET pw='" + pw + "' WHERE id='" + id + "';" ;
        db.execSQL(sql);
        // db.close();
    }

    // 장바구니 메뉴 추가
    public void CartMenu(SQLiteDatabase db, String code, String name, int img, int price, String id, int count){
        String sql = "INSERT INTO " + mDBopenHelper.DB_TABLE_CART + "(code, name, img, price, id, count)" + "values" + "(" + "'" + code + "'" + ",'" + name + "'" + ",'" + img + "'" + ",'" + price + "'" + ",'" + id + "'" + ",'" + count + "'" + ")";
        db.execSQL(sql);
    }

    // 미리 저장되어 있는 장바구니 메뉴의 이미지, 이름, 가격
    public Cursor searchCartMenu(String code){
        sql = "SELECT img, name, price FROM " + mDBopenHelper.DB_TABLE_CART + " WHERE code = " + "'" + code + "'";
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c;

    }

    // 학생 메뉴 추가
    public void studentMenu(SQLiteDatabase db, String code, String name, int img, int price, int count){
        String sql = "INSERT INTO " + mDBopenHelper.DB_TABLE_STUDENT_MENU + "(code, name, img, price, count)" + "values" + "(" + "'" + code + "'" + ",'" + name + "'" + ",'" + img + "'" + ",'" + price + "'" + ",'" + count + "'" + ")";
        db.execSQL(sql);
    }


    // 미리 저장되어 있는 학생 메뉴의 이미지
    public Cursor searchStudentMenuImage(String code){
        sql = "SELECT img FROM " + mDBopenHelper.DB_TABLE_STUDENT_MENU + " WHERE code = " + "'" + code + "'";
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c;

    }

    // 미리 저장되어 있는 학생 메뉴의 이름
    public Cursor searchStudentMenuName(String code){
        sql = "SELECT name FROM " + mDBopenHelper.DB_TABLE_STUDENT_MENU + " WHERE code = " + "'" + code + "'";
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c;

    }

    // 미리 저장되어 있는 학생 메뉴의 가격
    public Cursor searchStudentMenuPrice(String code){
        sql = "SELECT price FROM " + mDBopenHelper.DB_TABLE_STUDENT_MENU + " WHERE code = " + "'" + code + "'";
        c = mDB.rawQuery(sql, null);
        c.moveToNext();

        return c;

    }

}
