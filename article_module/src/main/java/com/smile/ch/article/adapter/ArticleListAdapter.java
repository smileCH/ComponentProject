package com.smile.ch.article.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smile.ch.article.R;
import com.smile.ch.article.bean.ArticleListBean;

import java.util.List;

/**
 * Author：CHENHAO
 * date：2018/5/4
 * desc：文章列表适配器
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.articleViewHolder>{

    private Context context;
    private List<ArticleListBean.DatasBean> mDatas;
    private LayoutInflater inflater;
    private OnRvItemClickListener listener;

    public ArticleListAdapter(Context context, List<ArticleListBean.DatasBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public articleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new articleViewHolder(inflater.inflate(R.layout.item_article_list_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(articleViewHolder holder, final int position) {
        ArticleListBean.DatasBean bean = mDatas.get(position);
        holder.tvTitle.setText(bean.getTitle());
        holder.tvAuthor.setText(bean.getAuthor());
        holder.tvCategory.setText(bean.getSuperChapterName()+" / "+bean.getChapterName());
        holder.tvDate.setText(bean.getNiceDate());
        if (bean.getTags().size() > 0){
            holder.tvTags.setText(bean.getTags().get(0).getName());
            holder.tvTags.setVisibility(View.VISIBLE);
        }else {
            holder.tvTags.setVisibility(View.GONE);
        }

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    class articleViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvTags, tvAuthor, tvCategory, tvDate;
        LinearLayout llItem;

        public articleViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_article_title);
            tvTags = itemView.findViewById(R.id.item_article_tags);
            tvAuthor = itemView.findViewById(R.id.item_article_author);
            tvCategory = itemView.findViewById(R.id.item_article_category);
            tvDate = itemView.findViewById(R.id.item_article_date);
            llItem = itemView.findViewById(R.id.item_layout);
        }
    }


    public interface OnRvItemClickListener{
        void onItemClick(int position);
    }

    public void setOnRvItemClickListener(OnRvItemClickListener listener){
        this.listener = listener;
    }
}
