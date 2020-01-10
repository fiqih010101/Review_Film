package com.juaracoding.reviewfilm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juaracoding.reviewfilm.R;
import com.juaracoding.reviewfilm.model.genre.Genre;

import java.util.List;

public class AdapterMovieGenre extends RecyclerView.Adapter<AdapterMovieGenre.ViewHolder> {

    Context context;
    List<Genre> genreData;

    public AdapterMovieGenre(Context context, List<Genre> genreData) {
        this.context = context;
        this.genreData = genreData;
    }

    @NonNull
    @Override
    public AdapterMovieGenre.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovieGenre.ViewHolder holder, int position) {

//        holder.txtGenre.setAdapter();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {
        //          buat variable
        public Spinner txtGenre;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

//          findViewById
            txtGenre = (Spinner)itemView.findViewById(R.id.cmbGenre);

        }
    }
}
