package com.example.android.beatbox;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.internal.Constants;

import java.util.HashMap;
import java.util.Map;

public class forms extends AppCompatActivity {
    EditText edtName, edtPhone;
    ProgressDialog progressDialog;
    FloatingActionButton fab;

    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_forms);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);

        // Initializing Queue for Volley
        queue = Volley.newRequestQueue(getApplicationContext());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.getText().toString().trim().length() > 0 && edtPhone.getText().toString().trim().length() > 0) {
                    postData(edtName.getText().toString().trim(), edtPhone.getText().toString().trim());
                } else {
                    Snackbar.make(view, "Required Fields Missing", Snackbar.LENGTH_LONG).show();
                }
            }
        });


}
    public void postData(final String name, final String phone) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                com.example.android.beatbox.Constants.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Response: " + response);
                        if (response.length() > 0) {
                            Snackbar.make(fab,"Successfully Posted", Snackbar.LENGTH_LONG).show();
                            edtName.setText(null);
                            edtPhone.setText(null);
                        } else {
                            Snackbar.make(fab, "Try Again", Snackbar.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Snackbar.make(fab, "Error while Posting Data", Snackbar.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();
                params.put(com.example.android.beatbox.Constants.nameField, name);
                params.put(com.example.android.beatbox.Constants.phoneField, phone);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy (
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }
}
