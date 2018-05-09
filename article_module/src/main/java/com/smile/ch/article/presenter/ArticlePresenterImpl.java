package com.smile.ch.article.presenter;

import android.content.Context;

import com.smile.ch.article.bean.ArticleListBean;
import com.smile.ch.article.bean.BannerBean;
import com.smile.ch.article.contract.ArticleContract;
import com.smile.ch.article.model.ArticleListModel;
import com.smile.ch.common.base.BasePresenter;
import com.smile.ch.common.base.bean.BaseResponseBean;

import java.util.List;

/**
 * Author：CHENHAO
 * date：2018/5/3
 * desc：
 */

public class ArticlePresenterImpl extends BasePresenter<ArticleContract.BannerView> {
    private ArticleListModel model;

    public ArticlePresenterImpl(){
        model = new ArticleListModel();
    }

    /**
     * 请求banner图片、文章列表数据
     */
    public void requestBannerImgs(Context context, int page){
        model.getBannerDatas(context, page, new ArticleContract.IBannerModelCallback() {
            @Override
            public void onSuccess(BaseResponseBean<List<BannerBean>> response) {
                if (getMvpView() != null) {
                    getMvpView().onBannerSuccess(response.getData());
                }
            }

            @Override
            public void onFail(String msg) {
                if (getMvpView() != null) {
                    getMvpView().onBannerFail(msg);
                }
            }
        }, new ArticleContract.IArticleListModelCallback() {
            @Override
            public void onSuccess(List<ArticleListBean.DatasBean> lists) {
                if (getMvpView() != null){
                    getMvpView().onArticleListSuccess(lists);
                }
            }

            @Override
            public void onFail(String msg) {
                if (getMvpView() != null){
                    getMvpView().onArticleListFail(msg);
                }
            }
        });
    }

    /**
     * 取消请求
     */
    public void cancleHttpRequest(){
        model.cancleHttpRequest();
    }
}
