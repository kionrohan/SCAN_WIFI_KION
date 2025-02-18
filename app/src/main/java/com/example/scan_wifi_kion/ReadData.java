package com.example.scan_wifi_kion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadData extends AppCompatActivity {

    Button data;
    TextView receivedData;
    private String esp32Ip = "192.168.4.1"; // IP of ESP32 Access Point (default IP when ESP32 is AP)
    private String esp32Endpoint = "/sendData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_data);


        data =findViewById(R.id.readdata);
        receivedData =findViewById(R.id.textViewResult);
        data.setOnClickListener(v -> {
            String data = "READ";
                sendData(data);
        });
    }
    private void sendData(String data) {
        new Thread(() -> {
            try {

                URL url = new URL("http://" + esp32Ip + esp32Endpoint + "?data=" + data);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(20000);

                // Connect to the ESP32
                connection.connect();

                // Check the response code to verify if the request was successful
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // If the request was successful, read the response from the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Update the UI with the response from the ESP32
                    String responseData = response.toString();
                    runOnUiThread(() -> {
                        receivedData.setText(responseData);// Display data in TextView
                        readData(responseData);
                    });

                } else {
                    runOnUiThread(() ->
                            Toast.makeText(ReadData.this, "Failed to connect: " + responseCode, Toast.LENGTH_SHORT).show()
                    );
                }

                connection.disconnect();

            } catch (IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(ReadData.this, "Connection Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
                Log.e("Connection Error", e.toString());
            }
        }).start();
    }
    private void readData(String data) {
        if (data.contains("READ")) {  // Use contains instead of exact match
            Intent intent = new Intent(ReadData.this, ShowData.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Unexpected Data: " + data, Toast.LENGTH_SHORT).show();
        }
    }
}
