package com.example.englishgrammertest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<QuestionAndAnswer> getQuestion(String level){
        List<QuestionAndAnswer> list = new ArrayList<QuestionAndAnswer>();
        Cursor cursor = database.rawQuery("SELECT qa.question,qa.answer FROM question_answer as qa join level as l where l.id=qa.level_id and l.name='"+level+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            QuestionAndAnswer qa = new QuestionAndAnswer();
            qa.setQuestion_name(cursor.getString(0));
            qa.setCorrect_answer(cursor.getString(1));
            list.add(qa);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getFalseAnswer(String question){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT f.answer from false_answer as f join question_answer as qa where qa.question='"+question+"' and qa.id=f.question_id",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    
} 