package com.example.wenlin.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wenlin.volley.beans.Data;

import it.sephiroth.android.library.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private Data.ResultItem resultItem;

    private ImageView Image;
    private TextView NameText;
    private TextView Sex;
    private TextView Note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.resultItem = (Data.ResultItem) this.getIntent().getExtras().get("DATA");
        this.Image = (ImageView) findViewById(R.id.img);
        this.NameText = (TextView) findViewById(R.id.name);
        this.Sex = (TextView) findViewById(R.id.sex);
        this.Note = (TextView) findViewById(R.id.note);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams params = this.Image.getLayoutParams();
        params.height = width;
        this.Image.setLayoutParams(params);
        Picasso.with(this).load(this.resultItem.getImageName()).placeholder(R.drawable.ic_loading).into(this.Image);

        this.NameText.setText("姓名:" + resultItem.getName());
        this.Sex.setText("性別: " + resultItem.getSex());
        this.Note.setText ("附註:" + resultItem.getNote());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
