package com.example.manuel.miniproyecto.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Manuel on 28/12/2015.
 */
public class Rain {

    @SerializedName("3h")
    @Expose
    private double _3h;

    /**
     *
     * @return
     * The _3h
     */
    public double get3h() {
        return _3h;
    }

    /**
     *
     * @param _3h
     * The 3h
     */
    public void set3h(double _3h) {
        this._3h = _3h;
    }

}
