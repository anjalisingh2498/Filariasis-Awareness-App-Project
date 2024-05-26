package com.ensias.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientRequestPage extends AppCompatActivity {

    private TextView contentTextView;
    private Button languageButton;
    private boolean isEnglishDisplayed = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_request_page);
        contentTextView = findViewById(R.id.textView);
        languageButton = findViewById(R.id.language_button);

        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnglishDisplayed) {
                    contentTextView.setText(R.string.lf_content_kannada);
                    languageButton.setText("Switch to English");
                } else {
                    contentTextView.setText(R.string.appname);
                    languageButton.setText("ಕನ್ನಡಕ್ಕೆ ಬದಲಿಸಿ");
                }
                isEnglishDisplayed = !isEnglishDisplayed;
            }
        });

    }

}
