package com.dicoding.picodiploma.moviecatalogue2;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.dicoding.picodiploma.moviecatalogue2.MovieAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    private RecyclerView rvMovie;
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        rvMovie = v.findViewById(R.id.lv_list);
        rvMovie.setHasFixedSize(true);
        prepare();
        addItem();
        showSelectedMovie();
        return v;
    }

    private void showSelectedMovie(){
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter movieAdapter = new MovieAdapter(movies);
        rvMovie.setAdapter(movieAdapter);
        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data, int position) {
                showSelectedMovie(data, position);
            }
        });
    }

    private void showSelectedMovie(Movie movie,int position) {
        Toast.makeText(getActivity(), movies.get(position).getName(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(),DetailMovie.class);
        i.putExtra(DetailMovie.EXTRA_MOVIE,movies.get(position));
        startActivity(i);
    }


    private void addItem() {
        movies = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setName(dataName[i]);
            movie.setDescription(dataDescription[i]);
            movies.add(movie);
        }
        adapter = new MovieAdapter(getContext());
        adapter.setMovies(movies);
    }
    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name1);
        dataDescription = getResources().getStringArray(R.array.data_description1);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo1);
    }
}
