package com.juaracoding.reviewfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.juaracoding.reviewfilm.activity.AddMovie;
import com.juaracoding.reviewfilm.adapter.AdapterMovie;
import com.juaracoding.reviewfilm.model.genre.GenreModel;
import com.juaracoding.reviewfilm.model.genre.GenreModel;
import com.juaracoding.reviewfilm.model.movie.MovieModel;
import com.juaracoding.reviewfilm.model.rating.RatingModel;
import com.juaracoding.reviewfilm.service.APIClient;
import com.juaracoding.reviewfilm.service.APIInterfacesRest;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView listMovie;
    ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listMovie = findViewById(R.id.listMovie);
        btnAdd = findViewById(R.id.btnAdd);


//      ketika tombol add di klik
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddMovie.class);
                startActivity(i);
            }
        });

        callMovieList();
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callMovieList(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<MovieModel> call3 = apiInterface.getMovie();
        call3.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                progressDialog.dismiss();
                MovieModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data !=null) {


                    AdapterMovie adapter = new AdapterMovie(MainActivity.this,data.getData().getMoviedb());

                    listMovie.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    listMovie.setItemAnimator(new DefaultItemAnimator());
                    listMovie.setAdapter(adapter);





                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }
}
