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

    boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


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

        Toast.makeText(getApplicationContext(), ""+isAdmin, Toast.LENGTH_SHORT).show();
    }
}
