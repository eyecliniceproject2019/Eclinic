package com.example.eyeclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Eyecare extends AppCompatActivity
{
    public static final String EXTRA_POS = "com.example.eclinic.EXTRA_POS";
    private Toolbar mToolbar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyecare);

        mToolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setTitle("Specicles Shopping");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView=(ListView)findViewById(R.id.listview);
        EyeAdapter adapter=new EyeAdapter(this);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {

                Intent intent=new Intent(getApplicationContext(),AddActivity.class);
                //intent.putExtra("Position",pos);
                intent.putExtra(EXTRA_POS,pos);

                startActivity(intent);





            }
        });


    }
}
