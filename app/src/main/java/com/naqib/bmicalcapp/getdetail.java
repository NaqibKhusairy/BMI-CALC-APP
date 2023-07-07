package com.naqib.bmicalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class getdetail extends AppCompatActivity {
    TextView penerangn;
    Button enter,exit;
    EditText Name,Berat,Tinggi,Phone,Phone60,gnd;
    RadioGroup rgBerat,rgTinggi,Jantina;
    RadioButton rbBerat,rbTinggi,BtnGender;
    String name,berat,tinggi,phone,kgg,cmm,Gender;
    float brt,tnggi;
    int SelectedKgG,SelectedCmM,SelectedGander;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdetail);
        penerangn=findViewById(R.id.textView2);
        enter=findViewById(R.id.button);
        exit=findViewById(R.id.button2);
        Name=findViewById(R.id.etName);
        Phone=findViewById(R.id.etRegPhone);
        Phone60=findViewById(R.id.Et60);
        Berat=findViewById(R.id.etBerat);
        Tinggi=findViewById(R.id.etTinggi);
        rgBerat=findViewById(R.id.KGG);
        rgTinggi=findViewById(R.id.CMM);
        Jantina=findViewById(R.id.rgGenderMF);
        gnd=findViewById(R.id.mleorfmale);

        penerangn.setText("Complete this form to check your detail");

        Phone60.setEnabled(false);
        gnd.setEnabled(false);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=(Name.getText().toString()).toUpperCase();
                phone=Phone.getText().toString();
                berat=Berat.getText().toString();
                tinggi=Tinggi.getText().toString();

                try {
                    //radio button = get value
                    SelectedKgG=rgBerat.getCheckedRadioButtonId();
                    rbBerat=findViewById(SelectedKgG);
                    kgg=rbBerat.getText().toString();

                    SelectedCmM=rgTinggi.getCheckedRadioButtonId();
                    rbTinggi=findViewById(SelectedCmM);
                    cmm=rbTinggi.getText().toString();

                    SelectedGander=Jantina.getCheckedRadioButtonId();
                    BtnGender=findViewById(SelectedGander);
                    Gender=(BtnGender.getText().toString()).toUpperCase();

                    //string to float
                    brt = Float.parseFloat(berat);
                    tnggi = Float.parseFloat(tinggi);

                    if(required()){
                        Toast.makeText(getApplicationContext(), "Hi "+name+" Your Details is ..... ", Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(getApplicationContext(),detail.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("phone", phone);
                        bundle.putFloat("brt", brt);
                        bundle.putFloat("tnggi", tnggi);
                        bundle.putString("kgg", kgg);
                        bundle.putString("cmm", cmm);
                        bundle.putString("Gender", Gender);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                } catch (Exception e) {
                    required();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Bye... \nThankyou for using ur System", Toast.LENGTH_SHORT).show();
                Intent x= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(x);
            }
        });
    }

    public boolean required() {
        if(name.isEmpty() && berat.isEmpty()&& tinggi.isEmpty()&&phone.isEmpty()&&SelectedKgG<=0&&SelectedCmM<=0&&SelectedGander<=0){
            Name.setError("Please enter your Name.");
            Berat.setError("Please enter your Weight and choose KG or G.");
            Tinggi.setError("Please enter your Height and choose CM or M.");
            Phone.setError("Please enter your Phone Number.");
            gnd.setError("Please choose your Gender.");
            return false;
        }
        else if(name.isEmpty()){
            Name.setError("Please enter your Name.");
            return false;
        }
        else if(berat.isEmpty()) {
            Berat.setError("Please enter your Weight.");
            return false;
        }
        else if(tinggi.isEmpty()) {
            Tinggi.setError("Please enter your Height.");
            return false;
        }
        else if(phone.isEmpty()) {
            Phone.setError("Please enter your Phone Number.");
            return false;
        }
        else if(SelectedKgG<=0) {
            Berat.setError("Please choose KG or G.");
            return false;
        }
        else if(SelectedCmM<=0) {
            Tinggi.setError("Please choose CM or M.");
            return false;
        }
        else if(SelectedGander<=0) {
            gnd.setError("Please choose your Gender.");
            return false;
        }
        else {
            return true;
        }
    }
}