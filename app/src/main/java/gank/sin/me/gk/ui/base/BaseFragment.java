package gank.sin.me.gk.ui.base;

import android.support.v4.app.Fragment;

import gank.sin.me.gk.dagger.component.DaggerApplicationComponent;
import gank.sin.me.gk.dagger.component.FragmentComponent;
import gank.sin.me.gk.dagger.module.ActivityModule;
import gank.sin.me.gk.dagger.module.FragmentModule;

/**
 * Created by sin on 2016/8/8
 */

public class BaseFragment extends Fragment {
    private FragmentComponent mFragmentComponent;

    protected FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null)
            mFragmentComponent = ((BaseActivity)getActivity()).getComponent()
                    .plus(new FragmentModule());
        return mFragmentComponent;
    }
}
