package com.octave.intelligentinsole.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.octave.intelligentinsole.BaseActivity;
import com.octave.intelligentinsole.R;
import com.octave.intelligentinsole.utils.AnalogData;
import com.octave.intelligentinsole.views.DrawPressurePath;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EvaluationParamActivity extends BaseActivity {
    @Bind(R.id.left)
    DrawPressurePath mLeft;
    @Bind(R.id.right)
    DrawPressurePath mRight;

    public final static int SUCCESS_ADD_STEPCOUNT = 1;
    public final static int FAILURE_ADD_STEPCOUNT = 0;
    public static int StepCount = 0;
    private View view;
    private Handler mHandler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_evaluation_param;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mHandler = new Handler();
        //为两个控件开启两个线程，测试以不同的速率传输不同的值到两个控件刷新正误
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                AnalogData a = new AnalogData();
                mLeft.initPoint(a.getData());
                mHandler.postDelayed(this, 3000);
                float[] p = new float[9];
                p[AnalogData.getRandom(0, 8)] = AnalogData.getRandom(0, 20);
                p[AnalogData.getRandom(0, 8)] = AnalogData.getRandom(0, 20);
                p[AnalogData.getRandom(0, 8)] = AnalogData.getRandom(0, 20);
                p[AnalogData.getRandom(0, 8)] = AnalogData.getRandom(0, 20);
                mLeft.initRect(p);
                StepCount++;
//                DataShowFragment.handler.sendEmptyMessage(StepCount);
            }
        });

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                AnalogData a = new AnalogData();
                mRight.initPoint(a.getData());
                mHandler.postDelayed(this, 2000);
                StepCount++;
//                DataShowFragment.handler.sendEmptyMessage(StepCount);
                float[] p = new float[9];
                p[AnalogData.getRandom(0, 8)] = AnalogData.getRandom(0, 20);
                p[AnalogData.getRandom(0, 8)] = AnalogData.getRandom(0, 20);
                p[AnalogData.getRandom(0, 8)] = AnalogData.getRandom(0, 20);
                p[AnalogData.getRandom(0, 8)] = AnalogData.getRandom(0, 20);
                mRight.initRect(p);
//                DrawPressurePath.handler.sendEmptyMessage(AnalogData.getRandom(0, 8));
//                mRight.setmRectColor(Color.GREEN,AnalogData.getRandom(0, 8));
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
