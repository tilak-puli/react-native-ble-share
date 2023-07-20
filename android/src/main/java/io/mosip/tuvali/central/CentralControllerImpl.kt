package io.mosip.tuvali.central

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanRecord
import android.content.Context
import android.os.ParcelUuid
import io.mosip.tuvali.ble.central.Central
import io.mosip.tuvali.ble.central.ICentralListener
import io.mosip.tuvali.central.exception.InvalidServiceUUIDException
import io.mosip.tuvali.central.exception.ScanStartFailedException
import io.mosip.tuvali.common.events.DeviceFoundEvent
import io.mosip.tuvali.common.events.Event
import io.mosip.tuvali.exception.BLEException
import io.mosip.tuvali.transfer.Util
import java.util.*

class CentralControllerImpl(context: Context) : CentralController, ICentralListener {
  private val logTag = Util.getLogTag(javaClass.simpleName)
  private var central: Central

  init {
    central = Central(context, this@CentralControllerImpl)
  }

  override fun startScanning(serviceUUID: String?) {
    val filterBuilder = ScanFilter.Builder()

    if (serviceUUID != null) {
      filterBuilder
        .setServiceUuid(ParcelUuid(createUUID(serviceUUID)))
    }

    central.scan(filterBuilder.build())
  }

  private fun createUUID(serviceUUID: String?): UUID {
    try {
      return UUID.fromString(serviceUUID)
    } catch (e: Exception) {
      throw InvalidServiceUUIDException("Invalid UUID Received")
    }
  }

  override fun stopScanning() {
    central.stop()
  }

  override fun connect(deviceAddress: String) {
    TODO("Not yet implemented")
  }

  override fun discoverServices() {
    TODO("Not yet implemented")
  }

  override fun requestMTU(mtu: Int) {
    TODO("Not yet implemented")
  }

  override fun sendData(serviceUUID: String, charUUID: String, data: String) {
    TODO("Not yet implemented")
  }

  override fun subscribe(serviceUUID: String, charUUID: String) {
    TODO("Not yet implemented")
  }

  override fun unsubscribe(serviceUUID: String, charUUID: String) {
    TODO("Not yet implemented")
  }

  override fun disconnect() {
    TODO("Not yet implemented")
  }

  override fun subscribeToEvents(listener: (Event) -> Unit) {
    TODO("Not yet implemented")
  }

  override fun onScanStartedFailed(errorCode: Int) {
    throw ScanStartFailedException("Failed to start scanning with android error code: $errorCode")
  }

  @SuppressLint("MissingPermission")
  override fun onDeviceFound(device: BluetoothDevice, scanRecord: ScanRecord?) {
    DeviceFoundEvent(device.name, device.address, scanRecord?.txPowerLevel)
  }

  override fun onDeviceConnected(device: BluetoothDevice) {
    TODO("Not yet implemented")
  }

  override fun onDeviceDisconnected(isManualDisconnect: Boolean) {
    TODO("Not yet implemented")
  }

  override fun onWriteFailed(device: BluetoothDevice?, charUUID: UUID, err: Int) {
    TODO("Not yet implemented")
  }

  override fun onWriteSuccess(device: BluetoothDevice?, charUUID: UUID) {
    TODO("Not yet implemented")
  }

  override fun onServicesDiscovered(serviceUuids: List<UUID>) {
    TODO("Not yet implemented")
  }

  override fun onServicesDiscoveryFailed(errorCode: Int) {
    TODO("Not yet implemented")
  }

  override fun onRequestMTUSuccess(mtu: Int) {
    TODO("Not yet implemented")
  }

  override fun onRequestMTUFailure(errorCode: Int) {
    TODO("Not yet implemented")
  }

  override fun onReadSuccess(charUUID: UUID, value: ByteArray?) {
    TODO("Not yet implemented")
  }

  override fun onReadFailure(charUUID: UUID?, err: Int) {
    TODO("Not yet implemented")
  }

  override fun onSubscriptionSuccess(charUUID: UUID) {
    TODO("Not yet implemented")
  }

  override fun onSubscriptionFailure(charUUID: UUID, err: Int) {
    TODO("Not yet implemented")
  }

  override fun onNotificationReceived(charUUID: UUID, value: ByteArray?) {
    TODO("Not yet implemented")
  }

  override fun onClosed() {
    TODO("Not yet implemented")
  }

  override fun onException(exception: BLEException) {
    TODO("Not yet implemented")
  }
}
