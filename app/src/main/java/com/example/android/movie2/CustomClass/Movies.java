package com.example.android.movie2.CustomClass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by KaPo on 8/19/2015.
 */
public class Movies implements Comparator<Movies>, Parcelable {
    String PosterUrl;
    String releaseDate;
    String overview;
    Float average;
    String original_title;
    Float popularity;
    String id;
    ArrayList<String> trailers;
    ArrayList<Review> reviews;

    public Movies(String id, String Url, String releaseDate, String overview, Float average, String original_title, Float popularity,ArrayList<String> trailers, ArrayList<Review> reviews) {
        this.id = id;
        this.PosterUrl = Url;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.average = average;
        this.original_title = original_title;
        this.popularity = popularity;
        this.trailers = trailers;
        this.reviews = reviews;

    }

    public Float getAverage() {
        return average;
    }

    public Float getpopularity() {
        return popularity;
    }

    //sort Desc
    public static Comparator<Movies> MovieRatingComparator
            = new Comparator<Movies>() {

        public int compare(Movies lhs, Movies rhs) {
            Float movie1 = lhs.getAverage();
            Float movie2 = rhs.getAverage();

            if (movie1 < movie2) return 1;
            if (movie1 > movie2) return -1;
            return 0;
        }
    };

    //sort Desc
    public static Comparator<Movies> PopularityComparator
            = new Comparator<Movies>() {

        public int compare(Movies lhs, Movies rhs) {
            Float movie1 = lhs.getpopularity();
            Float movie2 = rhs.getpopularity();

            if (movie1 < movie2) return 1;
            if (movie1 > movie2) return -1;
            return 0;
        }
    };


    @Override
    public int compare(Movies lhs, Movies rhs) {
        return (int) (lhs.getAverage() - rhs.getAverage());
    }

    protected Movies(Parcel in) {
        PosterUrl = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        average = in.readByte() == 0x00 ? null : in.readFloat();
        original_title = in.readString();
        popularity = in.readByte() == 0x00 ? null : in.readFloat();
        id = in.readString();
        if (in.readByte() == 0x01) {
            trailers = new ArrayList<String>();
            in.readList(trailers, String.class.getClassLoader());
        } else {
            trailers = null;
        }
        if (in.readByte() == 0x01) {
            reviews = new ArrayList<Review>();
            in.readList(reviews, Review.class.getClassLoader());
        } else {
            reviews = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PosterUrl);
        dest.writeString(releaseDate);
        dest.writeString(overview);
        if (average == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeFloat(average);
        }
        dest.writeString(original_title);
        if (popularity == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeFloat(popularity);
        }
        dest.writeString(id);
        if (trailers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(trailers);
        }
        if (reviews == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(reviews);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}