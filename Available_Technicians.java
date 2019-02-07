package com.example.melani.mas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Available_Technicians extends AppCompatActivity {

    private String URLstring = "http://192.168.1.220:5000/api/attends";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<AvailableTechnicianModel> dataModelArrayList;
    private TechListAdapter listAdapter;
    private String SerialNumberEachList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available__technicians);

        listView = findViewById(R.id.lvtechnicians);

        retrieveJSON();
        Bundle bundle = getIntent().getExtras();
        SerialNumberEachList = bundle.getString("SerialNumber");
        //Intent intent = new Intent(getApplicationContext(),AsignTechnicianActivity.class);
       // intent.putExtra("SerialNumber1",message);
       // startActivity(intent);
        Toast.makeText(this, "babba" + SerialNumberEachList, Toast.LENGTH_LONG).show();


    }

    private void retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("attendTechnician");

                            for (int i = 0; i < dataArray.length(); i++) {

                                AvailableTechnicianModel attenedtechmodel = new AvailableTechnicianModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);

//                                    playerModel.setImgURL(dataobj.getJSONObject("technicianId"));

                                JSONObject image = dataobj.getJSONObject("technicianId");

                                attenedtechmodel.setImg("http://10.10.4.175:5000/"+image.getString("profilePicture"));

                                String firstname = image.getString("firstName");
                                String lastname = image.getString("lastName");

                                attenedtechmodel.setTechname(firstname+" "+lastname);


                                dataModelArrayList.add(attenedtechmodel);

                            }

                            setupListview();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage()+"aaaabbb", Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void setupListview(){
        removeSimpleProgressDialog();  //will remove progress dialog
        listAdapter = new TechListAdapter(this, dataModelArrayList,SerialNumberEachList);
        listView.setAdapter(listAdapter);
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


