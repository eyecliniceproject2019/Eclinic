package com.example.eyeclinic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Retrieve extends AppCompatActivity
{
    private Toolbar mToolbar;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Cliniccard cliniccard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        mToolbar = (Toolbar) findViewById(R.id.retrieve_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("My Clinic Card");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cliniccard = new Cliniccard();
        listView = (ListView) findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance();


        }
    }




