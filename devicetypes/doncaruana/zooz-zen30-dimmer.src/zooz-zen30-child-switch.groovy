metadata {
	definition(name: "Zooz Zen30 Child Switch", namespace: "doncaruana", author: "Don Caruana", mnmn: "SmartThings", ocfDeviceType: "oic.d.switch", vid: "generic-switch",  runLocally: false, executeCommandsLocally: false) {
		capability "Actuator"
		capability "Switch"
		capability "Outlet"
		capability "Light"
	}

	simulator {
	}

	// tile definitions
	tiles(scale: 2) {
		multiAttributeTile(name: "switch", type: "switch", width: 6, height: 4, canChangeIcon: true) {
			tileAttribute("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label: '${name}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#00A0DC"
				attributeState "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			}
		}

		main "switch"
		details(["switch"])
	}
}

def installed() {
	configure()
}

def updated() {
	configure()
}

def configure() {
	// Device-Watch simply pings if no device events received for checkInterval duration of 32min
	//sendEvent(name: "checkInterval", value: 30 * 60 + 2 * 60, displayed: false, data: [protocol: "zwave", hubHardwareId: parent.hubID, offlinePingable: "1"])
    parent.refreshChild()
}

def handleZWave(physicalgraph.zwave.commands.basicv1.BasicReport cmd) {
	switchEvents(cmd)
}

def handleZWave(physicalgraph.zwave.commands.basicv1.BasicSet cmd) {
	switchEvents(cmd)
}

def handleZWave(physicalgraph.zwave.commands.switchbinaryv1.SwitchBinaryReport cmd) {
	switchEvents(cmd)
}

def switchEvents(physicalgraph.zwave.Command cmd) {
	def value = (cmd.value ? "on" : "off")
	sendEvent(name: "switch", value: value, descriptionText: "$device.displayName was turned $value")
}

def handleZWave(physicalgraph.zwave.Command cmd) {
	sendEvent(descriptionText: "$device.displayName: $cmd", isStateChange: true, displayed: false)
}

def on() {
	parent.childOn(device.deviceNetworkId)
}

def off() {
	parent.childOff(device.deviceNetworkId)
}

/**
 * PING is used by Device-Watch in attempt to reach the Device
 * */
def ping() {
	parent.refreshChild()
}

def refresh() {
    parent.refreshChild()
}

