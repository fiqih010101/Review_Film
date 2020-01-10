
package com.juaracoding.reviewfilm.model.movie;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Moviedb implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("directedby")
    @Expose
    private String directedby;
    @SerializedName("writenby")
    @Expose
    private String writenby;
    @SerializedName("intheater")
    @Expose
    private String intheater;
    @SerializedName("studio")
    @Expose
    private String studio;
    @SerializedName("image1")
    @Expose
    private String image1;
    @SerializedName("image2")
    @Expose
    private String image2;
    @SerializedName("image3")
    @Expose
    private String image3;
    public final static Creator<Moviedb> CREATOR = new Creator<Moviedb>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Moviedb createFromParcel(Parcel in) {
            return new Moviedb(in);
        }

        public Moviedb[] newArray(int size) {
            return (new Moviedb[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4674244390773846034L;

    protected Moviedb(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.judul = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.genre = ((String) in.readValue((String.class.getClassLoader())));
        this.directedby = ((String) in.readValue((String.class.getClassLoader())));
        this.writenby = ((String) in.readValue((String.class.getClassLoader())));
        this.intheater = ((String) in.readValue((String.class.getClassLoader())));
        this.studio = ((String) in.readValue((String.class.getClassLoader())));
        this.image1 = ((String) in.readValue((String.class.getClassLoader())));
        this.image2 = ((String) in.readValue((String.class.getClassLoader())));
        this.image3 = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Moviedb() {
    }

    /**
     * 
     * @param studio
     * @param directedby
     * @param image3
     * @param intheater
     * @param writenby
     * @param rating
     * @param genre
     * @param id
     * @param judul
     * @param image1
     * @param image2
     */
    public Moviedb(String id, String judul, String rating, String genre, String directedby, String writenby, String intheater, String studio, String image1, String image2, String image3) {
        super();
        this.id = id;
        this.judul = judul;
        this.rating = rating;
        this.genre = genre;
        this.directedby = directedby;
        this.writenby = writenby;
        this.intheater = intheater;
        this.studio = studio;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirectedby() {
        return directedby;
    }

    public void setDirectedby(String directedby) {
        this.directedby = directedby;
    }

    public String getWritenby() {
        return writenby;
    }

    public void setWritenby(String writenby) {
        this.writenby = writenby;
    }

    public String getIntheater() {
        return intheater;
    }

    public void setIntheater(String intheater) {
        this.intheater = intheater;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(judul);
        dest.writeValue(rating);
        dest.writeValue(genre);
        dest.writeValue(directedby);
        dest.writeValue(writenby);
        dest.writeValue(intheater);
        dest.writeValue(studio);
        dest.writeValue(image1);
        dest.writeValue(image2);
        dest.writeValue(image3);
    }

    public int describeContents() {
        return  0;
    }

}
