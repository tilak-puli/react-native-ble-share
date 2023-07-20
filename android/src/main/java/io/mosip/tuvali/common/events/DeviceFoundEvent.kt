package io.mosip.tuvali.common.events

class DeviceFoundEvent(val name: String, val address: String, val powerLevel: Int?): Event
