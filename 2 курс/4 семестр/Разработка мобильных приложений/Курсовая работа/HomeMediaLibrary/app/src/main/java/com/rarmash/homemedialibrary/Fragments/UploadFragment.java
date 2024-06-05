package com.rarmash.homemedialibrary.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rarmash.homemedialibrary.Constant;
import com.rarmash.homemedialibrary.R;
import com.rarmash.homemedialibrary.models.Book;
import com.rarmash.homemedialibrary.models.Movie;
import com.rarmash.homemedialibrary.models.Music;

public class UploadFragment extends Fragment {
    ImageView uploadImage;
    TextInputEditText mediaTitle, mediaYear, mediaAuthor, mediaGenre;
    RadioGroup mediaTypeGroup;
    RadioButton radioMovie, radioMusic, radioBook;
    Button saveButton;
    String imageURL, media_reference;
    Uri uri;
    Object media;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upload, container, false);

        uploadImage = rootView.findViewById(R.id.uploadImage);
        mediaTitle = rootView.findViewById(R.id.media_title);
        mediaYear = rootView.findViewById(R.id.media_year);
        mediaAuthor = rootView.findViewById(R.id.media_author);
        mediaGenre = rootView.findViewById(R.id.media_genre);
        saveButton = rootView.findViewById(R.id.save_media);

        mediaTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        mediaYear.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        mediaAuthor.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        mediaGenre.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1000)});

        mediaTypeGroup = rootView.findViewById(R.id.media_type_group);
        radioMovie = rootView.findViewById(R.id.radio_movie);
        radioMusic = rootView.findViewById(R.id.radio_music);
        radioBook = rootView.findViewById(R.id.radio_book);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(getContext(), "Постер не выбран", Toast.LENGTH_SHORT);
                        }
                    }
                }
        );
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        return rootView;
    }

    public void saveData() {
        String title = mediaTitle.getText().toString();
        String year = mediaYear.getText().toString();
        String author = mediaAuthor.getText().toString();
        String genre = mediaGenre.getText().toString();

        if (TextUtils.isEmpty(title)) {
            mediaTitle.setError("Поле не может быть пустым");
            mediaTitle.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(year)) {
            mediaYear.setError("Поле не может быть пустым");
            mediaYear.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(author)) {
            mediaAuthor.setError("Поле не может быть пустым");
            mediaAuthor.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(genre)) {
            mediaAuthor.setError("Поле не может быть пустым");
            mediaAuthor.requestFocus();
            return;
        }
        if (title.contains("\n")) {
            mediaTitle.setError("Поле имеет запрещенный символ ('\\n')");
            mediaTitle.requestFocus();
            return;
        }
        if (year.contains("\n")) {
            mediaYear.setError("Поле имеет запрещенный символ ('\\n')");
            mediaYear.requestFocus();
            return;
        }
        if (author.contains("\n")) {
            mediaAuthor.setError("Поле имеет запрещенный символ ('\\n')");
            mediaAuthor.requestFocus();
            return;
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(R.layout.saving_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData() {
        String title = mediaTitle.getText().toString();
        String year = mediaYear.getText().toString();
        String author = mediaAuthor.getText().toString();
        String genre = mediaGenre.getText().toString();

        int selectedId = mediaTypeGroup.getCheckedRadioButtonId();

        if (selectedId == radioMovie.getId()) {
            media = new Movie(title, author, genre, Integer.parseInt(year), imageURL);
            media_reference = Constant.MOVIES;
        } else if (selectedId == radioMusic.getId()) {
            media = new Music(title, author, genre, Integer.parseInt(year), imageURL);
            media_reference = Constant.MUSIC;
        } else if (selectedId == radioBook.getId()) {
            media = new Book(title, author, genre, Integer.parseInt(year), imageURL);
            media_reference = Constant.BOOKS;
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(media_reference);
        Query checkMediaDB = reference.orderByChild(Constant.TITLE).equalTo(title);

        checkMediaDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    mediaTitle.setError("Медиа уже существует");
                } else {
                    FirebaseDatabase.getInstance().getReference(media_reference).child(title).setValue(media).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Медиа успешно добавлено", Toast.LENGTH_SHORT).show();
                                MediaFragment mediaFragment = new MediaFragment();

                                ((AppCompatActivity) getContext()).getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.frame_layout, mediaFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}