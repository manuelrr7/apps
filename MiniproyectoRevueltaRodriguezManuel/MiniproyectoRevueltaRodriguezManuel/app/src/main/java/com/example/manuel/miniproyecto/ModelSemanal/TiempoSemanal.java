package com.example.manuel.miniproyecto.ModelSemanal;

/**
 * Created by manuel on 13/11/2015.
 */
public class TiempoSemanal
{
    private String message;

    private String cnt;

    private String cod;

    private Listado[] listado;

    private City city;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getCnt ()
    {
        return cnt;
    }

    public void setCnt (String cnt)
    {
        this.cnt = cnt;
    }

    public String getCod ()
    {
        return cod;
    }

    public void setCod (String cod)
    {
        this.cod = cod;
    }

    public Listado[] getListado ()
    {
        return listado;
    }

    public void setListado (Listado[] listado)
    {
        this.listado = listado;
    }

    public City getCity ()
    {
        return city;
    }

    public void setCity (City city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", cnt = "+cnt+", cod = "+cod+", listado = "+listado+", city = "+city+"]";
    }
}

