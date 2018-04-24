package com.example.tgraydas.lab_6_pedro_grand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {
    public NetworkManager networkManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        networkManager = NetworkManager.getInstance(this);
        getForms();
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getForms()
    {
        final ArrayList<String> items = new ArrayList<String>();
        networkManager.getForms(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray resp = response.getJSONArray("0");
                    for(int i = 0 ; i < resp.length() ; i++){
                        JSONObject jo = resp.getJSONObject(i);
                        if (jo.getBoolean("enabled") == true)
                        {
                            items.add(jo.getString("name"));
                        }
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
                    ListView listView = findViewById(R.id.list_view_forms);
                    listView.setAdapter(arrayAdapter);
                }
                catch(JSONException e) {
                    Toast toast = new Toast(getApplicationContext());
                    toast.setText("Fallo la conección, revise si esta loggeado");
                    System.out.println(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

}
