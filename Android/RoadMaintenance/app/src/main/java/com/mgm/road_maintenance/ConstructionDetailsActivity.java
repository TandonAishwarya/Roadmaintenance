package com.mgm.road_maintenance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mgm.model.TenderModel;
import com.mgm.utilities.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConstructionDetailsActivity extends AppCompatActivity {
    EditText etWorker, etEstimate, etSupervisor, etRawMaterial;
    String userId, id, title;
    String worker="", estimate="", supervisor="", rawMaterial="", tender = "";
    Spinner spTender;
    Button bConstruction;
    ArrayList<TenderModel> tenderlist = new ArrayList<>();
    ArrayList<String> Tender = new ArrayList<>();
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction_details);
        etWorker = findViewById(R.id.worker);
        etEstimate = findViewById(R.id.estimate);
        etSupervisor = findViewById(R.id.supervisor);
        etRawMaterial = findViewById(R.id.raw_material);
        spTender = findViewById(R.id.spinnerTender);
        bConstruction = findViewById(R.id.baddConstructionData);
        pref = getApplicationContext()
                .getSharedPreferences("UserDetails", MODE_PRIVATE);
        userId = pref.getString("id", "");
        getTenderData();
        spTender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tender = tenderlist.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bConstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worker=etWorker.getText().toString();
                estimate=etEstimate.getText().toString();
                supervisor=etSupervisor.getText().toString();
                rawMaterial=etRawMaterial.getText().toString();

                if (worker.equals("")||estimate.equals("")||supervisor.equals("")||rawMaterial.equals("")|| tender.equals(""))
                    Toast.makeText(ConstructionDetailsActivity.this, "Fields Cannot be empty", Toast.LENGTH_LONG).show();
                else
                    addConstruction();
            }
        });
    }

    public void getTenderData() {
        Log.d("Called", "Dept method called");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_FETCH_TENDER_DETAILS_FOR_CONTRACTOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        //getting the whole json object from the response
                        try {

                            JSONObject obj = new JSONObject(response);
                            //we have the array named json inside the object
                            //so here we are getting that json array
                            JSONArray jsonArray = obj.getJSONArray("record");
                            //now looping through all the elements of the json array
                            if (jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    id = jsonObject.getString("id");
                                    title = jsonObject.getString("title");

                                    TenderModel model = new TenderModel(id, title);
                                    tenderlist.add(model);
                                    Tender.add(title);
                                }
                                ArrayAdapter<String> adapter =
                                        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Tender);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spTender.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConstructionDetailsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", userId);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void addConstruction() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_ADD_CONSTRUCTION_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("Successfully Registered")) {
                            Toast.makeText(ConstructionDetailsActivity.this, response, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ConstructionDetailsActivity.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(ConstructionDetailsActivity.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConstructionDetailsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tender_id", tender);
                params.put("worker", worker);
                params.put("estimate", estimate);
                params.put("supervisor", supervisor);
                params.put("raw_material", rawMaterial);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}