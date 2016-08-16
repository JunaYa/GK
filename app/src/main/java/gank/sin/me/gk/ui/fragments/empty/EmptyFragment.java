package gank.sin.me.gk.ui.fragments.empty;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gank.sin.me.gk.R;
import gank.sin.me.gk.databinding.ViewEmptyBinding;
import gank.sin.me.gk.ui.base.BaseFragment;

/**
 * Created by sin on 2016/8/16.
 */

public class EmptyFragment extends BaseFragment {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewEmptyBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_empty,container,false);

        return binding.getRoot();
    }
}
