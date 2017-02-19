package com.octave.intelligentinsole.views;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Paosin Von Scarlet on 2017/2/16.
 */

public class LineChart extends LineChartView {

    private LineChartView mLineChartView;               //线性图表控件

    //数据
    private String[] mAxisX= new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private String[] mAxisY = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private LineChartData mLineData;                    //图表数据
    private int maxNumberOfLines = 4;                   //图上折线/曲线的最多条数
    private int numberOfPoints = 12;                    //图上的节点数
    private int numberOfLines = 1;                      //图上折线/曲线的显示条数
    /*=========== 状态相关 ==========*/
    private boolean isHasAxes = true;                   //是否显示坐标轴
    private boolean isHasAxesNames = true;              //是否显示坐标轴名称
    private boolean isHasLines = true;                  //是否显示折线/曲线
    private boolean isHasPoints = true;                 //是否显示线上的节点
    private boolean isFilled = false;                   //是否填充线下方区域
    private boolean isHasPointsLabels = false;          //是否显示节点上的标签信息
    private boolean isCubic = false;                    //是否是立体的
    private boolean isPointsHasSelected = false;        //设置节点点击后效果(消失/显示标签)
    private boolean isPointsHaveDifferentColor;         //节点是否有不同的颜色
    /*=========== 其他相关 ==========*/
    private ValueShape pointsShape = ValueShape.CIRCLE; //点的形状(圆/方/菱形)
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints]; //将线上的点放在一个数组中

    public LineChart(Context context) {
        super(context);
        mLineChartView = new LineChartView(context);
    }

    public LineChartView getmLineChartView() {
        return mLineChartView;
    }

    /**
     * 带参数的初始化数据
     * @param data  线数据
     * @param axisX X轴坐标
     */
    public void initData(float[][] data, String[] axisX) {
        setLinesDatas(data,axisX);
        resetViewport();
    }

    public void initData() {
        setLinesDatas(setRandPointsValues(),mAxisX);            //设置每条线的一些属性
        resetViewport();            //计算并绘图
    }

    /**
     * 设置线的相关数据
     */
    private void setLinesDatas(float[][] data, String[] axisX) {
        mLineChartView.cancelDataAnimation();
        numberOfLines=data.length;
        List<Line> lines = new ArrayList<>();
        //循环将每条线都设置成对应的属性
        for (int i = 0; i < numberOfLines; ++i) {
            //节点的值
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < axisX.length; ++j) {
                values.add(new PointValue(j, data[i][j]));
            }

            /*========== 设置线的一些属性 ==========*/
            Line line = new Line(values);               //根据值来创建一条线
            line.setColor(ChartUtils.COLORS[i]);        //设置线的颜色
            line.setShape(pointsShape);                 //设置点的形状
            line.setHasLines(isHasLines);               //设置是否显示线
            line.setHasPoints(isHasPoints);             //设置是否显示节点
            line.setCubic(isCubic);                     //设置线是否立体或其他效果
            line.setFilled(isFilled);                   //设置是否填充线下方区域
            line.setHasLabels(isHasPointsLabels);       //设置是否显示节点标签
            //设置节点点击的效果
            line.setHasLabelsOnlyForSelected(isPointsHasSelected);
            //如果节点与线有不同颜色 则设置不同颜色
            if (isPointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        mLineData = new LineChartData(lines);                      //将所有的线加入线数据类中
        mLineData.setBaseValue(Float.NEGATIVE_INFINITY);           //设置基准数(大概是数据范围)
//        //其他的一些属性方法 可自行查看效果
//        mLineData.setValueLabelBackgroundAuto(true);            //设置数据背景是否跟随节点颜色
//        mLineData.setValueLabelBackgroundColor(Color.BLUE);     //设置数据背景颜色
//        mLineData.setValueLabelBackgroundEnabled(true);         //设置是否有数据背景
//        mLineData.setValueLabelsTextColor(Color.RED);           //设置数据文字颜色
//        mLineData.setValueLabelTextSize(15);                    //设置数据文字大小
//        mLineData.setValueLabelTypeface(Typeface.MONOSPACE);    //设置数据文字样式


        //如果显示坐标轴
        if (isHasAxes) {
            Axis aX = new Axis();                    //X轴
            Axis aY = new Axis().setHasLines(true);  //Y轴
            aX.setTextColor(Color.GRAY);             //X轴灰色
            aY.setTextColor(Color.GRAY);             //Y轴灰色
            //setLineColor()：此方法是设置图表的网格线颜色 并不是轴本身颜色
            //如果显示名称
            if (isHasAxesNames) {
                aX.setName("Axis X");                //设置名称
                aY.setName("Axis Y");
            }
            mLineData.setAxisXBottom(aX);            //设置X轴位置 下方
            mLineData.setAxisYLeft(aY);              //设置Y轴位置 左边
        } else {
            mLineData.setAxisXBottom(null);
            mLineData.setAxisYLeft(null);
        }

        mLineChartView.setLineChartData(mLineData);    //设置图表控件
    }

    private void resetViewport() {
        //创建一个图标视图 大小为控件的最大大小
        final Viewport v = new Viewport(mLineChartView.getMaximumViewport());
        v.left = 0;                             //坐标原点在左下
        v.bottom = 0;
        v.top = 100;                            //最高点为100
        v.right = numberOfPoints - 1;           //右边为点 坐标从0开始 点号从1 需要 -1
        mLineChartView.setMaximumViewport(v);   //给最大的视图设置 相当于原图
        mLineChartView.setCurrentViewport(v);   //给当前的视图设置 相当于当前展示的图
    }

    /**
     * 利用随机数设置每条线对应节点的值
     */
    private float[][] setRandPointsValues() {
        float[][] data = new float[1][numberOfPoints];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                data[i][j] = (float) Math.random() * 100f;
            }
        }
        return data;
    }

    /**
     * 将折线转为曲线
     */
    private void changeCubicLines() {
        isCubic = !isCubic;         //取反即可
        setLinesDatas(setRandPointsValues(),mAxisX);            //重新设置
        if (isCubic) {
            final Viewport v = new Viewport(mLineChartView.getMaximumViewport());
            v.bottom = -5;          //防止曲线超过范围做边界保护
            v.top = 105;            //根据具体需求设置 建议设置一下
            mLineChartView.setMaximumViewport(v);                   //设置最大视图
            mLineChartView.setCurrentViewportWithAnimation(v);      //有动画的增加当前视图
        } else {
            final Viewport v = new Viewport(mLineChartView.getMaximumViewport());
            v.bottom = 0;           //如果上面没有设置 那么这里也不用设置
            v.top = 100;            //同样的 建议设置一下
            //动画监听 在动画完成后 设置最大的视图 直接设置也可 但效果要好一点
            mLineChartView.setViewportAnimationListener(new ChangeLinesAnimListener(v));
            mLineChartView.setCurrentViewportWithAnimation(v);      //有动画的增加当前视图
        }
    }
    /**
     * 节点触摸监听
     */
    private class ValueTouchListener implements LineChartOnValueSelectListener {
        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {

        }

        @Override
        public void onValueDeselected() {

        }
    }

    /**
     * 线条改变动画监听
     */
    private class ChangeLinesAnimListener implements ChartAnimationListener {

        private Viewport v;

        public ChangeLinesAnimListener(Viewport v) {
            this.v = v;
        }

        @Override
        public void onAnimationStarted() {
        }

        @Override
        public void onAnimationFinished() {
            mLineChartView.setMaximumViewport(v);                   //设置最大视图
            mLineChartView.setViewportAnimationListener(null);      //取消监听
        }
    }

    public void setOnValueTouchListener() {
        mLineChartView.setOnValueTouchListener(new ValueTouchListener());
    }
}
