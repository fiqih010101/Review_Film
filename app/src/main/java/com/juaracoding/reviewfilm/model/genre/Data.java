
package com.juaracoding.reviewfilm.model.genre;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("genre")
    @Expose
    private List<Genre> genre = null;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4703474562246005320L;

    protected Data(Parcel in) {
        in.readList(this.genre, (com.juaracoding.reviewfilm.model.genre.Genre.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param genre
     */
    public Data(List<Genre> genre) {
        super();
        this.genre = genre;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(genre);
    }

    public int describeContents() {
        return  0;
    }

}
