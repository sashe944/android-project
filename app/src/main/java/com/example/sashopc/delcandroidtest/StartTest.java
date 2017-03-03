package com.example.sashopc.delcandroidtest;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.sashopc.delcandroidtest.Android.QuestionActivity;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


public class StartTest extends AppCompatActivity {

    ImageButton imgStartTest;
    DatabaseHelper MyDb;
    EditText checkStudent;
    ImageButton imgCheck;
    TextView tvWelcome;
    TextView checkFacultyNumber;
    TextView tvSpecialityAndYear;
    Cursor studentCursor;
    String firstName, lastName;
    public static String fNumber;
    public static String textSpecialityAndYear;
    Spinner optionSpinner;
    ArrayAdapter<CharSequence> adapter;
    Intent mainIntent;
    String itemText;
    EditText specialityAndYear;
    public static String testName;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);

      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Start of the test");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        checkFacultyNumber = (TextView) findViewById(R.id.tvFacultyNumber);
        tvSpecialityAndYear = (TextView) findViewById(R.id.tvSpecialityAndYear);
        specialityAndYear=(EditText)findViewById(R.id.specialityAndYearEditText);
        imgStartTest = (ImageButton) findViewById(R.id.StartTest);
        imgStartTest.setVisibility(View.INVISIBLE);
        buttonEffect(imgStartTest);
        imgCheck = (ImageButton) findViewById(R.id.ibStart);
        checkStudent = (EditText) findViewById(R.id.studentEditText);
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        optionSpinner = (Spinner) findViewById(R.id.optionSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinnerItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionSpinner.setAdapter(adapter);

        HttpClient httpclient = new DefaultHttpClient();


        MyDb = new DatabaseHelper(this);
        imgCheck.setOnClickListener(check);
        imgStartTest.setOnClickListener(start);
        optionSpinner.setVisibility(View.INVISIBLE);
    }

    View.OnClickListener check = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            getStudent();
            getItem();
            if (checkStudent.getText().toString().equals(fNumber)) {
                checkFacultyNumber.setText("Валиден номер!");
                textSpecialityAndYear = specialityAndYear.getText().toString();

                checkFacultyNumber.setTextColor(Color.parseColor("#00BFFF"));
                tvSpecialityAndYear.setVisibility(View.VISIBLE);

                if(specialityAndYear.getText().toString().equals("")){
                    tvSpecialityAndYear.setText("Полето не е попълнено");
                    tvSpecialityAndYear.setTextColor((Color.RED));
                }else{
                    tvSpecialityAndYear.setText("Полето е попълнено");
                    tvSpecialityAndYear.setTextColor(Color.parseColor("#00BFFF"));
                    optionSpinner.setVisibility(View.VISIBLE);
                    tvWelcome.setText(String.format("%s %s", firstName, lastName));
                }
                specialityAndYear.setVisibility(View.VISIBLE);

            } else {
                checkFacultyNumber.setText("Невалиден номер!");
                checkFacultyNumber.setTextColor(Color.RED);
                imgStartTest.setVisibility(View.INVISIBLE);
                optionSpinner.setVisibility(View.INVISIBLE);
                tvWelcome.setText("");
            }
        }
    };

    public View.OnClickListener start = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            testName = optionSpinner.getSelectedItem().toString();
            startActivity(mainIntent);
        }
    };

    private void getStudent() {
        try {
            studentCursor = MyDb.getReadableDatabase().query(DatabaseHelper.ThirdTABLE_NAME,
                    null,
                    "FacultyNumber LIKE " + checkStudent.getText().toString(),
                    null,
                    null,
                    null,
                    null);
            if (studentCursor.moveToFirst()) {
                fNumber = studentCursor.getString(0);
                firstName = studentCursor.getString(1);
                lastName = studentCursor.getString(2);
            }
            studentCursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getItem() {
        optionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

                    itemText = (String) optionSpinner.getSelectedItem();
                    if (itemText.equals("Android Test")) {
                        imgStartTest.setVisibility(View.VISIBLE);
                        MainApplication.TEST_TYPE = testType.TEST_ANDROID;
                    }
                    if (itemText.equals("C# Test")) {
                        imgStartTest.setVisibility(View.VISIBLE);
                        MainApplication.TEST_TYPE = testType.TEST_CSHARP;
                    }
                    if (itemText.equals("JAVA Test")) {
                        imgStartTest.setVisibility(View.VISIBLE);
                        MainApplication.TEST_TYPE = testType.TEST_JAVA;
                    }
                mainIntent = new Intent(StartTest.this, QuestionActivity.class);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
}
