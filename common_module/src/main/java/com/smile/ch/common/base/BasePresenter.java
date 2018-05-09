package com.smile.ch.common.base;

/**
 * Author：CHENHAO
 * date：2018/5/3
 * desc：presenter基类
 */

public abstract class BasePresenter<V> {
    private V mvpView;

    /**
     * 绑定view
     */
    public void attachView(V mvpView){
        this.mvpView = mvpView;
    }

    /**
     * 解绑view
     */
    public void detachView(){
        mvpView = null;
    }

    /**
     * 获取view
     */
    public V getMvpView(){
        return mvpView;
    }

}
