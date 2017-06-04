package com.modoo.modoobooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by YOONGOO on 2017-06-05.
 */

public class BorrowBookItem extends LinearLayout {

    TextView tv_book_title;
    TextView tv_book_author;

    public BorrowBookItem(Context context) {
        super(context);

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.current_read_book, this, false);
        addView(view);

        tv_book_title = (TextView)findViewById(R.id.tv_read_book_title);
        tv_book_author = (TextView)findViewById(R.id.tv_read_book_author);
    }


    public void setTitleText(String name)  {
        this.tv_book_title.setText(name);
    }
    public String getTitleText() { return this.tv_book_title.getText().toString(); }


    public void setAuthorText(String name)  {
        this.tv_book_author.setText(name);
    }
    public String getAuthorText() { return this.tv_book_author.getText().toString(); }

}
