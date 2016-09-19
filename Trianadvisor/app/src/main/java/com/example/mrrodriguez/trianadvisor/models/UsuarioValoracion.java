package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuel on 18/12/2015.
 */
public class UsuarioValoracion {

    @SerializedName("__type")
    @Expose
    private String __type;

    @SerializedName("objectId")
    @Expose
    private String objectId;

    @SerializedName("className")
    @Expose
    private String className;


    /**
     * No args constructor for use in serialization
     *
     */
    public UsuarioValoracion() {
    }

    public UsuarioValoracion(String objectId){
        this.objectId = objectId;
    }

    /**
     *
     * @param objectId
     * @param Type
     * @param className
     */
    public UsuarioValoracion(String Type, String className, String objectId) {
        this.__type = Type;
        this.className = className;
        this.objectId = objectId;
    }

    /**
     *
     * @return
     * The Type
     */
    public String getType() {
        return __type;
    }

    /**
     *
     * @param Type
     * The __type
     */
    public void setType(String Type) {
        this.__type = Type;
    }

    /**
     *
     * @return
     * The className
     */
    public String getClassName() {
        return className;
    }

    /**
     *
     * @param className
     * The className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     *
     * @return
     * The objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     *
     * @param objectId
     * The objectId
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}