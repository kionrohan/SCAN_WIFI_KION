package com.example.scan_wifi_kion;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.NetworkCapabilities;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.net.wifi.WifiNetworkSpecifier;
import android.net.NetworkRequest;
import android.net.ConnectivityManager;
import android.net.Network;
import java.util.ArrayList;
import java.util.List;

public class Wifi_Connect extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private WifiManager wifiManager;
    private ListView lvWifiList;
    private Button btnScan;
    private EditText etPassword;

    private List<ScanResult> filteredResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_connect);
        lvWifiList = findViewById(R.id.lvWifiList);
        btnScan = findViewById(R.id.btnScan);
        etPassword = findViewById(R.id.etPassword);


        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "Enabling WiFi...", Toast.LENGTH_SHORT).show();
            wifiManager.setWifiEnabled(true);
        }

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scanWifi();
            }
        });

        lvWifiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScanResult selectedScanResult = filteredResults.get(position);
                String selectedSSID = selectedScanResult.SSID;
                promptPasswordAndConnect(selectedSSID);
            }
        });
    }

    private void scanWifi() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            performScan();
        }
    }



    private void performScan() {
        registerReceiver(new WifiReceiver(), new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        boolean success = wifiManager.startScan();

        if (!success) {
            Toast.makeText(this, "Scan failed. Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private class WifiReceiver extends android.content.BroadcastReceiver {
        @Override
        public void onReceive(Context context, android.content.Intent intent) {
            if (ActivityCompat.checkSelfPermission(Wifi_Connect.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            List<ScanResult> results = wifiManager.getScanResults();
            filteredResults.clear();
            List<String> filteredWifiList = new ArrayList<>();

            for (ScanResult scanResult : results) {
                if (scanResult.SSID.startsWith("KION_")) {
                    filteredResults.add(scanResult);
                    filteredWifiList.add(scanResult.SSID);
                }
            }

            if (filteredWifiList.isEmpty()) {
                Toast.makeText(context, "No KION_ WiFi networks found.", Toast.LENGTH_SHORT).show();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                    android.R.layout.simple_list_item_1, filteredWifiList);
            lvWifiList.setAdapter(adapter);
        }
    }

    private void promptPasswordAndConnect(String ssid) {
        // Create an input dialog for the password
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Connect to " + ssid);

        final EditText passwordInput = new EditText(this);
        passwordInput.setHint("Enter Password");
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(passwordInput);

        builder.setPositiveButton("Connect", (dialog, which) -> {
            String password = passwordInput.getText().toString();
            if (!password.isEmpty()) {
                connectToWifiModern(ssid, password);
            } else {
                Toast.makeText(Wifi_Connect.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void connectToWifiModern(String ssid, String password) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            WifiNetworkSpecifier.Builder builder = new WifiNetworkSpecifier.Builder();
            builder.setSsid(ssid);
            builder.setWpa2Passphrase(password);

            WifiNetworkSpecifier wifiNetworkSpecifier = builder.build();

            NetworkRequest.Builder networkRequestBuilder = new NetworkRequest.Builder();
            networkRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
            networkRequestBuilder.setNetworkSpecifier(wifiNetworkSpecifier);

            NetworkRequest networkRequest = networkRequestBuilder.build();

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            connectivityManager.requestNetwork(networkRequest, new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);

                    // Bind the network to the process
                    connectivityManager.bindProcessToNetwork(network);

                    runOnUiThread(() -> {
                        Toast.makeText(Wifi_Connect.this, "Connected to " + ssid, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Wifi_Connect.this, ReadData.class);
                        startActivity(intent);
                    });
                }

                @Override
                public void onUnavailable() {
                    super.onUnavailable();

                    runOnUiThread(() ->
                            Toast.makeText(Wifi_Connect.this, "Failed to connect to " + ssid + ". Please check password or network.", Toast.LENGTH_LONG).show()
                    );
                }

                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    runOnUiThread(() ->
                            Toast.makeText(Wifi_Connect.this, "Connection lost to " + ssid, Toast.LENGTH_LONG).show()
                    );
                }
            });
        } else {
            Toast.makeText(this, "This feature requires Android 10 or higher.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                performScan();
            } else {
                Toast.makeText(this, "Location permission is required to scan WiFi.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


