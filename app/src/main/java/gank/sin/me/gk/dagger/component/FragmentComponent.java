package gank.sin.me.gk.dagger.component;

import dagger.Subcomponent;
import gank.sin.me.gk.dagger.module.FragmentModule;
import gank.sin.me.gk.ui.fragments.boon.BoonFragment;
import gank.sin.me.gk.ui.fragments.empty.EmptyFragment;

/**
 * Created by qoo on 16-8-5.
 */
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BoonFragment fragment);

    void inject(EmptyFragment emptyFragment);
}
