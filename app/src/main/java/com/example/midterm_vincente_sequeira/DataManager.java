package com.example.midterm_vincente_sequeira;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView listViewHistory;
    private TextView textViewEmpty;

    private DataManager dataManager;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("History");
        }

        dataManager = DataManager.getInstance();

        initializeViews();
        setupListView();
        displayHistory();
    }

    private void initializeViews() {
        listViewHistory = findViewById(R.id.listViewHistory);
        textViewEmpty = findViewById(R.id.textViewEmpty);
    }

    private void setupListView() {
        historyList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        listViewHistory.setAdapter(adapter);
    }

    private void displayHistory() {
        ArrayList<String> history = dataManager.getHistoryAsStrings();

        if (history.isEmpty()) {
            showEmptyMessage();
        } else {
            showHistoryList(history);
        }
    }

    private void showEmptyMessage() {
        listViewHistory.setVisibility(ListView.GONE);
        textViewEmpty.setVisibility(TextView.VISIBLE);
        textViewEmpty.setText("No history yet.\nGenerate some multiplication tables!");
    }

    private void showHistoryList(ArrayList<String> history) {
        listViewHistory.setVisibility(ListView.VISIBLE);
        textViewEmpty.setVisibility(TextView.GONE);

        historyList.clear();
        historyList.addAll(history);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "History: " + history.size() + " tables generated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}