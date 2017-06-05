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

    @BindView(R.id.tb_main_bar)
    Toolbar tb_main_bar;
    @BindView(R.id.google_map)
    MapView m_mapView;
    @BindView(R.id.ll_main_read_books)
    LinearLayout ll_main_read_books;
    // chart
    @BindView(R.id.chart)
    ColumnChartView chartView;
    ColumnChartData chartData;

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
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
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
        chartView.setOnValueTouchListener(new ValueTouchListener());
        generateDefaultData();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.6283032, 127.4561366), 15.0f));
    }

    // 대출 책에 대한 동적 뷰 생성
    private void createBorrowBook(String paramTitleText, String paramAuthorText) throws Exception{
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        param.gravity = Gravity.CENTER;
        final BorrowBookItem borrowBookItem = new BorrowBookItem(getApplicationContext());
        borrowBookItem.setLayoutParams(param);

        borrowBookItem.setIconResource(R.drawable.img_book);
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

    private void generateDefaultData() {
        int numSubcolumns = 1;
        int numColumns = 12;

        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        List<AxisValue> x_values = new ArrayList<>();
        for (int i = 0; i < numColumns; ++i) {
            x_values.add(new AxisValue(i).setLabel((i+1) + "월"));
            values = new ArrayList<SubcolumnValue>();

            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
        }

        chartData = new ColumnChartData(columns);


        Axis axisX = new Axis(x_values);
        Axis axisY = new Axis().setHasLines(true);

        axisX.setName("월");
        axisX.setTextColor(Color.BLACK);
        axisX.setTextSize(12);

        SimpleAxisValueFormatter formatter = new SimpleAxisValueFormatter();
        //formatter.setDecimalSeparator('.');
        formatter.setDecimalDigitsNumber(0);
        formatter.setAppendedText("월".toCharArray());
        axisX.setFormatter(formatter);


        axisY.setName("대출 수");
        axisY.setTextColor(Color.BLACK);
        axisY.setTextSize(17);

        chartData.setAxisXBottom(axisX);
        chartData.setAxisYLeft(axisY);

        chartView.setColumnChartData(chartData);

    }

    // 챠트 클릭시 나타나는 이벤트를 정의
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

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
