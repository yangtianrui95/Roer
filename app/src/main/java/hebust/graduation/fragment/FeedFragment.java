package hebust.graduation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import hebust.graduation.BaseFragment;
import hebust.graduation.R;
import hebust.graduation.contract.FeedContract;

public class FeedFragment extends BaseFragment implements FeedContract.View {


    @BindView(R.id.id_rv_list)
    RecyclerView mRvList;

    @BindView(R.id.id_srl_refresh)
    SwipeRefreshLayout mSrlRefresh;


    public FeedFragment() {
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
        ButterKnife.bind(view);
    }


    @Override
    public void showFeedList() {

    }

    @Override
    public void setPresenter(FeedContract.Presenter presenter) {

    }
}
