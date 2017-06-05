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

public class BorrowBookItem extends LinearLayout {

    ImageView iv_read_book;
    TextView tv_book_title;
    TextView tv_book_author;
    Button btn_read_book_return;

    public BorrowBookItem(Context context) {
        super(context);

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.current_read_book, this, false);
        addView(view);

        iv_read_book = (ImageView)findViewById(R.id.iv_read_book);
        tv_book_title = (TextView)findViewById(R.id.tv_read_book_title);
        tv_book_author = (TextView)findViewById(R.id.tv_read_book_author);
        btn_read_book_return = (Button)findViewById(R.id.btn_read_book_return);
    }


    public void setIconResource(int resId){
        this.iv_read_book.setImageResource(resId);
    }

    public void setTitleText(String name)  {
        this.tv_book_title.setText(name);
    }
    public String getTitleText() { return this.tv_book_title.getText().toString(); }


    public void setAuthorText(String name)  {
        this.tv_book_author.setText(name);
    }
    public String getAuthorText() { return this.tv_book_author.getText().toString(); }

    public void setButtonText(String parmaText) { this.btn_read_book_return.setText(parmaText);}
}
