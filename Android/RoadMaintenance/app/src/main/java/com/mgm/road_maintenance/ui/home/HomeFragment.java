package com.mgm.road_maintenance.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.mgm.road_maintenance.R;
import com.mgm.utilities.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView TenderRecyclerView;
    List<TenderModel> modelList = new ArrayList<>();
    SharedPreferences pref;
    TextView tv;
    String id, title, description, type, area, taluka, district, state, sq_km, amount, tender_date, is_alloted,allotment_letter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        tv = root.findViewById(R.id.tvnotenderalloted);
        TenderRecyclerView = root.findViewById(R.id.alloted_tender);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        TenderRecyclerView.setLayoutManager(layoutManager);
        getAllotedTenderData();
        pref = getActivity()
                .getSharedPreferences("UserDetails", MODE_PRIVATE);
        id = pref.getString("id", "");
        return root;
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

                                TenderAdapter adapter = new TenderAdapter(modelList, getActivity(), "ApprovedTender");
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
                        Toast.makeText(getActivity(), "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
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
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        request.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);


    }
}