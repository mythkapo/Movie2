package com.example.android.movie2.CustomClass;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kapo.ng on 10/7/2015.
 */
public class Review implements Parcelable {
    public String author;
    public String content;
    public Review(String author, String content) {
        this.author = author;
        this.content = content;

    }



    protected Review(Parcel in) {
        author = in.readString();
        content = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(content);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}