package com.example.saroj.layout;

/**
 * Created by shudeepslove on 8/9/2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class listview extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private EditText editTextId;
    private Button buttonGet,BUTTONALL,hospital,image;
    public static final String KEY_id="id";

    private TextView textViewResult;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_items);
        listView = (ListView) findViewById(R.id.listView);
        editTextId = (EditText) findViewById(R.id.editTextId);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        BUTTONALL=(Button) findViewById(R.id.viewAll);
        hospital=(Button) findViewById(R.id.viewHospital);
        image=(Button) findViewById(R.id.viewimage1);
        buttonGet.setOnClickListener(this);
        hospital.setOnClickListener(this);
        image.setOnClickListener(this);
        BUTTONALL.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    private void getData() {
        String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(listview.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        //String name="";
        // String address="";
        // String vc = "";
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {

                JSONObject collegeData = result.getJSONObject(i);
                String name = collegeData.getString(Config.KEY_NAME);
                String phone = collegeData.getString(Config.KEY_PHONE);
                String vc = collegeData.getString(Config.KEY_VC);
                //String Output=name +"-" +address;
                // list.add(createEmployee("employee",Output));
                // textViewResult.setText("Name:\t" + name + "\nAddress:\t" + address + "\nVice Chancellor:\t" + vc);
                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_ID,name);
                employees.put(Config.TAG_PHONE,phone);
                list.add(employees);



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                listview.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_PHONE},
                new int[]{R.id.id, R.id.phone});

        listView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        if(v==buttonGet)
        {
        getData();
    } else
        if(v==BUTTONALL) {
            Intent i= new Intent(listview.this,login.class);
            String id = editTextId.getText().toString().trim();
            i.putExtra(KEY_id,id);
            startActivity(i);
        }

            if(v==hospital){
                Intent i= new Intent(listview.this,hospital.class);
//                String id = editTextId.getText().toString().trim();
//                i.putExtra(KEY_id,id);
                startActivity(i);
            }

        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewEmployee.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        String empId1 = map.get(Config.TAG_PHONE).toString();
        intent.putExtra(Config.EMP_ID,empId);
        intent.putExtra(Config.EMP_PHONE,empId1);
        startActivity(intent);
    }
}