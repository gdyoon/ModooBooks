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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookManaFragment extends Fragment {

    @BindView(R.id.ll_wait_books)
    LinearLayout ll_wait_books;
    @BindView(R.id.ll_borrow_request)
    LinearLayout ll_borrow_request;

    public BookManaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_mana, container, false);
        ButterKnife.bind(this, view);

        searchedReturnBookItem("제이쿼리 입문 (2017)", "홍길동 저");
        searchedReturnBookItem("안드로이드 정복하기 (2017)", "홍길동 저");

        searchedBorrowRequestBookItem("나미야 잡화점의 기적 (2012)", "히가시노 게이고");
        searchedBorrowRequestBookItem("정의란 무엇인가 (2012)", "마이클 샌더슨");

        return view;
    }

    // 반납 항목 동적 생성
    private void searchedReturnBookItem( String paramTitleText, String paramAuthorText){
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        param.gravity = Gravity.CENTER;
        final BorrowBookItem searchedItem = new BorrowBookItem(getContext());
        searchedItem.setLayoutParams(param);

        searchedItem.setTitleText(paramTitleText);
        searchedItem.setAuthorText(paramAuthorText);
        searchedItem.setButtonText("반납승인");

        searchedItem.getBtn_read_book_return().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedItem.removeAllViews();
                Toast.makeText(getContext(), "반납이 승인 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        searchedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret_title = searchedItem.getTitleText();
                String ret_subtitle = searchedItem.getAuthorText();

                Toast.makeText(getContext(), ret_title + " , " + ret_subtitle, Toast.LENGTH_SHORT).show();
            }
        });

        ll_wait_books.addView(searchedItem);
    }

    // 대출 요청 항목 동적 생성
    private void searchedBorrowRequestBookItem( String paramTitleText, String paramAuthorText){
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        param.gravity = Gravity.CENTER;
        final BorrowBookItem searchedItem = new BorrowBookItem(getContext());
        searchedItem.setLayoutParams(param);

        searchedItem.setTitleText(paramTitleText);
        searchedItem.setAuthorText(paramAuthorText);
        searchedItem.setButtonText("입고");


        searchedItem.getBtn_read_book_return().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedItem.removeAllViews();
                Toast.makeText(getContext(), "입고 처리 완료", Toast.LENGTH_SHORT).show();
            }
        });


        searchedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret_title = searchedItem.getTitleText();
                String ret_subtitle = searchedItem.getAuthorText();

                Toast.makeText(getContext(), ret_title + " , " + ret_subtitle, Toast.LENGTH_SHORT).show();
            }
        });

        ll_borrow_request.addView(searchedItem);
    }
}
