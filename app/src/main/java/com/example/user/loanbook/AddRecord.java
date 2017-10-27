package com.example.user.loanbook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class AddRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mShowDate;
    private Spinner spr_people_name;
    Context context;
    String label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        setTitle("Add Record");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        mShowDate = (TextView) findViewById(R.id.tv_DateShow);
        spr_people_name = (Spinner) findViewById(R.id.spr_people_name);
    }

    @Override
    protected void onStart() {
        super.onStart();

        spr_people_name.setOnItemSelectedListener(this);
        loadSpinnerData();
        mShowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day_of_month = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,
                        mDateSetListener,
                        year, month, day_of_month);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                mShowDate.setText(date);

            }
        };
    }

    public void loadSpinnerData() {
        MyDb db = new MyDb(this);
        List<String> labels = db.getNames();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,labels);
        spr_people_name.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        label = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(context, "You Selected:" + label, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
