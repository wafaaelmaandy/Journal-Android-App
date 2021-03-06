package com.wafaaelm3andy.journalapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEntryActivity extends AppCompatActivity  {
    EditText detailsView;
    SQLiteDatabase sqLiteDatabase ;
    NoteDbHelper noteDbHelper=new NoteDbHelper(AddEntryActivity.this);






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);



            detailsView=(EditText) findViewById(R.id.edit_details);


            noteDbHelper = new NoteDbHelper(this);
            open();


        }



        public void open() throws SQLException {
            sqLiteDatabase = noteDbHelper.getWritableDatabase();
        }

        public void save(View view){

            if (detailsView.getText().length() == 0) {
                return;
            }
            else {
            String details = detailsView.getText().toString();
            addNote(details);

            //clear UI text fields
            detailsView.clearFocus();
            Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,BasicJournalActivity.class);
            startActivity(i);}}


        public long addNote(String detials ){
            ContentValues cv = new ContentValues();
            cv.put(NoteContract.NotelistEntry.COLUMN_DETAILS,detials);
            return sqLiteDatabase.insert(NoteContract.NotelistEntry.TABLE_NAME,null, cv);



        }






    public void close(View view) {
        startActivity(new Intent(AddEntryActivity.this,BasicJournalActivity.class));

    }
}
