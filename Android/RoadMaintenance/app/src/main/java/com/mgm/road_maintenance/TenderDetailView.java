package com.mgm.road_maintenance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TenderDetailView extends AppCompatActivity {
    TextView tvtitle, tvdescription, tvtype, tvarea, tvtaluka, tvdistrict, tvstate, tvsq_km, tvamount, tvtender_date, tvis_alloted;
    Button brequestTender;
    Intent i;
    ImageView iv;
    SharedPreferences pref;
    String contractorid, tenderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_detail_view);

        i = getIntent();
        tvtitle = findViewById(R.id.tvtitle);
        tvdescription = findViewById(R.id.tvdescription);
        tvtype = findViewById(R.id.tvtype);
        tvarea = findViewById(R.id.tvarea);
        tvtaluka = findViewById(R.id.tvtaluka);
        tvdistrict = findViewById(R.id.tvdistrict);
        tvstate = findViewById(R.id.tvstate);
        tvsq_km = findViewById(R.id.tvsq_km);
        tvamount = findViewById(R.id.tvamount);
        tvtender_date = findViewById(R.id.tvtender_date);
        tvis_alloted = findViewById(R.id.tvis_alloted);
        brequestTender = findViewById(R.id.brequesttender);

        pref = getApplicationContext()
                .getSharedPreferences("UserDetails", MODE_PRIVATE);
        contractorid = pref.getString("id", null);
        tenderid = i.getStringExtra("id");
        tvtitle.setText("Title : " + i.getStringExtra("title"));
        tvdescription.setText("Description : " + i.getStringExtra("description"));
        tvtype.setText("Type : " + i.getStringExtra("type"));
        tvarea.setText("Area : " + i.getStringExtra("area"));
        tvtaluka.setText("Taluka : " + i.getStringExtra("taluka"));
        tvdistrict.setText("District : " + i.getStringExtra("district"));
        tvstate.setText("State : " + i.getStringExtra("state"));
        tvsq_km.setText("Sq Km : " + i.getStringExtra("sq_km"));
        //tvamount.setText("Amount : " + i.getStringExtra("amount"));
        tvtender_date.setText("Tender Date : " + i.getStringExtra("tender_date"));
        if (Integer.parseInt(i.getStringExtra("is_alloted")) == 1)
            tvis_alloted.setText("Alloted");
        else
            tvis_alloted.setText("Not Alloted");


        brequestTender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenderRequest();
                Intent i = new Intent(TenderDetailView.this, RequestForTender.class);
                i.putExtra("contractor_id", contractorid);
                i.putExtra("tender_id", tenderid);
                startActivity(i);

            }
        });
    }

    void tenderRequest() {

    }

  /*  public void showAlertBox(String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MenteesDetailView.this);
        alert.setMessage(msg);
        alert.setTitle("Request Sent");
        alert.setCancelable(false);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.create().show();
    }*/

}
