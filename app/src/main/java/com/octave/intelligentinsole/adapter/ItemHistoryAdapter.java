package com.octave.intelligentinsole.adapter;

import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.octave.intelligentinsole.R;
import com.octave.intelligentinsole.entity.EntityOfHistory;
import com.octave.intelligentinsole.entity.HistorySection;
import com.octave.intelligentinsole.utils.TimeUtils;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/2/15.
 */

public class ItemHistoryAdapter extends BaseSectionQuickAdapter<HistorySection> {

    public ItemHistoryAdapter(int layoutResId, int sectionHeadResId, List<HistorySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, HistorySection historySection) {
        baseViewHolder.setText(R.id.tv_history_header, historySection.header);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HistorySection historySection) {
        EntityOfHistory data = (EntityOfHistory) historySection.t;
        baseViewHolder.setText(R.id.tv_history_step, data.getStepsString())
                .setText(R.id.tv_history_time, data.getDurationString())
                .setText(R.id.tv_history_starttime, TimeUtils.millisToHMS(data.getStartTime()))
                .setText(R.id.tv_history_distance,data.getDistance())
                .setImageResource(R.id.iv_history_typeicon, data.getImgSrc());
        switch (data.getType()) {
            case 2:
                RelativeLayout relativeLayout = baseViewHolder.getView(R.id.rl_history_parent);
                relativeLayout.removeView(baseViewHolder.getView(R.id.ll_history_datagroup));
                break;
            default:
                break;
        }


    }
}
