package com.example.assigment_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private ListView ImdbIteams;
    private Button movies;
    private Button films;
    private Button Logout;
    private TextView title;
    private CustomAdaptor adaptor;
    private ArrayList<Movie> MovieList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        ImdbIteams = findViewById(R.id.ViewListMain);
       // Logout=findViewById(R.id.LogOut);
        adaptor=new CustomAdaptor(this,MovieList);
        ImdbIteams.setAdapter(adaptor);


    }

    @Override
    protected void onStart()
    {
        super.onStart();


                 for(int i=1;i<50;i++){
                String url = "https://www.omdbapi.com/?s=movie&apikey=63546d26&y=2023&page="+i+"&r=json";

                ImdbIteams.setVisibility(View.VISIBLE);
                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //    ArrayList<String>imdb=new ArrayList<>();
                                //  Log.d("number",response.length()+"tala");
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    if (obj.has("Search")) {
                                        JSONArray results = obj.getJSONArray("Search");
                                        addIteams(results);

                                    } else {
                                        Log.d("volley_error", "No Search key in the response");

                                    }

                                } catch (JSONException e) {
                                    Log.d("volley_error", e.getMessage());

                                    //throw new RuntimeException(e);
                                }
                            }



                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error message", error.getMessage());
                    }
                });
                queue.add(request);
            }
            //Toast.makeText(getApplicationContext(),"Now onStart() calls", Toast.LENGTH_LONG).show(); //onStart Called

    }
    private void addIteams(JSONArray results){
      //  ArrayList<Movie>imdb=new ArrayList<>();
        for(int i=0;i<results.length();i++){
            try{
               JSONObject obj=results.getJSONObject(i);
               Movie movie=new Movie(obj.getString("Title"),obj.getString("Poster"),obj.getString("Type"),obj.getString("Year"),obj.getString("imdbID"));
                MovieList.add(movie);  // Add the movie to your MovieList

            }catch (JSONException e){
                Log.d("Volley Error ",e.getMessage());
            }
        }
        adaptor.notifyDataSetChanged();

    }


    public class CustomAdaptor extends ArrayAdapter<Movie> {
        private Context context;
        private ArrayList<Movie>MovieList;

        CustomAdaptor(Context context,ArrayList<Movie>MovieList){
            super(context,R.layout.row,MovieList);
            this.context = context;
            this.MovieList = MovieList;
        }
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView Title=row.findViewById(R.id.RowMovieTitle);
            TextView AirYear=row.findViewById(R.id.RowAirYear);
            ImageView Poster=row.findViewById(R.id.MovieImage);
            Title.setText(MovieList.get(position).getTitle());
            AirYear.setText(MovieList.get(position).getYear());
            if (!MovieList.get(position).getPoster().equals("N/A")) {
                Glide.with(context).load(MovieList.get(position).getPoster()).into(Poster);
            }else{
                Poster.setImageResource(R.drawable.welcome_logo);

            }

            return row;
        }
    }

}