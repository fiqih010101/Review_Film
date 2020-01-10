
package com.juaracoding.reviewfilm.model.genre;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("genre")
    @Expose
    private String genre;
    public final static Creator<Genre> CREATOR = new Creator<Genre>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        public Genre[] newArray(int size) {
            return (new Genre[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1578855551388626433L;

    protected Genre(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.genre = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Genre() {
    }

    /**
     * 
     * @param genre
     * @param id
     */
    public Genre(String id, String genre) {
        super();
        this.id = id;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(genre);
    }

    public int describeContents() {
        return  0;
    }

}
