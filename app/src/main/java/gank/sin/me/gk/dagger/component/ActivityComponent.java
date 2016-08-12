package gank.sin.me.gk.dagger.component;

import dagger.Subcomponent;
import gank.sin.me.gk.dagger.module.ActivityModule;
import gank.sin.me.gk.dagger.module.FragmentModule;
import gank.sin.me.gk.ui.main.MainActivity;
import gank.sin.me.gk.ui.search.SearchActivity;
import gank.sin.me.gk.ui.web.WebActivity;

/**
 * Created by qoo on 16-8-5.
 */
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

//    void inject(MainActivity mainActivity);

    void inject(SearchActivity searchActivity);

    void inject(WebActivity webActivity);

    FragmentComponent plus(FragmentModule fragmentModule);

}
