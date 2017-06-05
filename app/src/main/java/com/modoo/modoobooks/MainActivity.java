package com.modoo.modoobooks;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    String LOG_TAG = MainActivity.class.getSimpleName();

    // 최상단 툴바
    @BindView(R.id.tb_main_bar)
    Toolbar tb_main_bar;
    // 구글 맵 ( 실제로 사용하진 않음 ) - API 키 연동은 해놨는데 그냥 장식용임
    @BindView(R.id.google_map)
    MapView m_mapView;
    // 현재 대출 중인 책을 담기 위한 레이아웃
    @BindView(R.id.ll_main_read_books)
    LinearLayout ll_main_read_books;
    // 챠트 라이브러리
    @BindView(R.id.chart)
    ColumnChartView chartView;
    ColumnChartData chartData;

    // 사용 X
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

        // 검색, 알람 두가지를 가지고 있는 메뉴 세팅 하기
        tb_main_bar.inflateMenu(R.menu.menu_main_bar);

        // 검색 또는 알람 버튼 클릭 시 핸들러
        tb_main_bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // 검색 버튼 클릭 시
                if(item.getItemId() == R.id.menu_main_search){
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                }
                // 알람 버튼 클릭 시
                else if (item.getItemId() == R.id.menu_main_alarm){
                    Intent intent = new Intent(getApplicationContext(), MyAlarmActivity.class);
                    startActivity(intent);
                }

                return false;
            }
        });

        // 구글 맵 초기화 및 연동
        m_mapView.onCreate(savedInstanceState);
        m_mapView.onResume();
        m_mapView.getMapAsync(this);


        try {
            // 대출 중인 책을 동적 뷰로 생성
            createBorrowBook("제이쿼리 입문 (2017)", "홍길동 저");
            createBorrowBook("안드로이드 정복하기 (2017)", "홍길동 저");
        } catch (Exception e) {
            Log.d(LOG_TAG, " createBorrowBooks : " + e.toString());
        }

        // 챠트의 막대를 클릭 시 등록할 이벤트 클래스
        chartView.setOnValueTouchListener(new ValueTouchListener());
        generateDefaultData();
    }


    // 구글 맵 로딩이 끝났을 때 호출되는 메서드
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.6283032, 127.4561366), 15.0f));
    }

    // 사용자 얼굴 이미지 뷰 클릭 시 내정보 보기 버튼 연동
    @OnClick(R.id.iv_main_user)
    public void onMyPictureClicked(){
        Intent intent = new Intent(getApplicationContext(), MyInfoActivity.class);
        startActivity(intent);
    }

    // 대출 책에 대한 동적 뷰 생성
    private void createBorrowBook(String paramTitleText, String paramAuthorText) throws Exception{
        // 레이아웃 가로, 세로 값 정의 파라미터는 픽셀 값으로 들어감
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        // 가운데 정렬
        param.gravity = Gravity.CENTER;
        // 동적 뷰에 대한 클래스를 하나 생성 해서
        final BorrowBookItem borrowBookItem = new BorrowBookItem(getApplicationContext());
        // 가로, 세로를 정의한 파라미터를 지정
        borrowBookItem.setLayoutParams(param);

        // 동적 뷰에 해당하는 아이콘과 책 제목, 책 저자 파라미터로 연결
        borrowBookItem.setIconResource(R.drawable.img_book);
        borrowBookItem.setTitleText(paramTitleText);
        borrowBookItem.setAuthorText(paramAuthorText);

        // 동적으로 생성된 뷰 클릭 시 연결할 이벤트 리스너
        borrowBookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret_title = borrowBookItem.getTitleText();
                String ret_author = borrowBookItem.getAuthorText();
            }
        });

        // XML 코드로 정의한 LinearLayout 밑에 동적 뷰 추가
        ll_main_read_books.addView(borrowBookItem);
    }

    // 차트의 기본 데이터 생성 코드
    private void generateDefaultData() {
        // 한 컬럼 당 막대 개수
        int numSubcolumns = 1;
        // 컬럼 개수
        int numColumns = 12;

        // 컬럼 리스트 객체를 생성 해서
        List<Column> columns = new ArrayList<Column>();
        // 컬럼에 집어 넣을 데이터 생성
        List<SubcolumnValue> values;

        // 각 컬럼의 X축을 나타내기 위해 리스트 객체 정의
        List<AxisValue> x_values = new ArrayList<>();

        // 컬럼 개수 만큼 순회해서
        for (int i = 0; i < numColumns; ++i) {
            // 1월 - 12월 까지 X축 컬럼 명 넣기
            x_values.add(new AxisValue(i).setLabel((i+1) + "월"));
            values = new ArrayList<SubcolumnValue>();

            // 한 컬럼당 막대 개수만큼 순회
            for (int j = 0; j < numSubcolumns; ++j) {
                // 데이터를 랜덤으로 생성, 여기다가 쿼리로 불러와서 책 읽은 권수 넣으면 될듯
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }

            // 컬럼 객체를 하나 생성해서 생성한 데이터를 컬럼 리스트 객체에 하나씩 넣기
            Column column = new Column(values);
            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
        }

        // 컬럼 리스트 객체 전체를 챠트 데이터의 인자로 넣고
        chartData = new ColumnChartData(columns);

        // 정의한 X축 데이터를 지정
        Axis axisX = new Axis(x_values);
        // 정확하게 뭐하는 코드인지는 모르겠음
        Axis axisY = new Axis().setHasLines(true);

        // 전체 X를 가리킬 대표 이름
        axisX.setName("월");
        // X축 색상과 폰트 크기 정의
        axisX.setTextColor(Color.BLACK);
        axisX.setTextSize(12);

        // 이 부분은 일단 무시
        SimpleAxisValueFormatter formatter = new SimpleAxisValueFormatter();
        //formatter.setDecimalSeparator('.');
        formatter.setDecimalDigitsNumber(0);
        formatter.setAppendedText("월".toCharArray());
        axisX.setFormatter(formatter);


        // Y축 데이터 정의
        axisY.setName("대출 수");
        axisY.setTextColor(Color.BLACK);
        axisY.setTextSize(17);

        // X축 Y축 데이터를 지정 하고
        chartData.setAxisXBottom(axisX);
        chartData.setAxisYLeft(axisY);

        // 챠트 뷰에 여태까지 생성한 챠트 데이터를 심기
        chartView.setColumnChartData(chartData);

    }

    // 챠트 클릭시 나타나는 이벤트를 정의하는 클래스
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        // 챠트 바 클릭시
        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(getApplicationContext(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
