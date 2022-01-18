package in.springr.relay;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.widget.Toast;

import com.harrysoft.androidbluetoothserial.BluetoothManager;
import com.harrysoft.androidbluetoothserial.BluetoothSerialDevice;
import com.harrysoft.androidbluetoothserial.SimpleBluetoothDeviceInterface;

import java.util.Collection;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BluetoothWorker {

    private SimpleBluetoothDeviceInterface deviceInterface;
    private BluetoothManager bluetoothManager;
    private BluetoothConnectionListener bluetoothConnectionListener;
    private String message;


    public void disconnect() {
        bluetoothManager.close();

    }

    @SuppressLint("MissingPermission")
    public void connect(String message, BluetoothConnectionListener _bluetoothConnectionListener) {
        this.bluetoothConnectionListener = _bluetoothConnectionListener;
        this.message = message;
        bluetoothManager = BluetoothManager.getInstance();
        if (bluetoothManager == null) {
            // Bluetooth unavailable on this device :( tell the user
            bluetoothConnectionListener.onConnectFail();
            return;
        }

        Collection<BluetoothDevice> pairedDevices = bluetoothManager.getPairedDevicesList();
        String deviceMac = "";
        for (BluetoothDevice device : pairedDevices) {

            if (device.getName().equals("HC-05")) {
              //  Log.e("My Bluetooth App", "Device name: " + device.getName());
              //  Log.e("My Bluetooth App", "Device MAC Address: " + device.getAddress());
                setupDevice(device.getAddress());
                return;
            }

        }

        bluetoothConnectionListener.onConnectFail();

    }

    private void setupDevice(String mac) {
        Log.e("BT","MAC");
        bluetoothManager.openSerialDevice(mac)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onConnected, this::onError);
    }

    private void onConnected(BluetoothSerialDevice connectedDevice) {
        // You are now connected to this device!
        // Here you may want to retain an instance to your device:
        deviceInterface = connectedDevice.toSimpleDeviceInterface();

        // Listen to bluetooth events
        deviceInterface.setListeners(this::onMessageReceived, this::onMessageSent, this::onError);

        // Let's send a message:
        Log.e("BT","SEND_MSG>" + message);
        deviceInterface.sendMessage(message);

    }

    private void onMessageSent(String message) {
        // We sent a message! Handle it here.
        Log.e("BT","SENT");
        bluetoothManager.close();
        bluetoothConnectionListener.onDataSent();
    }

    private void onMessageReceived(String message) {
        // We received a message! Handle it here.
        Log.e("BT","RECEIVED");
    }

    private void onError(Throwable error) {
        // Handle the error
        Log.e("BT","ERROR>" + error.getMessage());
        bluetoothConnectionListener.onConnectFail();
    }


    interface BluetoothConnectionListener{
        void onDataSent();
        void onConnectFail();
    }

}
