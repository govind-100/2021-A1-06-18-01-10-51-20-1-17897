package com.example.myapplicationbygovindu;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button send;
    private Button voice;
    boolean isText = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        send = (Button)findViewById(R.id.send);

        voice = findViewById(R.id.voice);

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceMessage(v);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isText = checkAllFIelds();
                if(isText)
                {
                    openActivity2();
                }

            }
        });


    }


    // to check whether input provided or not

    private boolean checkAllFIelds()
    {
        if(editText.length() == 0)
        {
            editText.setError("This field should not be empty");
            return false;

        }
        return true;
    }

    // intent for second activity

    public void openActivity2()
    {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", editText.getText().toString());
        startActivity(intent);
    }


    // implementation of voice input method
    public void voiceMessage(View view)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent,10);
        }
        else
        {
            Toast.makeText(this, "Your device doesn't support speech intput",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode)
        {
            case 10:
            {
                if(resultCode == RESULT_OK && data != null)
                {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editText.setText(result.get(0).toString());
                }
                break;
            }
        }
    }

}