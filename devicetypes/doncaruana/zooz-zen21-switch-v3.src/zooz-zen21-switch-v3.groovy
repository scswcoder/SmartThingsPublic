/**
 *  Zooz Zen21 Switch v3
 *
 *  2019-02-12 - Initial release
 *  
 *  Supported Command Classes
 *  
 *         Association v2
 *         Association Group Information
 *         Basic
 *         Configuration
 *         Device Reset Local
 *         Manufacturer Specific
 *         Powerlevel
 *         Switch_binary
 *         Version v2
 *         ZWavePlus Info v2
 *         Multi-Channel Association
 *         Transport Service
 *         Security 2 (S2)
 *         Supervision
 *         Firmware Update Metadata
 *
 *   Parm Size Description                                   Value
 *      1    1 Invert Switch                                 0 (Default)-Upper paddle turns light on, 1-Lower paddle turns light on
 *      2    1 LED Indicator                                 0 (Default)-LED is on when light is OFF, 1-LED is on when light is ON, 2-LED is always off, 3-LED is always on
 *      3    1 Auto Turn-Off                                 0 (Default)-Timer disabled, 1-Timer enabled; Set time in parameter 4
 *      4    4 Turn-off Timer                                60 (Default)-Time in minutes after turning on to automatically turn off (1-65535 minutes)
 *      5    1 Auto Turn-On                                  0 (Default)-Timer disabled, 1-Timer enabled; Set time in parameter 6
 *      6    4 Turn-on Timer                                 60 (Default)-Time in minutes after turning off to automatically turn on (1-65535 minutes)
 *      8    1 Power Restore                                 2 (Default)-Remember state from pre-power failure, 0-Off after power restored, 1-On after power restore
 */

metadata {
	definition (name: "Zooz Zen21 Switch v3", namespace: "doncaruana", author: "Don Caruana", ocfDeviceType: "oic.d.switch", mnmn: "SmartThings", vid: "generic-switch") {
		capability "Actuator"
		capability "Indicator"
		capability "Switch"
		capability "Polling"
		capability "Refresh"
		capability "Sensor"
		capability "Health Check"
		capability "Light"

//zw:L type:1001 mfr:027A prod:B111 model:1E1C ver:3.00 zwv:5.03 lib:03 cc:5E,25,85,8E,59,55,86,72,5A,73,70,6C,9F,7A role:05 ff:8700 ui:8704

	fingerprint mfr:"027A", prod:"B111", model:"1E1C", deviceJoinName: "Zooz Zen21 Switch v3"
	fingerprint deviceId:"0x1001", inClusters: "0x5E,0x59,0x85,0x70,0x5A,0x72,0x73,0x25,0x86,0x8E,0x55,0x6C,0x9F,0x7A"
	fingerprint cc: "0x5E,0x59,0x85,0x70,0x5A,0x72,0x73,0x25,0x86,0x8E,0x55,0x6C,0x9F,0x7A", mfr:"027A", prod:"B111", model:"1E1C", deviceJoinName: "Zooz Zen21 Switch v3"
	}

	simulator {
		status "on":  "command: 2003, payload: FF"
		status "off": "command: 2003, payload: 00"

		// reply messages
		reply "2001FF,delay 5000,2602": "command: 2603, payload: FF"
		reply "200100,delay 5000,2602": "command: 2603, payload: 00"
	}

	preferences {
		input "ledIndicator", "enum", title: "LED Indicator", description: "When Off... ", options:["on": "When On", "off": "When Off", "never": "Never", "always": "Always"], defaultValue: "off"
		input "invertSwitch", "bool", title: "Invert Switch", description: "Flip switch upside down", required: false, defaultValue: false
		input "powerRestore", "enum", title: "After Power Restore", description: "State after power restore", options:["prremember": "Remember", "proff": "Off", "pron": "On"],defaultValue: "prremember",displayDuringSetup: false
		input "autoTurnoff", "bool", title: "Auto Off", description: "Light will automatically turn off after set time", required: false, defaultValue: false
		input "autoTurnon", "bool", title: "Auto On", description: "Light will automatically turn on after set time", required: false, defaultValue: false
		input "offTimer", "number", title: "Off Timer", description: "Time in minutes to automatically turn off", required: false, defaultValue: 60, range: "1..65535"
		input "onTimer", "number", title: "On Timer", description: "Time in minutes to automatically turn on", required: false, defaultValue: 60, range: "1..65535"
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
				attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
			}
		}
		standardTile("indicator", "device.indicatorStatus", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "when off", action:"indicator.indicatorWhenOff", icon:"st.indicators.lit-when-off"
			state "when on", action:"indicator.indicatorWhenOn", icon:"st.indicators.lit-when-on"
			state "never", action:"indicator.indicatorNever", icon:"st.indicators.never-lit"
		}


		standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}

		main "switch"
		details(["switch","indicator","refresh"])
	}
}

private getCommandClassVersions() {
	[
		0x5E: 2,  // Z-Wave Plus Info
		0x59: 1,  // Association Group Information
		0x85: 2,  // Association
		0x70: 1,  // Configuration
		0x5A: 1,  // Device Reset Locally
		0x72: 2,  // Manufacturer Specific
		0x73: 1,  // Powerlevel
		0x25: 1,  // Binary Switch
		0x86: 1,  // Version
		0x8E: 1,  // Multi Channel Association
		0x55: 1,  // Transport Service
		0x6C: 1,  // Supervision
		0x9F: 1,  // S2 (Security 2)
		0x7A: 1,  // Firmware Update Meta Data
	]
}

def installed() {
	def cmds = []

	cmds << mfrGet()
	cmds << zwave.versionV1.versionGet().format()
	cmds << parmGet(1)
	cmds << parmGet(2)
	cmds << parmGet(3)
	cmds << parmGet(4)
	cmds << parmGet(5)
	cmds << parmGet(6)
	cmds << parmGet(8)
	cmds << zwave.basicV1.basicSet(value: 0xFF).format()
	// Device-Watch simply pings if no device events received for 32min(checkInterval)
	sendEvent(name: "checkInterval", value: 2 * 15 * 60 + 2 * 60, displayed: false, data: [protocol: "zwave", hubHardwareId: device.hub.hardwareID])
	return response(delayBetween(cmds,200))
}

def updated(){
	// These are needed when parameter defaults are null or non-workable numbers. They are set to the device defaults
	def setOffTimer = 60
	if (offTimer) {setOffTimer = offTimer}
	def setOnTimer = 60
	if (onTimer) {setOnTimer = onTimer}
	def commands = []
	if (getDataValue("MSR") == null) {
		def level = 99
		commands << mfrGet()
		commands << zwave.versionV1.versionGet().format()
		commands << zwave.basicV1.basicSet(value: level).format()
		commands << zwave.switchMultilevelV1.switchMultilevelGet().format()
	}
	def setPowerRestore = powerRestore == "prremember" ? 2 : powerRestore == "proff" ? 0 : 1
	def setAutoTurnon = autoTurnon == true ? 1 : 0
	def setAutoTurnoff = autoTurnoff == true ? 1 : 0
	def setInvertSwitch = invertSwitch == true ? 1 : 0
	def setLedIndicator = 0
	switch (ledIndicator) {
		case "off":
			setLedIndicator = 0
			break
		case "on":
			setLedIndicator = 1
			break
		case "never":
			setLedIndicator = 2
			break
		case "always":
			setLedIndicator = 3
			break
		default:
			setLedIndicator = 0
			break
	}
	//parmset takes the parameter number, it's size, and the value - in that order
	commands << parmSet(8, 1, setPowerRestore)
	commands << parmSet(6, 4, setOnTimer)
	commands << parmSet(5, 1, setAutoTurnon)
	commands << parmSet(4, 4, setOffTimer)
	commands << parmSet(3, 1, setAutoTurnon)
	commands << parmSet(2, 1, setLedIndicator)
	commands << parmSet(1, 1, setInvertSwitch)

	commands << parmGet(8)
	commands << parmGet(6)
	commands << parmGet(5)
	commands << parmGet(4)
	commands << parmGet(3)
	commands << parmGet(2)
	commands << parmGet(1)
	// Device-Watch simply pings if no device events received for 32min(checkInterval)
	sendEvent(name: "checkInterval", value: 2 * 15 * 60 + 2 * 60, displayed: false, data: [protocol: "zwave", hubHardwareId: device.hub.hardwareID])
		return response(delayBetween(commands, 500))
}

def parse(String description) {
	def result = null
	def cmd = zwave.parse(description, commandClassVersions)
	if (cmd) {
		result = createEvent(zwaveEvent(cmd))
	}
	if (result?.name == 'hail' && hubFirmwareLessThan("000.011.00602")) {
		result = [result, response(zwave.basicV1.basicGet())]
		log.debug "Was hailed: requesting state update"
	} else {
		log.debug "Parse returned ${result?.descriptionText}"
	}
	return result
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd) {
	[name: "switch", value: cmd.value ? "on" : "off"]
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicSet cmd) {
	[name: "switch", value: cmd.value ? "on" : "off"]
}

def zwaveEvent(physicalgraph.zwave.commands.switchbinaryv1.SwitchBinaryReport cmd) {
	[name: "switch", value: cmd.value ? "on" : "off"]
}


def zwaveEvent(physicalgraph.zwave.commands.configurationv1.ConfigurationReport cmd) {
	def name = ""
	def value = ""
	def reportValue = cmd.configurationValue[0]
	log.debug "---CONFIGURATION REPORT V1--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
	switch (cmd.parameterNumber) {
		case 1:
			name = "topoff"
			value = reportValue == 1 ? "true" : "false"
			break
		case 2:
			switch (reportValue) {
				case 0:
					value = "off"
					break
				case 1:
					value = "on"
					break
				case 2:
					value = "never"
					break
				case 3:
					value = "always"
					break
				default:
					value = "off"
					break
			}
			name = "ledfollow"
			break
		case 3:
			name = "autooff"
			value = reportValue == 1 ? "true" : "false"
			break
		case 4:
			name = "autoofftimer"
            value = cmd.configurationValue[3] + (cmd.configurationValue[2] * 0x100) + (cmd.configurationValue[1] * 0x10000) + (cmd.configurationValue[0] * 0x1000000)
			break
		case 5:
			name = "autoon"
			value = reportValue == 1 ? "true" : "false"
			break
		case 6:
			name = "autoontimer"
            value = cmd.configurationValue[3] + (cmd.configurationValue[2] * 0x100) + (cmd.configurationValue[1] * 0x10000) + (cmd.configurationValue[0] * 0x1000000)
			break
		case 8:
			name = "afterfailure"
			switch (reportValue) {
				case 0:
					value = "off"
					break
				case 1:
					value = "on"
					break
				case 2:
					value = "remember"
					break
				default:
					break
			}
		default:
			break
	}
	createEvent([name: name, value: value])
}


def zwaveEvent(physicalgraph.zwave.commands.hailv1.Hail cmd) {
	createEvent([name: "hail", value: "hail", descriptionText: "Switch button was pressed", displayed: false])
}

def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd) {
	def manufacturerCode = String.format("%04X", cmd.manufacturerId)
	def productTypeCode = String.format("%04X", cmd.productTypeId)
	def productCode = String.format("%04X", cmd.productId)
	def msr = manufacturerCode + "-" + productTypeCode + "-" + productCode
	updateDataValue("MSR", msr)
	updateDataValue("Manufacturer", "Zooz")
	updateDataValue("Manufacturer ID", manufacturerCode)
	updateDataValue("Product Type", productTypeCode)
	updateDataValue("Product Code", productCode)
	createEvent([descriptionText: "$device.displayName MSR: $msr", isStateChange: false])
}

def zwaveEvent(physicalgraph.zwave.commands.crc16encapv1.Crc16Encap cmd) {
	def versions = commandClassVersions
	def version = versions[cmd.commandClass as Integer]
	def ccObj = version ? zwave.commandClass(cmd.commandClass, version) : zwave.commandClass(cmd.commandClass)
	def encapsulatedCommand = ccObj?.command(cmd.command)?.parse(cmd.data)
	if (encapsulatedCommand) {
		zwaveEvent(encapsulatedCommand)
	}
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	// Handles all Z-Wave commands we aren't interested in
	[:]
}

def on() {
	delayBetween([
		zwave.basicV1.basicSet(value: 0xFF).format(),
		zwave.switchBinaryV1.switchBinaryGet().format()
	])
}

def off() {
	delayBetween([
		zwave.basicV1.basicSet(value: 0x00).format(),
		zwave.switchBinaryV1.switchBinaryGet().format()
	])
}

def poll() {
	zwave.switchBinaryV1.switchBinaryGet().format()
}

def refresh() {
	log.debug "refresh() is called"
	def commands = []
		if (getDataValue("MSR") == null) {
			commands << mfrGet()
			commands << zwave.versionV1.versionGet().format()
		}
		commands << zwave.switchBinaryV1.switchBinaryGet().format()
}

/**
 * PING is used by Device-Watch in attempt to reach the Device
 * */
def ping() {
	refresh()
}

def parmSet(parmnum, parmsize, parmval) {
	return zwave.configurationV1.configurationSet(scaledConfigurationValue: parmval, parameterNumber: parmnum, size: parmsize).format()
}

def parmGet(parmnum) {
	return zwave.configurationV1.configurationGet(parameterNumber: parmnum).format()
}

def mfrGet() {
	return zwave.manufacturerSpecificV2.manufacturerSpecificGet().format()
}


def zwaveEvent(physicalgraph.zwave.commands.versionv1.VersionReport cmd) {	
	updateDataValue("applicationVersion", "${cmd.applicationVersion}")
	updateDataValue("applicationSubVersion", "${cmd.applicationSubVersion}")
	updateDataValue("zWaveLibraryType", "${cmd.zWaveLibraryType}")
	updateDataValue("zWaveProtocolVersion", "${cmd.zWaveProtocolVersion}")
	updateDataValue("zWaveProtocolSubVersion", "${cmd.zWaveProtocolSubVersion}")
}

def zwaveEvent(physicalgraph.zwave.commands.versionv1.VersionCommandClassReport cmd) {
	log.debug "vccr"
	def rcc = ""
	log.debug "version: ${cmd.commandClassVersion}"
	log.debug "class: ${cmd.requestedCommandClass}"
	rcc = Integer.toHexString(cmd.requestedCommandClass.toInteger()).toString() 
	log.debug "${rcc}"
	if (cmd.commandClassVersion > 0) {log.debug "0x${rcc}_V${cmd.commandClassVersion}"}
}
