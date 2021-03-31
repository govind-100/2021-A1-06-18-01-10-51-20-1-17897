package com.example.myapplicationbygovindu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    EditText phone_email;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        phone_email = (EditText) findViewById(R.id.p_e);
        confirm = (Button) findViewById(R.id.confirm);

        String getMessage = getIntent().getStringExtra("message");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(phone_email.getText().toString().length() == 0)
                {
                    phone_email.setHint("This field should not be empty");
                }
                else if(isValidMail(phone_email.getText().toString())){

                    String[] toMail =  phone_email.getText().toString().trim().split(" ");
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, toMail);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, getMessage);

                    startActivity(Intent.createChooser(emailIntent, "choose one application"));
                    Toast.makeText(getApplicationContext(),"Sending Email...", Toast.LENGTH_SHORT).show();

                }
                else if(isValidPhone(phone_email.getText().toString())){
                    Intent phoneIntent= new Intent(Intent.ACTION_VIEW);
                    phoneIntent.setData(Uri.parse(String.format("smsto: %s", phone_email.getText().toString().trim())));
                    phoneIntent.putExtra("sms_body", getMessage);

                    startActivity(Intent.createChooser(phoneIntent,"choose one application"));
                    Toast.makeText(getApplicationContext(),"Sending SMS...", Toast.LENGTH_SHORT).show();

                }
                else{
                    phone_email.getText().clear();
                    phone_email.setHint("Enter a valid phone number or an email address");
                }

            }
        });

    }


    private boolean isValidMail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private boolean isValidPhone(String phone){
        if((int)(phone.charAt(0)-'0') >= 6 && phone.length() <= 15 && phone.length() >= 7){
            return Patterns.PHONE.matcher(phone).matches();
        }
        return false;
    }


}
