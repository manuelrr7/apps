package com.example.manuel.miniproyecto;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.widget.TextView;
import java.util.ArrayList;


 //Adaptador para los favoritos


public class FavoritoAdapter extends RecyclerView.Adapter<FavoritoAdapter.ViewHolder>{

    private ArrayList<Favorito> mDataset;
    Context contexto;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public ImageButton btn_vista;
        public TextView lanzar;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView)v.findViewById(R.id.txtNomFav);
            btn_vista = (ImageButton) v.findViewById(R.id.imgLanzar);
            lanzar = (TextView) v.findViewById(R.id.textViewLanzar);

        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public FavoritoAdapter(ArrayList<Favorito> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public FavoritoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_fragment_favorito, viewGroup, false);

        contexto = v.getContext();

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final FavoritoAdapter.ViewHolder holder, final int position) {

        holder.nombre.setText(mDataset.get(position).getNombre());

        holder.lanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = contexto.getSharedPreferences("mispreferencias", Context.MODE_APPEND);
                SharedPreferences.Editor editar = prefs.edit();
                Intent i = new Intent(contexto, MainActivity.class);
                i.putExtra("titulo", Utils.listadoCiudadesFav.get(position));
                if(prefs.getString("ciudad_defecto",null).equalsIgnoreCase(Utils.listadoCiudadesFav.get(position))){
                }else {
                    //new GcmRegistrationAsyncTask(contexto).execute(Utils.listadoCiudadesFav.get(position));
                    editar.putString("ciudad_defecto", Utils.listadoCiudadesFav.get(position));
                    editar.commit();

                }
                contexto.startActivity(i);
                ((Activity) contexto).finish();
            }
        });
    }


    @Override
    public int getItemCount()  {
        return mDataset.size();
    }
}
