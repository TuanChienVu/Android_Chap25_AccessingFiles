package com.vutuanchien.android_chap25_readwritefile_internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edDataWrite, edDataRead;
    static final int READ_BLOCK_SIZE = 100;
    final String FILE_NAME = "mytextfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edDataRead = (EditText) findViewById(R.id.edDataRead);
        edDataWrite = (EditText) findViewById(R.id.edDataWrite);
    }

    // write text to file
    public void writeData(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout = openFileOutput(FILE_NAME, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(edDataWrite.getText().toString());
            edDataWrite.setText("");
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void readData(View v) {
        //reading text from file
        try {
            FileInputStream fileIn = openFileInput(FILE_NAME);
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            edDataRead.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
