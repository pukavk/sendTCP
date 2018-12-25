package com.example.lenovo.sendtcp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       final Button sendmess = findViewById(R.id.button);
       final EditText editText = findViewById(R.id.editText);

        final Thread runsend = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    Socket soc = new Socket("192.168.1.104",12345);

                    OutputStream out = soc.getOutputStream();

                    DataOutputStream dataou = new DataOutputStream(out);

                    dataou.writeUTF(editText.getText().toString());

                    dataou.flush();

                    dataou.close();

                    out.close();
                    soc.close();
                }
                catch (Exception i)
                {

                }
            }
        });

       sendmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new Thread(runsend).start();
            }
        });


    }


}