package com.example.manuel.miniproyecto.ModelSemanal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel on 28/12/2015.
 */
public class OpenWeatherFiveDays {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private long cnt;
    @SerializedName("list")
    @Expose
    private List<com.example.manuel.miniproyecto.ModelSemanal.List> list = new ArrayList<com.example.manuel.miniproyecto.ModelSemanal.List>();

    /**
     * No args constructor for use in serialization
     *
     */
    public OpenWeatherFiveDays() {
    }

    /**
     *
     * @param message
     * @param cnt
     * @param cod
     * @param list
     * @param city
     */
    public OpenWeatherFiveDays(City city, String cod, double message, long cnt, List<com.example.manuel.miniproyecto.ModelSemanal.List> list) {
        this.city = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
    }

    /**
     *
     * @return
     * The city
     */
    public City getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     *
     * @param cod
     * The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     *
     * @return
     * The message
     */
    public double getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(double message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The cnt
     */
    public long getCnt() {
        return cnt;
    }

    /**
     *
     * @param cnt
     * The cnt
     */
    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    /**
     *
     * @return
     * The list
     */
    public List<com.example.manuel.miniproyecto.ModelSemanal.List> getList() {
        return list;
    }

    /**
     *
     * @param list
     * The list
     */
    public void setList(List<com.example.manuel.miniproyecto.ModelSemanal.List> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
