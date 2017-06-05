package com.modoo.modoobooks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 내 정보 수정 액티비티
public class MyInfoActivity extends AppCompatActivity {

    // 각 행에 해당하는 TextView 리소스 가져오기
    @BindView(R.id.tv_my_info_name)             TextView tv_my_info_name;
    @BindView(R.id.tv_my_info_addr)             TextView tv_my_info_addr;
    @BindView(R.id.tv_my_info_phone)            TextView tv_my_info_phone;
    @BindView(R.id.tv_my_info_gender)           TextView tv_my_info_gender;
    @BindView(R.id.tv_my_info_age)              TextView tv_my_info_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        // 버터나이프로 리소스 한꺼번에 맵핑
        ButterKnife.bind(this);
    }

    //
    private TextView getTextViewFromIndex(int options){
        TextView retTextView = null;
        switch (options){
            case 0: retTextView = tv_my_info_name;      break;
            case 1: retTextView = tv_my_info_phone;     break;
            case 2: retTextView = tv_my_info_addr;      break;
            case 3: retTextView = tv_my_info_gender;    break;
            case 4: retTextView = tv_my_info_age;       break;
        }

        return retTextView;
    }

    private String getStringFromIndex(int options){
        String retStr = null;

        switch (options){
            case 0: retStr = "이름";      break;
            case 1: retStr = "연락처";      break;
            case 2: retStr = "주소";      break;
            case 3: retStr = "성별";      break;
            case 4: retStr = "나이";      break;
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
            R.id.rl_my_info_age})
    public void onLayoutNodeInfoClicked(View paramView){
        int options = -1;
        switch (paramView.getId()){
            case R.id.rl_my_info_name:       options = 0;    break;
            case R.id.rl_my_info_phone:      options = 1;    break;
            case R.id.rl_my_info_addr:       options = 2;    break;
            case R.id.rl_my_info_gender:     options = 3;    break;
            case R.id.rl_my_info_age:        options = 4;    break;
        }

        // 옵션 값 플래그를 넘기면
        showEditDialog(options);
    }

}
