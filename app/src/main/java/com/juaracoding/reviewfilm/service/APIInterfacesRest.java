package com.juaracoding.reviewfilm.service;

/**
 * Created by user on 1/10/2018.
 */










import com.juaracoding.reviewfilm.model.genre.GenreModel;
import com.juaracoding.reviewfilm.model.movie.MovieModel;
import com.juaracoding.reviewfilm.model.moviedb.add.AddModel;
import com.juaracoding.reviewfilm.model.rating.RatingModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */
// untuk meng inisialisasi method yang nantinya akan digunakan pada activity
public interface APIInterfacesRest {

 /*   @GET("weather")   <---- endpoint dari API Server
    Call<WeatherModel> getWeather(@Query("q") String q, @Query("appid") String appid);
*/

// post data multipart, di gunakan ketika data yang diinput memiliki lebih dari satu type data
    @Multipart
    @POST("moviedb/add")
    Call<AddModel> addDataMovie(

            @Part("judul") RequestBody txtJudul,
            @Part("rating") RequestBody cmbRating,
            @Part("genre") RequestBody cmbGenre,
            @Part("directedby") RequestBody txtDirectedBy,
            @Part("writenby") RequestBody txtWritenBy,
            @Part("intheater") RequestBody txtIntheater,
            @Part("studio") RequestBody txtStudio,
            @Part MultipartBody.Part img1,
            @Part MultipartBody.Part img2,
            @Part MultipartBody.Part img3


    );

//  get movie
    @GET("moviedb/all")
    Call<MovieModel> getMovie();

//  get rating
    @GET("rating/all ")
    Call<RatingModel> getRating(); // <-------- yang di dalam <nama model>, getRating adalah nama method

//  get genre
    @GET("genre/all")
    Call<GenreModel> getGenre();

//    @FormUrlEncoded , digunakan ketika hanya memiliki 1 type data
//    @POST("geolocation/add")
//    Call<PostResult> postDataGeo(@Field("latitude") String lat, @Field("longitude") String lon, @Field("datetime") String datetime, @Field("photo") String photo);



/*
    @GET("forecast")
    Call<ForcastWeatherModel> getForecastBasedLocation(@Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String appid);
*/

/*
    @GET("users")
    Call<com.juaracoding.absensi.model.reqres.User> getUserReqres(@Query("page") String page);

    @GET("posts")
    Call<List<Post>> getPost();

    @GET("comments")
    Call<List<Comment>> getComment(@Query("postId") String postId);*/



   /* @FormUrlEncoded
    @POST("api/user/login")
    Call<Authentication> getAuthentication(@Field("username") String username, @Field("password") String password);

    @GET("api/user_mobile/all")
    Call<Authentication> getUser(@Query("username_k") String user);

   @GET("api/komplain/ticket")
   Call<KomplainModel> getTicket(@Query("username") String username);

    @FormUrlEncoded
    @POST("api/komplain/add")
    Call<UpdateKomplain> addKomplain(@Field("username_komplain") String username, @Field("kode_edc") String kode_edc, @Field("masalah") String masalah, @Field("notes_komplain") String notes);

    @GET("api/edc_problem/all")
    Call<MasalahEdcModel> getEDCProblem();
*//*
    @GET("api/dataorder/all")
    Call<ModelOrder> getOrder(@Query("username") String user);



/*
            @Part MultipartBody.Part img1,
           @Part MultipartBody.Part img2,
           @Part MultipartBody.Part img3,
 *//*

   @Multipart
   @POST("api/dataorder/update")
   Call<Komplain> updateData(

           @Part("pod_date") RequestBody podate,
           @Part("status") RequestBody status,
           @Part("lat") RequestBody lat,
           @Part("lon") RequestBody lon,
           @Part("poddate") RequestBody poddate,
           @Part("recievedate") RequestBody recievedate,
           @Part("id") RequestBody id,
           @Part MultipartBody.Part img1


   );


   @Multipart
   @POST("api/komplain/update")
   Call<UpdateKomplain> sendImage(
           @Part("id") RequestBody id,
           @Part("username_penanganan") RequestBody username_komplain,
           @Part("hasil_penanganan") RequestBody masalah,
           @Part("tanggal_penanganan") RequestBody kode_edc,
           @Part("notes_penanganan") RequestBody notes_komplain,
           @Part("status") RequestBody status,
           @Part MultipartBody.Part img1


   );*/

}
