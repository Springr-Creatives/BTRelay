package in.springr.relay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //"RELAY1 OFF\r\n"
    private DeviceManager deviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deviceManager = new DeviceManager();
        Button buttonPhoneON = findViewById(R.id.buttonPhoneON);
        buttonPhoneON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceManager.setPhoneChargerOn(new DeviceManager.DeviceStatusListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        });

        Button buttonPhoneOff = findViewById(R.id.buttonPhoneOff);
        buttonPhoneOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceManager.setPhoneChargerOff(new DeviceManager.DeviceStatusListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        });


        Button buttonAmplifierON = findViewById(R.id.buttonAmplifierON);
        buttonAmplifierON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceManager.setAmplifierOn(new DeviceManager.DeviceStatusListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        });


        Button buttonAmplifierOff = findViewById(R.id.buttonAmplifierOff);
        buttonAmplifierOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceManager.setAmplifierOff(new DeviceManager.DeviceStatusListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        });
    }
}