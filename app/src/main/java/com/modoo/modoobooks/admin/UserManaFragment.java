package com.modoo.modoobooks.admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.modoo.modoobooks.BorrowBookItem;
import com.modoo.modoobooks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserManaFragment extends Fragment {
    @BindView(R.id.ll_wait_users)
    LinearLayout ll_wait_users;

    public UserManaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_mana, container, false);
        ButterKnife.bind(this, view);

        createWaitUser("홍길동", "bon92");
        createWaitUser("김동민", "abc123");
        createWaitUser("윤기돈", "gdyoon");

        return view;
    }

    // 대출 책에 대한 동적 뷰 생성
    private void createWaitUser(String paramNameText, String paramIdText){
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        param.gravity = Gravity.CENTER;
        final WaitUserItem waitUserItem = new WaitUserItem(getContext());
        waitUserItem.setLayoutParams(param);

        waitUserItem.setUserNameText(paramNameText);
        waitUserItem.setUserId(paramIdText);

        waitUserItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret_name = waitUserItem.getUserNameText();
                String ret_id = waitUserItem.getUserId();
            }
        });

        ll_wait_users.addView(waitUserItem);
    }

}
