package com.example.onepunchmanapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Database {
    private File folder;
    private String data_storage_root;
    private void intern(Context Scontext) {
        Database_Basics database_basics=new Database_Basics();
        folder = new File(database_basics.dbfolder(Scontext));
        data_storage_root=folder+File.separator;
    }
    SQLiteDatabase oeffnen(Context sContect) {
        intern(sContect);String databasepath = data_storage_root+allgemein.vokabeldatei;
        return oeffnen(databasepath,1);}
    SQLiteDatabase oeffnen(String path,int welche){
        SQLiteDatabase db=SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        if(allgemein.Entwicklermodus==1) {System.out.println(path+" Ist offen");}
        return db;}
    void neuesdupplizieren(Context sContect){
        intern(sContect);
        if (!folder.exists()){ boolean success = folder.mkdir();}
        File testfile = new File(data_storage_root+allgemein.vokabeldatei);
        if (!testfile.exists()) { datenbankbauen(allgemein.vokabeldatei,allgemein.vokabeldatei,sContect);}
        else {System.out.println("scheint datei zu geben");}

    }
    public void datenbankbauen(String Inputname,String outputname,Context context){

        try {
            InputStream in;
            AssetManager assetManager = context.getAssets();
            in = assetManager.open(Inputname);

            if (!folder.exists()){ boolean success = folder.mkdir(); if(allgemein.Entwicklermodus==1) {System.out.println(success+"Folder"); }}
            if(allgemein.Entwicklermodus==1) {System.out.println(data_storage_root);}
            OutputStream myOutput = new FileOutputStream(data_storage_root+outputname);
            byte[] buffer = new byte[allgemein.inputstrembuffer];
            int length;
            while ((length = in.read(buffer)) > 0) { myOutput.write(buffer, 0, length); }
            in.close(); myOutput.flush(); myOutput.close();if(allgemein.Entwicklermodus==1) {System.out.println("Datenbank:"+outputname+" wurde gebaut");}
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
