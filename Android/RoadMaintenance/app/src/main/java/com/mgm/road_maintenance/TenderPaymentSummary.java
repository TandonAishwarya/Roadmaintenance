package com.mgm.road_maintenance;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mgm.adapter.TenderAdapter;
import com.mgm.model.TenderModel;
import com.mgm.road_maintenance.ui.home.HomeViewModel;
import com.mgm.utilities.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TenderPaymentSummary extends AppCompatActivity {
    private HomeViewModel homeViewModel;
    private RecyclerView TenderRecyclerView;
    List<TenderModel> modelList = new ArrayList<>();
    SharedPreferences pref;
    TextView tv;
    String id, title, description, type, area, taluka, district, state, sq_km, amount, tender_date, is_alloted, allotment_letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_payment_summary);
        tv = findViewById(R.id.tvnotenderalloted);
        TenderRecyclerView = findViewById(R.id.alloted_tender);
        LinearLayoutManager layoutManager = new LinearLayoutManager(TenderPaymentSummary.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        TenderRecyclerView.setLayoutManager(layoutManager);
        getAllotedTenderData();
        pref = TenderPaymentSummary.this
                .getSharedPreferences("UserDetails", MODE_PRIVATE);
        id = pref.getString("id", "");
    }


    void getAllotedTenderData() {
        //creating a string request to send request to the url
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_GET_ALLOTED_TENDER_DETAILS,
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
                                    description = jsonObject.getString("description");
                                    type = jsonObject.getString("type");
                                    area = jsonObject.getString("area");
                                    taluka = jsonObject.getString("taluka");
                                    district = jsonObject.getString("district");
                                    state = jsonObject.getString("state");
                                    sq_km = jsonObject.getString("sq_km");
                                    amount = jsonObject.getString("amount");
                                    tender_date = jsonObject.getString("tender_date");
                                    is_alloted = jsonObject.getString("is_alloted");
                                    allotment_letter = jsonObject.getString("allotment_letter");
                                    TenderModel model = new TenderModel(id, title, description, type, area, taluka, district, state, sq_km, amount, tender_date, is_alloted,allotment_letter);
                                    modelList.add(model);
                                }

                                TenderAdapter adapter = new TenderAdapter(modelList, TenderPaymentSummary.this, "TenderPaymentSummary");
                                TenderRecyclerView.setAdapter(adapter);
                            } else {
                                tv.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            tv.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(TenderPaymentSummary.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        tv.setVisibility(View.VISIBLE);

                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                headers.put("id", id);
                return headers;
            }

            @Override
            public Request.Priority getPriority() {
                return Request.Priority.IMMEDIATE;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(TenderPaymentSummary.this);
        request.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);


    }
}