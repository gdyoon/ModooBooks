package com.modoo.modoobooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.modoo.modoobooks.admin.AdminActivity;
import com.modoo.modoobooks.db.DB;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_login_id)
    EditText et_login_id;
    @BindView(R.id.et_login_pw)
    EditText et_login_pw;

    // 관리자인지 사용자인지 구분하기 위한 플래그 변수
    boolean isAdmin = false;
    public static boolean loginSucceed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // 임시
        et_login_id.setText("jjy123123");
        et_login_pw.setText("2222");
    }


    // 로그인, 회원가입 버튼 클릭 시
    @OnClick({R.id.btn_login, R.id.btn_join})
    public void onButtonClicked(View paramView){
        switch (paramView.getId()){
            // 로그인
            case R.id.btn_login:
                // 관리자
                if(isAdmin) {
                    String id = et_login_id.getText().toString();
                    String pw = et_login_pw.getText().toString();

                    if(id.equals("admin") && pw.equals("5678")){
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                }
                // 사용자
                else {


                    final String id = et_login_id.getText().toString();
                    String pw = et_login_pw.getText().toString();

                    DB.modoo_login modoo_login = new DB.modoo_login();
                    modoo_login.execute(id, pw);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(loginSucceed)
                            {

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                loginSucceed = false;
                                saveId(id);
                                Toast.makeText(getApplicationContext(),"로그인 성공", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 500);
                }

                break;
            // 회원가입
            case R.id.btn_join:
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void saveId(String paramId){
        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("user_id", paramId);
        editor.commit();
    }

    private String loadId(){
        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ret_id = mPref.getString("user_id", "empty");

        return ret_id;
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
