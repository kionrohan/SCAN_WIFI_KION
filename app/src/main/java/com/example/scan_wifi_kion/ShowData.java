package com.example.scan_wifi_kion;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ShowData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_data);
        String[][] data = {
                {"1100", "65265", "Slow battery Charging"}
        };

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Create Table Header
        TableRow headerRow = new TableRow(this);

        String[] headers = {"SVN", "PGN", "Description"};
        for (String header : headers) {
            TextView textView = new TextView(this);
            textView.setText(header);
            textView.setPadding(16, 16, 16, 16);
            textView.setBackgroundColor(Color.LTGRAY);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            headerRow.addView(textView);
        }

        tableLayout.addView(headerRow);

        // Add Data Rows
        for (String[] rowData : data) {
            TableRow row = new TableRow(this);
            for (String cellData : rowData) {
                TextView textView = new TextView(this);
                textView.setText(cellData);
                textView.setPadding(16, 16, 16, 16);
                textView.setTextColor(Color.BLACK);
                row.addView(textView);
            }
            tableLayout.addView(row);
        }
    }
    }

