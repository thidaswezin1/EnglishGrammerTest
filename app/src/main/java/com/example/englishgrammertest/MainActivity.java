package com.example.englishgrammertest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.content.*;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    RadioButton radio1,radio2,radio3;
    String question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent svc=new Intent(this,BackgroundMusic.class);
        startService(svc);
        Button startButton= findViewById(R.id.button);
        startButton.setOnClickListener(this);
        RadioGroup radioGroup = findViewById(R.id.radio);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radioGroup.setOnCheckedChangeListener(this);
        Switch music = findViewById(R.id.switch1);
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Intent svc=new Intent(getApplicationContext(),BackgroundMusic.class);
                    startService(svc);
                } else {
                    // The toggle is disabled
                    Intent svc=new Intent(getApplicationContext(),BackgroundMusic.class);
                    stopService(svc);
                }
            }
        });
    }
    public void onClick(View view){
        if(question!=null) {
            Intent intent = new Intent(this, QuestionAnswer.class);
            intent.putExtra("q", question);
            startActivity(intent);
        }
        else{
            new AlertDialog.Builder(this)
                    .setMessage("Please Choose Level!!!")
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onCheckedChanged(RadioGroup group, int checked){
        switch (checked){
            case R.id.radio1:

                question = radio1.getText().toString();
                break;
            case R.id.radio2:

                question = radio2.getText().toString();
                break;
            case R.id.radio3:

                question = radio3.getText().toString();
                break;
            default:

                question=null;

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
