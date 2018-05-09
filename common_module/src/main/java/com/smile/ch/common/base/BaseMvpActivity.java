package com.smile.ch.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Author：CHENHAO
 * date：2018/5/3
 * desc：
 */

public abstract class BaseMvpActivity<V, P extends BasePresenter<V>> extends AppCompatActivity{
    private P presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //创建presenter
        presenter = createPresenter();
        if (null != presenter) {
            presenter.attachView((V) this);
        }else {
            //throw new NullPointerException("presenter can not be empty");
        }
        //初始化控件
        initViews();
    }

    private void initViews() {
        initIntent();
        findViews();
        setViews();
        initListener();
        getData();
    }

    protected abstract int getLayoutId();
    protected abstract P createPresenter();
    protected void initIntent(){}
    protected abstract void findViews();
    protected void initListener(){}
    protected abstract void setViews();
    protected abstract void getData();

    /**
     * 获取presenter
     */
    protected P getPresenter(){
        return presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑，并且取消网络请求
        if (presenter != null){
            presenter.detachView();
        }
    }
}
