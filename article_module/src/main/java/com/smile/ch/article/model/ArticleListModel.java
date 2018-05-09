package com.smile.ch.article.model;

import android.content.Context;

import com.smile.ch.article.api.ArticleApi;
import com.smile.ch.article.bean.ArticleListBean;
import com.smile.ch.article.bean.BannerBean;
import com.smile.ch.article.contract.ArticleContract;
import com.smile.ch.common.base.bean.BaseResponseBean;
import com.smile.ch.common.http.RxObserver;
import com.smile.ch.common.http.RxRetrofitManager;
import com.smile.ch.common.http.cancle.ApiCancleManager;

import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：CHENHAO
 * date：2018/5/3
 * desc：
 */

public class ArticleListModel implements ArticleContract.BannerModel {
    /**
     * 请求banner图片以及文章列表数据
     */
    @Override
    public void getBannerDatas(Context context, final int page, final ArticleContract.IBannerModelCallback callback, final ArticleContract.IArticleListModelCallback articleCallback) {
        RxRetrofitManager.getInstance()
                .setTag("articleBanner")
                .getApiService(ArticleApi.class)
                .getBannerImgs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<BaseResponseBean<List<BannerBean>>>() {
                    @Override
                    public void accept(BaseResponseBean<List<BannerBean>> listBaseResponseBean) throws Exception {
                        if (listBaseResponseBean.getErrorCode() >= 0){
                            if (callback != null){
                                callback.onSuccess(listBaseResponseBean);
                            }
                        }else {
                            if (callback != null){
                                callback.onFail(listBaseResponseBean.getErrorMsg());
                            }
                        }
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<BaseResponseBean<List<BannerBean>>, ObservableSource<BaseResponseBean<ArticleListBean>>>() {
                    @Override
                    public ObservableSource<BaseResponseBean<ArticleListBean>> apply(BaseResponseBean<List<BannerBean>> listBaseResponseBean) throws Exception {
                        return RxRetrofitManager.getInstance().getApiService(ArticleApi.class).getArticleList(page);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<BaseResponseBean<ArticleListBean>>(context, true) {
                    @Override
                    public void onSuccess(BaseResponseBean<ArticleListBean> response) {
                        if (articleCallback != null){
                            articleCallback.onSuccess(response.getData().getDatas());
                        }
                    }

                    @Override
                    public void onFailed(BaseResponseBean<ArticleListBean> response) {
                        if (articleCallback != null){
                            articleCallback.onFail(response.getErrorMsg());
                        }
                    }
                });
    }

    @Override
    public void cancleHttpRequest() {
        ApiCancleManager.getInstance().cancel("articleBanner");
    }
}
