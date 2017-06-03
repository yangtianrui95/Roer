package hebust.graduation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private static final String TAG = "tianrui";

    protected boolean mIsVisible;
    protected boolean mHasLoaded;
    protected boolean mHasPrepare;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d("tianrui", "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (mIsVisible) {
            Log.d(TAG, "onViewCreated: load data in #onViewCreated ");
            initData();
            mHasLoaded = true;
        }
        mHasPrepare = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("tianrui", "setUserVisibleHint: " + isVisibleToUser);
        mIsVisible = getUserVisibleHint();
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
        mHasLoaded = false;
        mHasPrepare = false;
    }


    protected void lazyLoad() {
        Log.d(TAG, "lazyLoad: mIsVisible " + mIsVisible + " mHasLoaded " + mHasLoaded + " mHasPrepare " + mHasPrepare);
        if (!mIsVisible || mHasLoaded || !mHasPrepare) {
            return;
        }
        Log.d(TAG, "lazyLoad: load data in lazyLoad ");
        initData();
    }

    abstract protected void initData();
}
