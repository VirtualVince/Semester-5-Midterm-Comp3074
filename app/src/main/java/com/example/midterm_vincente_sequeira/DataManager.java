package com.example.midterm_vincente_sequeira;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class DataManager {
    private static DataManager instance;
    private ArrayList<String> currentTable;
    private LinkedHashSet<Integer> historyNumbers;

    private DataManager() {
        currentTable = new ArrayList<>();
        historyNumbers = new LinkedHashSet<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ArrayList<String> generateTable(int number) {
        currentTable.clear();

        for (int i = 1; i <= 10; i++) {
            int result = number * i;
            String tableRow = number + " × " + i + " = " + result;
            currentTable.add(tableRow);
        }

        historyNumbers.add(number);
        return new ArrayList<>(currentTable);
    }

    public ArrayList<String> getCurrentTable() {
        return currentTable;
    }

    public String removeTableItem(int position) {
        if (position >= 0 && position < currentTable.size()) {
            return currentTable.remove(position);
        }
        return null;
    }

    public void clearCurrentTable() {
        currentTable.clear();
    }

    public ArrayList<Integer> getHistory() {
        return new ArrayList<>(historyNumbers);
    }

    public ArrayList<String> getHistoryAsStrings() {
        ArrayList<String> historyStrings = new ArrayList<>();
        for (Integer num : historyNumbers) {
            historyStrings.add("Table for: " + num);
        }
        return historyStrings;
    }

    public void clearHistory() {
        historyNumbers.clear();
    }

    public boolean isHistoryEmpty() {
        return historyNumbers.isEmpty();
    }

    public int getCurrentTableSize() {
        return currentTable.size();
    }
}
