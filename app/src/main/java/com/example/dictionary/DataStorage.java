package com.example.dictionary;

import java.util.ArrayList;

public class DataStorage {
    ArrayList<WordInfo> data = new ArrayList<>();

    private static final DataStorage holder = new DataStorage();

    public static DataStorage getInstance() {
        return holder;
    }
}