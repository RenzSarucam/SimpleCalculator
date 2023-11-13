package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonadd, buttonsub, buttonmul, buttondiv;
    EditText editTextN1, editTextN2;
    TextView textView;
    ListView historyListView;
    List<String> inputHistory = new ArrayList<>();
    ArrayAdapter<String> historyAdapter;

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
        historyListView = findViewById(R.id.historyListView);

        buttonadd.setOnClickListener(this);
        buttonsub.setOnClickListener(this);
        buttonmul.setOnClickListener(this);
        buttondiv.setOnClickListener(this);

        // Initialize the history adapter and set it to the ListView
        historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inputHistory);
        historyListView.setAdapter(historyAdapter);
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

        // Add the input values and answer to the history
        String input = num1 + " " + getOperator(view.getId()) + " " + num2 + " = " + calculateResult(view.getId());
        inputHistory.add(input);

        // Update the ListView
        historyAdapter.notifyDataSetChanged();

        // Update the result TextView
        textView.setText(String.valueOf(calculateResult(view.getId())));
    }

    private String getOperator(int viewId) {
        if (viewId == R.id.btn_add) {
            return "+";
        } else if (viewId == R.id.btn_sub) {
            return "-";
        } else if (viewId == R.id.btn_mul) {
            return "*";
        } else if (viewId == R.id.btn_div) {
            return "/";
        }
        return "";
    }

    private int calculateResult(int viewId) {
        if (viewId == R.id.btn_add) {
            return num1 + num2;
        } else if (viewId == R.id.btn_sub) {
            return num1 - num2;
        } else if (viewId == R.id.btn_mul) {
            return num1 * num2;
        } else if (viewId == R.id.btn_div) {
            if (num2 != 0) {
                return num1 / num2;
            } else {
                Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
            }
        }
        return 0;
    }
}
