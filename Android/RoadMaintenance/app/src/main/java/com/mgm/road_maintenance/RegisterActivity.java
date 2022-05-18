package com.mgm.road_maintenance;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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

public class RegisterActivity extends Activity {
    EditText etname, etmobileno, etpassword, etrepassword, etemailid;
    Button bregister, blogin;
    String name, mobile, password, emailid, repassword, role;
    RadioGroup rgRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bregister = (Button) findViewById(R.id.reg_btn);
        blogin = (Button) findViewById(R.id.login_btn);
        etname = (EditText) findViewById(R.id.et_name);
        etemailid = (EditText) findViewById(R.id.et_email);
        etmobileno = (EditText) findViewById(R.id.et_mb);
        etpassword = (EditText) findViewById(R.id.et_pwd);
        etrepassword = (EditText) findViewById(R.id.et_re_pwd);
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = etname.getText().toString();
                emailid = etemailid.getText().toString();
                mobile = etmobileno.getText().toString();
                password = etpassword.getText().toString();
                repassword = etrepassword.getText().toString();

                if (name.equals("")) {
                    etname.setError("Please enter Name");
                } else if (emailid.equals(""))
                    etemailid.setError("Please enter Email Id");
                else if (!isValidEmailAddress(emailid))
                    etemailid.setError("Please enter proper Email Id");
                else if (mobile.equals(""))
                    etmobileno.setError("Please enter Mobile No");
                else if (mobile.length() != 10)
                    etmobileno.setError("Please enter Proper Mobile No");
                else if (password.equals(""))
                    etpassword.setError("Please enter Password");
                else if (!isValidPassword(password))
                    Toast.makeText(getApplicationContext(),
                            "Password must be greater than 6", Toast.LENGTH_LONG).show();
                else if (repassword.equals(""))
                    etrepassword.setError("Please Re enter Password");
                else if (!password.equals(repassword))
                    Toast.makeText(getApplicationContext(),
                            "Both Password should be same", Toast.LENGTH_LONG).show();
                else
                    SignUp();
            }
        });
    }

    public boolean isValidEmailAddress(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    public void SignUp() {

        registerUser();

        final ProgressDialog progressdialog = new ProgressDialog(RegisterActivity.this, R.style.AppTheme);
        progressdialog.setIndeterminate(true);
        progressdialog.setMessage("Creating Account...");
        progressdialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        progressdialog.dismiss();
                    }
                }, 3000);


    }

    private void registerUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("Successfully Registered")) {
                            Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("emailid", emailid);
                params.put("mobile", mobile);
                params.put("password", password);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
