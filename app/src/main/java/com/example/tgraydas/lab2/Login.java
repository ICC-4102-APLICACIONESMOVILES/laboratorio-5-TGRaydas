package com.example.tgraydas.lab2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link #} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View the_view = inflater.inflate(R.layout.fragment_login, container, false);
        Button the_button = the_view.findViewById(R.id.login_action);
        String DATABASE_NAME = "movies_db";
        final Database_PJ the_DB;
        the_DB = Room.databaseBuilder(getActivity().getApplicationContext(),
                Database_PJ.class, DATABASE_NAME).build();
        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClick(the_view);
            }
        });
        return the_view;
    }
    public boolean CheckInfo (String email, String password){
        String[] check_email = email.split("@");
        Integer len = check_email.length;
        if (len == 2 && password.length() > 5){
            return true;
        }
        else {
            return false;
        }
    }
    public void onLoginClick(View view){

        try {
            MainActivity.networkManager.login("ignacio@magnet.cl", "usuarioprueba", new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    MainActivity.WriteSharedPreferences(getActivity(), "ignacio@magnet.cl", "usuarioprueba");
                    
                    MainActivity.networkManager.getForms(new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                    System.out.println(error);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
