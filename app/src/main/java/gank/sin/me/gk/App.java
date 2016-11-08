package gank.sin.me.gk;

import android.app.Application;
import android.os.StrictMode;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.leakcanary.LeakCanary;

import gank.sin.me.gk.dagger.component.ApplicationComponent;
import gank.sin.me.gk.dagger.component.DaggerApplicationComponent;
import gank.sin.me.gk.dagger.module.ApplicationModule;
import okhttp3.OkHttpClient;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

public class App extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        getApplicationComponent();
        initFresco();

        enabledStrictMode();
        LeakCanary.install(this);
    }

    public ApplicationComponent getApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        return mApplicationComponent;
    }

    //初始化 Fresco
    private void initFresco() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, client)
                .build();
        Fresco.initialize(this, config);
    }

    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }
}
