package com.modoo.modoobooks.admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.modoo.modoobooks.BorrowBookItem;
import com.modoo.modoobooks.R;

import java.util.ArrayList;
import java.util.HashMap;

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

    //HashMap<Integer, String[]> dataList = new HashMap<>();

    ArrayList<String[]> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_mana, container, false);
        ButterKnife.bind(this, view);

        dataList.add(new String[]{"0","김동민", "jjy123123"});
        dataList.add(new String[]{"1","윤기돈", "bon92"});
        dataList.add(new String[]{"2","장삐쭈", "kgh1185"});

        for(int i=0; i<dataList.size(); i++){
            String[] values = dataList.get(i);
            createWaitUser(Integer.parseInt(values[0]), values[1], values[2]);
        }

        return view;
    }


    private void refreshLayout(){
        ll_wait_users.removeAllViews();

        for(int i=0; i<dataList.size(); i++){
            String[] values = dataList.get(i);
            createWaitUser(Integer.parseInt(values[0]), values[1], values[2]);
        }
    }

    // 대출 책에 대한 동적 뷰 생성
    private void createWaitUser(int id, String paramNameText, String paramIdText){
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        param.gravity = Gravity.CENTER;
        final WaitUserItem waitUserItem = new WaitUserItem(getContext());
        waitUserItem.setLayoutParams(param);
        waitUserItem.setId(id);
        waitUserItem.setUserNameText(paramNameText);
        waitUserItem.setUserId(paramIdText);

        // 승인버튼 클릭 시
        waitUserItem.getConfirmBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "승인 되었습니다.", Toast.LENGTH_SHORT).show();

                dataList.remove(waitUserItem.getId());
                refreshLayout();
            }
        });

        // 거절버튼 클릭 시
        waitUserItem.getRefuseBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "거절 되었습니다.", Toast.LENGTH_SHORT).show();

                for(int i=0; i<dataList.size(); i++){
                }

                refreshLayout();
            }
        });

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
