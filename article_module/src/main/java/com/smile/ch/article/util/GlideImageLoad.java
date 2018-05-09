package com.smile.ch.article.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smile.ch.article.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by ch on 2017/6/29  图片加载框架工具类
 */

public class GlideImageLoad extends ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .placeholder(R.mipmap.ic_launcher)  //图片还没加载出来时显示的图片
                .error(R.mipmap.ic_launcher)  //图片加载失败时显示的图片
                .dontAnimate()  //关闭动画，不然会出现图片一会大一会小的情况
              //  .crossFade() //动画
                .into(imageView);
    }
}
