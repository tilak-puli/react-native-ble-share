package com.ble.peripheral

import java.util.UUID

interface IPeripheralListener {
  fun onAdvertisementStartSuccessful()
  fun onAdvertisementStartFailed(errorCode: Int)
  fun onReceivedWrite(uuid: UUID, value: ByteArray?)
}
