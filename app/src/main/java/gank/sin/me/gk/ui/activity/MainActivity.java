package gank.sin.me.gk.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import gank.sin.me.gk.R;
import gank.sin.me.gk.databinding.ActivityMainBinding;
import gank.sin.me.gk.ui.base.BaseActivity;
import gank.sin.me.gk.ui.base.BaseFragment;
import gank.sin.me.gk.ui.fragments.BoonFragment;
import gank.sin.me.gk.ui.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding mBinding;
    @Inject
    MainViewModel mMainViewModel;
    @Inject
    @Named("fragments")
    ArrayList<BaseFragment> mFragments;

    private boolean linear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setViewModel(mMainViewModel);
        setSupportActionBar(mBinding.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);

        openFragment(mFragments.get(0));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initNav();
        getBoonFragment().initRecycler(mMainViewModel.getLinearLayoutManager());
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
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
        int id = item.getItemId();
        switch (id) {
            case R.id.action_layout:
                item.setIcon(linear ? R.drawable.ic_menu_grid : R.drawable.ic_menu_linear);
                getBoonFragment().initRecycler(linear ? mMainViewModel.getLinearLayoutManager() : mMainViewModel.getGridLayoutManager());
                linear = !linear;
                break;
            case R.id.action_search:
                startActivity(SearchActivity.newIntent(this));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_boon) {
        } else if (id == R.id.nav_android) {
            // Handle the camera action
        } else if (id == R.id.nav_ios) {

        } else if (id == R.id.nav_web) {

        } else if (id == R.id.nav_source) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mBinding.toolbar.setTitle(item.getTitle().toString());
        getBoonFragment().getData(item.getTitle().toString(), 1);
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNav() {

        Menu menu = mBinding.navView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.isChecked()) {
                getBoonFragment().getData(item.getTitle().toString(), 1);
                break;
            }
        }

    }

    private BoonFragment getBoonFragment() {
        return (BoonFragment) mFragments.get(0);
    }

    private void openFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
