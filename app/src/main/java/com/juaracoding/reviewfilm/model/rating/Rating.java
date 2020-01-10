
package com.juaracoding.reviewfilm.model.rating;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rating")
    @Expose
    private String rating;
    public final static Creator<Rating> CREATOR = new Creator<Rating>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        public Rating[] newArray(int size) {
            return (new Rating[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7931346365177206093L;

    protected Rating(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rating() {
    }

    /**
     * 
     * @param rating
     * @param id
     */
    public Rating(String id, String rating) {
        super();
        this.id = id;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(rating);
    }

    public int describeContents() {
        return  0;
    }

}
