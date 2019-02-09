package com.madfree.simplenews;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ArticleAdapter extends PagedListAdapter<Article, ArticleAdapter.ItemViewHolder> {

    private Context mContext;

    public ArticleAdapter(Context mContext) {
        super(DIFF_CALLBACK);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recylcerview_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        Article article = getItem(position);

        if (article != null) {
            Glide.with(mContext)
                    .load(article.urlToImage)
                    .into(itemViewHolder.articlePicture);

            itemViewHolder.titleText.setText(article.title);
            itemViewHolder.descriptionText.setText(article.description);
        } else {
            Toast.makeText(mContext, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private static DiffUtil.ItemCallback<Article> DIFF_CALLBACK = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.title == newItem.title;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView articlePicture;
        TextView titleText;
        TextView descriptionText;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            articlePicture = itemView.findViewById(R.id.image_view);
            titleText = itemView.findViewById(R.id.title_text_view);
            descriptionText = itemView.findViewById(R.id.description_text_view);
        }
    }
}
