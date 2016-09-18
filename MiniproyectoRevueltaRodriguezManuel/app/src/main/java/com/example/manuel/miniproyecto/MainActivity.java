package com.example.manuel.miniproyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Region;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.manuel.miniproyecto.PojoG.Predictions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity{




    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;


    AutoCompleteTextView ciudad;
    ImageButton bus;

    private String nombre_ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bus = (ImageButton) findViewById(R.id.botonbuscar);
        //bus.setOnClickListener(this);
        ciudad = (AutoCompleteTextView) findViewById(R.id.editCiudad);
        //ciudad.setVisibility(View.INVISIBLE);

        //Se recoge el nombre de la ciudad buscada.
        Bundle extras = getIntent().getExtras();
        nombre_ciudad = extras.getString("titulo");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id ==R.id.action_search){
            Intent i = new Intent(MainActivity.this, BuscarInicioActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }




    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position==0) {
                return new DetalleTiempoFragment(nombre_ciudad);
            } else if(position==1) {
                return new SemanalFragment(nombre_ciudad);
            } else if(position==2) {
                return new FavoritosFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Hoy";
                case 1:
                    return "Semanal";
                case 2:
                    return "Fav";
            }
            return null;
        }
    }

    //ASYNTASK QUE REALIZA LA CONSULTA AL API DE GOOGLE PLACES

   /* class GooglePlaceTask extends AsyncTask<String, Void, List<Predictions>> {

        @Override
        protected List<Predictions> doInBackground(String... params) {
            List<Predictions> result = null;
            URL url = null;

            try {
                if (params[0] != null) {

                    url = new URL(UrlApis.URL_GOOGLE_PLACES_BASE() + params[0].replace(" ", "").replace("España","Spain").trim()
                            + UrlApis.URL_GOOGLE_PLACES_APPEND());

                    BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
                    Gson gson = new Gson();
                    com.example.manuel.miniproyecto.PojoG.Region region = gson.fromJson(r, com.example.manuel.miniproyecto.PojoG.Region.class);
                    result = Arrays.asList(region.getPredictions());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Predictions> predictionses) {
            super.onPostExecute(predictionses);

            if (predictionses != null) {
                //si el autocompletar no vale descomenta esto y comenta el otro adapter.
                //ciudad.setAdapter(new GoogleplacesAdapter(MainActivity.this, android.R.layout.simple_list_item_1,predictionses));
                ciudad.setAdapter(new GooglePersonalizadoAdapter(MainActivity.this,predictionses));
                ciudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        SharedPreferences prefs = getSharedPreferences("mispreferencias", Context.MODE_APPEND);
                        SharedPreferences.Editor editar = prefs.edit();

                        Predictions pred = (Predictions) parent.getItemAtPosition(position);

                        editar.putString("ciudad_defecto",pred.getDescription());
                        editar.commit();

                        MainActivity.this.finish();
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        i.putExtra("titulo", pred.getDescription());
                        startActivity(i);
                    }
                });
            }

        }
    }*/

}
