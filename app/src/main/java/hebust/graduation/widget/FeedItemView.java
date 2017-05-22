package hebust.graduation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hebust.graduation.R;
import hebust.graduation.adapter.FeedView;
import ytr.roer.Roer;

import static hebust.graduation.beans.Feed.*;


public class FeedItemView extends RelativeLayout implements FeedView {

    @BindView(R.id.id_pic_item)
    ImageView mIvPic;

    @BindView(R.id.id_tv_source_item)
    TextView mTvSource;

    @BindView(R.id.id_tv_title_item)
    TextView mTvTitle;


    public FeedItemView(Context context) {
        this(context, null);
    }

    public FeedItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View rootView = inflater.inflate(R.layout.item_feed, this, true);
        ButterKnife.bind(this, rootView);
    }

    @Override
    public void onBind(FeedItem item) {
        if (item == null) {
            return;
        }
        mTvSource.setText(item.getSource());
        mTvTitle.setText(item.getTitle());
        Roer.getInstance().bind(item.getPic(), mIvPic);
    }
}
