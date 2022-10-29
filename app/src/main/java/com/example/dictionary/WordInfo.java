package com.example.dictionary;

public class WordInfo {
    String wordName;
    int frequency;
    String meaning;

    public WordInfo(String newName, int newFreq, String newMeaning) {
        wordName = newName;
        frequency = newFreq;
        meaning = newMeaning;
    }
}