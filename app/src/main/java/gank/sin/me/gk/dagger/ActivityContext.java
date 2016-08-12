package gank.sin.me.gk.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by sin on 2016/8/8.
 */


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
