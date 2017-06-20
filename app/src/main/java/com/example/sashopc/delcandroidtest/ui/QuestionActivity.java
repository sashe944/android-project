package com.example.sashopc.delcandroidtest.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.sashopc.delcandroidtest.MainApplication;
import com.example.sashopc.delcandroidtest.async.ApiCallback;
import com.example.sashopc.delcandroidtest.async.QAAsyncTask;
import com.example.sashopc.delcandroidtest.model.Answer;
import com.example.sashopc.delcandroidtest.model.Question;
import com.example.sashopc.delcandroidtest.R;
import com.example.sashopc.delcandroidtest.model.AnswerType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import static android.widget.RadioGroup.*;

public class QuestionActivity extends AppCompatActivity implements CountdownListener {

    private static final String EXTRA_TEST_TYPE = "testType";
    public static final String ANDROID_TEST_TYPE = "Answer_table";
    public static final String CSHARP_TEST_TYPE = "CSharpAnswer_table";
    public static final String JAVA_TEST_TYPE = "JavaAnswer_table";

    //    DatabaseHelper MyDb;
    double sumPoints;
    MainApplication app;
    ObjectAnimator anim;
    int counter;

    // New
    private TextView tvTimer;
    private ProgressBar pbTimer;
    private TextView tvQuestionText;

    private RadioGroup rgAnswers;
    private LinearLayout llCheckBoxes;
    private EditText etFreeAnswer;

    private TextView tvStatus;
    private Button btnNextQuestion;

    private CheckBox chOne;
    private CheckBox chTwo;
    private CheckBox chThree;
    private RadioButton rbOne;
    private RadioButton rbTwo;
    private RadioButton rbThree;
    private Gson gson = new GsonBuilder().create();
    private Question currentQuestion;

    public static Intent newIntent(Context context, String testType) {
        Intent intent = new Intent(context, QuestionActivity.class);
        intent.putExtra(EXTRA_TEST_TYPE, testType);
        return intent;
    }

    private String getExtraTestType() {
        return getIntent().getStringExtra(EXTRA_TEST_TYPE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_new);

        app = (MainApplication) getApplication();

        tvQuestionText = (TextView) findViewById(R.id.tvQuestionText);
        btnNextQuestion = (Button) findViewById(R.id.btnNext);
        btnNextQuestion.setOnClickListener(nextQuestionOnClickListener);
        btnNextQuestion.setEnabled(false);
        btnNextQuestion.setAlpha(0.1f);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvStatus.setText("Все още не е избран отговор!");
        tvStatus.setTextColor(Color.RED);


        llCheckBoxes = (LinearLayout) findViewById(R.id.llCheckBoxes);
        rgAnswers = (RadioGroup) findViewById(R.id.rgAnswers);
        etFreeAnswer = (EditText) findViewById(R.id.etFreeAnswer);

        chOne = (CheckBox) findViewById(R.id.checkBox1);
        chTwo = (CheckBox) findViewById(R.id.checkBox2);
        chThree = (CheckBox) findViewById(R.id.checkBox3);

        rbOne = (RadioButton) findViewById(R.id.radioButton1);
        rbTwo = (RadioButton) findViewById(R.id.radioButton2);
        rbThree = (RadioButton) findViewById(R.id.radioButton3);

        pbTimer = (ProgressBar) findViewById(R.id.pbTimer);
        tvTimer = (TextView) findViewById(R.id.tvTimer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        counter = 0;

//        MyDb = new DatabaseHelper(this);

        setTitle();
        initTimer();
        readQuestionAndAnswerFromServer();
        //setRadioGroupSelectedListener();
//        setCheckBoxesChangeListeners();
//        validateText();
        animateProgressBar();

        // getRandomQuestion
        //getAnswersForQuestion
        //getQuestionType
        //showView(questionType)
        //populateViews(question)
    }

    private void setTitle() {
        switch (getExtraTestType()) {
            case ANDROID_TEST_TYPE:
                getSupportActionBar().setTitle("ANDROID TEST");
                break;
            case CSHARP_TEST_TYPE:
                getSupportActionBar().setTitle("CSHARP TEST");
                break;
            case JAVA_TEST_TYPE:
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


    private void addFreeTextChangeListener() {
        etFreeAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    tvStatus.setText("Все още не е въведен текст!!");
                    tvStatus.setTextColor(Color.RED);
                    btnNextQuestion.setEnabled(false);
                    btnNextQuestion.setAlpha(0.1f);
                } else {
                    tvStatus.setText("Вече е въведен текс!");
                    tvStatus.setTextColor(Color.GREEN);
                    btnNextQuestion.setEnabled(true);
                    btnNextQuestion.setAlpha(0.5f);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void validateText() {

        tvStatus.setText("Все още не е въведен текст!");
        tvStatus.setTextColor(Color.RED);


    }

    private void readQuestionAndAnswerFromServer() {
        new QAAsyncTask(new ApiCallback() {
            @Override
            public void onResponse(String response) {
                Log.d("asd", "response" + response);
                currentQuestion = gson.fromJson(response, Question.class);
                Log.d("asd", "randomQuestion" + currentQuestion);
                showQuestionLayout(currentQuestion);
            }
        }).execute(getExtraTestType());
    }

    private void showQuestionLayout(Question question) {
        switch (question.type) {
            case AnswerType.RADIO_BUTTON: {
                rgAnswers.setVisibility(VISIBLE);
                llCheckBoxes.setVisibility(GONE);
                etFreeAnswer.setVisibility(GONE);
                populateRadioButtons(question);
                setRadioGroupSelectedListener();
                break;
            }
            case AnswerType.CHECK_BOXES: {
                rgAnswers.setVisibility(GONE);
                llCheckBoxes.setVisibility(VISIBLE);
                etFreeAnswer.setVisibility(GONE);
                populateCheckBoxes(question);
                setCheckBoxesChangeListeners();
                break;
            }
            case AnswerType.EDIT_TEXT: {
                rgAnswers.setVisibility(GONE);
                llCheckBoxes.setVisibility(GONE);
                etFreeAnswer.setVisibility(VISIBLE);
                addFreeTextChangeListener();
                break;
            }
        }
        tvQuestionText.setText(question.questionText);
    }


    private void populateRadioButtons(Question question) {
        rbOne.setText(question.answers.get(0).answerText);
        rbTwo.setText(question.answers.get(1).answerText);
        rbThree.setText(question.answers.get(2).answerText);
    }

    private void populateCheckBoxes(Question question) {
        chOne.setText(question.answers.get(0).answerText);
        chTwo.setText(question.answers.get(1).answerText);
        chThree.setText(question.answers.get(2).answerText);
    }


//    private void obtainSingleCorrectAnswer() {
//
//        rbCorrectAnswer = "SELECT Answer FROM " + AnswerTableName + " JOIN " + tableName +
//                " WHERE QuestionID = " + questionID + " AND IsCorrect = 1 AND Type = 1";
//
//        cursorCorrectAnswer = MyDb.getReadableDatabase().rawQuery(rbCorrectAnswer, null);
//        if (cursorCorrectAnswer.moveToFirst()) {
//
//            dbCorrectAnswer = cursorCorrectAnswer.getString(cursorCorrectAnswer.getColumnIndex("Answer"));
//        }
//        cursorCorrectAnswer.close();
//    }

//    private void obtainMultipleCorrectAnswer() {
//
//        chCorrectAnswer = "SELECT Answer FROM " + AnswerTableName + " JOIN " + tableName +
//                " WHERE QuestionID = " + questionID + " AND IsCorrect = 1 AND Type = 2 ";
//
//        cursorCorrectAnswers = MyDb.getReadableDatabase().rawQuery(chCorrectAnswer, null);
//
//        if (cursorCorrectAnswers.moveToNext()) {
//
//            dbCheckBoxAnswer1 = cursorCorrectAnswers.getString(cursorCorrectAnswers.getColumnIndex("Answer"));
//        }
//        if (cursorCorrectAnswers.moveToLast()) {
//
//            dbCheckBoxAnswer2 = cursorCorrectAnswers.getString(cursorCorrectAnswers.getColumnIndex("Answer"));
//        }
//
//        cursorCorrectAnswers.close();
//    }

    private void setCheckBoxesChangeListeners() {

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


    private void enableNext() {
        tvStatus.setText("Отговор бе избран!");
        tvStatus.setTextColor(Color.GREEN);
        btnNextQuestion.setEnabled(true);
        btnNextQuestion.setAlpha(0.5f);
    }

    private void wholeGrade() {
        Toast toast = Toast.makeText(QuestionActivity.this, "Брой точки: " + String.valueOf(sumPoints), Toast.LENGTH_SHORT);
        View toastView = toast.getView();

        toastView.setBackgroundResource(R.drawable.toast_message_style);

        toast.show();
    }

    public void animateProgressBar() {
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarFirstQuestion);
        anim = ObjectAnimator.ofInt(mProgressBar, "progress", 100, 0);
        anim.setDuration(MainApplication.TEST_DURATION_IN_MILLIS);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
    }

    private void setRadioGroupSelectedListener() {
        rgAnswers.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                tvStatus.setText("Отговор бе избран!");
                tvStatus.setTextColor(Color.GREEN);

                btnNextQuestion.setEnabled(true);
                btnNextQuestion.setAlpha(0.5f);

            }

        });
    }

    View.OnClickListener nextQuestionOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (currentQuestion.type) {
                case 1:
                    int currentCheckedId = rgAnswers.getCheckedRadioButtonId();
                    RadioButton rbCheckedAnswer = (RadioButton) findViewById(currentCheckedId);
                    String radioAnswer = rbCheckedAnswer.getText().toString();
                    for (com.example.sashopc.delcandroidtest.model.Answer answer : currentQuestion.answers) {
                        if (answer.answerText.equalsIgnoreCase(radioAnswer) && answer.isCorrect) {
                            sumPoints += 5;
                        }
                    }
                    rbCheckedAnswer.setChecked(false);
                    break;
                case 2:
                    for (int i = 0; i < currentQuestion.answers.size(); i++) {
                        Answer answer = currentQuestion.answers.get(i);
                        if (answer.answerText.equalsIgnoreCase(chOne.getText().toString()) && answer.isCorrect && chOne.isChecked()) {
                            sumPoints += 2.5;
                        } else if (answer.answerText.equalsIgnoreCase(chTwo.getText().toString()) && answer.isCorrect && chTwo.isChecked()) {
                            sumPoints += 2.5;
                        } else if (answer.answerText.equalsIgnoreCase(chThree.getText().toString()) && answer.isCorrect && chThree.isChecked()) {
                            sumPoints += 2.5;
                        }
                    }
            }

            readQuestionAndAnswerFromServer();
            validateText();
            btnNextQuestion.setEnabled(false);
            tvStatus.setText("Все още не е избран отговор!");
            tvStatus.setTextColor(Color.RED);
            btnNextQuestion.setAlpha(0.1f);

            chOne.setChecked(false);
            chTwo.setChecked(false);
            chThree.setChecked(false);

            rbOne.setChecked(false);
            rbTwo.setChecked(false);
            rbThree.setChecked(false);

            etFreeAnswer.setText(null);

            wholeGrade();
            counter++;

            Log.d("asd", "sumPoints: " + sumPoints);

            if (counter == 6) {
                Intent moveToEnd = new Intent(QuestionActivity.this, EndTestActivity.class);
                moveToEnd.putExtra("Grade", sumPoints);
                putQuestionTypeThreeInDb();
                startActivity(moveToEnd);
            }

        }
    };

    private void putQuestionTypeThreeInDb() {
//        SQLiteDatabase db = MyDb.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        if(currentQuestion.getType() == 3){
//
//            Answer = textArea.getText().toString();
//            contentValues.put("Question",currentQuestion.getQuestionText());
//            contentValues.put("Answer", Answer);
//            contentValues.put("FacultyNumber", StartTest.fNumber);
//            contentValues.put("SpecialityAndYear",StartTest.textSpecialityAndYear);
//            contentValues.put("TestName",StartTest.testName);
//            contentValues.put("Grade",grade);
//            db.insert(DatabaseHelper.FourthTABLE_NAME, null, contentValues);
//        }else {
//
//            contentValues.put("Question","No question was found");
//            contentValues.put("Answer", "No answer was found");
//            contentValues.put("FacultyNumber", StartTest.fNumber);
//            contentValues.put("SpecialityAndYear",StartTest.textSpecialityAndYear);
//            contentValues.put("TestName",StartTest.testName);
//            contentValues.put("Grade",grade);
//            db.insert(DatabaseHelper.FourthTABLE_NAME, null, contentValues);
//        }

    }

    private void initTimer() {
        app.setCountdownListener(this);
        pbTimer.setMax((int) MainApplication.TEST_DURATION_IN_MILLIS);
    }

    @Override
    public void timerTick(long millisUntilFinished) {
        tvTimer.setText(String.format("%d : %d",
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

        pbTimer.setProgress((int) millisUntilFinished);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(QuestionActivity.this, "BackPressButton Disabled!", Toast.LENGTH_SHORT).show();
    }
}
