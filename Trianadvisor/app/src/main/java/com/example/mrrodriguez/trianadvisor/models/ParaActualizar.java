package com.example.mrrodriguez.trianadvisor.models;



public class ParaActualizar {

    private ActuValoracion actuValoracion;
    private String objectId;

    public ParaActualizar(){}

    public ParaActualizar(ActuValoracion actuValoracion, String objectId) {
        this.actuValoracion = actuValoracion;
        this.objectId = objectId;
    }

    public ActuValoracion getActuValoracion() {
        return actuValoracion;
    }

    public void setActuValoracion(ActuValoracion actuValoracion) {
        this.actuValoracion = actuValoracion;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
