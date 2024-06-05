package com.rarmash.homemedialibrary.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rarmash.homemedialibrary.Constant;
import com.rarmash.homemedialibrary.R;
import com.rarmash.homemedialibrary.models.Book;
import com.rarmash.homemedialibrary.models.Movie;
import com.rarmash.homemedialibrary.models.Music;

import java.util.Objects;

public class UpdateFragment extends Fragment {
    ImageView updateImage;
    Button updateButton;
    TextInputEditText updateMediaTitle, updateMediaYear, updateMediaAuthor, updateMediaGenre;
    RadioGroup mediaTypeGroup;
    RadioButton radioMovie, radioMusic, radioBook;
    String title, year, author, genre;
    String oldTitle;
    String imageURL;
    String key, oldImageURL, mediaType;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Object media = null;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);

        updateButton = rootView.findViewById(R.id.update_media);
        updateMediaTitle = rootView.findViewById(R.id.media_title);
        updateImage = rootView.findViewById(R.id.update_image);
        updateMediaYear = rootView.findViewById(R.id.media_year);
        updateMediaAuthor = rootView.findViewById(R.id.media_author);
        updateMediaGenre = rootView.findViewById(R.id.media_genre);

        mediaTypeGroup = rootView.findViewById(R.id.media_type_group);
        radioMovie = rootView.findViewById(R.id.radio_movie);
        radioMusic = rootView.findViewById(R.id.radio_music);
        radioBook = rootView.findViewById(R.id.radio_book);

        updateMediaTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        updateMediaYear.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        updateMediaAuthor.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        updateMediaGenre.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1000)});

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            updateImage.setImageURI(uri);
                        } else {
                            Toast.makeText(getContext(), "Постер не выбран", Toast.LENGTH_SHORT);
                        }
                    }
                }
        );
        Bundle bundle = getArguments();
        if (bundle != null) {
            Glide.with(getContext()).load(bundle.getString(Constant.DATAIMAGE)).into(updateImage);
            updateMediaTitle.setText(bundle.getString(Constant.TITLE));
            oldTitle = bundle.getString(Constant.TITLE);
            updateMediaYear.setText(bundle.getString(Constant.YEAR).substring(13, bundle.getString(Constant.YEAR).length()));
            updateMediaAuthor.setText(bundle.getString(Constant.AUTHOR).substring(7, bundle.getString(Constant.AUTHOR).length()));
            updateMediaGenre.setText(bundle.getString(Constant.GENRE).substring(7, bundle.getString(Constant.GENRE).length()));
            key = bundle.getString(Constant.KEY);
            oldImageURL = bundle.getString(Constant.DATAIMAGE);
            mediaType = bundle.getString(Constant.MEDIATYPE);
            switch (mediaType) {
                case Constant.BOOKS:
                    radioBook.setChecked(true);
                    break;
                case Constant.MOVIES:
                    radioMovie.setChecked(true);
                    break;
                case Constant.MUSIC:
                    radioMusic.setChecked(true);
                    break;
            }
        }
        databaseReference = FirebaseDatabase.getInstance().getReference(mediaType).child(oldTitle);

        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        return rootView;
    }

    public void saveData() {
        if (uri != null) {
            storageReference = FirebaseStorage.getInstance().getReference().child("Android Images").child(uri.getLastPathSegment());

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
                    updateData();
                    dialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                }
            });
        } else {
            updateDataWithoutImage();
        }
    }

    public void updateData() {
        title = updateMediaTitle.getText().toString();
        year = updateMediaYear.getText().toString().trim();
        author = updateMediaAuthor.getText().toString();
        genre = updateMediaGenre.getText().toString();

        if (TextUtils.isEmpty(title)) {
            updateMediaTitle.setError("Поле не может быть пустым");
            updateMediaTitle.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(year)) {
            updateMediaYear.setError("Поле не может быть пустым");
            updateMediaYear.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(author)) {
            updateMediaAuthor.setError("Поле не может быть пустым");
            updateMediaAuthor.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(genre)) {
            updateMediaGenre.setError("Поле не может быть пустым");
            updateMediaGenre.requestFocus();
            return;
        }
        if (title.contains("\n")) {
            updateMediaTitle.setError("Поле имеет запрещенный символ ('\\n')");
            updateMediaTitle.requestFocus();
            return;
        }
        if (year.contains("\n")) {
            updateMediaYear.setError("Поле имеет запрещенный символ ('\\n')");
            updateMediaYear.requestFocus();
            return;
        }
        if (author.contains("\n")) {
            updateMediaAuthor.setError("Поле имеет запрещенный символ ('\\n')");
            updateMediaAuthor.requestFocus();
            return;
        }

        int selectedId = mediaTypeGroup.getCheckedRadioButtonId();

        if (selectedId == radioMovie.getId()) {
            media = new Movie(title, author, genre, Integer.parseInt(year), imageURL);
        } else if (selectedId == radioMusic.getId()) {
            media = new Music(title, author, genre, Integer.parseInt(year), imageURL);
        } else if (selectedId == radioBook.getId()) {
            media = new Book(title, author, genre, Integer.parseInt(year), imageURL);
        }

        databaseReference.setValue(media).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(getContext(), "Обновлен", Toast.LENGTH_SHORT).show();

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

    public void updateDataWithoutImage() {
        final String newTitle = updateMediaTitle.getText().toString();
        title = newTitle;
        year = updateMediaYear.getText().toString().trim();
        author = updateMediaAuthor.getText().toString();
        genre = updateMediaGenre.getText().toString();

        if (TextUtils.isEmpty(title)) {
            updateMediaTitle.setError("Поле не может быть пустым");
            updateMediaTitle.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(year)) {
            updateMediaYear.setError("Поле не может быть пустым");
            updateMediaYear.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(author)) {
            updateMediaAuthor.setError("Поле не может быть пустым");
            updateMediaAuthor.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(genre)) {
            updateMediaGenre.setError("Поле не может быть пустым");
            updateMediaGenre.requestFocus();
            return;
        }
        if (title.contains("\n")) {
            updateMediaTitle.setError("Поле имеет запрещенный символ ('\\n')");
            updateMediaTitle.requestFocus();
            return;
        }
        if (year.contains("\n")) {
            updateMediaYear.setError("Поле имеет запрещенный символ ('\\n')");
            updateMediaYear.requestFocus();
            return;
        }
        if (author.contains("\n")) {
            updateMediaAuthor.setError("Поле имеет запрещенный символ ('\\n')");
            updateMediaAuthor.requestFocus();
            return;
        }

        int selectedId = mediaTypeGroup.getCheckedRadioButtonId();

        if (selectedId == radioMovie.getId()) {
            media = new Movie(title, author, genre, Integer.parseInt(year), oldImageURL);
        } else if (selectedId == radioMusic.getId()) {
            media = new Music(title, author, genre, Integer.parseInt(year), oldImageURL);
        } else if (selectedId == radioBook.getId()) {
            media = new Book(title, author, genre, Integer.parseInt(year), oldImageURL);
        }

        databaseReference.setValue(media).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    if (!Objects.equals(oldTitle, newTitle)) {
                        databaseReference.getParent().child(newTitle).setValue(media);
                        databaseReference.getParent().child(oldTitle).removeValue();
                    }
                    Toast.makeText(getContext(), "Обновлено", Toast.LENGTH_SHORT).show();

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