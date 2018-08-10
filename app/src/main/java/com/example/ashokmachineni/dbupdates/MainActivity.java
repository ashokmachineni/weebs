package com.example.ashokmachineni.dbupdates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<RvData> prodata = new ArrayList<RvData>();
    RecyclerView recyclerView;
    RvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getServerData();
    }

    private void getServerData() {
        String urlLink = "http://www.techsolpoint.com/api_example/api.json";
        System.out.println(urlLink);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlLink,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            Gson gson = new Gson();
                            JSONArray jsonArray = response.getJSONArray("list");
                            for(int i=0; i< jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                RvData rvData = gson.fromJson(String.valueOf(jsonObject),RvData.class);
                                prodata.add(rvData);
                            }
                            rvAdapter = new RvAdapter(getApplicationContext(),prodata);
                            recyclerView.setAdapter(rvAdapter);


                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }

                }
    );
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);

}}
