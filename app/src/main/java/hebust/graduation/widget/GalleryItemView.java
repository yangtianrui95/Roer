package hebust.graduation.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import hebust.graduation.R;
import ytr.roer.Roer;

public class GalleryItemView extends RelativeLayout implements GalleryView {

    @BindView(R.id.id_iv_img)
    ImageView mIvImg;

    public GalleryItemView(Context context) {
        this(context, null);
    }

    public GalleryItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final View root = LayoutInflater.from(context).inflate(R.layout.item_gallery, this);
        ButterKnife.bind(this, root);
    }

    @Override
    public void bind(String url) {
        if (TextUtils.isEmpty(url)){
            return;
        }
        Roer.getInstance().bind(url, mIvImg);
    }
}
