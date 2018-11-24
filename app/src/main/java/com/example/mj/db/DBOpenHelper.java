package com.example.mj.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by minsu on 2017-11-09.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "User.db";
    public static final String DB_TABLE_USER = "User";
    public static final String DB_TABLE_STUDENT_MENU = "StudentMenu";
    public static final String DB_TABLE_CART = "Cart";
    static final int DB_VERSION = 2;

    public DBOpenHelper (Context context){
        // 생성자를 이용하여 클래스 사용 시 기본적으로 화면의 제어권자를 입력받는다.
        super(context, DB_NAME, null, DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스 필드 변경 시 앱 삭제 후 재설치하자!
        db.execSQL("CREATE TABLE " + DB_TABLE_USER + "(" +
                "number INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT," +
                "pw TEXT," +
                "name TEXT," +
                "birth TEXT," +
                "gender TEXT," +
                "phone TEXT)");

        Log.d("user Table ","유저 정보 테이블 생성");

        db.execSQL("CREATE TABLE " + DB_TABLE_STUDENT_MENU + "(" +
                "number INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code TEXT," +
                "name TEXT," +
                "img TEXT," +
                "price INTEGER," +
                "count INTEGER)");

        Log.d("menu Table ","메뉴 테이블 생성");


        db.execSQL("CREATE TABLE " + DB_TABLE_CART + "(" +
                "number INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code TEXT," +
                "name TEXT," +
                "img TEXT," +
                "price INTEGER," +
                "id TEXT," +
                "count INTEGER)");

        Log.d("Cart Table ","장바구니 테이블 생성");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        if(oldVersion == 1 && newVersion == 2){
            Cursor c ;
            ArrayList<Bundle> bundles = new ArrayList<Bundle>();

            c= db.query(DBOpenHelper.DB_TABLE_NAME,
                    new String[]{"_number","name","id","pw","birth"},
                    null,
                    null,
                    null,
                    null,
                    "_number ASC");


            if(c != null){
                while(c.moveToNext()){
                    Bundle bundleData = new Bundle();
                    bundleData.putString("_number",c.getString(0));
                    bundleData.putString("name",c.getString(1));
                    bundleData.putString("id",c.getString(2));
                    bundleData.putString("pw",c.getString(3));
                    bundleData.putString("birth",c.getString(4));
                    bundles.add(bundleData);

                }

                c.close();
                db.execSQL("DROP TABLE IF EXISTS" + DB_TABLE_NAME);

                onCreate(db);

                for(Bundle bundleData : bundles){
                    ContentValues values = new ContentValues();
                    values.put("number", bundleData.getString("_number"));
                    values.put("name", bundleData.getString("name"));
                    values.put("id", bundleData.getString("id"));
                    values.put("pw", bundleData.getString("pw"));
                    values.put("birth", bundleData.getString("birth"));

                    db.insert(DB_TABLE_NAME,null,values);

                }
            }
        }

        else {
            db.execSQL("DROP TABLE IF EXISTS" + DB_TABLE_NAME);
            onCreate(db);

        }
        */
    }

}

