package com.mgm.road_maintenance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mgm.utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class RequestForTender extends AppCompatActivity {
    Intent i;
    String tenderid, contractorid;
    TextView tvcontractorid, tvtenderid;
    Button applyForTender;
    EditText etAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_tender);
        i = getIntent();
        tenderid = i.getStringExtra("tender_id");
        contractorid = i.getStringExtra("contractor_id");
        tvcontractorid = findViewById(R.id.tvcontractorid);
        tvtenderid = findViewById(R.id.tvtenderid);
        etAmt = findViewById(R.id.etAmount);
        applyForTender = findViewById(R.id.bapplytender);

        applyForTender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etAmt.getText().toString().equals("")) {
                    Toast.makeText(RequestForTender.this, "Amt field required", Toast.LENGTH_LONG);
                } else {
                    sendRequest();
                }
            }
        });
    }

    void sendRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REQUEST_FOR_TENDER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("1")) {
                            Toast.makeText(RequestForTender.this, "Request for Tender Sent", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RequestForTender.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RequestForTender.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tenderid", tenderid);
                params.put("contractorid", contractorid);
                params.put("amount", etAmt.getText().toString());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}