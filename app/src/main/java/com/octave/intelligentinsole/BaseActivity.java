package com.octave.intelligentinsole;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.octave.intelligentinsole.interfaces.BaseActivityInterface;

import butterknife.ButterKnife;

/**
 * Created by Paosin Von Scarlet on 2017/2/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityInterface,View.OnClickListener {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到布局文件
        setContentView(getLayoutId());

        //初始化View
        initView();

        //初始化界面数据
        initData();

        //绑定监听器与适配器
        initListener();

    }

    /**
     * 对统一的按钮进行统一处理
     *
     * @param v 点击的View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                processClick(v);
                break;
        }
    }

    /**
     * 显示一个Toast
     *
     * @param msg 吐司内容
     */
    protected void baseToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示一个Toast
     *
     * @param msgId 吐司内容
     */
    protected void baseToast(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示一个SnackBar
     * 使用的时候会自动屏蔽掉系统自带的ui比如说华为坑爹的虚拟按键栏
     * @param msg
     */
    protected void baseSnackBar(String msg) {
        //去掉虚拟按键
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //隐藏虚拟按键栏
                | View.SYSTEM_UI_FLAG_IMMERSIVE //防止点击屏幕时,隐藏虚拟按键栏又弹了出来
        );
        final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT);
        snackbar.setAction("close", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).show();
    }

    /**
     * 显示一个SnackBar
     * @param msgId
     */
    protected void baseSnackBar(int msgId) {
        //去掉虚拟按键
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //隐藏虚拟按键栏
                | View.SYSTEM_UI_FLAG_IMMERSIVE //防止点击屏幕时,隐藏虚拟按键栏又弹了出来
        );
        final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(), msgId, Snackbar.LENGTH_SHORT);
        snackbar.setAction("close", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).show();
    }


}
