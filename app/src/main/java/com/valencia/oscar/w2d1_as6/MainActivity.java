package com.valencia.oscar.w2d1_as6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText name;
    String fileName = "name.txt";
    static final int READ_BLOCK_SIZE = 100;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    OutputStreamWriter outputStreamWriter;
    InputStreamReader inputStreamReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
    }

    public void saveName(View view) {
        String nameValue = name.getText().toString();
        try{
            fileOutputStream = openFileOutput(fileName,MODE_PRIVATE);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(nameValue);
            outputStreamWriter.close();
            Toast.makeText(this,"File updated!",Toast.LENGTH_SHORT).show();
            name.setText("");
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
            Log.d("MY_TAG","Exception: "+e.toString());
        }
    }

    public void showName(View view) {
        try{
            fileInputStream = openFileInput(fileName);
            inputStreamReader =new InputStreamReader(fileInputStream);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            StringBuilder s = new StringBuilder();
            int charRead;
            while((charRead=inputStreamReader.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                s.append(readString);
            }
            inputStreamReader.close();
            Log.d("MY_TAG",s.toString());
            Toast.makeText(this,s.toString(),Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Log.d("MY_TAG",e.toString());
        }
    }
    public void initElements(){
        name = findViewById(R.id.name);
    }
}
