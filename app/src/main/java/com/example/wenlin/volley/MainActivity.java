package com.example.wenlin.volley;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wenlin.volley.beans.Data;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private RequestQueue requestQueue;
    private TextView mText;
    private TextView mText2;
    private ProgressDialog pDialog;
    private Button jsonButton;
    private Button gsonButton;
    private Button listButton;

    private Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.testresult);
        mText2 = (TextView) findViewById(R.id.testresult2);
        this.gsonButton = (Button) findViewById(R.id.btn_gson);
        this.jsonButton = (Button) findViewById(R.id.btn_json);
        this.listButton = (Button) findViewById(R.id.btn_list);
        this.execute();
        doJson();
        dobtn();

    }
    private void execute(){
        this.requestQueue = Volley.newRequestQueue(this);
    }




    private void doGet(){
        showDialog();
        String url= "http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=f4a75ba9-7721-4363-884d-c3820b0b917c&limit=10";
        final StringRequest req = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                hideDialog();
                Gson gson=new Gson();
                data=gson.fromJson(response, Data.class);
                mText2.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                if(error.networkResponse != null) {
                    Toast.makeText(MainActivity.this, "  error", Toast.LENGTH_SHORT).show();
                }else{
                    nonetwork();
                }
            }
        });
        requestQueue.add(req);

    }

    private void doJson(){
        showDialog();
        String url= "http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=f4a75ba9-7721-4363-884d-c3820b0b917c&limit=10";
        final StringRequest req = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        hideDialog();
                        Gson gson=new Gson();
                        data=gson.fromJson(response, Data.class);
                        String no="";
                        for(Data.ResultItem resultItem:data.getResult().getResults()){
                            no +="id: "+ resultItem.get_id()+"\n"+"name: "+resultItem.getName()+"\n"+"reason: "+resultItem.getAge()+"\n\n";
                        }
                        mText.setText(no);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                if(error.networkResponse != null) {
                    Toast.makeText(MainActivity.this, "  error", Toast.LENGTH_SHORT).show();
                }else{
                    nonetwork();
                }
            }
        });
        requestQueue.add(req);

    }

    public  void dobtn(){
        gsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mText2.setText("");
                mText.setText("");
                doJson();

            }
        });

        jsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mText2.setText("");
                mText.setText("");
                doGet();
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("DATA", data.getResult());
                startActivity(intent);


            }
        });

    }

    //no network dialog
    private void nonetwork(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(" error");
        alertDialog.setMessage("check your network and reload app.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }



    private void showDialog() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("wait a minute");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
    }

    private void hideDialog() {
        if (pDialog != null){
            pDialog.dismiss();
            pDialog = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
