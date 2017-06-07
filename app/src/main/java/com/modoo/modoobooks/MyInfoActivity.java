package com.modoo.modoobooks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.modoo.modoobooks.db.DB;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 내 정보 수정 액티비티
public class MyInfoActivity extends AppCompatActivity {

    // 각 행에 해당하는 TextView 리소스 가져오기
    @BindView(R.id.tv_my_info_user_name)        TextView tv_my_info_user_name;
    @BindView(R.id.tv_my_info_user_road)        TextView tv_my_info_user_road;
    @BindView(R.id.tv_my_info_user_borrow)      TextView tv_my_info_user_borrow;
    @BindView(R.id.tv_my_info_name)             TextView tv_my_info_name;
    @BindView(R.id.tv_my_info_addr)             TextView tv_my_info_addr;
    @BindView(R.id.tv_my_info_phone)            TextView tv_my_info_phone;
    @BindView(R.id.tv_my_info_email)            TextView tv_my_info_email;
    @BindView(R.id.tv_my_info_gender)           TextView tv_my_info_gender;
    @BindView(R.id.tv_my_info_age)              TextView tv_my_info_age;

    public static String myInfo = "";
    public static int returnCount = 0;
    public static int borrowCount = 0;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        returnCount = 0;
        borrowCount = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        // 버터나이프로 리소스 한꺼번에 맵핑
        ButterKnife.bind(this);

        String id = loadId();
        Log.d("TOKEN", id);

        DB.modoo_user_modify_get modoo_user_modify_get = new DB.modoo_user_modify_get();
        modoo_user_modify_get.execute(id);

        DB.modoo_user_state_borrow modoo_user_state_borrow = new DB.modoo_user_state_borrow();
        modoo_user_state_borrow.execute(id);

        DB.modoo_user_state_return modoo_user_state_return = new DB.modoo_user_state_return();
        modoo_user_state_return.execute(id);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_my_info_user_road.setText("읽은 책 : " + returnCount);
                tv_my_info_user_borrow.setText("대출 중 : " + borrowCount);

                String[] token = myInfo.split("#");
                tv_my_info_user_name.setText(token[0]);
                tv_my_info_name.setText(token[0]);
                tv_my_info_phone.setText(token[1]);
                tv_my_info_addr.setText(token[2]);
                tv_my_info_email.setText(token[3]);
                tv_my_info_gender.setText(token[4]);

            }
        }, 500);
    }

    private String loadId(){
        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ret_id = mPref.getString("user_id", "empty");

        return ret_id;
    }

    @OnClick(R.id.btn_modify_confirm)
    public void onModifyButtonClicked(){
        String id = loadId();
        String name = tv_my_info_name.getText().toString();
        String addr = tv_my_info_addr.getText().toString();
        String phone = tv_my_info_phone.getText().toString();
        String email = tv_my_info_email.getText().toString();
        String gender = tv_my_info_gender.getText().toString();
        String age = tv_my_info_age.getText().toString();

        DB.modoo_user_modify_info modoo_user_modify_info = new DB.modoo_user_modify_info();
        modoo_user_modify_info.execute(id, name, addr, phone, email, gender);

        finish();
    }

    private TextView getTextViewFromIndex(int options){
        TextView retTextView = null;
        switch (options){
            case 0: retTextView = tv_my_info_name;      break;
            case 1: retTextView = tv_my_info_phone;     break;
            case 2: retTextView = tv_my_info_addr;      break;
            case 3: retTextView = tv_my_info_email;      break;
            case 4: retTextView = tv_my_info_gender;    break;
            case 5: retTextView = tv_my_info_age;       break;
        }

        return retTextView;
    }

    private String getStringFromIndex(int options){
        String retStr = null;

        switch (options){
            case 0: retStr = "이름";      break;
            case 1: retStr = "연락처";      break;
            case 2: retStr = "주소";      break;
            case 3: retStr = "이메일";     break;
            case 4: retStr = "성별";      break;
            case 5: retStr = "나이";      break;

        }


        return retStr;
    }

    // 프로필 수정을 하기 위한 대화상자
    private void showEditDialog(final int options) {

        // 대화상자에 EditText를 넣어서 수정할 수 있게 한다
        final EditText et_modify = new EditText(this);
        et_modify.setBackgroundColor(Color.WHITE);
        et_modify.setHint("수정할 내용을 입력해주세요");
        et_modify.setPadding(100, 50, 0, 0);

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setView(et_modify);
        //alertdialog.setMessage("");

        // 수정 버튼 클릭 시
        alertdialog.setPositiveButton("수정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String retStr = et_modify.getText().toString();
                // 인덱스를 기반으로 텍스트 뷰 리소스를 리턴
                TextView retTextView = getTextViewFromIndex(options);
                // 텍스트 뷰 수정
                retTextView.setText(retStr);
                // 대충 알리기
                Toast.makeText(getApplicationContext(), "수정 완료", Toast.LENGTH_SHORT).show();
            }
        });

        // 취소 버튼 클릭 시
        alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // 대화상자 타이틀을 지정하기 위해 인덱스 기반으로 문자열을 가져옴
        String retStr = getStringFromIndex(options);
        // 대화상자 생성
        AlertDialog alert = alertdialog.create();
        alert.setIcon(R.drawable.img_book);
        alert.setTitle("내 정보 " + retStr + " 수정");
        alert.show();
    }


    // 레이아웃 클릭 시 옵션을 다이얼로그의 인자로 넘김
    @OnClick({R.id.rl_my_info_name,
            R.id.rl_my_info_phone,
            R.id.rl_my_info_addr,
            R.id.rl_my_info_gender,
            R.id.rl_my_info_age,
            R.id.rl_my_info_email})
    public void onLayoutNodeInfoClicked(View paramView){
        int options = -1;
        switch (paramView.getId()){
            case R.id.rl_my_info_name:       options = 0;    break;
            case R.id.rl_my_info_phone:      options = 1;    break;
            case R.id.rl_my_info_addr:       options = 2;    break;
            case R.id.rl_my_info_email:      options = 3;    break;
            case R.id.rl_my_info_gender:     options = 4;    break;
            case R.id.rl_my_info_age:        options = 5;    break;

        }

        // 옵션 값 플래그를 넘기면
        showEditDialog(options);
    }

}
