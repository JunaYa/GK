package gank.sin.me.gk.dagger.component;


import javax.inject.Singleton;

import dagger.Component;
import gank.sin.me.gk.dagger.module.ActivityModule;
import gank.sin.me.gk.dagger.module.ApiModule;
import gank.sin.me.gk.dagger.module.ApplicationModule;
import gank.sin.me.gk.dagger.module.DBModule;

/**
 * Created by qoo on 16-8-5.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class
        , ApiModule.class
        , DBModule.class

})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule activityModule);

}
