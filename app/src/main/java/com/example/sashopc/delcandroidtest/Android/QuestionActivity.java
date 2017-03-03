package com.example.sashopc.delcandroidtest.Android;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sashopc.delcandroidtest.CountdownListener;
import com.example.sashopc.delcandroidtest.DatabaseHelper;
import com.example.sashopc.delcandroidtest.MainApplication;
import com.example.sashopc.delcandroidtest.Question;
import com.example.sashopc.delcandroidtest.R;
import com.example.sashopc.delcandroidtest.StartTest;
import com.example.sashopc.delcandroidtest.answerType;
import com.example.sashopc.delcandroidtest.testType;

import java.util.concurrent.TimeUnit;

import static android.widget.RadioGroup.*;

public class QuestionActivity extends AppCompatActivity implements CountdownListener {
    private Button btnFirsQuestion;

    DatabaseHelper MyDb;
    private  TextView tvQuestionOne;
    private TextView tvQuestionOne1;
    private TextView tvValidateText;
    private TextView tvCheckBoxValidateText;
    private TextView tvTimePlaceholder;
    RadioGroup rgSingleAnswer;
    RadioButton rbOne, rbTwo, rbThree;
    CheckBox chOne,chTwo,chThree;
    double sumPoints;
    String answerOne, answerTwo, answerThree;
    String question;
    MainApplication app;
    ObjectAnimator anim;
    Cursor cursorAnswers;
    int questionID;
    Cursor cursorQuestion;
    String rbCorrectAnswer;
    String chCorrectAnswer;
    Cursor cursorCorrectAnswer;
    Cursor cursorCorrectAnswers;
    String dbCorrectAnswer;
    String dbCheckBoxAnswer1;
    String dbCheckBoxAnswer2;
    int questionType;
    int type;
    LinearLayout linearLayoutCheckBoxes;
    LinearLayout linearLayoutText;
    String queryType;
    Cursor cursorType;
    EditText textArea;
    TextView textViewValidateTextArea;
    TextView textViewQuestiontTextArea;
    String tableName;
    String AnswerTableName;
    double grade;
    int counter;
    String Answer;
    Question currentQuestion = new Question();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        app = (MainApplication) getApplicationContext();

        btnFirsQuestion = (Button) findViewById(R.id.buttonNext1);
        btnFirsQuestion.setOnClickListener(nextQuestion);
        tvValidateText = (TextView) findViewById(R.id.textViewValidate1);
        tvCheckBoxValidateText = (TextView)findViewById(R.id.textViewValidate);

        tvCheckBoxValidateText.setText("Все още не е избран отговор!");
        tvCheckBoxValidateText.setTextColor(Color.RED);

        tvValidateText.setText("Все още не е избран отговор!");
        tvValidateText.setTextColor(Color.RED);

        btnFirsQuestion.setEnabled(false);
        btnFirsQuestion.setAlpha(0.1f);


        linearLayoutCheckBoxes = (LinearLayout)findViewById(R.id.checkBoxAnswersLayout);
        linearLayoutText = (LinearLayout)findViewById(R.id.textAreaAnswersLayout);

        tvQuestionOne = (TextView) findViewById(R.id.textViewQuestion1);
        tvQuestionOne1 = (TextView) findViewById(R.id.textViewQuestion);
        textViewQuestiontTextArea = (TextView)findViewById(R.id.textViewQuestiontTextArea);

        textViewValidateTextArea = (TextView)findViewById(R.id.textViewValidateTextArea);
        chOne = (CheckBox) findViewById(R.id.checkBox1);
        chTwo = (CheckBox) findViewById(R.id.checkBox2);
        chThree = (CheckBox) findViewById(R.id.checkBox3);
        rbOne = (RadioButton) findViewById(R.id.radioButton1);
        rbTwo = (RadioButton) findViewById(R.id.radioButton2);
        rbThree = (RadioButton) findViewById(R.id.radioButton3);
        rgSingleAnswer = (RadioGroup) findViewById(R.id.radioGroup1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        counter=0;


        textArea = (EditText)findViewById(R.id.edText);
        MyDb = new DatabaseHelper(this);



        initLayout();
        initTimer();
        readQuestionAndAnswerFromDb();
        getPointsForSingleAnswer();
        getPointsForMultipleAnswers();
        validateText();
        animateProgressBar();

        // getRandomQuestion
        //getAnswersForQuestion
        //getQuestionType
        //showView(questionType)
        //populateViews(question)
    }

    private void initLayout() {


        switch (MainApplication.TEST_TYPE) {
            case testType.TEST_ANDROID:
                getSupportActionBar().setTitle("ANDROID TEST");

                break;
            case testType.TEST_CSHARP:
                getSupportActionBar().setTitle("CSHARP TEST");


                break;
            case testType.TEST_JAVA:
                getSupportActionBar().setTitle("JAVA TEST");

                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        app.startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        app.pauseTimer();
    }

    private void getQuestionType(){

        queryType ="SELECT Type\n" +
                "                FROM\n" +
                "        Question_table\n" +
                "        ORDER BY RANDOM()\n" +
                "        limit 1 ";

        cursorType = MyDb.getReadableDatabase().rawQuery(queryType,null);
        cursorType.moveToFirst();
        type = cursorType.getInt(cursorType.getColumnIndex("Type"));

    }

    private void validateText(){

        textViewValidateTextArea.setText("Все още не е въведен текст!");
        textViewValidateTextArea.setTextColor(Color.RED);

          textArea.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

              }

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {
                  textViewValidateTextArea.setText("Вече е въведен е текс!");
                  textViewValidateTextArea.setTextColor(Color.GREEN);
                  btnFirsQuestion.setEnabled(true);
                  btnFirsQuestion.setAlpha(0.5f);
              }

              @Override
              public void afterTextChanged(Editable s) {

              }
          });
        textArea.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    textArea.getText();
                }
            }
        });
    }
    private void readQuestionAndAnswerFromDb() {
        getQuestionType();

        tableName = null;
        switch (MainApplication.TEST_TYPE){
            case testType.TEST_ANDROID:
                tableName = DatabaseHelper.FirstTABLE_NAME;
                break;
            case testType.TEST_CSHARP:
                tableName=DatabaseHelper.SixthTABLE_NAME;
                break;
            case testType.TEST_JAVA:
                tableName = DatabaseHelper.EightTABLE_NAME;
                break;
        }

        if(TextUtils.isEmpty(tableName)){
            // TODO show some error
            Toast.makeText(QuestionActivity.this, "EMPTY TABLE!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        AnswerTableName = null;
        switch (MainApplication.TEST_TYPE){
            case testType.TEST_ANDROID:
                AnswerTableName = DatabaseHelper.SecondTABLE_NAME;
                break;
            case testType.TEST_CSHARP:
                AnswerTableName=DatabaseHelper.SeventhTABLE_NAME;
                break;
            case testType.TEST_JAVA:
                AnswerTableName = DatabaseHelper.NinethTABLE_NAME;
                break;
        }

        String queryQuestionType ="SELECT * FROM " + tableName + " \n" +
                "where Type=" +type + " order by random()\n" +
                "limit 1";
        cursorQuestion = MyDb.getReadableDatabase().rawQuery(queryQuestionType, null);
        cursorQuestion.moveToFirst();
        questionType = cursorQuestion.getInt(cursorQuestion.getColumnIndex("Type"));
        question = cursorQuestion.getString(cursorQuestion.getColumnIndex("Question"));
        questionID = cursorQuestion.getInt(cursorQuestion.getColumnIndex("ID"));
        cursorQuestion.close();

        currentQuestion.setQuestionText(question);
        currentQuestion.setType(questionType);


        showQuestionLayout(currentQuestion);
        populateQuestionViews(currentQuestion);


        String queryAnswers = "SELECT * FROM " + AnswerTableName +
                " where QuestionID = " + questionID;

        cursorAnswers = MyDb.getReadableDatabase().rawQuery(queryAnswers, null);


        switch (currentQuestion.getType()){
            case 1: {
                 populateRadioButtons();
                break;
            }
            case 2:{
                 populateCheckBoxes();
                break;
            }
            case 3:{
                 populateEditText();
                break;
            }
        }


        cursorAnswers.close();
    }

    private void showQuestionLayout(Question question) {
        switch (question.getType()) {
            case answerType.RADIO_BUTTON: {
                linearLayoutCheckBoxes.setVisibility(View.GONE);
                linearLayoutText.setVisibility(View.GONE);
                rgSingleAnswer.setVisibility(View.VISIBLE);
                break;
            }
            case answerType.CHECK_BOXES:{
                linearLayoutCheckBoxes.setVisibility(View.VISIBLE);
                linearLayoutText.setVisibility(View.GONE);
                rgSingleAnswer.setVisibility(View.GONE);
                break;
            }
            case answerType.EDIT_TEXT:{
                linearLayoutCheckBoxes.setVisibility(View.GONE);
                linearLayoutText.setVisibility(View.VISIBLE);
                rgSingleAnswer.setVisibility(View.GONE);
                break;
            }
        }
    }

    private void populateQuestionViews(Question currentQuestion){
        tvQuestionOne.setText(currentQuestion.getQuestionText());
        tvQuestionOne1.setText(currentQuestion.getQuestionText());
        textViewQuestiontTextArea.setText(currentQuestion.getQuestionText());
    }

    private void populateRadioButtons(){

        if (cursorAnswers.moveToFirst()){
            answerOne = cursorAnswers.getString(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_ANSWER));
            assert rbOne != null;
            rbOne.setText(answerOne);
            if (cursorAnswers.getInt(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_IS_CORRECT))==1){
                dbCorrectAnswer = answerOne;
            }

        }
        if (cursorAnswers.moveToNext()){
            answerTwo = cursorAnswers.getString(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_ANSWER));
            assert rbTwo != null;
            rbTwo.setText(answerTwo);
            if (cursorAnswers.getInt(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_IS_CORRECT))==1){
                dbCorrectAnswer = answerTwo;
            }

        }
        if (cursorAnswers.moveToNext()){
            answerThree = cursorAnswers.getString(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_ANSWER));
            assert rbThree != null;
            rbThree.setText(answerThree);
            if (cursorAnswers.getInt(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_IS_CORRECT))==1){
                dbCorrectAnswer = answerThree;
            }

        }
    }

    private void populateCheckBoxes() {

        if (cursorAnswers.moveToFirst()) {
            answerOne = cursorAnswers.getString(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_ANSWER));
            assert chOne != null;
            chOne.setText(answerOne);
            if (cursorAnswers.getInt(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_IS_CORRECT)) == 1) {
                dbCheckBoxAnswer1 = answerOne;
            }

        }
        if (cursorAnswers.moveToNext()) {
            answerTwo = cursorAnswers.getString(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_ANSWER));
            assert chTwo != null;
            chTwo.setText(answerTwo);
            if (cursorAnswers.getInt(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_IS_CORRECT)) == 1) {
                dbCheckBoxAnswer2 = answerTwo;
            }

        }
        if (cursorAnswers.moveToNext()) {
            answerThree = cursorAnswers.getString(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_ANSWER));
            assert chThree != null;
            chThree.setText(answerThree);
            if (cursorAnswers.getInt(cursorAnswers.getColumnIndex(DatabaseHelper.CLMN_IS_CORRECT)) == 1) {
                dbCheckBoxAnswer2 = answerThree;
            }
        }

    }


    private void populateEditText(){

    }


    private void obtainSingleCorrectAnswer(){

     rbCorrectAnswer="SELECT Answer FROM " +  AnswerTableName + " JOIN " +tableName +
                " WHERE QuestionID = "+ questionID + " AND IsCorrect = 1 AND Type = 1";

        cursorCorrectAnswer = MyDb.getReadableDatabase().rawQuery(rbCorrectAnswer, null);
       if(cursorCorrectAnswer.moveToFirst()) {

           dbCorrectAnswer = cursorCorrectAnswer.getString(cursorCorrectAnswer.getColumnIndex("Answer"));
       }
        cursorCorrectAnswer.close();
    }
    private void obtainMultipleCorrectAnswer(){

        chCorrectAnswer="SELECT Answer FROM " + AnswerTableName + " JOIN " + tableName +
                " WHERE QuestionID = "+ questionID + " AND IsCorrect = 1 AND Type = 2 ";

        cursorCorrectAnswers = MyDb.getReadableDatabase().rawQuery(chCorrectAnswer,null);

       if(cursorCorrectAnswers.moveToNext()) {

           dbCheckBoxAnswer1 = cursorCorrectAnswers.getString(cursorCorrectAnswers.getColumnIndex("Answer"));
       }
        if(cursorCorrectAnswers.moveToLast()){

           dbCheckBoxAnswer2 = cursorCorrectAnswers.getString(cursorCorrectAnswers.getColumnIndex("Answer"));
       }

        cursorCorrectAnswers.close();
    }

    private void getPointsForMultipleAnswers() {

        obtainMultipleCorrectAnswer();

        chOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                enableNext();


            }
        });

        chTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                enableNext();


            }
        });

        chThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                enableNext();

            }
        });
    }


    private void enableNext(){
        tvCheckBoxValidateText.setText("Отговор бе избран!");
        tvCheckBoxValidateText.setTextColor(Color.GREEN);
        btnFirsQuestion.setEnabled(true);
        btnFirsQuestion.setAlpha(0.5f);

    }

    private void wholeGrade(){
         grade = sumPoints;
        Toast toast = Toast.makeText(QuestionActivity.this, "Брой точки: " + String.valueOf(grade), Toast.LENGTH_SHORT);
        View toastView = toast.getView();

        toastView.setBackgroundResource(R.drawable.toast_message_style);

        toast.show();
    }

    public void animateProgressBar() {
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarFirstQuestion);
        anim = ObjectAnimator.ofInt(mProgressBar, "progress", 100, 0);
        anim.setDuration(MainApplication.MINUTES);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
    }

    private void getPointsForSingleAnswer() {

        obtainSingleCorrectAnswer();

        rgSingleAnswer.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                tvValidateText.setText("Отговор бе избран!");
                tvValidateText.setTextColor(Color.GREEN);

                btnFirsQuestion.setEnabled(true);
                btnFirsQuestion.setAlpha(0.5f);

            }

        });
    }
    View.OnClickListener nextQuestion = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (questionType) {
                case 1:
                    int currentCheckedId = rgSingleAnswer.getCheckedRadioButtonId();
                    RadioButton rbAnswerOne = (RadioButton) findViewById(currentCheckedId);
                    if (rbAnswerOne.getText().toString().equals(dbCorrectAnswer)) {

                        sumPoints += 5;

                    } else {
                        sumPoints += 0;
                    }
                    rbAnswerOne.setChecked(false);
                    break;
                case 2:


                    if (chOne.isChecked() && chOne.getText().toString().equals(dbCheckBoxAnswer1)
                            || chOne.getText().toString().equals(dbCheckBoxAnswer2)) {

                        sumPoints += 2.5;
                    }else {
                        sumPoints += 0.0;

                    }
                    if (chTwo.isChecked() && chTwo.getText().toString().equals(dbCheckBoxAnswer2)
                            || chTwo.getText().toString().equals(dbCheckBoxAnswer1)) {

                        sumPoints += 2.5;
                    } else if (chTwo.isChecked() && chTwo.getText().toString().equals(answerTwo)) {
                        enableNext();
                    } else {
                        sumPoints += 0.0;
                    }
                    if (chThree.isChecked() && chThree.getText().toString().equals(dbCheckBoxAnswer2)
                            || chThree.getText().toString().equals(dbCheckBoxAnswer1)) {
                        sumPoints += 2.5;

                    } else if (chThree.isChecked() && chThree.getText().toString().equals(answerThree)) {
                        enableNext();
                    } else {
                        sumPoints += 0.0;
                    }
                    break;

            }

            readQuestionAndAnswerFromDb();
            validateText();
            btnFirsQuestion.setEnabled(false);
            tvCheckBoxValidateText.setText("Все още не е избран отговор!");
            tvCheckBoxValidateText.setTextColor(Color.RED);
            btnFirsQuestion.setAlpha(0.1f);

                chOne.setChecked(false);
                chTwo.setChecked(false);
                chThree.setChecked(false);

                wholeGrade();

            tvValidateText.setText("Все още не е избран отговор!");
            tvValidateText.setTextColor(Color.RED);

            counter++;


            if(counter==6) {
            Intent moveToEnd = new Intent(QuestionActivity.this,EndTest.class);
            moveToEnd.putExtra("Grade",grade);
            putQuestionTypeThreeInDb();
            startActivity(moveToEnd);
            }

        }


    };

    private void putQuestionTypeThreeInDb(){
        SQLiteDatabase db = MyDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(currentQuestion.getType() == 3){

            Answer = textArea.getText().toString();
            contentValues.put("Question",currentQuestion.getQuestionText());
            contentValues.put("Answer", Answer);
            contentValues.put("FacultyNumber", StartTest.fNumber);
            contentValues.put("SpecialityAndYear",StartTest.textSpecialityAndYear);
            contentValues.put("TestName",StartTest.testName);
            contentValues.put("Grade",grade);
            db.insert(DatabaseHelper.FourthTABLE_NAME, null, contentValues);
        }else {

            contentValues.put("Question","No question was found");
            contentValues.put("Answer", "No answer was found");
            contentValues.put("FacultyNumber", StartTest.fNumber);
            contentValues.put("SpecialityAndYear",StartTest.textSpecialityAndYear);
            contentValues.put("TestName",StartTest.testName);
            contentValues.put("Grade",grade);
            db.insert(DatabaseHelper.FourthTABLE_NAME, null, contentValues);
        }

    }

    private void initTimer() {
        tvTimePlaceholder = (TextView) findViewById(R.id.TimerQuestion1);
        app.setCountdownListener(this);
    }

        @Override
        public void TimerTick(long millisUntilFinished) {
            // TODO: 21.7.2016 г. update texview, ...
            tvTimePlaceholder.setText(String.format("%s", String.format("%d : %d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))));
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(QuestionActivity.this, "BackPressButton Disabled!", Toast.LENGTH_SHORT).show();
    }
}
