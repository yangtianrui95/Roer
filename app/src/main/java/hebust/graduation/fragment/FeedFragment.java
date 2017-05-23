package hebust.graduation.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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


    private FeedContract.Presenter mPresenter;


    public FeedFragment() {
        mPresenter = new FeedPresenter(new RemoteFeedDataSource(), this);
    }

    public static FeedFragment newInstance() {
        return new FeedFragment();
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

    @Override
    public void showErrorPage() {
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

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
        }, 1000);

    }
}
