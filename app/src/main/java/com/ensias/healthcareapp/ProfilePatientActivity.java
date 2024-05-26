package com.ensias.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class ProfilePatientActivity extends AppCompatActivity {
    private RadioGroup[] questionRadioGroups;
    private TextView resultTextView;
    private Button evaluateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        questionRadioGroups = new RadioGroup[10];
        questionRadioGroups[0] = findViewById(R.id.question1_radio_group);
        questionRadioGroups[1] = findViewById(R.id.question2_radio_group);
        questionRadioGroups[2] = findViewById(R.id.question3_radio_group);
        questionRadioGroups[3] = findViewById(R.id.question4_radio_group);
        questionRadioGroups[4] = findViewById(R.id.question5_radio_group);
        questionRadioGroups[5] = findViewById(R.id.question6_radio_group);
        questionRadioGroups[6] = findViewById(R.id.question7_radio_group);
        questionRadioGroups[7] = findViewById(R.id.question8_radio_group);
        questionRadioGroups[8] = findViewById(R.id.question9_radio_group);
        questionRadioGroups[9] = findViewById(R.id.question10_radio_group);

        // Add remaining RadioGroups for questions 4 to 10

        resultTextView = findViewById(R.id.textView);
        evaluateButton = findViewById(R.id.submitButton);

        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateAnswers();
            }
        });
    }

    private void evaluateAnswers() {
        int[] scores = new int[3]; // Index 0: Critical stage, Index 1: Consider checkup, Index 2: Safe

        for (RadioGroup radioGroup : questionRadioGroups) {
            int selectedOptionId = radioGroup.getCheckedRadioButtonId();
            if (selectedOptionId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedOptionId);
                int optionIndex = radioGroup.indexOfChild(selectedRadioButton);

                // Increment score based on option index
                if (optionIndex == 0) {
                    scores[0]++; // Option A
                } else if (optionIndex == 1) {
                    scores[1]++; // Option B
                } else {
                    scores[2]++; // Option C
                }
            }
        }


        String level;
        if (scores[0] >= 5 || (scores[0] >= 4 && scores[1] >= 4)) {
            level = "Critical Stage please book an appoitnemnt";

        } else if (scores[1] >= 7) {
            level = "Consider Checkup";
        } else {
            level = "You seem Safe but still getting checked is not a bad option";
        }

        // Display the result
        resultTextView.setText("Your level is: " + level);
        String message = "Your level is: " + level;
        Toast.makeText(ProfilePatientActivity.this, message, Toast.LENGTH_LONG).show();
        String reportContent = generateReportContent();
        if (reportContent != null) {
            saveReportToFile(reportContent);
        } else {
            Toast.makeText(ProfilePatientActivity.this, "Failed to generate report", Toast.LENGTH_SHORT).show();
        }
       // Intent i =new Intent(MainActivity.this, QuestionnaireReport.class);
        //i.putExtra("Report", message);
        //startActivity(i);
    }



    private String generateReportContent() {


        StringBuilder reportContent = new StringBuilder();
        // Append the content based on the evaluation

        return reportContent.toString();
    }

    private void saveReportToFile(String reportContent) {
        try {
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Reports");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, "report.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(reportContent.getBytes());
            fos.close();
           // Toast.makeText(MainActivity.this, "Report saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
           // Toast.makeText(MainActivity.this, "Failed to save report", Toast.LENGTH_SHORT).show();
        }
    }

}

