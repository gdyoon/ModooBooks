package com.modoo.modoobooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.sp_search_category)
    Spinner sp_search_category;
    @BindView(R.id.ll_searched_item)
    LinearLayout ll_searched_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initializeView();
    }

    private void initializeView() {
        List<String> list = new ArrayList<>();
        list.add("자판기");
        list.add("도서");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_search_category.setAdapter(adapter);
    }

    @OnClick(R.id.btn_search_category)
    public void onSearchButtonClicked(){

        // 다시 지우고 갱신
        ll_searched_item.removeAllViews();

        switch (sp_search_category.getSelectedItemPosition())
        {
            // 자판기 검색
            case 0:
                searchedItem(R.drawable.img_vending_machine, "충북대학교 자판기", "청주시 서원구 충대로 1");
                searchedItem(R.drawable.img_vending_machine, "시계탑 자판기", "충북 청주시 서원구 사창동");
                searchedItem(R.drawable.img_vending_machine, "시외버스 터미널 자판기", "충청북도 청주시 흥덕구 풍산로 6");
                break;

            // 도서 검색
            case 1:
                searchedItem(R.drawable.img_book, "PHP 정복하기 (2015)", "홍길동 저");
                searchedItem(R.drawable.img_book, "Xamarin 완벽 가이드 (2017)", "윤기돈 저");
                searchedItem(R.drawable.img_book, "SQL 전문가가 되는 지름길 (2016)", "김동민 저");
                break;
        }
    }

    // 대출 책에 대한 동적 뷰 생성
    private void searchedItem(int resourceId, String paramTitleText, String paramAuthorText){
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        param.gravity = Gravity.CENTER;
        final BorrowBookItem searchedItem = new BorrowBookItem(getApplicationContext());
        searchedItem.setLayoutParams(param);

        searchedItem.setIconResource(resourceId);
        searchedItem.setTitleText(paramTitleText);
        searchedItem.setAuthorText(paramAuthorText);
        searchedItem.setButtonText("선택");

        searchedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret_title = searchedItem.getTitleText();
                String ret_subtitle = searchedItem.getAuthorText();

                Toast.makeText(getApplicationContext(), ret_title + " , " + ret_subtitle, Toast.LENGTH_SHORT).show();
            }
        });

        ll_searched_item.addView(searchedItem);
    }

}
