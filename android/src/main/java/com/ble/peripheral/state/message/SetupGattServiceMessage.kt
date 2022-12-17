package com.ble.peripheral.state.message

import android.bluetooth.BluetoothGattService

class SetupGattServiceMessage(val service: BluetoothGattService): IMessage(PeripheralMessageTypes.SETUP_SERVICE){}
