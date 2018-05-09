package debug;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smile.ch.detail.BannerDetailActivity;
import com.smile.ch.detail.constants.Constant;

/**
 * Author：CHENHAO
 * date：2018/5/7
 * desc：组件化单独运行时，有时候需要在特定条件下才能运行，比如当我们单独运行banner的详情页的时候，
 * 需要先知道这个url地址，所以这个activity的作用就是提供所需的条件，让程序能正常跑起来，在最后合并进行集成打包的
 * 时候此activity会被移除不会打包进app的
 */

public class MainTestActivity extends AppCompatActivity{

    private String bannerUrl = "http://www.wanandroid.com/navi";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //模拟条件
        Intent intent = new Intent(this, BannerDetailActivity.class);
        intent.putExtra(Constant.BANNER_URL_INTENT, bannerUrl);
        startActivity(intent);
        finish();
    }
}
