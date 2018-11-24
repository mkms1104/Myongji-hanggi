package com.example.mj.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.mj.appClose.BackPressCloseHandler;
import com.example.mj.db.Database;
import com.example.mj.fragment.BuyFragment;
import com.example.mj.fragment.CartFragment;
import com.example.mj.fragment.FavoriteFragment;
import com.example.mj.R;
import com.example.mj.fragment.MainMenuFragment;
import com.example.mj.fragment.ModifyFragment;
import com.example.mj.fragment.QuestionFragment;
import com.example.mj.fragment.ReviewFragment;
import com.example.mj.fragment.SettingFragment;
import com.example.mj.fragment.StudentMenuFragment;
import com.example.mj.login.LoginActivity;
import com.example.mj.popUp.PopupActivity_3;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv1;
    private BackPressCloseHandler backPressCloseHandler; // 앱 종료 핸들링
    private DrawerLayout drawerLayout;
    private View drawerView;
    Intent intent;

    String id;
    Cursor cursor;
    TextView slideNameTx;
    Button btnLogout;
    FrameLayout mFragmentContainer;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MainMenuFragment mMainMenuFragment;
    CartFragment mCartFragment;
    FavoriteFragment mFavoriteFragment;
    ModifyFragment mModifyFragment;
    QuestionFragment mQuestionFragment;
    ReviewFragment mReviewFragment;
    SettingFragment mSettingFragment;
    BuyFragment mBuyFragment;

    private boolean isMainMenu;
    // public static final int REQUEST_CART_ACTIVITY = 1548;
    private final String logout_Code = "t";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slideNameTx = (TextView) findViewById(R.id.slide_nameTx); // 슬라이드 메뉴의 이름 란

        id = LoginActivity.sloginId; // 기본키 값 아이디를 가져옴
        cursor = Database.getInstance().searchName(id);
        slideNameTx.setText(cursor.getString(0) + "님 환영합니다.\n 맛있는 한끼 되세요.");
        cursor.close();


        btnLogout = (Button) findViewById(R.id.btnLogout); // 로그아웃 버튼
        btnLogout.setOnClickListener(this);

        backPressCloseHandler = new BackPressCloseHandler(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
        tv1 = (TextView)findViewById(R.id.mainText);

        mFragmentContainer = (FrameLayout)findViewById(R.id.fragment_container);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        mMainMenuFragment = new MainMenuFragment();
        mCartFragment = new CartFragment();
        mFavoriteFragment = new FavoriteFragment();
        mModifyFragment = new ModifyFragment();
        mReviewFragment = new ReviewFragment();
        mSettingFragment = new SettingFragment();
        mQuestionFragment = new QuestionFragment();
        mBuyFragment = new BuyFragment();

        fragmentTransaction.replace(R.id.fragment_container,mMainMenuFragment);
        fragmentTransaction.commit();
        isMainMenu = true;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override // 상태바커스텀
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false); //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false); //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false); //홈 아이콘을 숨김처리합니다.

        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.layout_actionbar, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnMenu: // 액션 바 슬라이드 메뉴 버튼
                drawerLayout.openDrawer(drawerView);
                break;

            case R.id.btnCart: // 액션 바 카트 버튼
                drawerLayout.closeDrawer(drawerView);
                changeFragment(mCartFragment, false);
                break;

            // 슬라이드 바의 여러 메뉴들
            case R.id.btnMenuFavorite:
                drawerLayout.closeDrawer(drawerView);
                changeFragment(mFavoriteFragment, false);
                break;

            case R.id.btnMenuStudent:
                mMainMenuFragment.selectStudent();
                if (!isMainMenu) {
                    changeFragment(mMainMenuFragment, true);
                }
                drawerLayout.closeDrawer(drawerView);
                mMainMenuFragment.setCurrentPage(0);
                break;

            case R.id.btnMenuTeacher:
                mMainMenuFragment.selectTeacher();
                if(!isMainMenu) {
                    changeFragment(mMainMenuFragment, true);
                }
                drawerLayout.closeDrawer(drawerView);
                mMainMenuFragment.setCurrentPage(1);
                break;

            case R.id.btnMenuBuylist:
                mMainMenuFragment.selectBuylist();
                if(!isMainMenu) {
                    changeFragment(mMainMenuFragment, true);
                }
                drawerLayout.closeDrawer(drawerView);
                mMainMenuFragment.setCurrentPage(2);
                break;

            case R.id.btnMenuReview:
                drawerLayout.closeDrawer(drawerView);
                changeFragment(mReviewFragment,false);
                break;

            case R.id.btnMenuQuestion:
                drawerLayout.closeDrawer(drawerView);
                changeFragment(mQuestionFragment,false);
                break;

            case R.id.btnMenuSetting:
                drawerLayout.closeDrawer(drawerView);
                changeFragment(mSettingFragment,false);
                break;

            case R.id.btnMenuModify:
                drawerLayout.closeDrawer(drawerView);
                changeFragment(mModifyFragment,false);
                break;

            case R.id.main_logo:
                mMainMenuFragment.selectStudent();
                if (!isMainMenu) {
                    changeFragment(mMainMenuFragment, true);
                }
                drawerLayout.closeDrawer(drawerView);
                mMainMenuFragment.setCurrentPage(0);
                break;

            case R.id.btnLogout:

                new AlertDialog.Builder(this)
                        .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.putExtra("Logout_Code", logout_Code);
                                startActivity(intent);
                                finish();

                            }

                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();

                break;


        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { // back key 제어
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(!isMainMenu){
                    changeFragment(mMainMenuFragment,true);

                }else{

                    backPressCloseHandler.onBackPressed();
                }
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    private void changeFragment(Fragment fragment,boolean isMain){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
        isMainMenu = isMain;
    }

    public void openCartFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,mCartFragment);
        fragmentTransaction.commit();
        isMainMenu = false;
    }

    public void openBuyFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,mBuyFragment);
        fragmentTransaction.commit();
        isMainMenu = false;
    }



}
