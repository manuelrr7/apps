package com.example.manuel.miniproyecto.PojoG;

/**
 * Created by Manuel on 28/12/2015.
 */
public class Terms {

    private String value;

    private String offset;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getOffset ()
    {
        return offset;
    }

    public void setOffset (String offset)
    {
        this.offset = offset;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+", offset = "+offset+"]";
    }
}


