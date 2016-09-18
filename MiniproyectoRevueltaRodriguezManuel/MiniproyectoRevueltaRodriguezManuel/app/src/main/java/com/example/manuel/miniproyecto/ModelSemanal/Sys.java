package com.example.manuel.miniproyecto.ModelSemanal;

/**
 * Created by manuel on 13/11/2015.
 */
public class Sys
{
    private String pod;

    public String getPod ()
    {
        return pod;
    }

    public void setPod (String pod)
    {
        this.pod = pod;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pod = "+pod+"]";
    }
}

