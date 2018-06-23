package com.acadview.filestorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText Name,Email,Password;
    Spinner Country,State;
    Button Submit;
    String userName, userEmail, userPwd, userCountry , userState;

    String[] countries = new String[]{"INDIA", "USA", "AUSTRALIA"};
    String[] stateIndia = new String[]{"DELHI","CHANDIGARH","HARAYANA"};
    String[] stateUSA = new String[]{"MEXICO","WASHINGTON","LOWA"};
    String[] stateAustralia = new String[]{"MELBOURNE","VICTORIA","QUEENLAND"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.pwd);
        Country = findViewById(R.id.country);
        State = findViewById(R.id.state);
        Submit = findViewById(R.id.btn);
        Country = findViewById(R.id.country);
        State = findViewById(R.id.state);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countries);
        Country.setAdapter(adapter);


        Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    setAdapter(stateIndia);
                } else if (i == 1) {
                    setAdapter(stateUSA);
                } else if (i == 2) {
                    setAdapter(stateAustralia);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
    }

    public void setAdapter(String[] states) {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout. simple_list_item_1,states);
        State.setAdapter(arrayAdapter);







        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                if (TextUtils.isEmpty(Name.getText().toString())) {
                    Name.setError("Field is  empty");
                    return;
                }

                if (TextUtils.isEmpty(Email.getText().toString())) {
                    Email.setError("Field is empty");
                    return;
                }

                if (TextUtils.isEmpty(Password.getText().toString())) {
                    Password.setError("Field is empty");
                    return;
                }



                userName = Name.getText().toString();
                userEmail = Email.getText().toString();
                userPwd = Password.getText().toString();
                userCountry = Country.getSelectedItem().toString();
                userState = State.getSelectedItem().toString();


                String FullDetail = "NAME:" + userName + "\n" + "EMAIL:" + userEmail + "\n" + "Password:" + userPwd + "\n" + "COUNTRY:" + userCountry +"\n" + "STATE" + userState;

                FileOutputStream fos;


                try {
                    fos = openFileOutput("userData",MODE_PRIVATE);
                    fos.write(FullDetail.getBytes());
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                startActivity(intent);





            }
        });




    }
}
