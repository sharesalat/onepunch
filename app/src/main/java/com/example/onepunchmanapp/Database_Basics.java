package com.example.onepunchmanapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;
import java.io.File;


class Database_Basics {
    String dbfolder(Context context){
        ContextWrapper c = new ContextWrapper(context);
        String path= c.getExternalFilesDir(null)+ File.separator+allgemein.Appordner;
        if(allgemein.Entwicklermodus==0) {
            path = c.getFilesDir()+"";}
        System.out.println(path+ "test");
        return path;
    }


}
