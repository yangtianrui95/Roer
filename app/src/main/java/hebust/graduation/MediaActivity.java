package hebust.graduation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hebust.graduation.contract.MediaContract;
import hebust.graduation.data.RemoteMediaDataSource;
import hebust.graduation.presenter.MediaPresenter;
import ytr.roer.Roer;

public class MediaActivity extends AppCompatActivity implements MediaContract.View {


    @BindView(R.id.image_scrolling_top)
    ImageView mIvImg;
    @BindView(R.id.fab_scrolling)
    FloatingActionButton mFab;
    @BindView(R.id.tv_scrolling)
    TextView mTvText;

    private MediaPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        ButterKnife.bind(this);

        mPresenter = new MediaPresenter(new RemoteMediaDataSource(), this);
        mPresenter.start();

    }

    public static void startActivity(Activity context) {
        context.overridePendingTransition(0, 0);
        context.startActivity(new Intent(context, MediaActivity.class));
    }

    @Override
    public void showImage() {
        Roer.getInstance().bind(Constants.Urls.HOST + "/jpeg", mIvImg);
    }

    @Override
    public void showText(String text) {
        mTvText.setText(text);
    }

    @Override
    public void shareInfo() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Roer");
        intent.setType("text/plain");
        startActivity(intent);
    }


    @Override
    public void setPresenter(MediaPresenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.fab_scrolling)
    void onFabClick(View view) {
        mPresenter.share();
    }

}
