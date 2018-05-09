package com.smile.ch.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.smile.ch.common.utils.Utils;

/**
 * Author：CHENHAO
 * date：2018/5/3
 * desc：组件开发中我们的application是放在debug包下的，进行集成合并时是需要移除掉的，
 * 所以组件module中不能使用debug包下的application的context，
 * 因此组件中必须通过Utils.getContext()方法来获取全局 Context
 */

public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Utils.init(this);
    }

    public static BaseApplication getApplication(){
        return application;
    }
}
