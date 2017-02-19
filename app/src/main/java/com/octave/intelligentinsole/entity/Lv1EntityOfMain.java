package com.octave.intelligentinsole.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.octave.intelligentinsole.adapter.ItemOfMainAdapter;

/**
 * Created by Paosin Von Scarlet on 2017/2/19.
 */

public class Lv1EntityOfMain implements MultiItemEntity {
    private int imgId;
    private String title;
    private int btnBcId;
    private Object intentClass;

    public Object getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(Object intentClass) {
        this.intentClass = intentClass;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBtnBcId() {
        return btnBcId;
    }

    public void setBtnBcId(int btnBcId) {
        this.btnBcId = btnBcId;
    }

    public Lv1EntityOfMain(int imgId, String title, int btnBcId, Object intentClass) {
        this.imgId = imgId;
        this.title = title;
        this.btnBcId = btnBcId;
        this.intentClass = intentClass;
    }

    @Override
    public int getItemType() {
        return ItemOfMainAdapter.TYPE_LEVEL_1;
    }
}
