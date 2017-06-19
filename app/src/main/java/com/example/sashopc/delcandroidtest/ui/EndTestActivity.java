package com.example.sashopc.delcandroidtest.ui;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sashopc.delcandroidtest.DatabaseHelper;
import com.example.sashopc.delcandroidtest.R;
import com.example.sashopc.delcandroidtest.ui.StartTest;

public class EndTestActivity extends AppCompatActivity {

    double wholeGrade;
    ImageButton sumAnswers;
    ImageButton closeApp;
    int grade;
    TextView moreInformation;
    DatabaseHelper MyDb;
    String queryID;
    Cursor cursorGradeId;
    int gradeId;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_test);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("End of the test");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        closeApp = (ImageButton)findViewById(R.id.ibClose);
        sumAnswers= (ImageButton) findViewById(R.id.ibCheckScale);
        sumAnswers.setOnClickListener(moreInfoAboutTest);
        closeApp.setOnClickListener(closeApplication);

        MyDb = new DatabaseHelper(this);
        getWholeGrade();
        putScore();

    }
    private void getWholeGrade(){

        wholeGrade = getIntent().getDoubleExtra("Grade",grade);

        if(wholeGrade>25.5 && wholeGrade<=30){
            grade=6;
        }else if(wholeGrade>20.5 && wholeGrade<=25.5){
            grade = 5;
        }else if(wholeGrade>15.5 && wholeGrade<=20.5){
            grade = 4;
        }else if(wholeGrade>=10.5 && wholeGrade<=15.5){
            grade = 3;
        }else{
            grade = 2;
        }
       Toast toast = Toast.makeText(EndTestActivity.this, "Оценка от теста: " + String.valueOf(grade), Toast.LENGTH_SHORT);
        View toastView = toast.getView();

        toastView.setBackgroundResource(R.drawable.toast_message_style);

        toast.show();
    }
    private void getCurrentGradeId(){
      queryID = "SELECT GradeID FROM Grades_table\n" +
       " order by RANDOM() " +
              " limit 1 ";

        cursorGradeId = MyDb.getReadableDatabase().rawQuery(queryID, null);

       if( cursorGradeId!=null && cursorGradeId.moveToNext()) {

           gradeId = cursorGradeId.getInt(cursorGradeId.getColumnIndex("GradeID"));
       }

        cursorGradeId.close();
    }
    private void putScore(){
        getCurrentGradeId();
        db = MyDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("FacultyNumber", StartTestActivity.fNumber);
        contentValues.put("SpecialityAndYear",StartTest.textSpecialityAndYear);
        contentValues.put("TestName",StartTest.testName);
        contentValues.put("Grade",grade);

        db.update(DatabaseHelper.FourthTABLE_NAME, contentValues, " GradeID = " + gradeId, null);

    }

    View.OnClickListener moreInfoAboutTest = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            moreInformation = (TextView)findViewById(R.id.tvMoreInfo);
            moreInformation.setText("Скала за оценяване: \nпод 10.5 точки 2\n" +
                    "от 10.5 до 15.5 точки: 3\n" +
                    "от 16.5 до 20.5 точки: 4\n" +
                    "от 21.5 до 25.5 точки: 5\n" +
                    "от 26.5 до 30 точки: 6\n");

        }
    };

    View.OnClickListener closeApplication = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Toast toast = Toast.makeText(EndTestActivity.this, "Android test ще се затвори!", Toast.LENGTH_SHORT);

            View toastView = toast.getView();

            toastView.setBackgroundResource(R.drawable.toast_message_style);

            toast.show();

            finishAffinity();
        }
    };

}
