package com.modoo.modoobooks.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.modoo.modoobooks.R;

/**
 * Created by YOONGOO on 2017-06-05.
 */

public class WaitUserItem extends LinearLayout {

    TextView tv_wait_user_name;
    TextView tv_wait_user_id;


    public WaitUserItem(Context context) {
        super(context);

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.wait_user_item, this, false);
        addView(view);

        tv_wait_user_name = (TextView)findViewById(R.id.tv_wait_user_name);
        tv_wait_user_id = (TextView)findViewById(R.id.tv_wait_user_id);
    }



    public void setUserNameText(String name)  {
        this.tv_wait_user_name.setText(name);
    }
    public String getUserNameText() { return this.tv_wait_user_name.getText().toString(); }


    public void setUserId(String name)  {
        this.tv_wait_user_id.setText(name);
    }
    public String getUserId() { return this.tv_wait_user_id.getText().toString(); }

}
