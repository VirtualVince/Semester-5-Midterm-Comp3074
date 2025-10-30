package com.example.midterm_vincente_sequeira;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private Button buttonGenerate;
    private ListView listViewTable;
    private Button buttonHistory;

    private DataManager dataManager;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> tableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager = DataManager.getInstance();

        initializeViews();
        setupListView();
        setupListeners();
        restoreTable();
    }

    private void initializeViews() {
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        listViewTable = findViewById(R.id.listViewTable);
        buttonHistory = findViewById(R.id.buttonHistory);
    }

    private void setupListView() {
        tableList = dataManager.getCurrentTable();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tableList);
        listViewTable.setAdapter(adapter);
    }

    private void setupListeners() {
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateTable();
            }
        });

        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHistory();
            }
        });

        listViewTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
            }
        });
    }

    private void generateTable() {
        String input = editTextNumber.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int number = Integer.parseInt(input);

            if (number < 1 || number > 1000) {
                Toast.makeText(this, "Please enter a number between 1 and 1000", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<String> newTable = dataManager.generateTable(number);

            tableList.clear();
            tableList.addAll(newTable);
            adapter.notifyDataSetChanged();

            editTextNumber.setText("");
            Toast.makeText(this, "Table generated for " + number, Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteDialog(final int position) {
        final String item = tableList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Item");
        builder.setMessage("Do you want to delete: " + item + "?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem(position, item);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteItem(int position, String item) {
        dataManager.removeTableItem(position);
        tableList.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted: " + item, Toast.LENGTH_SHORT).show();
    }

    private void navigateToHistory() {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    private void restoreTable() {
        tableList.clear();
        tableList.addAll(dataManager.getCurrentTable());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_clear_all) {
            showClearAllDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showClearAllDialog() {
        if (dataManager.getCurrentTableSize() == 0) {
            Toast.makeText(this, "No items to clear", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Clear All");
        builder.setMessage("Are you sure you want to clear all items?");

        builder.setPositiveButton("Clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAllItems();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void clearAllItems() {
        int count = dataManager.getCurrentTableSize();
        dataManager.clearCurrentTable();
        tableList.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Cleared " + count + " items", Toast.LENGTH_SHORT).show();
    }
}