package io.mosip.tuvali.ble.peripheral

import io.mosip.tuvali.ble.peripheral.state.exception.StateHandlerException
import java.util.UUID

interface IPeripheralListener {
  fun onAdvertisementStartSuccessful()
  fun onAdvertisementStartFailed(errorCode: Int)
  fun onReceivedWrite(uuid: UUID, value: ByteArray?)
  fun onDeviceConnected()
  fun onDeviceNotConnected(isManualDisconnect: Boolean, isConnected: Boolean)
  fun onSendDataNotified(uuid: UUID, isSent: Boolean)
  fun onClosed()
  fun onMTUChanged(mtu: Int)
  fun onException(e: StateHandlerException)
}
