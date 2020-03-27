package com.example.onepunchmanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        checken();
          Bauen();
    }
    public void checken() {
        String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        int permsRequestCode = 200;
        requestPermissions(perms, permsRequestCode);
        int testpem = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (testpem < 0) {
            SystemClock.sleep(5000);
            testpem = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (testpem < 0) {
//TODO:Meldung für Benutzer eintragen das die APP ohne schreibrechtenicht funktioniert
                SystemClock.sleep(4000);
                this.finishAffinity();
            }
        }
    }
    private void Bauen() {
        Database database =new Database();
        database.neuesdupplizieren(context);
        LinearLayout ll = findViewById(R.id.buttonlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

          for (int i=0;i<allgemein.training.length;i++){

            Button myButton2 = new Button(context);
            myButton2.setText(allgemein.training[i]);
            myButton2.setId(i);
            myButton2.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
                                                 System.out.println("test");
                                                  allgemein.runtimewo=v.getId();
                                                  Intent give = new Intent(MainActivity.this, situps.class);
                                                  startActivity(give);}
             });
            myButton2.setLayoutParams(lp);
            ll.addView(myButton2, lp);
        }
    }
}
