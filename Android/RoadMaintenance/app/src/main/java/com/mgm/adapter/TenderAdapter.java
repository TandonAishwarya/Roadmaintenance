package com.mgm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mgm.model.TenderModel;
import com.mgm.road_maintenance.PaymentSummary;
import com.mgm.road_maintenance.R;
import com.mgm.road_maintenance.TenderAllotmentLetter;
import com.mgm.road_maintenance.TenderDetailView;

import java.util.List;

public class TenderAdapter extends RecyclerView.Adapter<TenderAdapter.ViewHolder> {
    private List<TenderModel> tenderModelList;
    Context context;
    String tag;

    public TenderAdapter(List<TenderModel> menteesModelList, Context context, String tag) {
        this.tenderModelList = menteesModelList;
        this.context = context;
        this.tag = tag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tender_details, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String title = tenderModelList.get(i).getTitle();
        String description = tenderModelList.get(i).getDescription();
        String date = tenderModelList.get(i).getTender_date();
        viewHolder.setData(i, title, description, date);
    }

    @Override
    public int getItemCount() {
        return tenderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView tenderDate;
        private Button bview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_decription);
            tenderDate = itemView.findViewById(R.id.tv_tender_date);
            bview = itemView.findViewById(R.id.bDetailView);
        }

        private void setData(final int pos, String strtitle, String strdescription, String strtenderDate) {
            title.setText(strtitle);
            description.setText(strdescription);
            tenderDate.setText(strtenderDate);
            if (tag.equals("ApprovedTender")) {
                bview.setText("Check Status");
                bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, TenderAllotmentLetter.class);
                        i.putExtra("id", tenderModelList.get(pos).getId());
                        i.putExtra("allotment_letter", tenderModelList.get(pos).getAllotment_letter());
                        context.startActivity(i);

                    }
                });

            } else if (tag.equals("TenderPaymentSummary")) {
                bview.setText("Payment Summary");
                bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, PaymentSummary.class);
                        i.putExtra("id", tenderModelList.get(pos).getId());
                        context.startActivity(i);

                    }
                });

            } else {
                bview.setText("View");
                bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, TenderDetailView.class);
                        i.putExtra("id", tenderModelList.get(pos).getId());
                        i.putExtra("title", tenderModelList.get(pos).getTitle());
                        i.putExtra("description", tenderModelList.get(pos).getDescription());
                        i.putExtra("type", tenderModelList.get(pos).getType());
                        i.putExtra("area", tenderModelList.get(pos).getArea());
                        i.putExtra("taluka", tenderModelList.get(pos).getTaluka());
                        i.putExtra("district", tenderModelList.get(pos).getDistrict());
                        i.putExtra("state", tenderModelList.get(pos).getState());
                        i.putExtra("sq_km", tenderModelList.get(pos).getSq_km());
                        i.putExtra("amount", tenderModelList.get(pos).getAmount());
                        i.putExtra("tender_date", tenderModelList.get(pos).getTender_date());
                        i.putExtra("is_alloted", tenderModelList.get(pos).getIs_alloted());

                        context.startActivity(i);
                    }
                });
            }
        }

    }
}
