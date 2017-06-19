package com.example.sashopc.delcandroidtest.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.sashopc.delcandroidtest.DatabaseHelper;
import com.example.sashopc.delcandroidtest.R;
import com.example.sashopc.delcandroidtest.async.ApiCallback;
import com.example.sashopc.delcandroidtest.async.LoginAsyncTask;
import com.example.sashopc.delcandroidtest.async.TypeTestsAsyncTask;
import com.example.sashopc.delcandroidtest.model.TestTypes;
import com.example.sashopc.delcandroidtest.model.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class StartTestActivity extends AppCompatActivity {

    ImageButton imgStartTest;
    DatabaseHelper MyDb;
    EditText etFacNumber;
    ImageButton imgLogin;
    TextView tvWelcome;
    TextView checkFacultyNumber;
    TextView tvSpecialityAndYear;
    Spinner optionSpinner;
    ArrayAdapter<CharSequence> adapter;
    String testType;
    EditText specialityAndYear;
    private final Gson gson = new GsonBuilder().create();


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Start of the test");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        checkFacultyNumber = (TextView) findViewById(R.id.tvFacultyNumber);
        tvSpecialityAndYear = (TextView) findViewById(R.id.tvSpecialityAndYear);
        specialityAndYear = (EditText) findViewById(R.id.specialityAndYearEditText);
        imgStartTest = (ImageButton) findViewById(R.id.StartTest);
        imgStartTest.setVisibility(View.INVISIBLE);
        buttonEffect(imgStartTest);
        imgLogin = (ImageButton) findViewById(R.id.ibStart);
        etFacNumber = (EditText) findViewById(R.id.studentEditText);
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        optionSpinner = (Spinner) findViewById(R.id.optionSpinner);

        MyDb = new DatabaseHelper(this);
        imgLogin.setOnClickListener(onLoginClickListener);
        imgStartTest.setOnClickListener(start);
        optionSpinner.setVisibility(View.INVISIBLE);
    }

    View.OnClickListener onLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (TextUtils.isEmpty(etFacNumber.getText().toString())) {
                etFacNumber.setError("Полето не е попълнено");
            } else {
                login();
            }
        }
    };

    public View.OnClickListener start = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(testType)) {
                // TODO show no test type selected dialog
            } else {
                startActivity(QuestionActivity.newIntent(StartTestActivity.this, testType));
            }
        }
    };

    private void login() {
        new LoginAsyncTask(new ApiCallback() {
            @Override
            public void onResponse(String response) {
                UserResponse userResponse = gson.fromJson(response, UserResponse.class);
                if (TextUtils.isEmpty(userResponse.name) || userResponse.name.equalsIgnoreCase("null")) {
                    // TODO show wrong credentials message
                } else {
                    getTestTypes();
                }
            }
        }).execute(etFacNumber.getText().toString());
    }

    private void getTestTypes() {
        new TypeTestsAsyncTask(new ApiCallback() {
            @Override
            public void onResponse(String response) {
                TestTypes testTypes = gson.fromJson(response, TestTypes.class);
                adapter = new ArrayAdapter(StartTestActivity.this, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter.addAll(testTypes.typeTests);
                optionSpinner.setAdapter(adapter);
                setOnTestTypeSelectedListener();
            }
        }).execute();
    }

    private void setOnTestTypeSelectedListener() {
        optionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

                testType = (String) optionSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public static void buttonEffect(View button) {
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