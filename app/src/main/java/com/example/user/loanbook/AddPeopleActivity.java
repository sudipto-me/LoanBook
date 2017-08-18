package com.example.user.loanbook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddPeopleActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private Spinner mSpinner;
    private DatePickerDialog.OnDateSetListener mDateSetLisener;
    Context context;
    private EditText mPeoplesName, mPeoplesNumber;
    private Button mAddPeople;
    String peoplesName, peoplesNumber, peoplesType, date;
    MyDb db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        setTitle("Add People");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        mDisplayDate = (TextView) findViewById(R.id.tv_show_date);
        mSpinner = (Spinner) findViewById(R.id.spr_man_type);
        mPeoplesName = (EditText) findViewById(R.id.et_new_name);
        mPeoplesNumber = (EditText) findViewById(R.id.et_new_number);
        mAddPeople = (Button) findViewById(R.id.btn_add_people);


    }

    @Override
    protected void onStart() {
        super.onStart();


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(context,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,
                        mDateSetLisener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetLisener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);

            }
        };
        db = new MyDb(this);


        mAddPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peoplesName = mPeoplesName.getText().toString().trim();
                peoplesNumber = mPeoplesNumber.getText().toString().trim();
                peoplesType = String.valueOf(mSpinner.getSelectedItem());

                db.insertPeople(peoplesName, peoplesNumber, peoplesType, date);

                Cursor cursor = db.getPeople();
                if (cursor.moveToFirst()) {
                    Log.d("Data" + "From" + cursor.getString(1), toString());
                }


            }
        });


    }
}
