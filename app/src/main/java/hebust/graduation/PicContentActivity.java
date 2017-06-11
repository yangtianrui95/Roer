package hebust.graduation;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ytr.roer.Roer;

public class PicContentActivity extends BaseActivity {

    private String mUrls;

    public static String EXTRA_KEY_URL = "extra_key_url";
    public static String OPTION_KEY_IMG = "option_key_img";
    private static final String TAG = "PIC";

    @BindView(R.id.iv_detail)
    ImageView mIvDetail;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "pic onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_content_actvity);
        ButterKnife.bind(this);
        mUrls = getIntent().getStringExtra(EXTRA_KEY_URL);
        ViewCompat.setTransitionName(mIvDetail, OPTION_KEY_IMG);

        // add transition listener
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addTransitionListener();
        }
    }


    @OnClick(android.R.id.content)
    void onPicClick(View view) {
        onBackPressed();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addTransitionListener() {
        Transition transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    // No-op
                    Log.d(TAG, "onTransitionStart: ");
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    Roer.getInstance().bind(mUrls, mIvDetail);
                    Log.d(TAG, "onTransitionEnd: ");
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    Log.d(TAG, "onTransitionCancel: ");
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {
                    Log.d(TAG, "onTransitionPause: ");

                }

                @Override
                public void onTransitionResume(Transition transition) {
                    Log.d(TAG, "onTransitionResume: ");
                }
            });
            Roer.getInstance().bind(mUrls, mIvDetail);
        }
    }

}
