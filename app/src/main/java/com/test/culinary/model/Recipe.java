package com.test.culinary.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;

public class Recipe implements Parcelable {

    private int id;
    private String title;
    private String time;
    private String amount;
    private String complexity;
    private String image;
    private String products;
    private JSONArray actions;

    public Recipe(int id, String title, String time, String amount, String complexity, String image, String products, JSONArray actions){
        this.id = id;
        this.title = title;
        this.time = time;
        this.amount = amount;
        this.complexity = complexity;
        this.image = image;
        this.products = products;
        this.actions = actions;
    }



    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Recipe(Parcel in) {
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getTime() {
        return time;
    }

    public String getAmount() {
        return amount;
    }

    public String getComplexity() {
        return complexity;
    }

    public String getImage() {
        return image;
    }

    public String getProducts() {
        return products;
    }

    public JSONArray getActions() {return actions; }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

}