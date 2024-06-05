package com.rarmash.homemedialibrary.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rarmash.homemedialibrary.Constant;
import com.rarmash.homemedialibrary.Fragments.MediaDetailFragment;
import com.rarmash.homemedialibrary.models.*;
import com.rarmash.homemedialibrary.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final Context context;
    private List<Object> mediaList;
    public MyAdapter(Context context, List<Object> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();
        HashMap<String, Object> media = (HashMap<String, Object>) mediaList.get(position);
        String mediaType = (String) media.get(Constant.MEDIATYPE);
        HashMap<String, Object> hashMap = (HashMap<String, Object>) mediaList.get(adapterPosition);

        switch (mediaType) {
            case Constant.MOVIES: {
                Movie movie = new Movie(hashMap.get("title").toString(), hashMap.get("author").toString(), hashMap.get("genre").toString(), Integer.parseInt(hashMap.get("releaseYear").toString()), hashMap.get("dataImage").toString());
                Glide.with(context).load(movie.getDataImage()).into(holder.recImage);
                holder.recCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            MediaDetailFragment mediaDetailFragment = new MediaDetailFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constant.TITLE, movie.getTitle());
                            bundle.putString(Constant.DATAIMAGE, movie.getDataImage());
                            bundle.putString(Constant.MEDIATYPE, mediaType);
                            mediaDetailFragment.setArguments(bundle);
                            ((AppCompatActivity) context).getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame_layout, mediaDetailFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
                break;
            }
            case Constant.MUSIC: {
                Music music = new Music(hashMap.get("title").toString(), hashMap.get("author").toString(), hashMap.get("genre").toString(), Integer.parseInt(hashMap.get("releaseYear").toString()), hashMap.get("dataImage").toString());
                Glide.with(context).load(music.getDataImage()).into(holder.recImage);
                holder.recCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            MediaDetailFragment mediaDetailFragment = new MediaDetailFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constant.TITLE, music.getTitle());
                            bundle.putString(Constant.DATAIMAGE, music.getDataImage());
                            bundle.putString(Constant.MEDIATYPE, mediaType);
                            mediaDetailFragment.setArguments(bundle);
                            ((AppCompatActivity) context).getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame_layout, mediaDetailFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
                break;
            }
            case Constant.BOOKS: {
                Book book = new Book(hashMap.get("title").toString(), hashMap.get("author").toString(), hashMap.get("genre").toString(), Integer.parseInt(hashMap.get("releaseYear").toString()), hashMap.get("dataImage").toString());
                Glide.with(context).load(book.getDataImage()).into(holder.recImage);
                holder.recCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            MediaDetailFragment mediaDetailFragment = new MediaDetailFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constant.TITLE, book.getTitle());
                            bundle.putString(Constant.DATAIMAGE, book.getDataImage());
                            bundle.putString(Constant.MEDIATYPE, mediaType);
                            mediaDetailFragment.setArguments(bundle);
                            ((AppCompatActivity) context).getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame_layout, mediaDetailFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mediaList != null ? mediaList.size() : 0;
    }

    public void searchMediaList(ArrayList<Object> searchList) {
        mediaList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView recImage;
    CardView recCard;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recCard = itemView.findViewById(R.id.recCard);
        recImage = itemView.findViewById(R.id.recImage);
    }
}