package com.example.dictionary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AddWord extends AppCompatActivity {
    Button home_page_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        home_page_button = findViewById(R.id.homePageButton);

        home_page_button.setOnClickListener(v -> {
            Intent intent = new Intent(AddWord.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    public void addWordButton(View view) {
        TextView errorWordText = findViewById(R.id.errorWordField);
        TextView errorFreqText = findViewById(R.id.errorFreqText);
        TextView errorMeaningText = findViewById(R.id.errorMeaningField);

        EditText wordField = findViewById(R.id.wordEntryField);
        EditText frequencyField = findViewById(R.id.frequencyTextField);
        EditText meaningField = findViewById(R.id.meaningTextField);

        String wordStr = wordField.getText().toString();
        String frequencyStr = frequencyField.getText().toString();
        String meaningStr = meaningField.getText().toString();
        int freq = 1;

        if (wordStr.equals("")) {
            errorWordText.setTextColor(Color.RED);
            errorWordText.setText("Word required.");
            return;
        }

        if (frequencyStr.equals("0")) {
            errorFreqText.setTextColor(Color.RED);
            errorFreqText.setText("Cannot be 0.");
            return;
        }
        else if (!frequencyStr.equals("")) {
            freq = Integer.parseInt(frequencyStr);
        }

        if (meaningStr.equals("")) {
            errorMeaningText.setTextColor(Color.RED);
            errorMeaningText.setText("Cannot be empty.");
            return;
        }

        boolean wordExists = false;

        for (int i = 0; i < DataStorage.getInstance().data.size(); i++) {
            if (wordStr.equals(DataStorage.getInstance().data.get(i).wordName)) {
                wordExists = true;
                break;
            }
        }

        if (wordExists) {
            errorWordText.setTextColor(Color.RED);
            errorWordText.setText("Word already exists.");
        }
        else {
            DataStorage.getInstance().data.add(new WordInfo(wordStr, freq, meaningStr));
            wordField.setText("");
            frequencyField.setText("");
            meaningField.setText("");
            errorFreqText.setText("");
            errorMeaningText.setText("");
            errorWordText.setTextColor(Color.GREEN);
            errorWordText.setText("Word successfully added!");
        }
    }

    public void clearButton(View view) {
        TextView errorWordText = findViewById(R.id.errorWordField);
        TextView errorFreqText = findViewById(R.id.errorFreqText);
        TextView errorMeaningText = findViewById(R.id.errorMeaningField);

        EditText wordField = findViewById(R.id.wordEntryField);
        EditText frequencyField = findViewById(R.id.frequencyTextField);
        EditText meaningField = findViewById(R.id.meaningTextField);

        errorWordText.setText("");
        errorFreqText.setText("");
        errorMeaningText.setText("");
        wordField.setText("");
        frequencyField.setText("");
        meaningField.setText("");
    }
}