package com.example.englishgrammertest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuestionAnswer extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener  {
    private int progressStatus = 0;
    String ques,question;
    String correct;
    String falseAnswer1,falseAnswer2;
    RadioButton radio1,radio2,radio3;
    String answer;
    List<Integer> falseList = new ArrayList<Integer>();
    int id;
    int correctMarks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionanswer);
        TextView textView =findViewById(R.id.textView);
        TextView textView1 =findViewById(R.id.question);

        textView.setText("0/10");

        RadioGroup radioGroup = findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(this);

        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);

        question = getIntent().getStringExtra("q");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<QuestionAndAnswer> list = databaseAccess.getQuestion(question);
        QuestionAndAnswer questionAndAnswer = list.get(0);
        id = questionAndAnswer.getQuestion_id();
        ques = questionAndAnswer.getQuestion_name();
        correct = questionAndAnswer.getCorrect_answer();

        textView1.setText(ques);

        List<String> listofFalseAnswer = databaseAccess.getFalseAnswer(ques);
        falseAnswer1 = listofFalseAnswer.get(0);
        falseAnswer2 = listofFalseAnswer.get(1);

        databaseAccess.close();

        Random random = new Random();
        int randomNo = random.nextInt((3-1)+1)+1;

        if(randomNo == 1){
            radio1.setText(correct);
            radio2.setText(falseAnswer1);
            radio3.setText(falseAnswer2);
        }
        else if (randomNo==2){
            radio1.setText(falseAnswer1);
            radio2.setText(correct);
            radio3.setText(falseAnswer2);
        }
        else{
            radio1.setText(falseAnswer1);
            radio2.setText(falseAnswer2);
            radio3.setText(correct);
        }

        Button nextbutton = findViewById(R.id.nextBtn);
        nextbutton.setOnClickListener(this);

    }
    public void onClick(View view){
        if(answer!=null){
            Intent intent = new Intent(this, QuestionAnswer2.class);
            intent.putExtra("answer", answer);
            intent.putExtra("correctAnswer",correct);
            System.err.println("Question>>>>>"+question);
            intent.putExtra("q",question);
            if(answer.equals(correct)){
               correctMarks++;
            }
            intent.putExtra("correct_marks",correctMarks);

            startActivity(intent);
        }
        else{
            new AlertDialog.Builder(this)
                    .setMessage("Please Choose Correct Answer!!!")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dlg, int sumthin) {
                            //Auto-generated method stub
                            //do nothing - it will close on its own
                        }
                    })
                    .show();
        }
    }
    public void onCheckedChanged(RadioGroup group, int checked){
        switch (checked){
            case R.id.radio1:

                answer = radio1.getText().toString();
                break;
            case R.id.radio2:

                answer = radio2.getText().toString();
                break;
            case R.id.radio3:

                answer = radio3.getText().toString();
                break;
            default:

                answer=null;

        }
    }
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent svc=new Intent(getApplicationContext(), BackgroundMusic.class);
                        stopService(svc);
                        Intent intent=new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        System.exit(0);

                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                }).show();

    }


}
