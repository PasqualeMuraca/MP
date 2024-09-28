package com.example.bettercalc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String op = "?";

    private TextView memory;

    private TextView primaryDisplay;
    private TextView secondaryDisplay;

    private Button clearButton;
    private Button mcButton;
    private Button msButton;
    private Button mrButton;

    private Button plusButton;
    private Button minusButton;
    private Button multiplyButton;
    private Button divideButton;
    private Button equalsButton;

    private Button zeroButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button dotButton;

    private boolean equalsJustPressed = false;


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

        associateElements();
        associateListeners();
    }

    private void associateElements() {
        primaryDisplay = findViewById(R.id.primary_display);
        secondaryDisplay = findViewById(R.id.secondary_display);

        clearButton = findViewById(R.id.clear_button);
        mcButton = findViewById(R.id.mc_button);
        msButton = findViewById(R.id.ms_button);
        mrButton = findViewById(R.id.mr_button);
        memory = findViewById(R.id.memory_textview);

        plusButton = findViewById(R.id.plus_button);
        minusButton = findViewById(R.id.minus_button);
        multiplyButton = findViewById(R.id.multiply_button);
        divideButton = findViewById(R.id.divide_button);
        equalsButton = findViewById(R.id.equals_button);

        zeroButton = findViewById(R.id.zero_button);
        oneButton = findViewById(R.id.one_button);
        twoButton = findViewById(R.id.two_button);
        threeButton = findViewById(R.id.three_button);
        fourButton = findViewById(R.id.four_button);
        fiveButton = findViewById(R.id.five_button);
        sixButton = findViewById(R.id.six_button);
        sevenButton = findViewById(R.id.seven_button);
        eightButton = findViewById(R.id.eight_button);
        nineButton = findViewById(R.id.nine_button);
        dotButton = findViewById(R.id.dot_button);
    }

    private void associateListeners() {
        clearButton.setOnClickListener(v -> clear());
        mcButton.setOnClickListener(v -> mc());
        msButton.setOnClickListener(v -> ms());
        mrButton.setOnClickListener(v -> mr());

        zeroButton.setOnClickListener(v -> addDigit("0"));
        oneButton.setOnClickListener(v -> addDigit("1"));
        twoButton.setOnClickListener(v -> addDigit("2"));
        threeButton.setOnClickListener(v -> addDigit("3"));
        fourButton.setOnClickListener(v -> addDigit("4"));
        fiveButton.setOnClickListener(v -> addDigit("5"));
        sixButton.setOnClickListener(v -> addDigit("6"));
        sevenButton.setOnClickListener(v -> addDigit("7"));
        eightButton.setOnClickListener(v -> addDigit("8"));
        nineButton.setOnClickListener(v -> addDigit("9"));
        dotButton.setOnClickListener(v -> addDigit("."));

        plusButton.setOnClickListener(v -> addOperator("+"));
        minusButton.setOnClickListener(v -> addOperator("-"));
        multiplyButton.setOnClickListener(v -> addOperator("*"));
        divideButton.setOnClickListener(v -> addOperator("/"));
        equalsButton.setOnClickListener(v -> equals());
    }

    private void clear() {
        primaryDisplay.setText("0");
        secondaryDisplay.setText("0");
    }

    private void mc() {
        memory.setText("memory");
    }

    private void ms() {
        memory.setText(primaryDisplay.getText());
    }

    private void mr() {
        if (!memory.getText().equals("memory"))
            primaryDisplay.setText(memory.getText());
    }

    private void addDigit(String digit) {
        if (equalsJustPressed) {
            clear();
            equalsJustPressed = false;
        }

        StringBuilder str = new StringBuilder(primaryDisplay.getText());
        if (primaryDisplay.getText().equals("0"))
            str = new StringBuilder();
        str.append(digit);
        primaryDisplay.setText(str.toString());

    }


    private void addOperator(String operator) {
        op = operator;
        secondaryDisplay.setText(primaryDisplay.getText());
        primaryDisplay.setText("0");
    }

    private void equals() {
        equalsJustPressed = true;
        float result = 0;
        float num1 = 0, num2 = 0;
        try {
            num1 = Float.parseFloat(secondaryDisplay.getText().toString());
            num2 = Float.parseFloat(primaryDisplay.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                result = 0;
        }
        secondaryDisplay.setText("");
        primaryDisplay.setText(String.valueOf(result));
    }

}