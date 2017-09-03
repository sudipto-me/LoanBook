package com.example.user.loanbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PeoplesListActivity extends AppCompatActivity {

    public RecyclerView rv_simple_people;
    public LinearLayoutManager linearLayoutManager;
    public PeopleAdapter peopleAdapter;
    List<People> peopleArrayList;
    Context context;
    MyDb db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peoples_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("People List");
        context = this;
        db = new MyDb(PeoplesListActivity.this);
        rv_simple_people = (RecyclerView) findViewById(R.id.rv_show_people);

    }

    @Override
    protected void onStart() {
        super.onStart();
        rv_simple_people.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(this);
        peopleAdapter = new PeopleAdapter(context, peopleArrayList);
        rv_simple_people.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        peopleArrayList = db.getAllPeoples();

        for (People people : peopleArrayList) {
            String log = "Id:" + people.getId() + "Name:" + people.getName() + "Number:" + people.getNumber();
            Log.d("Reading", "Peoples" + log);

        }
        peopleAdapter = new PeopleAdapter(context, peopleArrayList);
        rv_simple_people.setAdapter(peopleAdapter);
        peopleAdapter.notifyDataSetChanged();


    }
}
