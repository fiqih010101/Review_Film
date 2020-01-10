package com.juaracoding.reviewfilm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.reviewfilm.R;
import com.juaracoding.reviewfilm.model.movie.Moviedb;
import com.squareup.picasso.Picasso;


import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {

    Context context;
    List<Moviedb> movieData;

    public AdapterMovie(Context context, List<Moviedb> movieData) {
        this.context = context;
        this.movieData = movieData;
    }

    @NonNull
    @Override
    public AdapterMovie.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovie.ViewHolder holder, int position) {
//      tampilkan data ke layar
        
        holder.txtJudul.setText(movieData.get(position).getJudul());
        holder.txtRating.setText(movieData.get(position).getRating());
        holder.txtGenre.setText(movieData.get(position).getGenre());
        holder.txtDirectBy.setText(movieData.get(position).getDirectedby());
        holder.txtInTheater.setText(movieData.get(position).getIntheater());

        String image = movieData.get(position).getImage1();
        Picasso.get().load(image).into(holder.imageMovie);
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {
        //          buat variable
        public TextView txtJudul, txtRating, txtDirectBy, txtGenre, txtInTheater;
        public ImageView imageMovie;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

//          findViewById
            txtJudul = (TextView)itemView.findViewById(R.id.txtJudul);
            txtRating = (TextView)itemView.findViewById(R.id.txtRating);
            txtGenre = (TextView)itemView.findViewById(R.id.txtGenre);
            txtDirectBy = (TextView)itemView.findViewById(R.id.txtDirectBy);
            txtInTheater = (TextView)itemView.findViewById(R.id.txtInTheater);
            imageMovie = (ImageView)itemView.findViewById(R.id.imgMovie);
        }
    }
}
