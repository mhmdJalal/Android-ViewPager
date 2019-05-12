package com.codepolitan.viewpager2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepolitan.viewpager2.DetailActivity;
import com.codepolitan.viewpager2.R;
import com.codepolitan.viewpager2.model.Artikel;

import java.util.ArrayList;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.viewHolder> {

    private ArrayList<Artikel> artikels;
    private Context context;

    public ArtikelAdapter(ArrayList<Artikel> artikels, Context context) {
        this.artikels = artikels;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtikelAdapter.viewHolder holder, int position) {
        final Artikel data = artikels.get(position);

        holder.tvJudul.setText(data.getTitle());
        holder.tvAuthor.setText(data.getAuthor_name());
        Glide.with(context).load(data.getThumbnail()).into(holder.ivCover);
        Glide.with(context).load(data.getAuthor_image()).into(holder.ivAuthor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("artikel", data);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artikels.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView tvJudul, tvAuthor;
        ImageView ivCover, ivAuthor;

        viewHolder(View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            ivCover = itemView.findViewById(R.id.ivCover);
            ivAuthor = itemView.findViewById(R.id.ivAuthor);
        }
    }
}
