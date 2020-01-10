package com.juaracoding.reviewfilm.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.juaracoding.reviewfilm.MainActivity;
import com.juaracoding.reviewfilm.R;
import com.juaracoding.reviewfilm.model.genre.GenreModel;
import com.juaracoding.reviewfilm.model.moviedb.add.AddModel;
import com.juaracoding.reviewfilm.model.rating.RatingModel;
import com.juaracoding.reviewfilm.service.APIClient;
import com.juaracoding.reviewfilm.service.APIInterfacesRest;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovie extends AppCompatActivity {

    EditText txtJudul, txtDirectBy, txtWriten,txtStudio;
    CalendarView dtInTheater;
    Spinner spnGenre, spnRating;
    ImageView img1, img2, img3;
    ImageButton imgBtn1, imgBtn2, imgBtn3;
    Button btnSend;

    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

//        findViewById
//      Edit Text
        txtJudul = findViewById(R.id.txtJudul);
        txtDirectBy = findViewById(R.id.txtDirectBy);
        txtWriten = findViewById(R.id.txtWriten);
        txtStudio = findViewById(R.id.txtStudio);

        dtInTheater = findViewById(R.id.dtInTheater);

//      Button
        btnSend = findViewById(R.id.btnSend);

//      spiner
        spnGenre = findViewById(R.id.spnGenre);
        spnRating = findViewById(R.id.spnRating);

//        foto dan foto button
//        img1 = findViewById(R.id.img1);
//        img2 = findViewById(R.id.img2);
//        img3 = findViewById(R.id.img3);

        imgBtn1 = findViewById(R.id.imgBtn1);
        imgBtn2 = findViewById(R.id.imgBtn2);
        imgBtn3 = findViewById(R.id.imgBtn3);


////      Spinner Rating
        spinnerRating();
//
////      Spinner Genre
        spinnerGenre();

//      ketika tombol img button di klik
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });

//      ketika tombol img button di klik
        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder2();
            }
        });

//      ketika tombol img button di klik
        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder3();
            }
        });


        //      format tanggal
        dtInTheater.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                date = new GregorianCalendar(year, month, dayOfMonth).getTime();
            }
        });

//      Ketika tombol send di tekan
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String tanggal = formatter.format(date);
                postDataMovie(txtJudul.getText().toString(),
                        spnRating.getSelectedItem().toString(),
                        spnGenre.getSelectedItem().toString(),
                        txtDirectBy.getText().toString(),
                        txtWriten.getText().toString(),
                        tanggal,
                        txtStudio.getText().toString(),
                        "image1",
                        "image2",
                        "image3"
                );
            }
        });


    }

    //  post data with photo
    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void postDataMovie(String judul,
                              String rating,
                              String genre,
                              String directedby,
                              String writenby,
                              String intheater,
                              String studio,
                              String img1,
                              String img2,
                              String img3){

//      Request Body untuk post data dengan multipart untuk mengubah file ke binary
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), byteArray);
        MultipartBody.Part bodyImg = MultipartBody.Part.createFormData("image1", "image.png", requestFile);

        RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/jpeg"), byteArray2);
        MultipartBody.Part bodyImg2 = MultipartBody.Part.createFormData("image2", "image2.png", requestFile2);

        RequestBody requestFile3 = RequestBody.create(MediaType.parse("image/jpeg"), byteArray3);
        MultipartBody.Part bodyImg3 = MultipartBody.Part.createFormData("image3", "image3.png", requestFile3);


        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);

        progressDialog.show();
        Call<AddModel> call3 = apiInterface.addDataMovie(toRequestBody(judul), toRequestBody(rating),toRequestBody(genre), toRequestBody(directedby), toRequestBody(writenby), toRequestBody(intheater), toRequestBody(studio), bodyImg,bodyImg2, bodyImg3);
        call3.enqueue(new Callback<AddModel>() {
            @Override
            public void onResponse(Call<AddModel> call, Response<AddModel> response) {
                progressDialog.dismiss();
                AddModel data = response.body();

                if (data !=null) {


                    Toast.makeText(AddMovie.this,data.getMessage(),Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AddMovie.this, MainActivity.class);
                    startActivity(intent);




                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddMovie.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(AddMovie.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<AddModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }

//  Spiner Rating
    public void spinnerRating(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(AddMovie.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();


        Call<RatingModel> call3 = apiInterface.getRating();
        call3.enqueue(new Callback<RatingModel>() {
            @Override
            public void onResponse(Call<RatingModel> call, Response<RatingModel> response) {

                progressDialog.dismiss();
                RatingModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data !=null) {

                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.getData().getRating().size(); i++){
                        listSpinner.add(data.getData().getRating().get(i).getRating());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddMovie.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnRating.setAdapter(adapter);





                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddMovie.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(AddMovie.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }

    //  Spiner Genre
    public void spinnerGenre(){

        progressDialog.show();
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);

        Call<GenreModel> call3 = apiInterface.getGenre();
        call3.enqueue(new Callback<GenreModel>() {
            @Override
            public void onResponse(Call<GenreModel> call, Response<GenreModel> response) {
                progressDialog.dismiss();
                GenreModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data !=null) {

                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.getData().getGenre().size(); i++){
                        listSpinner.add(data.getData().getGenre().get(i).getGenre());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddMovie.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnGenre.setAdapter(adapter);





                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddMovie.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(AddMovie.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<GenreModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }



    public Uri fileUri;
    private int REQUEST_GALLERY = 100;
    private int REQUEST_GALLERY2 = 200;
    private int REQUEST_GALLERY3 = 300;

    //  open folder galery HP
    public void openFolder() {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,REQUEST_GALLERY);
    }

    //  open folder galery HP
    public void openFolder2() {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY2);
    }

    //  open folder galery HP
    public void openFolder3() {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY3);
    }

    //    onActivity Result
//    buat variable bitmap
    Bitmap bitmap;
    byte[] byteArray;
    byte[] byteArray2;
    byte[] byteArray3;

//  Start Activity Result untuk mengembalikan nilai dari activity lain atau aplikasi lain
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
//          convert file ke dalam bentuk binary
            Uri selectedImage = data.getData();

            imgBtn1.setImageURI(selectedImage);
            Bitmap bitmap = ((BitmapDrawable) imgBtn1.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byteArray = baos.toByteArray();
        } else if (requestCode == REQUEST_GALLERY2 && resultCode == Activity.RESULT_OK) {
            //          convert file ke dalam bentuk binary
            Uri selectedImage = data.getData();

            imgBtn2.setImageURI(selectedImage);
            Bitmap bitmap = ((BitmapDrawable) imgBtn2.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byteArray2 = baos.toByteArray();
        } else if (requestCode == REQUEST_GALLERY3 && resultCode == Activity.RESULT_OK) {
            //          convert file ke dalam bentuk binary
            Uri selectedImage = data.getData();

            imgBtn3.setImageURI(selectedImage);
            Bitmap bitmap = ((BitmapDrawable) imgBtn3.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byteArray3 = baos.toByteArray();
        }
    }

    //  Request Body untuk post data dengan multipart untuk mengubah string ke text plain
    public RequestBody toRequestBody(String value) {
        if (value == "") {
            value = "";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

}
