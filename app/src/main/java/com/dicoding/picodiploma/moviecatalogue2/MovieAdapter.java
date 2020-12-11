package com.dicoding.picodiploma.moviecatalogue2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    public MovieAdapter(ArrayList<Movie> list) {
        this.movies = list;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movie.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
        holder.txtName.setText(movie.getName());
        holder.txtDescription.setText(movie.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(movies.get(holder.getAdapterPosition()),position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data,int position);
    }



    private Context context;
    private ArrayList<Movie> movies;
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public int getCount() {
        return movies.size();
    }

    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        Movie movie = (Movie) getItem(i);
        viewHolder.bind(movie);
        return view;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;
        ViewHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }
        void bind(Movie movie) {
            txtName.setText(movie.getName());
            txtDescription.setText(movie.getDescription());
            imgPhoto.setImageResource(movie.getPhoto());
        }
    }
}
