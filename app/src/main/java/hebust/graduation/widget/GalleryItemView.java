package hebust.graduation.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import hebust.graduation.PicContentActivity;
import hebust.graduation.R;
import ytr.roer.Roer;

public class GalleryItemView extends RelativeLayout implements GalleryView {

    @BindView(R.id.id_iv_img)
    ImageView mIvImg;

    private String mUrls;

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
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPicContent();
            }
        });
    }

    /**
     * 跳转到详情页
     */
    private void goToPicContent() {
        if (TextUtils.isEmpty(mUrls)) {
            return;
        }
        @SuppressWarnings("unchecked")
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) getContext()
                , new Pair<View, String>(mIvImg, PicContentActivity.OPTION_KEY_IMG));

        final Intent intent = new Intent(getContext(), PicContentActivity.class);
        intent.putExtra(PicContentActivity.EXTRA_KEY_URL, mUrls);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getContext().startActivity(intent, options.toBundle());
        } else {
            getContext().startActivity(intent);
        }
    }

    @Override
    public void bind(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mUrls = url;
        Roer.getInstance().bind(url, mIvImg);
    }
}
