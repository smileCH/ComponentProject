package com.smile.ch.article.contract;

import android.content.Context;

import com.smile.ch.article.bean.ArticleListBean;
import com.smile.ch.article.bean.BannerBean;
import com.smile.ch.common.base.bean.BaseResponseBean;

import java.util.List;

/**
 * Author：CHENHAO
 * date：2018/5/3
 * desc：契约类
 */

public class ArticleContract {
    public interface BannerView{
        void onBannerSuccess(List<BannerBean> lists);
        void onBannerFail(String msg);
        void onArticleListSuccess(List<ArticleListBean.DatasBean> lists);
        void onArticleListFail(String msg);
    }

    public interface BannerModel{
        //请求数据，回调
        void getBannerDatas(Context context, int page, IBannerModelCallback callback, IArticleListModelCallback callback2);
        //取消请求
        void cancleHttpRequest();
    }

    public interface IBannerModelCallback{
        void onSuccess(BaseResponseBean<List<BannerBean>> response);
        void onFail(String msg);
    }
    public interface IArticleListModelCallback{
        void onSuccess(List<ArticleListBean.DatasBean> lists);
        void onFail(String msg);
    }
}
