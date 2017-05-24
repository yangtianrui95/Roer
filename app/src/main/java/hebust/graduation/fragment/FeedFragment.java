package hebust.graduation.fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hebust.graduation.App;
import hebust.graduation.R;
import hebust.graduation.adapter.FeedAdapter;
import hebust.graduation.beans.Feed;
import hebust.graduation.contract.FeedContract;
import hebust.graduation.data.RemoteFeedDataSource;
import hebust.graduation.presenter.FeedPresenter;

public class FeedFragment extends BaseFragment implements FeedContract.View {

    private static final String TAG = "feed_frag";

    @BindView(R.id.id_rv_list)
    RecyclerView mRvList;

    @BindView(R.id.id_srl_refresh)
    SwipeRefreshLayout mSrlRefresh;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private FeedContract.Presenter mPresenter;
    private String mChannel;

    private interface ExtraKey {
        String CHANNEL_KEY = "channel_key";
    }


    public FeedFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle != null){
            mChannel = bundle.getString(ExtraKey.CHANNEL_KEY);
        }
        mPresenter = new FeedPresenter(new RemoteFeedDataSource(mChannel), this);
    }

    public static FeedFragment newInstance(String channel) {
        final FeedFragment feedFragment = new FeedFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.CHANNEL_KEY, channel);
        feedFragment.setArguments(bundle);
        return feedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.start();
        mSrlRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });
    }


    @Override
    public void setPresenter(FeedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showFeedList(List<Feed.FeedItem> feedItems) {
        Log.d(TAG, "showFeedList: " + feedItems);
        if (feedItems.isEmpty()) {
            return;
        }
        mRvList.setAdapter(new FeedAdapter(feedItems));
        mRvList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 10;
            }
        });
    }

    @OnClick(R.id.fab)
    void onFabClick(View view) {
        mPresenter.refresh();
    }

    @Override
    public void showErrorPage() {
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        mSrlRefresh.setRefreshing(true);
        // rotate fab.
        final AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.fab_click);
        set.setInterpolator(new AccelerateInterpolator());
        set.setTarget(mFab);
        set.start();
    }

    @Override
    public void refresh(List<Feed.FeedItem> feedItems) {
        if (feedItems == null || feedItems.isEmpty()) {
            return;
        }
        final RecyclerView.Adapter adapter = mRvList.getAdapter();
        if (adapter == null || !(adapter instanceof FeedAdapter)) {
            return;
        }

        ((FeedAdapter) adapter).refreshData(feedItems);

        App.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSrlRefresh.setRefreshing(false);
            }
        }, 2000);

    }
}
