package com.rarmash.homemedialibrary.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rarmash.homemedialibrary.Adapter.MyAdapter;
import com.rarmash.homemedialibrary.Constant;
import com.rarmash.homemedialibrary.R;
import com.rarmash.homemedialibrary.models.Book;
import com.rarmash.homemedialibrary.models.Movie;
import com.rarmash.homemedialibrary.models.Music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MediaFragment extends Fragment {
    List<Object> mediaList;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        int numColons = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numColons);
        recyclerView.setLayoutManager(gridLayoutManager);

        builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(R.layout.loading_layout);
        dialog = builder.create();
        dialog.show();

        mediaList = new ArrayList<>();

        adapter = new MyAdapter(getContext(), mediaList);
        recyclerView.setAdapter(adapter);

        loadMedia(Constant.BOOKS);
        loadMedia(Constant.MOVIES);
        loadMedia(Constant.MUSIC);

        return rootView;
    }

    private void loadMedia(String mediaType) {
        databaseReference = FirebaseDatabase.getInstance().getReference(mediaType);
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HashMap<String, Object> hashMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    hashMap.put(Constant.MEDIATYPE, mediaType);
                    mediaList.add(hashMap);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                dialog.dismiss();
            }
        });
    }
}