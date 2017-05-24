package hebust.graduation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hebust.graduation.contract.MainViewContract;
import hebust.graduation.data.ChannelRepository;
import hebust.graduation.data.LocalChannelDataSource;
import hebust.graduation.data.RemoteChannelDataSource;
import hebust.graduation.fragment.FeedFragment;
import hebust.graduation.presenter.MainViewPresenter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainViewContract.View {

    private static final String TAG = "main_act";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView mNavMenu;

    @BindView(R.id.id_vp_content)
    ViewPager mVpContent;

    @BindView(R.id.id_tab)
    TabLayout mTabLayout;

    private MainViewPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

        mPresenter = new MainViewPresenter(new ChannelRepository(new LocalChannelDataSource(),
                new RemoteChannelDataSource()), this);

        mPresenter.start();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavMenu.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feed) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            // TODO: 17-5-24 展示图片性能
        } else if (id == R.id.nav_slideshow) {
            // todo 展示缓存性能
            MediaActivity.startActivity(this);
        } else if (id == R.id.nav_manage) {
            // todo header 分析

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showTabs(List<String> channels) {

        Log.d(TAG, "showTabs: channels=" + channels);

        if (channels == null || channels.isEmpty()) {
            return;
        }

        final List<Fragment> fragmentList = new ArrayList<>();
        final List<String> tabs = new ArrayList<>();

        for (String s : channels) {
            fragmentList.add(FeedFragment.newInstance(s));
            tabs.add(Constants.getChannelNameById(s));
        }

        mVpContent.removeAllViews();

        // setup adapter.
        mVpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs.get(position);
            }
        });

        mTabLayout.setupWithViewPager(mVpContent);


    }

    @Override
    public void showFeedDetail() {

    }

    @Override
    public void showLoading() {
        //change loading status.
    }

    @Override
    public void showErrorPage(boolean hideTabs) {

    }

    @Override
    public void onPageRefresh() {
    }

    @Override
    public void setPresenter(MainViewContract.Presenter presenter) {

    }
}
