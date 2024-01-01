package com.example.assigment_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class shows_list extends AppCompatActivity {
    private RequestQueue queue;
    private ListView ShowsList;
    private shows_list.CustomAdaptor adaptor;
    private ArrayList<Shows> SList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_list);
        queue = Volley.newRequestQueue(this);
        ShowsList = findViewById(R.id.ShowsList);
        adaptor=new shows_list.CustomAdaptor(this,SList);
        ShowsList.setAdapter(adaptor);


        String url = "https://api.tvmaze.com/shows";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               // ArrayList<Shows> shows = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        JSONObject imageObj= obj.getJSONObject("image");
                        String poster=imageObj.optString("medium","");
                        Shows show= new Shows(obj.getString("name"),obj.getString("premiered"),poster);
                        SList.add(show);

                    }catch(JSONException exception){
                        Log.d("volley_error", exception.getMessage());
                    }
                    adaptor.notifyDataSetChanged();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_errorResponse Fail", error.toString());
            }
        });

        queue.add(request);


    }
    public class CustomAdaptor extends ArrayAdapter<Shows> {
        private Context context;
        private ArrayList<Shows>SList;

        CustomAdaptor(Context context,ArrayList<Shows>SList){
            super(context,R.layout.row,SList);
            this.context = context;
            this.SList = SList;
        }
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView Title=row.findViewById(R.id.RowMovieTitle);
            TextView AirYear=row.findViewById(R.id.RowAirYear);
            ImageView Poster=row.findViewById(R.id.MovieImage);
            Title.setText(SList.get(position).getTitle());
            AirYear.setText(SList.get(position).getYear());
            if (!SList.get(position).getPoster().equals("")) {
                Glide.with(context).load(SList.get(position).getPoster()).into(Poster);
            }else{
                Poster.setImageResource(R.drawable.welcome_logo);

            }

            return row;
        }
    }
}