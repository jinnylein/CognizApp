package com.example.jin.jintest2;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class FragmentStart extends Fragment {

    private String uriString;
    private Context context;
    private JSONArray questions;
    private Intent startGame;
    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);

        ((Button) rootView.findViewById(R.id.buttonStart2)).setEnabled(false);
        ((Button) rootView.findViewById(R.id.buttonStart3)).setEnabled(false);

        queue = Volley.newRequestQueue(MainActivity.context);

        uriString = "https://gist.githubusercontent.com/lengers/2f37c68881b6d924b963e5474d365e6a/raw/0ec81132633308545bd422df5c8c0bc873db5829/all.json";
        context = rootView.getContext();
        startGame = new Intent(context, GameActivity.class);


        Button button = rootView.findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HEY", "Hey");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, uriString,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                try {
                                    String globalResponse = new String(response.getBytes(), "UTF-8");
                                    questions = new JSONArray(globalResponse);
                                    startGame.putExtra("questions", globalResponse);
                                    startActivity(startGame);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                queue.add(stringRequest);
            }
        });

        return rootView;
    }


}

