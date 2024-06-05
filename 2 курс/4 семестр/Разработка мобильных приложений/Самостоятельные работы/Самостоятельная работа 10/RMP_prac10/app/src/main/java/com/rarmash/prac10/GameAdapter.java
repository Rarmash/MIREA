package com.rarmash.prac10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private ArrayList<Game> games;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView yearTextView;

        public ViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.game_title);
            yearTextView = view.findViewById(R.id.game_year);
        }

        public void bind(Game game) {
            titleTextView.setText(game.getTitle());
            yearTextView.setText(game.getYearOfRelease());
        }
    }

    public GameAdapter(ArrayList<Game> games) {
        if (games == null) {
            throw new IllegalArgumentException("Games list cannot be null");
        }
        this.games = games;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(games.get(position));
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setGames(ArrayList<Game> newGames) {
        if (newGames == null) {
            throw new IllegalArgumentException("Games list cannot be null");
        }
        this.games = newGames;
        notifyDataSetChanged();
    }

}