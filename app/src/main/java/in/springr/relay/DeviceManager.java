package in.springr.relay;

public class DeviceManager {

    private BluetoothWorker bluetoothWorker;

    public DeviceManager() {
        this.bluetoothWorker = new BluetoothWorker();
    }

    public void setPhoneChargerOn(DeviceStatusListener deviceStatusListener) {

        bluetoothWorker.connect("RELAY2 ON\r\n", new BluetoothWorker.BluetoothConnectionListener() {
            @Override
            public void onDataSent() {
                deviceStatusListener.onSuccess();
            }

            @Override
            public void onConnectFail() {
                deviceStatusListener.onFailure();
            }
        });
    }

    public void setPhoneChargerOff(DeviceStatusListener deviceStatusListener) {

        bluetoothWorker.connect("RELAY2 OFF\r\n", new BluetoothWorker.BluetoothConnectionListener() {
            @Override
            public void onDataSent() {
                deviceStatusListener.onSuccess();
            }

            @Override
            public void onConnectFail() {
                deviceStatusListener.onFailure();
            }
        });
    }


    public void setAmplifierOff(DeviceStatusListener deviceStatusListener) {

        bluetoothWorker.connect("RELAY1 OFF\r\n", new BluetoothWorker.BluetoothConnectionListener() {
            @Override
            public void onDataSent() {
                deviceStatusListener.onSuccess();
            }

            @Override
            public void onConnectFail() {
                deviceStatusListener.onFailure();
            }
        });
    }

    public void setAmplifierOn(DeviceStatusListener deviceStatusListener) {

        bluetoothWorker.connect("RELAY1 ON\r\n", new BluetoothWorker.BluetoothConnectionListener() {
            @Override
            public void onDataSent() {
                deviceStatusListener.onSuccess();
            }

            @Override
            public void onConnectFail() {
                deviceStatusListener.onFailure();
            }
        });
    }

    interface DeviceStatusListener {
        void onSuccess();

        void onFailure();
    }

}
