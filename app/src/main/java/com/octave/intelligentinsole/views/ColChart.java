package com.octave.intelligentinsole.views;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by Paosin Von Scarlet on 2017/2/16.
 */

public class ColChart extends ColumnChartView {
    private static final String[] DEFAULT_AXIS_X_STRING = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "May", "Fri", "Jul", "Sat"};

    private ColumnChartView mColumnChartView;                //柱形图控件

    private boolean isHasAxes = true;                       //是否显示坐标轴
    private boolean isHasAxesNames = true;                  //是否显示坐标轴
    private boolean isHasColumnLabels = false;              //是否显示列标签
    private boolean isColumnsHasSelected = false;           //设置列点击后效果(消失/显示标签)

    private static final int DEFAULT_DATA = 0;              //默认数据标志位
    private static final int SUBCOLUMNS_DATA = 1;           //多子列数据标志位
    private static final int NEGATIVE_SUBCOLUMNS_DATA = 2;  //反向多子列标志位
    private static final int STACKED_DATA = 3;              //堆放数据标志位
    private static final int NEGATIVE_STACKED_DATA = 4;     //反向堆放数据标志位
    private static boolean IS_NEGATIVE = false;             //是否需要反向标志位
    private float[] mData;

    private ColumnChartData mColumnChartData;               //柱状图数据
    private int dataType = DEFAULT_DATA;                    //默认数据状态

    public ColChart(Context context) {
        super(context);
        mColumnChartView = new ColumnChartView(context);
    }

    public ColumnChartView getmColumnChartView() {
        return mColumnChartView;
    }

    /**
     *
     * @param SubcolumnValue 子列数
     * @param data  无视子列，顺序排列的数据
     * @param axisX X轴的行标
     */
    public void initData(int SubcolumnValue, float[] data, String[] axisX) {
        this.mData = data;
        setColumnDatasByParams(SubcolumnValue, axisX);
    }

    public void initData() {
        mData = new float[DEFAULT_AXIS_X_STRING.length];
        for (int i = 0; i < DEFAULT_AXIS_X_STRING.length; i++)
            mData[i] = (float) Math.random() * 50f + 5;
        setColumnDatasByParams(1, DEFAULT_AXIS_X_STRING);       //根据状态设置相关数据
    }

    /**
     * 根据不同的参数 决定绘制什么样的柱状图
     *
     * @param numSubcolumns 子列数
     * @param axisXString   x轴的值
     */
    private void setColumnDatasByParams(int numSubcolumns, String[] axisXString) {
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        List<AxisValue> axisValues = new ArrayList<>();
        //双重for循环给每个子列设置随机的值和随机的颜色
        for (int i = 0; i < axisXString.length; ++i) {
            values = new ArrayList<>();
            for (int j = 0; j < numSubcolumns; ++j) {
                //设置列的值
                values.add(new SubcolumnValue(mData[i * numSubcolumns + j], ChartUtils.pickColor()));
            }

            /*===== 柱状图相关设置 =====*/
            Column column = new Column(values);
            column.setHasLabels(isHasColumnLabels);                    //没有标签
            column.setHasLabelsOnlyForSelected(isColumnsHasSelected);  //点击只放大
            columns.add(column);
            axisValues.add(new AxisValue(i).setLabel(axisXString[i]));
        }
        mColumnChartData = new ColumnChartData(columns);               //设置数据

        /*===== 坐标轴相关设置 类似于Line Charts =====*/
        if (isHasAxes) {
            Axis axisX = new Axis(axisValues);
            Axis axisY = new Axis().setHasLines(true);
            if (isHasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            mColumnChartData.setAxisXBottom(axisX);
            mColumnChartData.setAxisYLeft(axisY);
        } else {
            mColumnChartData.setAxisXBottom(null);
            mColumnChartData.setAxisYLeft(null);
        }
        mColumnChartView.setColumnChartData(mColumnChartData);
    }

    /**
     * 根据是否反向标记返回对应的反向标志
     *
     * @param isNegative 是否反向
     * @return 反向标志 -1：反向 1：正向
     */
    private int getNegativeSign(boolean isNegative) {
        if (isNegative) {
            int[] sign = new int[]{-1, 1};                      //-1：反向 1：正向
            return sign[Math.round((float) Math.random())];     //随机确定子列正反
        }
        return 1;                                               //默认全为正向
    }

    /**
     * 重置柱状图
     */
    private void resetColumnDatas() {
        /*========== 恢复相关属性 ==========*/
        isHasAxes = true;
        isHasAxesNames = true;
        isHasColumnLabels = false;
        isColumnsHasSelected = false;
        dataType = DEFAULT_DATA;
        mColumnChartView.setValueSelectionEnabled(isColumnsHasSelected);
    }
}
