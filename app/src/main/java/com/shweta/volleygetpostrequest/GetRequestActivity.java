package com.shweta.volleygetpostrequest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class GetRequestActivity extends AppCompatActivity {

    private TextView totalCasesWorld, totalDeathsWorld, totalRecoveredWorld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_request);

        //Initialize the objects
        totalCasesWorld = findViewById(R.id.newCasesWorld);
        totalDeathsWorld = findViewById(R.id.newDeathsWorld);
        totalRecoveredWorld = findViewById(R.id.newRecoveredWorld);

        getData();
    }

    void getData(){
        String myUrl = "https://corona.lmao.ninja/v2/all";
        StringRequest myRequest = new StringRequest(Request.Method.GET,myUrl,
                response -> {
                    try{
                        //Create a JSON object containing information from the API.
                        JSONObject myJsonObject = new JSONObject(response);
                        totalCasesWorld.setText(myJsonObject.getString("cases"));
                        totalRecoveredWorld.setText(myJsonObject.getString("recovered"));
                        totalDeathsWorld.setText(myJsonObject.getString("deaths"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> Toast.makeText(GetRequestActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }


}