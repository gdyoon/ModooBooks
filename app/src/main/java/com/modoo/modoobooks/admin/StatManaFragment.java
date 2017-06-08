package com.modoo.modoobooks.admin;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.modoo.modoobooks.MainActivity;
import com.modoo.modoobooks.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatManaFragment extends Fragment {
    @BindView(R.id.chart_borrow)
    ColumnChartView chart_borrow;
    @BindView(R.id.chart_return)
    ColumnChartView chart_return;

    ColumnChartData chart_borrow_data;
    ColumnChartData chart_return_data;

    public StatManaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat_mana, container, false);
        ButterKnife.bind(this, view);

        chart_return.setOnValueTouchListener(new ValueTouchListener());
        chart_borrow.setOnValueTouchListener(new ValueTouchListener());

        generateDefaultData(chart_return, chart_return_data, "월", "반납 수");
        generateDefaultData(chart_borrow, chart_borrow_data, "월", "대출 수");

        return view;
    }

    private void generateDefaultData(ColumnChartView paramView, ColumnChartData
            paramData, String xAxisTitle, String yAxisTitle) {
        int numSubcolumns = 1;
        int numColumns = 12;

        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        List<AxisValue> x_values = new ArrayList<>();
        for (int i = 0; i < numColumns; ++i) {
            x_values.add(new AxisValue(i).setLabel((i+1) + "월"));
            values = new ArrayList<SubcolumnValue>();

            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f, ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
        }

        paramData = new ColumnChartData(columns);


        Axis axisX = new Axis(x_values);
        Axis axisY = new Axis().setHasLines(true);

        axisX.setName(xAxisTitle);
        axisX.setTextColor(Color.BLACK);
        axisX.setTextSize(12);

        SimpleAxisValueFormatter formatter = new SimpleAxisValueFormatter();
        //formatter.setDecimalSeparator('.');
        formatter.setDecimalDigitsNumber(0);
        formatter.setAppendedText(xAxisTitle.toCharArray());
        axisX.setFormatter(formatter);


        axisY.setName(yAxisTitle);
        axisY.setTextColor(Color.BLACK);
        axisY.setTextSize(17);

        paramData.setAxisXBottom(axisX);
        paramData.setAxisYLeft(axisY);

        paramView.setColumnChartData(paramData);

    }

    // 챠트 클릭시 나타나는 이벤트를 정의
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {
        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(getContext(), columnIndex + " 월 통계 : " + (int)value.getValue(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

}
