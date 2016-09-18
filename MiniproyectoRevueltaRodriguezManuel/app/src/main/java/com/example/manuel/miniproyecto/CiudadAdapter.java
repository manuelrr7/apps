package com.example.manuel.miniproyecto;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


 // Adaptador para la lista de la consulta de los 5 días.


public class CiudadAdapter extends RecyclerView.Adapter<CiudadAdapter.ViewHolder> {

    private ArrayList<Semanal> mDataset;
    Context contexto;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagenTiempo;
        public TextView fecha;
        public TextView tempMax;
        public TextView tempMin;
        public TextView descripcion;


        public ViewHolder(View v) {
            super(v);
            descripcion = (TextView) v.findViewById(R.id.txtFecha);
            fecha = (TextView)v.findViewById(R.id.txtMin);
            tempMax = (TextView)v.findViewById(R.id.txtMax);
            tempMin = (TextView)v.findViewById(R.id.txtTemperatura);
            imagenTiempo  = (ImageView) v.findViewById(R.id.imgTiempo);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public CiudadAdapter(ArrayList<Semanal> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public CiudadAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_semanal, viewGroup, false);

        contexto = v.getContext();

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CiudadAdapter.ViewHolder holder, int position) {

        holder.fecha.setText(mDataset.get(position).getFecha());
        holder.tempMax.setText(mDataset.get(position).getTemp_max());
        holder.tempMin.setText(mDataset.get(position).getTemp_min());
        holder.descripcion.setText(mDataset.get(position).getDescripcion());
        Picasso.with(contexto).load(mDataset.get(position).getImagen()).fit().into(holder.imagenTiempo);
    }

    @Override
    public int getItemCount()  {
        return mDataset.size();
    }
}
