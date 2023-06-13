package com.example.electricbillcalculator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

import com.example.electricbillcalculator.R;


public class MainActivity extends Activity {

    private EditText usageEditText;
    private EditText rebateEditText;
    private TextView resultTextView;
    private Button calculateButton;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usageEditText = findViewById(R.id.usageEditText);
        rebateEditText = findViewById(R.id.rebateEditText);
        resultTextView = findViewById(R.id.resultTextView);
        calculateButton = findViewById(R.id.calculateButton);
        button = findViewById(R.id.button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCost();
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                        openAbout();
                    }
        });
    }

    public void openAbout(){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    private void calculateCost() {
        String usageString = usageEditText.getText().toString().trim();
        String rebateString = rebateEditText.getText().toString().trim();

        if (usageString.isEmpty()) {
            usageEditText.setError("Field cannot be blank");
            return;
        } else {
            usageEditText.setError(null);
        }

        if (rebateString.isEmpty()) {
            rebateEditText.setError("Field cannot be blank");
            return;
        } else {
            rebateEditText.setError(null);
        }

        double usage = Double.parseDouble(usageString);
        double rebate = Double.parseDouble(rebateString);

        if (usage < 1 || usage > 900) {
            usageEditText.setError("Please enter usage between 1 to 900");
            return;
        } else {
            usageEditText.setError(null);
        }

        if (rebate < 0 || rebate > 5) {
            rebateEditText.setError("Rebate must be between 0% and 5%");
            return;
        } else {
            rebateEditText.setError(null);
        }

        rebate = rebate /100;
        double cost = 0.0;

        if (usage >= 1 && usage <= 200) {
            cost = usage * 0.218;
        } else if (usage >= 201 && usage <= 300) {
            cost = 200 * 0.218 + (usage - 200) * 0.334;
        } else if (usage >= 301 && usage <= 600) {
            cost = 200 * 0.218 + 100 * 0.334 + (usage - 300) * 0.516;
        } else if (usage >= 601 && usage <= 900) {
            cost = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (usage - 600) * 0.546;
        }

        double finalCost = cost - (cost * rebate);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String finalCostFormatted = decimalFormat.format(finalCost);

        TextView finalCostTextView = findViewById(R.id.resultTextView);

        String finalCostText = "Total Bill: RM";

// Create a SpannableString for the entire text
        SpannableString spannableString = new SpannableString(finalCostText + finalCostFormatted);

// Set the color for the "Total Bill: RM" part (in white)
        ForegroundColorSpan blackColorSpan = new ForegroundColorSpan(Color.WHITE);
        spannableString.setSpan(blackColorSpan, 0, finalCostText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

// Set the color for the cost value part (in red)
        ForegroundColorSpan redColorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(redColorSpan, finalCostText.length(), spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

// Set the SpannableString to the TextView
        finalCostTextView.setText(spannableString);

    }



}



