package com.ble.peripheral

import android.bluetooth.BluetoothGattService
import android.content.Context
import android.os.HandlerThread
import android.os.Process
import com.ble.peripheral.impl.Controller
import com.ble.peripheral.state.IMessageSender
import com.ble.peripheral.state.StateHandler
import com.ble.peripheral.state.message.AdvertisementStartMessage
import com.ble.peripheral.state.message.SetupGattServiceMessage
import java.util.*

class Peripheral(context: Context, peripheralListener: IPeripheralListener) {
  private val controller: Controller =
    Controller(context)
  private lateinit var messageSender: IMessageSender

  private val handlerThread: HandlerThread = object : HandlerThread("PeripheralHandlerThread", Process.THREAD_PRIORITY_DEFAULT) {
    override fun onLooperPrepared() {
      messageSender = StateHandler(this.looper, controller, peripheralListener)
      controller.setHandlerThread(messageSender)
    }
  }

  init {
    //TODO: Call quit once instance is done
    handlerThread.start()
  }

  fun start(serviceUUID: UUID, scanRespUUID: UUID, advPayload: String, scanRespPayload: String) {
    val advStartMsg = AdvertisementStartMessage(
      serviceUUID,
      scanRespUUID,
      advPayload,
      scanRespPayload
    )
    messageSender.sendMessage(advStartMsg)
  }

  fun setupService(service: BluetoothGattService) {
    val setupServiceMsg = SetupGattServiceMessage(service)
    messageSender.sendMessage(setupServiceMsg)
  }
}
