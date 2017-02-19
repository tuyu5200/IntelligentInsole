package com.octave.intelligentinsole.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.octave.intelligentinsole.R;
import com.octave.intelligentinsole.entity.Lv0EntityOfMain;
import com.octave.intelligentinsole.entity.Lv1EntityOfMain;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/2/13.
 */

public class ItemOfMainAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public ItemOfMainAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_main);
        addItemType(TYPE_LEVEL_1, R.layout.item_main_child);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                switch (holder.getLayoutPosition()) {
                    case 0:
                        holder.setImageResource(R.id.iv_icon_main, R.drawable.backgroundpic)
                                .setImageResource(R.id.ibtn_main, R.drawable.backgroundpic);
                        break;
                    case 1:
                        holder.setImageResource(R.id.iv_icon_main, R.drawable.backgroundpic)
                                .setImageResource(R.id.ibtn_main, R.drawable.backgroundpic);
                        break;
                    case 2:
                        holder.setImageResource(R.id.iv_icon_main, R.drawable.backgroundpic)
                                .setImageResource(R.id.ibtn_main, R.drawable.backgroundpic);
                        break;
                }
                final Lv0EntityOfMain lv0 = (Lv0EntityOfMain) item;
                holder.setText(R.id.tv_title_main, lv0.getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final Lv1EntityOfMain lv1 = (Lv1EntityOfMain) item;
                holder.setImageResource(R.id.iv_icon_main_child, lv1.getImgId())
                        .setImageResource(R.id.ibtn_main_child, lv1.getBtnBcId())
                        .setText(R.id.tv_title_main_child, lv1.getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lv1.getIntentClass()!= null) {
                            System.out.println(lv1.getIntentClass().getClass());
                            Intent intent = new Intent(v.getContext(), lv1.getIntentClass());
                            v.getContext().startActivity(intent);
                        } else {
                            Toast.makeText(v.getContext(), "null", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }


//    @Override
//    protected void convert(BaseViewHolder baseViewHolder, Lv0EntityOfMain entityOfMain) {
//        baseViewHolder.setImageResource(R.id.iv_icon_main, R.drawable.backgroundpic)
//                .setText(R.id.tv_title_main, entityOfMain.getTitle())
//                .setImageResource(R.id.ibtn_main, R.drawable.backgroundpic);
//    }

}
