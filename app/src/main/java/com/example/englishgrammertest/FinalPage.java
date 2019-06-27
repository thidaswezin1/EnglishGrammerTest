package com.example.englishgrammertest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FinalPage extends Activity implements View.OnClickListener{
    String level;
    int correctMarks;
    Button tryButton,exitButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.finalpage);
        TextView marks = findViewById(R.id.textView3);
        correctMarks = getIntent().getIntExtra("correct_marks",0);
        marks.setText(String.valueOf(correctMarks));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_view_recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        level = getIntent().getStringExtra("q");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<QuestionAndAnswer> list = databaseAccess.getQuestion(level);
        databaseAccess.close();

        RecyclerViewDataAdapter dataAdapter = new RecyclerViewDataAdapter(list);

        // Set data adapter.
        recyclerView.setAdapter(dataAdapter);

        tryButton = findViewById(R.id.tryBtn);
        exitButton = findViewById(R.id.exitBtn);
        tryButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);

    }

    public void onClick(View view) {
        if(view==tryButton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(view==exitButton){
            Intent svc=new Intent(getApplicationContext(), BackgroundMusic.class);
            stopService(svc);
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
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
