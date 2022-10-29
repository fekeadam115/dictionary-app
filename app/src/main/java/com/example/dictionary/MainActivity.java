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

import java.util.ArrayList;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    Button next_Activity_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next_Activity_button = findViewById(R.id.newWordButton);

        next_Activity_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWord.class);
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    public void func(View view) {
        ArrayList<WordInfo> wordList = DataStorage.getInstance().data;
        EditText searchField = findViewById(R.id.nameBox);
        String searchName = searchField.getText().toString();
        TextView meaningField = findViewById(R.id.meaningBox);
        TextView freqWord1 = findViewById(R.id.frequentWordField1);
        TextView freqWord2 = findViewById(R.id.frequentWordField2);
        TextView freqWord3 = findViewById(R.id.frequentWordField3);

        if (searchName.equals("")) {
            meaningField.setTextColor(Color.RED);
            meaningField.setText("Word required.");
            return;
        }

        for (WordInfo wordInfo : wordList) {
            if (searchName.equals(wordInfo.wordName)) {
                meaningField.setTextColor(Color.BLACK);
                String message = "Word: " + searchName
                        + "\n\n" + "Frequency: " + wordInfo.frequency
                        + "\n\n" + "Meaning: " + wordInfo.meaning;
                meaningField.setText(message);
                freqWord1.setText("");
                freqWord2.setText("");
                freqWord3.setText("");
                return;
            }
        }

        int searchNameSize = searchName.length();
        String[] frequentWord = new String[3];
        int[] frequency = new int[3];
        int frequentWordCount = 0;
        int frequencyCount = 0;

        for (WordInfo wordInfo : wordList) {
            if (frequentWordCount == 3) {
                break;
            }
            if (wordInfo.wordName.substring(0, searchNameSize).contains(searchName)) {
                frequentWord[frequentWordCount] = wordInfo.wordName;
                frequency[frequencyCount] = wordInfo.frequency;
                frequentWordCount++;
                frequencyCount++;
            }
        }

        int high = Math.max(frequency[0], Math.max(frequency[1], frequency[2]));
        int low = Math.max(frequency[0], Math.min(frequency[1], frequency[2]));
        int mid = frequency[0] + frequency[1] + frequency[2] - high - low;

        if (frequency[0] == high) {
            freqWord1.setText(frequentWord[0]);
            if (frequency[1] == mid) {
                freqWord2.setText(frequentWord[1]);
                freqWord3.setText(frequentWord[2]);
            }
            else if (frequency[1] == low) {
                freqWord3.setText(frequentWord[1]);
                freqWord2.setText(frequentWord[2]);
            }
        }
        else if (frequency[0] == mid) {
            freqWord2.setText(frequentWord[0]);
            if (frequency[1] == high) {
                freqWord1.setText(frequentWord[1]);
                freqWord3.setText(frequentWord[2]);
            }
            else if (frequency[1] == low) {
                freqWord3.setText(frequentWord[1]);
                freqWord1.setText(frequentWord[2]);
            }
        }
        else if (frequency[0] == low) {
            freqWord3.setText(frequentWord[0]);
            if (frequency[1] == high) {
                freqWord1.setText(frequentWord[1]);
                freqWord2.setText(frequentWord[2]);
            }
            else if (frequency[1] == mid) {
                freqWord2.setText(frequentWord[1]);
                freqWord1.setText(frequentWord[2]);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void removeButton(View view) {
        EditText searchField = findViewById(R.id.nameBox);
        String searchName = searchField.getText().toString();
        ArrayList<WordInfo> wordList = DataStorage.getInstance().data;
        int index = 0;
        boolean exists = false;

        for (int i = 0; i < wordList.size(); i++) {
            if (searchName.equals(wordList.get(i).wordName)) {
                exists = true;
                index = i;
                break;
            }
        }

        TextView meaningField = findViewById(R.id.meaningBox);

        if (exists) {
            DataStorage.getInstance().data.remove(index);
            meaningField.setTextColor(Color.GREEN);
            meaningField.setText("Word successfully removed!");
        }
        else {
            meaningField.setTextColor(Color.RED);
            meaningField.setText("Word not found.");
        }
    }
}