package com.alyafei.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.TreeMap;

public class Calculator extends AppCompatActivity {

    EditText resultNumber;
    boolean plus,minus,mult,dev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        resultNumber= (EditText)findViewById(R.id.resultNumber);
        TreeMap<String,Button> tree= new TreeMap<>();
        tree.put("0",(Button)findViewById(R.id.button0));
        tree.put("1",(Button)findViewById(R.id.button1));
        tree.put("2",(Button)findViewById(R.id.button2));
        tree.put("3",(Button)findViewById(R.id.button3));
        tree.put("4",(Button)findViewById(R.id.button4));
        tree.put("5",(Button)findViewById(R.id.button5));
        tree.put("6",(Button)findViewById(R.id.button6));
        tree.put("7",(Button)findViewById(R.id.button7));
        tree.put("8",(Button)findViewById(R.id.button8));
        tree.put("9",(Button)findViewById(R.id.button9));
        tree.put(".",(Button)findViewById(R.id.buttonDot));
        tree.put("+",(Button)findViewById(R.id.buttonPlus));
        tree.put("-",(Button)findViewById(R.id.buttonMin));
        tree.put("*",(Button)findViewById(R.id.buttonMult));
        tree.put("/",(Button)findViewById(R.id.buttonDev));
        tree.put("=",(Button)findViewById(R.id.buttonResult));
        tree.put("C",(Button)findViewById(R.id.buttonC));

        tree.get("0").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.setText(resultNumber.getText()+"0");
            }
        });
        tree.get("1").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append("1");
                    else resultNumber.setText("1");
            }
        });
        tree.get("2").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                resultNumber.append("2");
                else resultNumber.setText("2");
            }
        });
        tree.get("3").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append("3");
                else resultNumber.setText("3");
            }
        });
        tree.get("4").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append("4");
                else resultNumber.setText("4");
            }
        });
        tree.get("5").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append("5");
                else resultNumber.setText("5");
            }
        });
        tree.get("6").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append("6");
                else resultNumber.setText("6");
            }
        });
        tree.get("7").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append("7");
                else resultNumber.setText("7");
            }
        });
        tree.get("8").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append("8");
                else resultNumber.setText("8");
            }
        });
        tree.get("9").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append("9");
                else resultNumber.setText("9");
            }
        });
        tree.get(".").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append(".");
            }
        });
        tree.get("+").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                resultNumber.append(" + ");
                plus=true;
            }
        });
        tree.get("-").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append(" - ");
                minus=false;
        }
        });
        tree.get("*").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append(" X ");
                mult=false;
            }
        });
        tree.get("/").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.append(" / ");
                if(plus){
                    calculatePlus();
                }else if(minus){

                }else if(mult){

                }else if(dev){

                }
            }
        });
        tree.get("=").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultNumber.getText().toString().equals("0"))
                    resultNumber.setText(resultNumber.getText()+"1");
                else resultNumber.setText("1");
            }
        });
        tree.get("C").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    resultNumber.setText("");
            }
        });

    }

    private void calculatePlus() {
        Toast.makeText(this,"PLUS",Toast.LENGTH_SHORT).show();
        plus=false;
    }

}
