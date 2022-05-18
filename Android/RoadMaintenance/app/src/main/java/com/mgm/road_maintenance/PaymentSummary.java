package com.mgm.road_maintenance;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentSummary extends AppCompatActivity {
    TextView tv_tender_name, tv_tender_amount, tv_tender_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);
        tv_tender_date=findViewById(R.id.tender_date);
        tv_tender_amount=findViewById(R.id.tender_amount);
        tv_tender_name=findViewById(R.id.tender_name);

    }
}