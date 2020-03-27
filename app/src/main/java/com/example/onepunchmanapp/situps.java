package com.example.onepunchmanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class situps extends AppCompatActivity {
    Context context;
    Database database=new Database();
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situps);
        context=getApplicationContext();
        Bauen();
        Grundbefuellen();
        Gui();
    }

    private void Gui() {
        TextView textView=findViewById(R.id.textView);
        textView.setText(allgemein.training[allgemein.runtimewo]);
        TextView textView1=findViewById(R.id.textView2);
        EditText editText=findViewById(R.id.editText);
        int rest=rest();
        if (allgemein.runtimewo==3){
            int anzahl=rest;
            anzahl=anzahl/1000;
            textView1.setText(anzahl+" km to Run Left");
            editText.setHint("How many did you run in Meters now?");
            if (rest<0){
                anzahl=anzahl*-1;
                textView1.setText("You did "+anzahl+"km more than needed");
            }
        }
        else {
            textView1.setText(rest + " Exercises to Do");
            if (rest<0){
                rest=rest*-1;
                textView1.setText("You did "+rest+" more than needed");
            }
            editText.setHint("How many did you exercise now?");
        }
    }

    private void Grundbefuellen() {

        SQLiteDatabase db = database.oeffnen(context);
        String sql = "Insert or ignore into "+allgemein.training[allgemein.runtimewo]+" Values("+getdate()+","+allgemein.howmuch[allgemein.runtimewo]+")";
        db.execSQL(sql);
        db.close();
    }
    int rest(){
        SQLiteDatabase db = database.oeffnen(context);
        String sql ="Select Anzahl from "+allgemein.training[allgemein.runtimewo]+" Where ID="+getdate();
        Cursor cursor= db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        return -1;
    }
    int getdate(){
        int day= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month=Calendar.getInstance().get(Calendar.MONTH)+1;
        int year=Calendar.getInstance().get(Calendar.YEAR);
        int derzeit=year+(month*10000)+(day*1000000);
        return derzeit;
    }

    private void Bauen() {
                SQLiteDatabase db = database.oeffnen(context);
                String sql = "CREATE TABLE If NOT EXISTS "+allgemein.training[allgemein.runtimewo] +"(ID  INTEGER PRIMARY KEY,Anzahl INTEGER);";
                db.execSQL(sql);
                db.close();
            }

    public void refresh(View view) {
        EditText editText=findViewById(R.id.editText);
        String inhalt=editText.getText()+"";
        if (!inhalt.equals("")){
         int anzahl= Integer.parseInt(inhalt);

             SQLiteDatabase db = database.oeffnen(context);
             String sql = "Update " + allgemein.training[allgemein.runtimewo] + " Set Anzahl=Anzahl-" + anzahl + " WHERE ID=" + getdate();
             db.execSQL(sql);
             db.close();
             Gui();
         }
    }
}
