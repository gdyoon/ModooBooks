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

    int id;
    TextView tv_wait_user_name;
    TextView tv_wait_user_id;
    Button btn_wait_user_confirm;
    Button btn_wait_user_refuse;


    public WaitUserItem(Context context) {
        super(context);

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.wait_user_item, this, false);
        addView(view);

        tv_wait_user_name = (TextView)findViewById(R.id.tv_wait_user_name);
        tv_wait_user_id = (TextView)findViewById(R.id.tv_wait_user_id);
        btn_wait_user_confirm = (Button)findViewById(R.id.btn_wait_user_confirm);
        btn_wait_user_refuse = (Button)findViewById(R.id.btn_wait_user_refuse);
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Button getConfirmBtn() { return btn_wait_user_confirm; }
    public Button getRefuseBtn() { return btn_wait_user_refuse; }

    public void setUserNameText(String name)  {
        this.tv_wait_user_name.setText(name);
    }
    public String getUserNameText() { return this.tv_wait_user_name.getText().toString(); }


    public void setUserId(String name)  {
        this.tv_wait_user_id.setText(name);
    }
    public String getUserId() { return this.tv_wait_user_id.getText().toString(); }

}
