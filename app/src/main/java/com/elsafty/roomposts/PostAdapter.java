package com.elsafty.roomposts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> items = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post currentPost = items.get(position);
        holder.titlTextView.setText(currentPost.getTitle());
        holder.bodyTextView.setText(currentPost.getBody());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setList(List<Post> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView titlTextView,bodyTextView;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titlTextView = itemView.findViewById(R.id.item_title_textView);
            bodyTextView = itemView.findViewById(R.id.item_body_textView);
        }
    }
}