package com.modoo.modoobooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    String LOG_TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tb_main_bar)
    Toolbar tb_main_bar;
    @BindView(R.id.google_map)
    MapView m_mapView;
    @BindView(R.id.ll_main_read_books)
    LinearLayout ll_main_read_books;

    private GoogleMap m_googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeView(savedInstanceState);
    }

    // 뷰 초기화
    private void initializeView(Bundle savedInstanceState){

        if(tb_main_bar == null)
            return;

        tb_main_bar.inflateMenu(R.menu.menu_main_bar);
        tb_main_bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // 검색 버튼 클릭 시
                if(item.getItemId() == R.id.menu_main_search){

                }
                // 알람 버튼 클릭 시
                else if (item.getItemId() == R.id.menu_main_alarm){

                }

                return false;
            }
        });


        m_mapView.onCreate(savedInstanceState);
        m_mapView.onResume();
        m_mapView.getMapAsync(this);

        try {
            createBorrowBook("제이쿼리 입문 (2017)", "홍길동 저");
            createBorrowBook("안드로이드 정복하기 (2017)", "홍길동 저");
        } catch (Exception e) {
            Log.d(LOG_TAG, " createBorrowBooks : " + e.toString());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.6283032, 127.4561366), 15.0f));
    }

    // 공유된 가계도 목록 출력 함수
    private void createBorrowBook(String paramTitleText, String paramAuthorText) throws Exception{
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        param.gravity = Gravity.CENTER;
        final BorrowBookItem borrowBookItem = new BorrowBookItem(getApplicationContext());
        borrowBookItem.setLayoutParams(param);
        borrowBookItem.setTitleText(paramTitleText);
        borrowBookItem.setAuthorText(paramAuthorText);
        borrowBookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret_title = borrowBookItem.getTitleText();
                String ret_author = borrowBookItem.getAuthorText();
            }
        });

        ll_main_read_books.addView(borrowBookItem);
    }
}
