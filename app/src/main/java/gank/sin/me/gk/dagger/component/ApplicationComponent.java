package gank.sin.me.gk.dagger.component;


import javax.inject.Singleton;

import dagger.Component;
import gank.sin.me.gk.dagger.module.ActivityModule;
import gank.sin.me.gk.dagger.module.ApiModule;
import gank.sin.me.gk.dagger.module.ApplicationModule;

/**
 * Created by qoo on 16-8-5.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule activityModule) ;
}
