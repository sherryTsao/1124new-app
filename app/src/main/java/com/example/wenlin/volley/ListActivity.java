package com.example.wenlin.volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wenlin.volley.beans.Data;

public class ListActivity extends AppCompatActivity {
    private Data.Result result;
    private ListView contentListView;
    private Adapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        this.result = (Data.Result) this.getIntent().getExtras().get("DATA");
        this.contentListView = (ListView) findViewById(R.id.list_content);
        this.Adapter = new Adapter(this);
        this.contentListView.setAdapter(this.Adapter);
        this.Adapter.clear();
        this.Adapter.addAll(result.getResults());
        this.Adapter.notifyDataSetChanged();

        this.contentListView.setOnItemClickListener(this.dataOnItemClickListener);

    }

    private AdapterView.OnItemClickListener dataOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            this.launchActivity(view);
        }

        private void launchActivity(View view) {
            Data.ResultItem resultItem = (Data.ResultItem) view.getTag();
            Intent intent = new Intent(ListActivity.this, DetailActivity.class);
            intent.putExtra("DATA", resultItem);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
