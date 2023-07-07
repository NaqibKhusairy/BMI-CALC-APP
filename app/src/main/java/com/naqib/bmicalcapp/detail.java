package com.naqib.bmicalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class detail extends AppCompatActivity {
    TextView tbl,YD,detail;
    Button back,exit;
    String name,phone,kgg,cmm,ClassBMI,Gender;
    String BMITable[][]= {
            {"   Category     ", " BMI Minimum", "  BMI Maximum"},
            {" Underweight     ", "         -              ","           8.49"},
            {"     Normal           ", "      18.5            ","          25"},
            {"  Overweight      ", "      25.1           ","          30"},
            {"      Obese           ", "      30.1            ", "            -"}
    };
    float brt,tnggi,BMI;

    public void CALC(){
        if(kgg.equals("G")){
            brt/=1000;
        }
        brt = Math.round(brt * 100) / 100.0f; // Round to two decimal places

        if(cmm.equals("CM")){
            tnggi/=100;
        }
        tnggi = Math.round(tnggi * 100) / 100.0f; // Round to two decimal places

        BMI=brt/(tnggi*tnggi);
        BMI = Math.round(BMI * 100) / 100.0f; // Round to two decimal places

        if (BMI<=18.49)
        {
            ClassBMI=BMITable[1][0];
        }
        else if (BMI>=18.5 && BMI<=25)
        {
            ClassBMI=BMITable[2][0];
        }
        else if (BMI>25 && BMI<=30)
        {
            ClassBMI=BMITable[3][0];
        }
        else if (BMI<=30.1)
        {
            ClassBMI=BMITable[4][0];
        }
        ClassBMI=ClassBMI.toUpperCase();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        YD=findViewById(R.id.textView3);
        detail=findViewById(R.id.textView2);
        back=findViewById(R.id.button);
        exit=findViewById(R.id.button2);
        tbl=findViewById(R.id.textView4);

        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
        phone="+60"+bundle.getString("phone");
        brt=bundle.getFloat("brt");
        tnggi=bundle.getFloat("tnggi");
        kgg=bundle.getString("kgg");
        cmm=bundle.getString("cmm");
        Gender=bundle.getString("Gender");

        StringBuilder tableText = new StringBuilder();
        for (String[] row : BMITable) {
            String category = row[0];
            String minBMI = row[1];
            String maxBMI = row[2];
            tableText.append(category).append("\t").append(minBMI).append("\t").append(maxBMI).append("\n");
        }
        CALC();
        tbl.setText("                            BMI CATEGORIES\n"
                   +"      BMI=WEIGHT(KG)/(HEIGHT(M)*HEIGHT(M))\n" +
                    "----------------------------------------------------------------------------\n" +
                tableText.toString());
        YD.setText("---------------------------------------------------------\n" +
                "YOUR DETAILS\n---------------------------------------------------------");
        detail.setText("NAME : "+name+"\nWEIGHT : "+brt+" KG"+"\nHEIGHT : "+tnggi+" M"+
                "\nGENDER : "+Gender+
                "\nPHONE NUMBER : "+phone+"\nYOUR BMI : "+BMI+"\nYOUR CATEGORY : "+ClassBMI+
                "\n---------------------------------------------------------");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b= new Intent(getApplicationContext(),getdetail.class);
                startActivity(b);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Bye "+name+"... \nThankyou for using ur System", Toast.LENGTH_SHORT).show();
                Intent x= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(x);
            }
        });
    }
}