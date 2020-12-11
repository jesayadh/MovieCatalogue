package com.dicoding.picodiploma.moviecatalogue2;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailMovie extends AppCompatActivity {
    ImageView imageView;
    TextView textView1,textView2;
    public static final String EXTRA_MOVIE = "extra_movie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        Movie movie = new Movie();
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String nama = movie.getName();
        String deskripsi = movie.getDescription();
        Integer foto = movie.getPhoto();


        imageView = findViewById(R.id.foto);
        textView1 = findViewById(R.id.nama);
        textView2 = findViewById(R.id.deskripsi);
        textView1.setText(nama);
        textView2.setText(deskripsi);
        Glide.with(DetailMovie.this)
                .load(foto)
                .into(imageView)
        ;
    }


}

