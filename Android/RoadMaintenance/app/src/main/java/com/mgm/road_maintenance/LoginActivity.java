package com.mgm.road_maintenance;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mgm.utilities.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends Activity {
    int MyVersion = Build.VERSION.SDK_INT;

    EditText etmbno, etpassword;
    Button btnlogin, btnreg, btnforgot;
    String mbno, password, role = "";
    private ProgressDialog mProgress;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etmbno = findViewById(R.id.et_mb);
        etpassword = findViewById(R.id.et_password);
        btnlogin = findViewById(R.id.login_btn);
        btnreg = findViewById(R.id.new_user_btn);
        btnforgot = findViewById(R.id.forgot_pwd_btn);

        if (!isNetworkAvailable()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    LoginActivity.this);
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Go To Settings",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog,
                                        int id) {
                                    // Restart the activity
                                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                                    startActivity(intent);

                                }

                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog,
                                        int id) {
                                    // Restart the activity
                                    dialog.dismiss();
                                    finish();
                                }

                            });

            AlertDialog alert = builder.create();
            alert.show();

        }
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
            }
        }

        btnlogin.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login();

                    }
                });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void login() {

        loginUser();
        final ProgressDialog progressdialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme);
        progressdialog.setIndeterminate(true);
        progressdialog.setMessage("Authenticating...");
        progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressdialog.show();


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        progressdialog.dismiss();
                    }
                }, 5000);

    }

    public void loginUser() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    pref = getApplicationContext()
                            .getSharedPreferences("UserDetails", MODE_PRIVATE);


                    //getting the whole json object from the response
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("record");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String emailid = jsonObject.getString("emailid");
                    String mobileno = jsonObject.getString("mobileno");


                    editor = pref.edit();
                    editor.putString("id", id);
                    editor.putString("name", name);
                    editor.putString("emailid", emailid);
                    editor.putString("mobile", mobileno);

                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                    showAlertBox("Invalid Username/Password");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError instanceof TimeoutError) {
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                headers.put("username", etmbno.getText().toString());
                headers.put("password", etpassword.getText().toString());
                headers.put("role", role);

                return headers;
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    // Private class isNetworkAvailable
    private boolean isNetworkAvailable() {
        // Using ConnectivityManager to check for Network Connection
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    public void showAlertBox(String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
        alert.setMessage(msg);
        alert.setTitle("Login Failed");
        alert.setCancelable(false);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.create().show();
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "Grant permission", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //    Toast.makeText(this, "All Permission Accepted", Toast.LENGTH_SHORT).show();
                } else {
                    //  Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
