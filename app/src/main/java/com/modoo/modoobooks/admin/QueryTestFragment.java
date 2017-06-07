package com.modoo.modoobooks.admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.modoo.modoobooks.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryTestFragment extends Fragment {

    @BindView(R.id.btn_query_modoo_login)                   Button btn_query_modoo_login;
    @BindView(R.id.btn_query_modoo_user_modify_info)        Button btn_query_modoo_user_modify_info;
    @BindView(R.id.btn_query_modoo_user_book_author)        Button btn_query_modoo_user_book_author;
    @BindView(R.id.btn_query_modoo_user_book_name)          Button btn_query_modoo_user_book_name;
    @BindView(R.id.btn_query_modoo_user_book_isbn)          Button btn_query_modoo_user_book_isbn;
    @BindView(R.id.btn_query_modoo_user_vm_around)          Button btn_query_modoo_user_vm_around;
    @BindView(R.id.btn_query_modoo_user_vm_local)           Button btn_query_modoo_user_vm_local;
    @BindView(R.id.btn_query_modoo_user_vm_id)              Button btn_query_modoo_user_vm_id;
    @BindView(R.id.btn_query_modoo_user_state_borrow)       Button btn_query_modoo_user_state_borrow;
    @BindView(R.id.btn_query_modoo_user_state_return)       Button btn_query_modoo_user_state_return;
    @BindView(R.id.btn_query_modoo_admin_user_assign)       Button btn_query_modoo_admin_user_assign;
    @BindView(R.id.btn_query_modoo_admin_user_refuse)       Button btn_query_modoo_admin_user_refuse;
    @BindView(R.id.btn_query_modoo_admin_return_confirm)    Button btn_query_modoo_admin_return_confirm;
    @BindView(R.id.btn_query_modoo_admin_state_borrow)      Button btn_query_modoo_admin_state_borrow;
    @BindView(R.id.btn_query_modoo_admin_state_return)      Button btn_query_modoo_admin_state_return;
    @BindView(R.id.btn_query_modoo_admin_stat_month_borrow) Button btn_query_modoo_admin_stat_month_borrow;
    @BindView(R.id.btn_query_modoo_admin_stat_month_return) Button btn_query_modoo_admin_stat_month_return;

    @OnClick({
            R.id.btn_query_modoo_login,
            R.id.btn_query_modoo_user_modify_info,
            R.id.btn_query_modoo_user_book_author,
            R.id.btn_query_modoo_user_book_name,
            R.id.btn_query_modoo_user_book_isbn,
            R.id.btn_query_modoo_user_vm_around,
            R.id.btn_query_modoo_user_vm_local,
            R.id.btn_query_modoo_user_vm_id,
            R.id.btn_query_modoo_user_state_borrow,
            R.id.btn_query_modoo_user_state_return,
            R.id.btn_query_modoo_admin_user_assign,
            R.id.btn_query_modoo_admin_user_refuse,
            R.id.btn_query_modoo_admin_return_confirm,
            R.id.btn_query_modoo_admin_state_borrow,
            R.id.btn_query_modoo_admin_state_return,
            R.id.btn_query_modoo_admin_stat_month_borrow,
            R.id.btn_query_modoo_admin_stat_month_return
    })
    public void onButtonClicked(View paramView){
        switch (paramView.getId()) {
            case R.id.btn_query_modoo_login:
                break;
            case R.id.btn_query_modoo_user_modify_info:
                break;
            case R.id.btn_query_modoo_user_book_author:
                break;
            case R.id.btn_query_modoo_user_book_name:
                break;
            case R.id.btn_query_modoo_user_book_isbn:
                break;
            case R.id.btn_query_modoo_user_vm_around:
                break;
            case R.id.btn_query_modoo_user_vm_local:
                break;
            case R.id.btn_query_modoo_user_vm_id:
                break;
            case R.id.btn_query_modoo_user_state_borrow:
                break;
            case R.id.btn_query_modoo_user_state_return:
                break;
            case R.id.btn_query_modoo_admin_user_assign:
                break;
            case R.id.btn_query_modoo_admin_user_refuse:
                break;
            case R.id.btn_query_modoo_admin_return_confirm:
                break;
            case R.id.btn_query_modoo_admin_state_borrow:
                break;
            case R.id.btn_query_modoo_admin_state_return:
                break;
            case R.id.btn_query_modoo_admin_stat_month_borrow:
                break;
            case R.id.btn_query_modoo_admin_stat_month_return:
                break;
        }
    }


    public QueryTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat_mana, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
