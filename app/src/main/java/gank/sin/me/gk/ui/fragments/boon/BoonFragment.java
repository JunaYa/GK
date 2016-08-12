package gank.sin.me.gk.ui.fragments.boon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gank.sin.me.gk.R;
import gank.sin.me.gk.ui.base.BaseFragment;

/**
 * Created by sin on 2016/8/11.
 */

public class BoonFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_boon,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
