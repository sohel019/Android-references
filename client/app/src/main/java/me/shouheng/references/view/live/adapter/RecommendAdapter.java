package me.shouheng.references.view.live.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Collections;

import me.shouheng.references.R;
import me.shouheng.references.model.live.data.Recommend;

/**
 * @author shouh
 * @version $Id: RecommendAdapter, v 0.1 2018/6/9 7:01 shouh Exp$
 */
public class RecommendAdapter extends BaseQuickAdapter<Recommend.RoomBean, BaseViewHolder> {

    private Context context;

    private OnRoomClickListener onRoomClickListener;

    public RecommendAdapter(Context context) {
        super(R.layout.item_remmend, Collections.emptyList());
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Recommend.RoomBean item) {
        helper.setText(R.id.tv_category, item.getName());

        Glide.with(context).load(item.getIcon())
                .placeholder(R.drawable.live_default_recommend_icon)
                .into((ImageView) helper.getView(R.id.iv_category));

        helper.addOnClickListener(R.id.tv_more);

        RecommendChildAdapter adapter = new RecommendChildAdapter(context, item.getList());
        adapter.setOnItemClickListener(((adapter1, view, position) -> {
            if (onRoomClickListener != null) {
                Recommend.RoomBean.ListBean listBean = getData().get(position).getList().get(position);
                onRoomClickListener.onRoomClick(listBean);
            }
        }));

        RecyclerView rv = helper.getView(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(context, 2));
        rv.setAdapter(adapter);
    }

    public void setOnRoomClickListener(OnRoomClickListener onRoomClickListener) {
        this.onRoomClickListener = onRoomClickListener;
    }

    public interface OnRoomClickListener {
        void onRoomClick(Recommend.RoomBean.ListBean listBean);
    }
}
