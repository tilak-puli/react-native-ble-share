package io.mosip.tuvali.central

import io.mosip.tuvali.common.events.Event

interface CentralController {
  fun startScanning(serviceUUID: String?)
  fun stopScanning()
  fun connect(deviceAddress: String)
  fun discoverServices()
  fun requestMTU(mtu: Int)
  fun sendData(serviceUUID: String, charUUID: String, data: String)
  fun subscribe(serviceUUID: String, charUUID: String)
  fun unsubscribe(serviceUUID: String, charUUID: String)
  fun disconnect()

  fun subscribeToEvents(listener: (Event) -> Unit)
}
