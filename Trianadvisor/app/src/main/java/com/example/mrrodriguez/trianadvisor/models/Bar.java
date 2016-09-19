package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuel on 14/12/2015.
 */
public class Bar {

    @SerializedName("results")
    @Expose
    private Results[] results;

    public Results[] getResults(){
        return results;
    }

    public void setResults (Results[] results){
        this.results = results;
    }

    @Override
    public String toString() {
        return "Class Bar [results = "+results+"]";
    }
}
