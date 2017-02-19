package com.octave.intelligentinsole.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.octave.intelligentinsole.adapter.ItemOfMainAdapter;

/**
 * Created by Paosin Von Scarlet on 2017/2/13.
 */

public class Lv0EntityOfMain extends AbstractExpandableItem<Lv1EntityOfMain> implements MultiItemEntity {
    private int imgId;
    private String title;
    private int btnBcId;

    public Lv0EntityOfMain(String title) {
        this.title = title;
    }

    public Lv0EntityOfMain(int imgId, String title, int btnBcId) {
        this.imgId = imgId;
        this.title = title;
        this.btnBcId = btnBcId;
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

    @Override
    public int getItemType() {
        return ItemOfMainAdapter.TYPE_LEVEL_0;
    }
}
