package com.octave.intelligentinsole.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Paosin Von Scarlet on 2017/2/15.
 */

public class HistorySection extends SectionEntity<EntityOfHistory> {
    public HistorySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public HistorySection(EntityOfHistory entityOfHistory) {
        super(entityOfHistory);
    }
}
