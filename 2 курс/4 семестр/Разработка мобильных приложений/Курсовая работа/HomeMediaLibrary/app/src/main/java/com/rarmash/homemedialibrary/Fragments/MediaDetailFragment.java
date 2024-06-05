package com.rarmash.homemedialibrary.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rarmash.homemedialibrary.Constant;
import com.rarmash.homemedialibrary.R;
import com.rarmash.homemedialibrary.models.*;

public class MediaDetailFragment extends Fragment {
    TextView mediaTitle, mediaAuthor, mediaYear, mediaGenre, mediaTypeField;
    ImageView detailImage;
    FloatingActionButton editBtn;
    FloatingActionMenu floatList;
    String imageUrl = "";
    String mediaType = "";
    String mTitle = "";
    SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_detail, container, false);

        mediaTitle = rootView.findViewById(R.id.detailTitle);
        mediaAuthor = rootView.findViewById(R.id.detailAuthor);
        mediaYear = rootView.findViewById(R.id.detailYear);
        mediaGenre = rootView.findViewById(R.id.detailDescription);
        detailImage = rootView.findViewById(R.id.detailImage);
        floatList = rootView.findViewById(R.id.floating_menu);
        editBtn = rootView.findViewById(R.id.editButton);
        mediaTypeField = rootView.findViewById(R.id.media_type);

        sharedPref = getContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        boolean isSuperUser = sharedPref.getBoolean("isSuperUser", false);

        System.out.println(isSuperUser);

        if (isSuperUser) {
            floatList.setVisibility(View.VISIBLE);
        } else {
            floatList.setVisibility(View.GONE);
        }

        Bundle bundle = this.getArguments();
        System.out.println(bundle.toString());
        if (bundle != null) {
            Object media = bundle.getSerializable(Constant.MEDIA);
            mTitle = bundle.getString(Constant.TITLE);
            imageUrl = bundle.getString(Constant.DATAIMAGE);
            mediaType = bundle.getString(Constant.MEDIATYPE);
            Glide.with(getContext()).load(bundle.getString(Constant.DATAIMAGE)).into(detailImage);

            switch (mediaType) {
                case Constant.MOVIES:
                    loadMovieData(mTitle);
                    break;
                case Constant.BOOKS:
                    loadBookData(mTitle);
                    break;
                case Constant.MUSIC:
                    loadMusicData(mTitle);
                    break;
            }
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateFragment updateFragment = new UpdateFragment();

                Bundle bundle = new Bundle();
                bundle.putString(Constant.TITLE, mediaTitle.getText().toString());
                bundle.putString(Constant.DATAIMAGE, imageUrl);
                bundle.putString(Constant.YEAR, mediaYear.getText().toString());
                bundle.putString(Constant.AUTHOR, mediaAuthor.getText().toString());
                bundle.putString(Constant.GENRE, mediaGenre.getText().toString());
                bundle.putString(Constant.MEDIATYPE, mediaType);
                updateFragment.setArguments(bundle);

                ((AppCompatActivity) getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, updateFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    private void loadBookData(String title) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(Constant.BOOKS).child(title);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Book book = snapshot.getValue(Book.class);

                    mediaTitle.setText(book.getTitle());
                    mediaTypeField.setText("Тип медиа: книга");
                    mediaAuthor.setText("Автор: " + book.getAuthor());
                    mediaYear.setText("Год выпуска: " + book.getReleaseYear());
                    mediaGenre.setText("Жанры: " + book.getGenre());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MediaDetailFragment", "Error loading book data");
            }
        });
    }

    private void loadMovieData(String title) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(Constant.MOVIES).child(title);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Movie movie = snapshot.getValue(Movie.class);

                    mediaTitle.setText(movie.getTitle());
                    mediaTypeField.setText("Тип медиа: фильм");
                    mediaAuthor.setText("Автор: " + movie.getAuthor());
                    mediaYear.setText("Год выпуска: " + movie.getReleaseYear());
                    mediaGenre.setText("Жанры: " + movie.getGenre());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MediaDetailFragment", "Error loading movie data");
            }
        });
    }

    private void loadMusicData(String title) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(Constant.MUSIC).child(title);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Music music = snapshot.getValue(Music.class);

                    mediaTitle.setText(music.getTitle());
                    mediaTypeField.setText("Тип медиа: музыка");
                    mediaAuthor.setText("Автор: " + music.getAuthor());
                    mediaYear.setText("Год выпуска: " + music.getReleaseYear());
                    mediaGenre.setText("Жанры: " + music.getGenre());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MediaDetailFragment", "Error loading music data");
            }
        });
    }
}
