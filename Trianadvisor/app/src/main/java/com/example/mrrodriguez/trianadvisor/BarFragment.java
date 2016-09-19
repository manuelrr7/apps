package com.example.mrrodriguez.trianadvisor;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mrrodriguez.trianadvisor.interfaces.TrianadvisorBarAPI;
import com.example.mrrodriguez.trianadvisor.models.Bar;
import com.example.mrrodriguez.trianadvisor.models.Results;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class BarFragment extends Fragment {

//    Context context;
//    ArrayList<String> result;
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayaoutManager;



    public static Context context;
    ArrayList<String> result;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayaoutManager;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bar_fragment, container, false);
        context = container.getContext();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewBar);
        mRecyclerView.setHasFixedSize(true);
        mLayaoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayaoutManager);

        new BarTask().execute();

        return v;

    }





    private class BarTask extends AsyncTask<Void, Void, Bar> {


        @Override
        protected Bar doInBackground(Void... params) {

            String url = "https://api.parse.com";

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(url)
                    .build();

            TrianadvisorBarAPI service =
                    retrofit.create(TrianadvisorBarAPI.class);

            Call<Bar> contacts = service.listadoBares();

            Response<Bar> result = null;

            try {
                result = contacts.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result != null) {
                return result.body();
            } else {
                return null;

            }
        }

        @Override
        protected void onPostExecute(Bar bares) {
            //super.onPostExecute(bares);
            result = new ArrayList();
            Results r = null;
            ArrayList<Results> lista = new ArrayList<>();

            //lista.add(bares.getResults());
            for (int i = 0; i < bares.getResults().length; i++){
                lista.add(bares.getResults()[i]);
            }

            bares.getResults();
            mAdapter = new BarAdapter(lista);
            mRecyclerView.setAdapter(mAdapter);
            Log.i("LISTADO", String.valueOf(bares));
        }



    }
}



