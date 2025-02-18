package com.example.scan_wifi_kion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText user,pass;
    Button signIn;
    String username ="username";
    String password="password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        user=findViewById(R.id.input_username);
        pass=findViewById(R.id.input_password);
        signIn=findViewById(R.id.signin);


        // Call the below method when you want to add records in database and modify records accordingly.

        // insertRecord();


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_user=user.getText().toString().trim();
                String input_pass=pass.getText().toString().trim();

                if(input_user.equals(username) && input_pass.equals(password)){
                    Intent intent =new Intent(MainActivity.this, Wifi_Connect.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Username and Password is incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertRecord() {
        Database db=Database.getInstance(this);
        CAN_Msg_Dao dao=db.canMsgDao();
        new Thread(() -> {

            dao.insert(new CAN_Msg("61444", "1675", "Engine Starter Mode"));
            dao.insert(new CAN_Msg("61444", "1483", "Source Address of TSC1"));
            dao.insert(new CAN_Msg("61443", "92", "Engine Percent Load At Current Speed"));
            dao.insert(new CAN_Msg("61443", "91", "Accelerator Position (%)"));
            dao.insert(new CAN_Msg("65247", "515", "Desired Engine Speed"));
            dao.insert(new CAN_Msg("65214", "189", "Engine Rated Speed"));
            dao.insert(new CAN_Msg("65251", "544", "Reference Engine Torque"));
            dao.insert(new CAN_Msg("65251", "188", "Engine Speed (Low Idle Point)"));
            dao.insert(new CAN_Msg("65251", "532", "Engine Speed (High Idle Point)"));
            dao.insert(new CAN_Msg("65251", "535", "Requested Speed Lower Limit"));
            dao.insert(new CAN_Msg("65251", "536", "Requested Speed Upper Limit"));
            dao.insert(new CAN_Msg("65251", "537", "Requested Torque Lower Limit"));
            dao.insert(new CAN_Msg("65251", "538", "Requested Torque Upper Limit"));
            dao.insert(new CAN_Msg("65262", "110", "Engine Coolant Temperature"));
            dao.insert(new CAN_Msg("65262", "174", "Fuel Temperature"));
            dao.insert(new CAN_Msg("65265", "70", "Parking Brake Switch"));
            dao.insert(new CAN_Msg("65265", "70", "Parking Brake Switch"));
            dao.insert(new CAN_Msg("65265", "84", "Vehicle Speed"));
            dao.insert(new CAN_Msg("65266", "183", "Fuel Rate"));
            dao.insert(new CAN_Msg("65266", "51", "Throttle Position"));
            dao.insert(new CAN_Msg("65270", "102", "Boost Pressure"));
            dao.insert(new CAN_Msg("65270", "105", "Intake Manifold Temperature"));
            dao.insert(new CAN_Msg("65271", "168", "Battery Potential"));
            dao.insert(new CAN_Msg("65269", "108", "Barometric Pressure"));
            dao.insert(new CAN_Msg("65269", "171", "Ambient Air Temperature"));
            dao.insert(new CAN_Msg("65269", "172", "Engine Air Inlet Temperature"));
            dao.insert(new CAN_Msg("65257", "250", "Total Fuel Used"));
            dao.insert(new CAN_Msg("65253", "247", "Total Engine Hours"));
            dao.insert(new CAN_Msg("65166", "1350", "Time at Last Service"));
            dao.insert(new CAN_Msg("65219", "604", "Transmission Neutral Switch"));
            dao.insert(new CAN_Msg("65219", "604", "Transmission Neutral Switch"));
            dao.insert(new CAN_Msg("65263", "100", "Engine Oil Pressure"));
            dao.insert(new CAN_Msg("65263", "101", "Engine Crankcase Pressure"));
            dao.insert(new CAN_Msg("65259", "587", "Model"));
            dao.insert(new CAN_Msg("65259", "588", "Serial Number"));
            dao.insert(new CAN_Msg("65242", "965", "Number of Software Identification Field"));
            dao.insert(new CAN_Msg("65242", "234", "Software Identification"));
            dao.insert(new CAN_Msg("65243", "157", "Injector Metering Rail Pressure"));
            dao.insert(new CAN_Msg("65252", "1081", "Wait to Start Lamp"));
            dao.insert(new CAN_Msg("65252", "1110", "Engine Protection System has Shutdown Engine"));
            dao.insert(new CAN_Msg("0", "695", "Override Control Mode"));
            dao.insert(new CAN_Msg("0", "897", "Override Control Mode Priority"));
            dao.insert(new CAN_Msg("0", "898", "Engine Requested/Limit Speed control"));
            dao.insert(new CAN_Msg("0", "518", "Engine Requested/Limit Torque control"));
            dao.insert(new CAN_Msg("65360", "N/A", "Kubota Proprietary Engine Information"));
            dao.insert(new CAN_Msg("65361", "N/A", "Kubota Proprietary Engine Information"));
            dao.insert(new CAN_Msg("65363", "N/A", "Kubota Proprietary Messages to Engine"));
            dao.insert(new CAN_Msg("61450", "132", "Engine Inlet Air Mass Flow Rate"));
            dao.insert(new CAN_Msg("64796", "4781", "Soot Mass"));
            dao.insert(new CAN_Msg("64800", "4765", "After Treatment 1 Diesel Oxidation Catalyst Intake Gas Temperature"));
            dao.insert(new CAN_Msg("64914", "3607", "Engine Emergency (Immediate) Shutdown Indication"));
            dao.insert(new CAN_Msg("64948", "3242", "After Treatment 1 Diesel Particulate Filter Intake Gas Temperature"));
            dao.insert(new CAN_Msg("64946", "3251", "After Treatment 1 Diesel Particulate Filter Differential Pressure"));
            dao.insert(new CAN_Msg("64947", "3246", "After Treatment 1 Diesel Particulate Filter Outlet Gas Temperature"));
            dao.insert(new CAN_Msg("64892", "3697", "Diesel Particulate Filter Lamp Command"));
            dao.insert(new CAN_Msg("64892", "3700", "Diesel Particulate Filter Active Regeneration Status"));
            dao.insert(new CAN_Msg("64892", "3701", "Diesel Particulate Filter Status"));
            dao.insert(new CAN_Msg("64892", "3702", "Diesel Particulate Filter Active Regeneration Inhibited Status"));
            dao.insert(new CAN_Msg("64892", "3703", "Diesel Particulate Filter Active Regeneration Inhibited Due to Inhibit Switch"));
            dao.insert(new CAN_Msg("64892", "3707", "Diesel Particulate Filter Active Regeneration Inhibited Due to Accelerator Pedal Off Idle"));
            dao.insert(new CAN_Msg("64892", "3708", "Diesel Particulate Filter Active Regeneration Inhibited Due to Out of Neutral"));
            dao.insert(new CAN_Msg("64800", "4765", "After Treatment 1 Diesel Oxidation Catalyst Intake Gas Temperature"));
            dao.insert(new CAN_Msg("64948", "3242", "After Treatment 1 Diesel Particulate Filter Intake Gas Temperature"));
            dao.insert(new CAN_Msg("64892", "3702", "Diesel Particulate Filter Active Regeneration Inhibited Status"));
            dao.insert(new CAN_Msg("64892", "3703", "Diesel Particulate Filter Active Regeneration Inhibited Due to Inhibit Switch"));
            dao.insert(new CAN_Msg("64892", "3707", "Diesel Particulate Filter Active Regeneration Inhibited Due to Accelerator Pedal Off Idle"));
            dao.insert(new CAN_Msg("64892", "3708", "Diesel Particulate Filter Active Regeneration Inhibited Due to Out of Neutral"));
            dao.insert(new CAN_Msg("64892", "3710", "Diesel Particulate Filter Active Regeneration Inhibited"));
            dao.insert(new CAN_Msg("64892", "3710", "Due to Parking Brake Not Set"));
            dao.insert(new CAN_Msg("64892", "3711", "Diesel Particulate Filter Active Regeneration Inhibited"));
            dao.insert(new CAN_Msg("64892", "3711", "Due to Low Exhaust Gas Temperature"));
            dao.insert(new CAN_Msg("64892", "3712", "Diesel Particulate Filter Active Regeneration Inhibited"));
            dao.insert(new CAN_Msg("64892", "3712", "Due to System Fault Active"));
            dao.insert(new CAN_Msg("64892", "3713", "Diesel Particulate Filter Active Regeneration Inhibited"));
            dao.insert(new CAN_Msg("64892", "3713", "Due to System Timeout"));
            dao.insert(new CAN_Msg("64892", "3715", "Diesel Particulate Filter Active Regeneration Inhibited"));
            dao.insert(new CAN_Msg("64892", "3715", "Due to Permanent System Lockout"));
            dao.insert(new CAN_Msg("64892", "3716", "Diesel Particulate Filter Active Regeneration Inhibited"));
            dao.insert(new CAN_Msg("64892", "3716", "Due to Engine Not Warmed Up"));
            dao.insert(new CAN_Msg("64892", "3698", "Exhaust Gas High Temperature Lamp Command"));
            dao.insert(new CAN_Msg("57344", "3695", "Diesel Particulate Filter Regeneration Inhibit Switch"));
            dao.insert(new CAN_Msg("57344", "3695", "Diesel Particulate Filter Regeneration Inhibit Switch"));
            dao.insert(new CAN_Msg("57344", "3696", "Diesel Particulate Filter Regeneration Force Switch"));
            dao.insert(new CAN_Msg("57344", "3696", "Diesel Particulate Filter Regeneration Force Switch"));
            dao.insert(new CAN_Msg("64891", "3719", "Diesel Particulate Filter 1 Soot Load Percent"));
            dao.insert(new CAN_Msg("64891", "3720", "Diesel Particulate Filter 1 Ash Load Percent"));
            dao.insert(new CAN_Msg("64891", "3721", "Diesel Particulate Filter 1 Time since Last Active"));
            dao.insert(new CAN_Msg("64891", "3721", "Regeneration"));
            dao.insert(new CAN_Msg("65226", "N/A", "Active Diagnostics"));
            dao.insert(new CAN_Msg("65227", "N/A", "Logged Diagnostics"));
            dao.insert(new CAN_Msg("65228", "N/A", "Clear Logged Diagnostics"));
            dao.insert(new CAN_Msg("65229", "N/A", "Freeze Frame Parameters"));
            dao.insert(new CAN_Msg("65230", "1218", "Active Trouble Codes"));
            dao.insert(new CAN_Msg("65230", "1219", "Previously Active Diagnostic Trouble Codes"));
            dao.insert(new CAN_Msg("65235", "N/A", "Diagnostic Data Clear/Reset For Active DTCs"));
            dao.insert(new CAN_Msg("57088", "N/A", "Stop Start Broadcast"));
            dao.insert(new CAN_Msg("60160", "N/A", "Transport Protocol (DT)"));
            dao.insert(new CAN_Msg("60416", "N/A", "Transport Protocol (BAM & RTS)"));
            dao.insert(new CAN_Msg("59392", "N/A", "Acknowledge"));
            dao.insert(new CAN_Msg("59904", "N/A", "PGN Request"));
            dao.insert(new CAN_Msg("60928", "N/A", "Manufacture Code in NAME Fields"));
            dao.insert(new CAN_Msg("64981", "2791", "Engine Exhaust Gas Recirculation 1 Valve 1 Control 1"));
            dao.insert(new CAN_Msg("64916", "27", "Engine Exhaust Gas Recirculation 1 Valve Position"));
            dao.insert(new CAN_Msg("65279", "97", "Water in Fuel Indicator"));
            dao.insert(new CAN_Msg("65374", "N/A", "Kubota Proprietary Messages for PCD"));
            dao.insert(new CAN_Msg("65378", "N/A", "KBT Proprietary Messages for ASH"));
            dao.insert(new CAN_Msg("65330", "N/A", "KBT Proprietary Messages for HMI"));
            dao.insert(new CAN_Msg("65367", "N/A", "KBT Proprietary Messages for HMI 3"));
            dao.insert(new CAN_Msg("65459", "N/A", "KBT Proprietary message for HMI 2"));
            dao.insert(new CAN_Msg("65460", "N/A", "KBT Proprietary message for HMI 4"));
            dao.insert(new CAN_Msg("65471", "N/A", "KBT Proprietary message for AT"));
            dao.insert(new CAN_Msg("64794", "4810", "After Treatment 1 Warm Up Diesel Oxidation Catalyst"));
            dao.insert(new CAN_Msg("64794", "4810", "Outlet Temperature"));

            // Add more entries here...

        }).start();
    }
}