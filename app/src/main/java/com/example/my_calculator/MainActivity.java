package com.example.my_calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText inputFirstNumber, inputSecondNumber;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize views
        inputFirstNumber = findViewById(R.id.inputFirstNumber);
        inputSecondNumber = findViewById(R.id.inputSecondNumber);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        textResult = findViewById(R.id.textResult);

        // Set button listeners using lambdas
        buttonPlus.setOnClickListener(v -> calculate('+'));
        buttonMinus.setOnClickListener(v -> calculate('-'));
        buttonMultiply.setOnClickListener(v -> calculate('*'));
        buttonDivide.setOnClickListener(v -> calculate('/'));
    }

    @SuppressLint("SetTextI18n")
    private void calculate(char operator) {
        String first = inputFirstNumber.getText().toString();
        String second = inputSecondNumber.getText().toString();

        if (first.isEmpty() || second.isEmpty()) {
            textResult.setText("Please fill in both fields");
            return;
        }
        try {
            double num1 = Double.parseDouble(first);
            double num2 = Double.parseDouble(second);
            double result = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textResult.setText("Cannot divide by zero, duh");
                        return;
                    }
                    break;
            }

            textResult.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            textResult.setText("Invalid input, only integers and floating point numbers are accepted");
        }
    }
}

//commit testing