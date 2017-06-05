package com.modoo.modoobooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinActivity extends AppCompatActivity {

    // findViewById -> BindView 로도 리소스 맵핑 가능
    @BindView(R.id.et_join_id)      EditText et_join_id;
    @BindView(R.id.et_join_pw)      EditText et_join_pw;
    @BindView(R.id.et_join_name)    EditText et_join_name;
    @BindView(R.id.et_join_phone)   EditText et_join_phone;
    @BindView(R.id.et_join_email)   EditText et_join_email;
    boolean isMan = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        // 버터나이프(리소스 바인딩) 라이브러리 바인딩
        ButterKnife.bind(this);
    }


    // 버튼 이벤트 핸들러
    @OnClick({R.id.btn_join_ok, R.id.btn_join_cancel})
    public void onButtonClicked(View paramView){
        switch (paramView.getId()){
            // 회원 가입 버튼
            case R.id.btn_join_ok:
                // 입력 값 EditText 로 부터 받아오기
                String id = et_join_id.getText().toString();
                String pw = et_join_pw.getText().toString();
                String name = et_join_name.getText().toString();
                String phone = et_join_phone.getText().toString();
                String email = et_join_email.getText().toString();

                // 입력정보 토스트로 띄워보기
                Toast.makeText(getApplicationContext(), id +"/"+ pw +"/" + name +"/"
                        + name +"/" + phone +"/" + email + "is_man?" + isMan , Toast.LENGTH_SHORT).show();
                break;

            // 취소 버튼
            case R.id.btn_join_cancel:
                finish();
                break;
        }
    }

    // 성별 라디오 버튼 클릭 시
    @OnClick({R.id.rb_join_man, R.id.rb_join_woman})
    public void onRadioButtonGenderChanged(View paramView){
        switch (paramView.getId()){
            case R.id.rb_join_man:   isMan = true;       break;
            case R.id.rb_join_woman: isMan = false;      break;
        }
    }
}
