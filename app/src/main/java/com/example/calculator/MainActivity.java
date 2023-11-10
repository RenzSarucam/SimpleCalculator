package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button buttonadd, buttonsub, buttonmul, buttondiv;
    EditText editTextN1, editTextN2;
    TextView textView;

    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonadd = findViewById(R.id.btn_add);
        buttonsub = findViewById(R.id.btn_sub);
        buttonmul = findViewById(R.id.btn_mul);
        buttondiv = findViewById(R.id.btn_div);
        editTextN1 = findViewById(R.id.Number1);
        editTextN2 = findViewById(R.id.Number2);
        textView = findViewById(R.id.answer);


        buttonadd.setOnClickListener(this);
        buttonsub.setOnClickListener(this);
        buttonmul.setOnClickListener(this);
        buttondiv.setOnClickListener(this);

    }

    public int getIntFromEditText(EditText editText) {
        if (editText.getText().toString().equals("")) {
            Toast.makeText(this, "Enter number", Toast.LENGTH_SHORT).show();
            return 0;
        } else
            return Integer.parseInt(editText.getText().toString());
    }

    @Override
    public void onClick(View view) {
        num1 = getIntFromEditText(editTextN1);
        num2 = getIntFromEditText(editTextN2);

        if (view.getId() == R.id.btn_add) {
            int sum = num1 + num2;
            textView.setText(String.valueOf(sum));
        } else if (view.getId() == R.id.btn_sub) {
            int difference = num1 - num2;
            textView.setText(String.valueOf(difference));
        } else if (view.getId() == R.id.btn_mul) {
            int product = num1 * num2;
            textView.setText(String.valueOf(product));
        } else if (view.getId() == R.id.btn_div) {
            if (num2 != 0) {
                int quotient = num1 / num2;
                textView.setText(String.valueOf(quotient));
            } else {
                Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
            }
        }
    }
}