package io.mosip.tuvali.central

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import io.mosip.tuvali.rnModule.RNEventEmitter
import io.mosip.tuvali.rnModule.RNEventMapper

class RNCentralModule (
  private val eventEmitter: RNEventEmitter,
  private val centralController: CentralController,
  reactContext: ReactApplicationContext
) : ReactContextBaseJavaModule(reactContext) {

  init {
    centralController.subscribeToEvents {
      eventEmitter.emitEvent(RNEventMapper.toMap(it))
    }
  }

  @ReactMethod
  fun startScanning(serviceUUID: String?) {
    centralController.startScanning(serviceUUID)
  }

  @ReactMethod
  fun stopScanning() {
    centralController.stopScanning()
  }

  @ReactMethod
  fun connect(deviceAddress: String) {
    centralController.connect(deviceAddress)
  }

  @ReactMethod
  fun discoverServices() {
    centralController.discoverServices()
  }

  @ReactMethod
  fun requestMTU(mtu: Int) {
    centralController.requestMTU(mtu)
  }

  @ReactMethod
  fun sendData(serviceUUID: String, charUUID: String, data: String) {
    centralController.sendData(serviceUUID, charUUID, data)
  }

  @ReactMethod
  fun subscribe(serviceUUID: String, charUUID: String) {
    centralController.subscribe(serviceUUID, charUUID)
  }

  @ReactMethod
  fun unsubscribe(serviceUUID: String, charUUID: String) {
    centralController.unsubscribe(serviceUUID, charUUID)
  }

  @ReactMethod
  fun disconnect() {
    centralController.disconnect()
  }

  override fun getName(): String {
    return "CentralModule"
  }
}
