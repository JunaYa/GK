package gank.sin.me.gk.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import gank.sin.me.gk.App;
import gank.sin.me.gk.R;
import gank.sin.me.gk.common.ToastUtils;
import gank.sin.me.gk.dagger.component.ActivityComponent;
import gank.sin.me.gk.dagger.module.ActivityModule;

/**
 * Created by sin on 2016/8/8.
 */

public class BaseActivity extends AppCompatActivity {
    protected ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = getComponent();
        StatusBarColor.compat(this,getResources().getColor(R.color.colorPrimary));
    }

    protected ActivityComponent getComponent() {
        if (mActivityComponent == null)
            mActivityComponent = ((App) getApplication()).getApplicationComponent().plus(new ActivityModule(this));
        return mActivityComponent;
    }

    protected void showBack(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
