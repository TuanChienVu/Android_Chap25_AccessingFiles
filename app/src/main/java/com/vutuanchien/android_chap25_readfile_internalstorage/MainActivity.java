package com.vutuanchien.android_chap25_readfile_internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button btnReadData;
    EditText edData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReadData = (Button) findViewById(R.id.btnreaddata);
        edData = (EditText) findViewById(R.id.eddata);

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
    }

    /**
     * function read file in Android
     * using openFileInput in Android to read
     * FileInputStream
     */
    public void readData() {
        String data;
        InputStream inputStream = getResources().openRawResource(R.raw.myfile);
        InputStreamReader inreader = new InputStreamReader(inputStream);
        BufferedReader bufreader = new BufferedReader(inreader);
        StringBuilder builder = new StringBuilder();
        if (inputStream != null) {
            try {
                while ((data = bufreader.readLine()) != null) {
                    builder.append(data);
                    builder.append("\n");
                }
                inputStream.close();
                edData.setText(builder.toString());
            } catch (IOException ex) {
                Log.e("ERROR", ex.getMessage());
            }
        }
    }
}
