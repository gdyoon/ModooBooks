package com.modoo.modoobooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by YOONGOO on 2017-06-05.
 */

// 동적 뷰 생성 클래스
public class BorrowBookItem extends LinearLayout {

    // 커스텀 뷰에 사용되는 뷰 목록
    ImageView iv_read_book;
    TextView tv_book_title;
    TextView tv_book_author;
    Button btn_read_book_return;

    public BorrowBookItem(Context context) {
        super(context);

        // 레이아웃 변경을 위한 인플레이터 선언
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        // 뷰를 동적으로 추가
        View view = li.inflate(R.layout.current_read_book, this, false);
        addView(view);

        // 리소스 불러오기
        iv_read_book = (ImageView)findViewById(R.id.iv_read_book);
        tv_book_title = (TextView)findViewById(R.id.tv_read_book_title);
        tv_book_author = (TextView)findViewById(R.id.tv_read_book_author);
        btn_read_book_return = (Button)findViewById(R.id.btn_read_book_return);
    }

    public Button getBtn_read_book_return() {
        return btn_read_book_return;
    }


    // 이미지 리소스 변경
    public void setIconResource(int resId){
        this.iv_read_book.setImageResource(resId);
    }

    // 책 제목 변경
    public void setTitleText(String name)  {
        this.tv_book_title.setText(name);
    }
    // 책 제목 얻어오기
    public String getTitleText() { return this.tv_book_title.getText().toString(); }
    // 책 저자 변경
    public void setAuthorText(String name)  {
        this.tv_book_author.setText(name);
    }
    // 책 저자 문자열 얻어오기
    public String getAuthorText() { return this.tv_book_author.getText().toString(); }

    // 버튼에 들어갈 텍스트 변경 시
    public void setButtonText(String parmaText) { this.btn_read_book_return.setText(parmaText);}
}
