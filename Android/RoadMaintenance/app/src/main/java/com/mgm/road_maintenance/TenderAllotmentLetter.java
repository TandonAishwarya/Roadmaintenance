package com.mgm.road_maintenance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mgm.utilities.Config;

public class TenderAllotmentLetter extends AppCompatActivity {
    Intent i;
    String letter;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_allotment_letter);
        i = getIntent();
        iv = findViewById(R.id.ivallotmentletter);
        letter = i.getStringExtra("allotment_letter");
        Glide.with(TenderAllotmentLetter.this)
                .load(Config.ADMIN_PANEL_IMAGE_URL + letter)
                .into(iv);
    }

}