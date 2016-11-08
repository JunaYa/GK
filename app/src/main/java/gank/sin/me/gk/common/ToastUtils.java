package gank.sin.me.gk.common;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import gank.sin.me.gk.dagger.ActivityContext;

/**
 * Created by aya on 16/10/10.
 */

public class ToastUtils {
    private static Context mContext;
    private static Toast mToast;


    @Inject
    public ToastUtils(@ActivityContext Context context){
        mContext = context;
    }

    public static void Show(String str){
        mToast = new Toast(mContext);
        mToast.setDuration(Toast.LENGTH_SHORT);

        if (mToast == null){
            mToast = new Toast(mContext);
        }
        mToast.setText(str);
        mToast.show();
    }
}
