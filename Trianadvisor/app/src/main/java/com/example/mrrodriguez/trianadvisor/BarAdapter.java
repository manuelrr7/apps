package com.example.mrrodriguez.trianadvisor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mrrodriguez.trianadvisor.models.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by manuel on 14/12/2015.
 */
public class BarAdapter extends RecyclerView.Adapter<BarAdapter.BarViewHolder>{

    private ArrayList<Results> mDataset;
    Context context;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class BarViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView nombre, calle;
        public ImageView imagenBar;
        public RelativeLayout relativeclic1;
        private ArrayList<Results> mDataset;


        public BarViewHolder(ArrayList<Results>res, View v) {

            super(v);

            nombre = (TextView) v.findViewById(R.id.barName);
            imagenBar = (ImageView) v.findViewById(R.id.imgBar);
            calle = (TextView) v.findViewById(R.id.textViewCalle);
            relativeclic1 =(RelativeLayout)v.findViewById(R.id.relative);
            mDataset = res;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position= getLayoutPosition();
            Intent i= new Intent(v.getContext(), BarScrollActivity.class);
            Results resultads = mDataset.get(position);
            i.putExtra("objectId",resultads.getObjectId());
            v.getContext().startActivity(i);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BarAdapter(ArrayList<Results> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BarViewHolder onCreateViewHolder(ViewGroup parent,
                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.barview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        BarViewHolder vh = new BarViewHolder(mDataset, v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BarViewHolder holder, int position) {

        final Results resultados = mDataset.get(position);


        if(resultados != null){
            String picasso = resultados.getFoto().getUrl();

            holder.calle.setText(resultados.getDireccion());
            holder.nombre.setText(resultados.getNombre());
            Picasso.with(BarFragment.context)
                    .load(picasso)
                    .into(holder.imagenBar);
        }else{
            holder.nombre.setText("No bar disponible");
            holder.calle.setText("No disponible");
            holder.imagenBar.setImageResource(R.drawable.sitio);
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}


