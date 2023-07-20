package io.mosip.tuvali.ble.central.state.message

import android.bluetooth.le.ScanFilter

class ScanStartMessage(val scanFilter: ScanFilter): IMessage(
  CentralStates.SCAN_START
)
