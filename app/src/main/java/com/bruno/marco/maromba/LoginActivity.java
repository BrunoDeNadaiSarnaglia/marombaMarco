package com.bruno.marco.maromba;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v) throws JSONException {
//        startActivity(new Intent(this, MainActivity.class));
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://maromba-server.herokuapp.com/api";

        EditText userInputEditText = (EditText) findViewById(R.id.user_input);
        final String userInput = userInputEditText.getText().toString();
        EditText emailInputEditText = (EditText) findViewById(R.id.email_input);
        final String emailInput = emailInputEditText.getText().toString();
        EditText passwordInputEditText = (EditText) findViewById(R.id.password_input);
        final String passwordInput = passwordInputEditText.getText().toString();

        final HashMap<String, String> params = new HashMap<String, String>()
        {{
            put("name", userInput);
            put("email", emailInput);
            put("password", passwordInput);
        }};

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://10.0.3.2:5000/api/users",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Context context = getApplicationContext();
                        CharSequence text = response.toString();
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Context context = getApplicationContext();
                        CharSequence text = error.getMessage();
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
