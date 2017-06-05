package com.modoo.modoobooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.modoo.modoobooks.admin.AdminActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    // 관리자인지 사용자인지 구분하기 위한 플래그 변수
    boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    // 로그인, 회원가입 버튼 클릭 시
    @OnClick({R.id.btn_login, R.id.btn_join})
    public void onButtonClicked(View paramView){
        switch (paramView.getId()){
            case R.id.btn_login:
                if(isAdmin) {
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.btn_join:
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
                break;
        }
    }

    // 관리자 또는 사용자 라디오 버튼 클릭 시
    @OnClick({R.id.rb_join_admin, R.id.rb_join_user})
    public void onRadioButtonClicked(View paramView){
        switch (paramView.getId()){
            case R.id.rb_join_admin:
                isAdmin = true;
                break;
            case R.id.rb_join_user:
                isAdmin = false;
                break;
        }

        // 토스트로 값 확인 하기
        Toast.makeText(getApplicationContext(), ""+isAdmin, Toast.LENGTH_SHORT).show();
    }
}
