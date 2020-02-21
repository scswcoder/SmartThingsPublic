/**
 *  Zooz Zen30 Dimmer
 *
 * Revision History:
 * 2020-01-09 - Initial release
 * 2020-01-10 - Fix for several parameters (wrong byte length), remove paddle inverse/toggle control (not in firmware this release)
 * 2020-01-11 - Moved child tile above refresh, added preference to have paddle control relay simultaneously
 * 2020-02-03 - Fix for zero value in ramp rate 
 * 2020-02-21 - Fix for power restore setting for relay
 *
 * Notes:
 *		1) This device has 21 scene buttons. 
 *		Presses are 1,2,3,4,5,hold,release. 
 *		Upper paddle presses 1,3,5,7,9,16,17
 *		Lower paddle presses 2,4,6,8,10,18,19
 *		Relay switch presses 11,12,13,14,15,20,21
 *
 *  Supported Command Classes
 *   V2: Association
 *   V1: Association Group Information (AGI)
 *   V2: Basic   (ST Max V1)
 *   V1: Binary Switch
 *   V3: Central Scene   (ST Max V1)
 *   V1: Configuration
 *   V1: Device Reset Locally
 *   V4: Firmware Update Meta Data   (ST Max V2)
 *   V2: Manufacturer Specific
 *   V3: Multi Channel Association   (ST Max V2)
 *   V4: Multi Channel   (ST Max V3)
 *   V4: Multilevel Switch   (ST Max V3)
 *   V1: Powerlevel
 *   V1: Security 2   (ST Max V1)
 *   V1: Supervision
 *   V2: Transport Service   (ST Max V1)
 *   V3: Version   (ST Max V1)
 *   V2: Z-Wave Plus Info
 *  
 *   Parm Size Description                                   Value
 *      1    1 LED Indicator for Dimmer                      0 (Default)-LED is on when light is OFF, 1-LED is on when light is ON, 2-LED is always off, 3-LED is always on
 *      2    1 LED Indicator for Relay                       0 (Default)-LED is on when light is OFF, 1-LED is on when light is ON, 2-LED is always off, 3-LED is always on
 *      3    1 Color for Dimmer LED                          0 (Default)-white, 1-blue, 2-green, 3-red
 *      4    1 Color for Relay LED                           0 (Default)-white, 1-blue, 2-green, 3-red
 *      5    1 Brightness for Dimmer LED                     1 (Default)-medium (60%), 0-bright (100%), 2-low (30%)
 *      6    1 Brightness for Relay LED                      1 (Default)-medium (60%), 0-bright (100%), 2-low (30%)
 *      7    1 LED Scene Indicator                           1 (Default)-LEDs disabled for indicating scene, 0-LEDs reflect scene selected
 *      8    4 Turn-off Timer for Dimmer                     0 (Default)-Time in minutes after turning on to automatically turn off, 0 disabled, (1-65535 minutes)
 *      9    4 Turn-on Timer for Dimmer                      0 (Default)-Time in minutes after turning off to automatically turn on, 0 disabled, (1-65535 minutes)
 *     10    4 Turn-off Timer for Relay                      0 (Default)-Time in minutes after turning on to automatically turn off, 0 disabled, (1-65535 minutes)
 *     11    4 Turn-on Timer for Relay                       0 (Default)-Time in minutes after turning off to automatically turn on, 0 disabled, (1-65535 minutes)
 *     12    1 Power Restore                                 0-both off, 1-dimmer off/relay on, 2-dimmer on/relay off, 3 (Default)-both Remember state from pre-power failure,
 * 								4-remember dimmer/relay on, 5-remember dimmer/relay off, 6-dimmer on/remember relay, 7-dimmer off/remember relay, 8-both on
 *     13    1 Physical Ramp Rate Control                    1 (Default)-Ramp rate in seconds to reach full brightness or off (1-99 seconds)
 *     14    1 Minimum Brightness                            1 (Default)-Minimum brightness that light will set (1-99%)
 *     15    1 Maximum Brightness                            99 (Default)-Maximum brightness that light will set (1-99%)
 *     17    1 Double Tap                                    0 (Default)-Light will go to full brightness with double tap, 1-light will go to max set in Parameter 11 with double tap
 *     18    1 Disable Double Tap                            0 (Default)-Double tap to full/max brightness, 1-double tap disabled-single tap to last brightness, 2-double tap disable-single tap to full/max brightness
 *     19    1 Disable Paddle                                1 (Default)-Paddle is used for local control, 0-Paddle single tap disabled, 2-Disable all control of Paddle
 *     20    1 Disable switch                                1 (Default)-switch is used for local control, 0-switch single tap disabled, 2-Disable all control of switch
 *     21    1 Physical Dimming Speed                        4 (Default)-Time in seconds to from 0 to 100% brightness, 1-99-dimming time
 *     22    1 Zwave Ramp Type                               1 (Default)-Zwave ramp speed set through command class, 0-Zwave ramp speed matches parameter 9
 *     23    1 Default Brightness                            0 (Default)-Last brightness level, 1-99-custom brightness level
 */

metadata {
	definition (name: "Zooz Zen30 Dimmer", namespace: "doncaruana", author: "Don Caruana",  ocfDeviceType: "oic.d.light", mnmn: "SmartThings", vid: "generic-dimmer", runLocally: false, executeCommandsLocally: false) {
		capability "Actuator"
		capability "Switch"
		capability "Switch Level"
		capability "Refresh"
		capability "Configuration"
		capability "Sensor"
		capability "Button"
		capability "Zw Multichannel"
			command "tapDownHold"
			command "tapDownRelease"
			command "tapDown1"
			command "tapDown2"
			command "tapDown3"
			command "tapDown4"
			command "tapDown5"
			command "tapUpHold"
			command "tapUpRelease"
			command "tapUp1"
			command "tapUp2"
			command "tapUp3"
			command "tapUp4"
			command "tapUp5"
			command "tapRBHold"
			command "tapRBRelease"
			command "tapRB1"
			command "tapRB2"
			command "tapRB3"
			command "tapRB4"
			command "tapRB5"

		fingerprint mfr: "027A", prod: "A000", model: "A008", deviceJoinName: "Zooz Zen30" //US
	}

	simulator {
		status "on":  "command: 2003, payload: FF"
		status "off": "command: 2003, payload: 00"
		reply "8E010101,delay 800,6007": "command: 6008, payload: 4004"
		reply "8505": "command: 8506, payload: 02"
		reply "59034002": "command: 5904, payload: 8102003101000000"
		reply "6007":  "command: 6008, payload: 0002"
		reply "600901": "command: 600A, payload: 10002532"
		reply "600902": "command: 600A, payload: 210031"
	}

	preferences {
		input "ledIndicator", "enum", title: "Dimmer LED Indicator", description: "When Off... ", options:["on": "When On", "off": "When Off", "never": "Never", "always": "Always"], defaultValue: "off"
		input "ledIndicatorRB", "enum", title: "Relay LED Indicator", description: "When Off... ", options:["on": "When On", "off": "When Off", "never": "Never", "always": "Always"], defaultValue: "off"
		input "ledColor", "enum", title: "Dimmer LED Color", description: "White", options:["white": "White", "blue": "Blue", "green": "Green", "red": "Red"], defaultValue: "white"
		input "ledColorRB", "enum", title: "Relay LED Color", description: "White", options:["white": "White", "blue": "Blue", "green": "Green", "red": "Red"], defaultValue: "white"
		input "ledBrightness", "enum", title: "Dimmer LED Brightness", description: "Medium (60%)... ", options:["bright": "Bright(100%)", "medium": "Medium(60%)", "low": "Low(30%)"], defaultValue: "medium"
		input "ledBrightnessRB", "enum", title: "Relay LED Brightness", description: "Medium (60%)... ", options:["bright": "Bright(100%)", "medium": "Medium(60%)", "low": "Low(30%)"], defaultValue: "medium"
		input "ledScene", "bool", title: "Disable LED Scene Indicator", description: "LEDs indicate scene", required: false, defaultValue: false
//		input "paddleControl", "enum", title: "Paddle Control", description: "Standard, Inverted, or Toggle", options:["std": "Standard", "invert": "Invert", "toggle": "Toggle"], defaultValue: "std"
		input "rampRate", "number", title: "Ramp Rate", description: "Seconds to reach full brightness (0-99)", required: false, defaultValue: 1, range: "0..99"
		input "offTimer", "number", title: "Dimmer Off Timer (0 Disables)", description: "Time in minutes to automatically turn off", required: false, defaultValue: 0, range: "0..65535"
		input "onTimer", "number", title: "Dimmer On Timer (0 Disables)", description: "Time in minutes to automatically turn on", required: false, defaultValue: 0, range: "0..65535"
		input "offTimerRB", "number", title: "Relay Off Timer (0 Disables)", description: "Time in minutes to automatically turn off", required: false, defaultValue: 0, range: "0..65535"
		input "onTimerRB", "number", title: "Relay On Timer (0 Disables)", description: "Time in minutes to automatically turn on", required: false, defaultValue: 0, range: "0..65535"
		input "powerRestore", "enum", title: "Dimmer After Power Restore", description: "State after power restore", options:["prremember": "Remember", "proff": "Off", "pron": "On"],defaultValue: "prremember",displayDuringSetup: false
		input "powerRestoreRB", "enum", title: "Relay After Power Restore", description: "State after power restore", options:["prrememberRB": "Remember", "proffRB": "Off", "pronRB": "On"],defaultValue: "prrememberRB",displayDuringSetup: false
		input "physDimspeed", "number", title: "Physical Dimming Speed", description: "Dimming time from 0 to 99", required: false, defaultValue: 4, range: "1..99"
		input "maxBright", "number", title: "Maximum Brightness", description: "Maximum brightness that the light can go to", required: false, defaultValue: 99, range: "1..99"
		input "minBright", "number", title: "Minimum Brightness", description: "Minimum brightness that the light can go to", required: false, defaultValue: 1, range: "1..99"
		input "doubleTap", "enum", title: "Double Tap Behavior", description: "Double/Single Tap behavior", options:["tap2full":"Double Tap to Full brightness", "tap2max": "Double Tap to Custom Max brightness", "tap1last": "Double Tap disabled, Single tap to last brightness", "tap1max" : "Double Tap disabled, Single tap to full brightness"],defaultValue: "tap1full",displayDuringSetup: false
		input "physDefBright", "number", title: "Physical On Brightness", description: "0 for last level or 1-99", required: false, defaultValue: 0, range: "0..99"
		input "zwaveOntype", "enum", title: "Zwave Ramp Rate Control", description: "Software Controlled", options:["zwcontrolphys": "Matches Physical Rate", "zwcontrollog": "Controlled by Ramp Type and Ramp Rate parameters"], required: false, defaultValue: zwcontrollog
		input "zwaveramptype","enum",title: "Zwave Ramp Type", description: "Instant, Seconds, Minutes, Switch Default", options:["zwinstant": "Instant", "zwsec": "Seconds", "zwmin": "Minutes", "zwdef" : "Default"],defaultValue: "zwdef",displayDuringSetup: false
		input "zwaverampspeed", "number", title: "Zwave Ramp Rate", description: "Time in seconds or minutes to reach brightness setting (as set in Zwave Ramp Type)", required: false, defaultValue: 4, range: "1..127"
		input "localControl", "enum", title: "Paddle Local Control", description: "Local paddle control enabled", options:["lcOn": "Local and Zwave On/Off enabled", "lcOff": "Disable local control", "lcAllOff": "Local and Zwave On/Off disabled"], defaultValue: "lcOn",displayDuringSetup: false
		input "localControlRB", "enum", title: "Relay Local Control", description: "Local relay control enabled", options:["lcOnRB": "Local and Zwave On/Off enabled", "lcOffRB": "Disable local control", "lcAllOffRB": "Local and Zwave On/Off disabled"], defaultValue: "lcOnRB",displayDuringSetup: false
		input "crossControl", "bool", title: "Allow paddle to control relay", description: "Allow paddle to control relay", required: false, defaultValue: false
		input (
					type: "paragraph",
					element: "paragraph",
					title: "Configure Paddle Association Groups:",
					description: "Devices in association group 2 will receive Basic Set commands directly from the switch when it is turned on or off. Use this to control another device as if it was connected to this switch.\n\n" +"Devices are entered as a comma delimited list of IDs in hexadecimal format."
					)
		input (
					name: "requestedGroup2",
					title: "Association Group 2 Members (Max of 5):",
					type: "text",
					required: false
					)
		input (
					type: "paragraph",
					element: "paragraph",
					title: "Configure Relay Association Groups:",
					description: "Devices in association group 3 will receive Basic Set commands directly from the switch when it is turned on or off. Use this to control another device as if it was connected to this switch.\n\n" +"Devices are entered as a comma delimited list of IDs in hexadecimal format."
					)
		input (
					name: "requestedGroup3",
					title: "Association Group 3 Members (Max of 5):",
					type: "text",
					required: false
					)
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#00a0dc", nextState:"turningOff"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"turningOn"
				attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#00a0dc", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"turningOn"
			}
			tileAttribute ("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action:"switch level.setLevel"
			}
		}
		childDeviceTiles("all")
		standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
				state "default", label: '', action: "refresh.refresh", icon: "st.secondary.refresh"
		}
	}
}

def installed() {
	def queryCmds = []
	def delay = 200
	def zwInfo = getZwaveInfo()
	def endpointCount = zwInfo.epc as Integer
	def endpointDescList = zwInfo.ep ?: []

	log.debug "zwInfo: ${zwInfo}"
	log.debug "device.rawDescription: ${device.rawDescription}"

	log.debug "endpointCount: ${endpointCount}"
	log.debug "endpointDescList: ${endpointDescList}"
	log.debug "endpointDescList.size: ${endpointDescList.size()}"


	// This is needed until getZwaveInfo() parses the 'ep' field
	if (endpointCount && !zwInfo.ep && device.hasProperty("rawDescription")) {
		try {
			def matcher = (device.rawDescription =~ /epc:(\[.*?\])/)  // extract 'ep' field
			endpointDescList = util.parseJson(matcher[0][1].replaceAll("'", '"'))
		} catch (Exception e) {
			//log.warn "couldn't extract ep from rawDescription"
		}
	}

		log.debug "endpointDescList: ${endpointDescList}"

	if (zwInfo.zw.contains("s")) {
		// device was included securely
		state.sec = true
	}

	if (endpointCount > 1 && endpointDescList.size() == 1) {
		// This means all endpoints are identical
		endpointDescList *= endpointCount
	}

	addChildSwitchDevice()
	sendEvent(name: "numberOfButtons", value: 21, displayed: false)
    refreshChild()
}

private addChildSwitchDevice() {
	def componentLabel
	componentLabel = "$device.displayName Relay"
	String dni = "${device.deviceNetworkId}-ep1"
	addChildDevice("smartthings","child switch", dni, device.hub.id,
		[completedSetup: true, label: "${componentLabel}", isComponent: false])
	log.debug "Endpoint 1 (Zen30 Relay Switch Endpoint) added as $componentLabel"
}

private getCommandClassVersions() {
	[
		0x85: 2,  // Association
		0x59: 1,  // Association Group Information (AGI)
		0x20: 1,  // Basic
		0x25: 1,  // Binary Switch
		0x5b: 1,  // Central Scene
		0x70: 1,  // Configuration
		0x5a: 1,  // Device Reset Locally
		0x7a: 2,  // Firmware Update Meta Data
		0x72: 2,  // Manufacturer Specific
		0x8e: 2,  // Multi Channel Association
		0x60: 3,  // Multi Channel
		0x26: 3,  // Multilevel Switch
		0x73: 1,  // Powerlevel
		0x9f: 1,  // Security 2
		0x6c: 1,  // Supervision
		0x55: 1,  // Transport Service
		0x86: 1,  // Version
		0x5e: 2,  // Z-Wave Plus Info
	]
}

def parse(String description) {
	// UI Trick to make 99%, which is actually the platform limit, show 100% on the control
	if (description.indexOf('command: 2603, payload: 63 63 00') > -1) {
		description = description.replaceAll('payload: 63 63 00','payload: 64 64 00')
	}
	if (description.indexOf('command: 2003, payload: 63') > -1) {
		description = description.replaceAll('payload: 63','payload: 64')
	}
	def result = null
	if (description.startsWith("Err")) {
			result = createEvent(descriptionText:description, isStateChange:true)
	} else if (description != "updated") {
		def cmd = zwave.parse(description, commandClassVersions)
				log.debug "parsing: ${cmd}"
		if (cmd) {
			result = zwaveEvent(cmd)
		}
	}
	log.debug("'$description' parsed to $result")
	return result
}

def uninstalled() {
	sendEvent(name: "epEvent", value: "delete all", isStateChange: true, displayed: false, descriptionText: "Delete endpoint devices")
}

def zwaveEvent(physicalgraph.zwave.commands.wakeupv1.WakeUpNotification cmd) {
	[ createEvent(descriptionText: "${device.displayName} woke up", isStateChange:true),
		response(["delay 2000", zwave.wakeUpV1.wakeUpNoMoreInformation().format()]) ]
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd) {
	if (cmd.value == 0) {
		createEvent(name: "switch", value: "off")
	} else if (cmd.value == 255) {
		createEvent(name: "switch", value: "on")
	} else {
		[ createEvent(name: "switch", value: "on"), createEvent(name: "level", value: cmd.value, unit: "%") ]
	}
}

private List loadEndpointInfo() {
	if (state.endpointInfo) {
		state.endpointInfo
	} else if (device.currentValue("epInfo")) {
		util.parseJson((device.currentValue("epInfo")))
	} else {
		[]
	}
}

def zwaveEvent(physicalgraph.zwave.commands.multichannelv3.MultiChannelEndPointReport cmd) {
	updateDataValue("endpoints", cmd.endPoints.toString())
	if (!state.endpointInfo) {
		state.endpointInfo = loadEndpointInfo()
	}
	if (state.endpointInfo.size() > cmd.endPoints) {
		cmd.endpointInfo
	}
	state.endpointInfo = [null] * cmd.endPoints
	//response(zwave.associationV2.associationGroupingsGet())
	[ createEvent(name: "epInfo", value: util.toJson(state.endpointInfo), displayed: false, descriptionText:""),
	  response(zwave.multiChannelV3.multiChannelCapabilityGet(endPoint: 1)) ]
}

def zwaveEvent(physicalgraph.zwave.commands.multichannelv3.MultiChannelCapabilityReport cmd) {
	def result = []
	def cmds = []
	if(!state.endpointInfo) state.endpointInfo = []
	state.endpointInfo[cmd.endPoint - 1] = cmd.format()[6..-1]
	if (cmd.endPoint < getDataValue("endpoints").toInteger()) {
		cmds = zwave.multiChannelV3.multiChannelCapabilityGet(endPoint: cmd.endPoint + 1).format()
	} else {
		log.debug "endpointInfo: ${state.endpointInfo.inspect()}"
	}
	result << createEvent(name: "epInfo", value: util.toJson(state.endpointInfo), displayed: false, descriptionText:"")
	if(cmds) result << response(cmds)
	result
}

def zwaveEvent(physicalgraph.zwave.commands.associationv2.AssociationGroupingsReport cmd) {
	state.groups = cmd.supportedGroupings
	if (cmd.supportedGroupings > 1) {
		[response(zwave.associationGrpInfoV1.associationGroupInfoGet(groupingIdentifier:2, listMode:1))]
	}
}

def zwaveEvent(physicalgraph.zwave.commands.associationgrpinfov1.AssociationGroupInfoReport cmd) {
	def cmds = []
	/*for (def i = 0; i < cmd.groupCount; i++) {
		def prof = cmd.payload[5 + (i * 7)]
		def num = cmd.payload[3 + (i * 7)]
		if (prof == 0x20 || prof == 0x31 || prof == 0x71) {
			updateDataValue("agi$num", String.format("%02X%02X", *(cmd.payload[(7*i+5)..(7*i+6)])))
			cmds << response(zwave.multiChannelAssociationV2.multiChannelAssociationSet(groupingIdentifier:num, nodeId:zwaveHubNodeId))
		}
	}*/
	for (def i = 2; i <= state.groups; i++) {
		cmds << response(zwave.multiChannelAssociationV2.multiChannelAssociationSet(groupingIdentifier:i, nodeId:zwaveHubNodeId))
	}
	cmds
}

def zwaveEvent(physicalgraph.zwave.commands.multichannelv3.MultiChannelCmdEncap cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x32: 3, 0x25: 1, 0x20: 1])
	if (encapsulatedCommand) {
		def formatCmd = ([cmd.commandClass, cmd.command] + cmd.parameter).collect{ String.format("%02X", it) }.join()
		if (state.enabledEndpoints.find { it == cmd.sourceEndPoint }) {
			createEvent(name: "epEvent", value: "$cmd.sourceEndPoint:$formatCmd", isStateChange: true, displayed: false, descriptionText: "(fwd to ep $cmd.sourceEndPoint)")
		}
		def childDevice = getChildDeviceForEndpoint(cmd.sourceEndPoint)
		if (childDevice) {
			log.debug "Got $formatCmd for ${childDevice.name}"
				if (formatCmd.indexOf('250300') > -1) {
					childDevices[0].sendEvent(name:"switch", value:"off")
                    sendEvent(name: "relay", value: "off")
                    state.relay = 0
			} 
				if (formatCmd.indexOf('2503FF') > -1) {
					childDevices[0].sendEvent(name:"switch", value:"on")
                    sendEvent(name: "relay", value: "on")
                    state.relay = 1
							}
						}
		}
}

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand(commandClassVersions)
	if (encapsulatedCommand) {
		state.sec = 1
		def result = zwaveEvent(encapsulatedCommand)
		result = result.collect {
			if (it instanceof physicalgraph.device.HubAction && !it.toString().startsWith("9881")) {
				response(cmd.CMD + "00" + it.toString())
			} else {
				it
			}
		}
		result
	}
}

def zwaveEvent(physicalgraph.zwave.commands.crc16encapv1.Crc16Encap cmd) {
	def versions = commandClassVersions
	// def encapsulatedCommand = cmd.encapsulatedCommand(versions)
	def version = versions[cmd.commandClass as Integer]
	def ccObj = version ? zwave.commandClass(cmd.commandClass, version) : zwave.commandClass(cmd.commandClass)
	def encapsulatedCommand = ccObj?.command(cmd.command)?.parse(cmd.data)
	if (encapsulatedCommand) {
		zwaveEvent(encapsulatedCommand)
	}
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	createEvent(descriptionText: "$device.displayName: $cmd", isStateChange: true)
}

def on() {
	// value of 255 restores previous brightness level
    if (crossControl) {relayOn()}
    turnOnOffSwitch(255)
}

def off() {
    if (crossControl) {relayOff()}
    turnOnOffSwitch(0)
}

private turnOnOffSwitch(level) {
	def dimmingDuration = state.dimDuration
	delayBetween([
			zwave.switchMultilevelV2.switchMultilevelSet(value: level, dimmingDuration: dimmingDuration).format(),
			zwave.basicV1.basicGet().format()
	],1000)
}

def refresh() {
	log.debug "refresh() is called"
	def commands = []
		if (getDataValue("MSR") == null) {
			commands << mfrGet()
			commands << zwave.versionV1.versionGet().format()
		}
	commands << zwave.switchMultilevelV1.switchMultilevelGet().format()
	delayBetween(commands,100)
}

def setLevel(value) {
	def valueaux = value as Integer
	def level = Math.max(Math.min(valueaux, 99), 0)
	if (level > 0) {
		sendEvent(name: "switch", value: "on")
	} else {
		sendEvent(name: "switch", value: "off")
	}
	sendEvent(name: "level", value: level, unit: "%")
	delayBetween ([zwave.basicV1.basicSet(value: level).format(), zwave.switchMultilevelV1.switchMultilevelGet().format()], 2000)
}

def setLevel(value, duration) {
	def valueaux = value as Integer
	def level = Math.max(Math.min(valueaux, 99), 0)
	def dimmingDuration = duration < 128 ? duration : 128 + Math.round(duration / 60)
	def getStatusDelay = duration < 128 ? (duration*1000)+2000 : (Math.round(duration / 60)*60*1000)+2000
	delayBetween ([zwave.switchMultilevelV2.switchMultilevelSet(value: level, dimmingDuration: dimmingDuration).format(),
				zwave.switchMultilevelV1.switchMultilevelGet().format()], getStatusDelay)
}

def configure() {
	commands([
		zwave.multiChannelV3.multiChannelEndPointGet()
	], 800)
}

// epCmd is part of the deprecated Zw Multichannel capability
def epCmd(Integer ep, String cmds) {
	def result
	if (cmds) {
		def header = state.sec ? "988100600D00" : "600D00"
		result = cmds.split(",").collect { cmd -> (cmd.startsWith("delay")) ? cmd : String.format("%s%02X%s", header, ep, cmd) }
	}
	result
}

// enableEpEvents is part of the deprecated Zw Multichannel capability
def enableEpEvents(enabledEndpoints) {
	state.enabledEndpoints = enabledEndpoints.split(",").findAll()*.toInteger()
	null
}

def sendCommand(endpointDevice, commands) {
	def result
	if (commands instanceof String) {
		commands = commands.split(',') as List
	}
	def endpoint = deviceEndpointNumber(endpointDevice)
	if (endpoint) {
		log.debug "${endpointDevice.deviceNetworkId} cmd: ${commands}"
		result = commands.collect { cmd ->
			if (cmd.startsWith("delay")) {
				new physicalgraph.device.HubAction(cmd)
			} else {
				new physicalgraph.device.HubAction(encap(cmd, endpoint))
			}
		}
		sendHubCommand(result, 0)
	}
}

private deviceEndpointNumber(device) {
	String dni = device.deviceNetworkId
	if (dni.size() >= 5 && dni[2..3] == "ep") {
		// Old format: 01ep2
		return device.deviceNetworkId[4..-1].toInteger()
	} else if (dni.size() >= 6 && dni[2..4] == "-ep") {
		// New format: 01-ep2
		return device.deviceNetworkId[5..-1].toInteger()
	} else {
		log.warn "deviceEndpointNumber() expected 'XX-epN' format for dni of $device"
	}
}

private getChildDeviceForEndpoint(Integer endpoint) {
	def children = childDevices
	if (children && endpoint) {
		return children.find{ it.deviceNetworkId.endsWith("ep$endpoint") }
	}
}

private command(physicalgraph.zwave.Command cmd) {
	if (state.sec) {
		zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
	} else {
		cmd.format()
	}
}

private commands(commands, delay=200) {
	delayBetween(commands.collect{ command(it) }, delay)
}

def encap(endpointNumber, cmd) {
	log.debug "encap cmd: ${cmd}, endpoint: ${endpointNumber}"
	if (cmd instanceof physicalgraph.zwave.Command) {
		command(zwave.multiChannelV3.multiChannelCmdEncap(destinationEndPoint: endpointNumber).encapsulate(cmd))
	} else if (cmd.startsWith("delay")) {
		cmd
	} else {
		def header = "600D00"
		String.format("%s%02X%s", header, endpointNumber, cmd)
	}
}

private encapWithDelay(commands, endpoint, delay=200) {
	delayBetween(commands.collect{ encap(it, endpoint) }, delay)
}

def relayOff() {
	executeChildOnOff(0x00, 1)
}

def relayOn() {
	executeChildOnOff(0xFF, 1)
}

def childOn(dni) {
	relayOn()
}

def childOff(dni) {
	relayOff()
}

private executeChildOnOff(value, endpoint) {
	sendCommands([ switchBinarySetCmd(value, endpoint) ])
}

def refreshChild() {
	sendCommands([ switchBinaryGetCmd(1) ])
}

private switchBinaryGetCmd(endpoint) {
	return multiChannelCmdEncapCmd(zwave.switchBinaryV1.switchBinaryGet(), endpoint)
}

private switchBinarySetCmd(val, endpoint=null) {
	return multiChannelCmdEncapCmd(zwave.switchBinaryV1.switchBinarySet(switchValue: val), endpoint)
}

private sendCommands(cmds) {
	def actions = []
	cmds?.each {
		actions << new physicalgraph.device.HubAction(it)
	}
	sendHubCommand(actions)
	return []
}

private multiChannelCmdEncapCmd(cmd, endpoint) {
	if (endpoint) {
		return secureCmd(zwave.multiChannelV3.multiChannelCmdEncap(destinationEndPoint:safeToInt(endpoint)).encapsulate(cmd))
	}
	else {
		return secureCmd(cmd)
	}
}

private secureCmd(cmd) {
	if (zwaveInfo?.zw?.contains("s") || ("0x98" in device.rawDescription?.split(" "))) {
		return zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
	}
	else {
		return cmd.format()
	}
}

private safeToInt(val, defaultVal=0) {
	return "${val}"?.isInteger() ? "${val}".toInteger() : defaultVal
}

def zwaveEvent(physicalgraph.zwave.commands.switchmultilevelv3.SwitchMultilevelReport cmd) {
	dimmerEvents(cmd)
}

def zwaveEvent(physicalgraph.zwave.commands.switchmultilevelv3.SwitchMultilevelSet cmd) {
	dimmerEvents(cmd)
}

private dimmerEvents(physicalgraph.zwave.Command cmd) {
	def value = (cmd.value ? "on" : "off")
	def result = [createEvent(name: "switch", value: value)]
	if (cmd.value && cmd.value <= 100) {
		result << createEvent(name: "level", value: cmd.value, unit: "%")
	}
	return result
}

def zwaveEvent(physicalgraph.zwave.commands.centralscenev1.CentralSceneNotification cmd) {
	def result = []
	switch (cmd.sceneNumber) {
		case 1:
			// Up
			switch (cmd.keyAttributes) {
				case 0:
					// Press Once
					result += createEvent(tapUp1Response("physical"))
                    if (crossControl) {
                    	if (paddleControl == "toggle" && state.relay == 1) {
                        	relayOff()}
                        else {relayOn()}
                    }
					break
				case 1:
					// release after hold
					result += createEvent(tapUpReleaseResponse("physical"))
					break
				case 2:
					// hold
					result += createEvent(tapUpHoldResponse("physical"))
					break
				case 3: 
					// 2 Times
					result+=createEvent(tapUp2Response("physical"))
					break
				case 4:
					// 3 Times
					result=createEvent(tapUp3Response("physical"))
					break
				case 5:
					// 4 Times
					result=createEvent(tapUp4Response("physical"))
					break
				case 6:
					// 5 Times
					result=createEvent(tapUp5Response("physical"))
					break
				default:
					log.debug ("unexpected up press keyAttribute: $cmd.keyAttributes")
			}
			break
		case 2:
			// Down
			switch (cmd.keyAttributes) {
				case 0:
					// Press Once
					result += createEvent(tapDown1Response("physical"))
                    if (crossControl) {
                    	if (paddleControl == "toggle" && state.relay == 0) {
                        	relayOn()}
                        else {relayOff()}
                    }
					break
				case 1:
					// release after hold
					result += createEvent(tapDownReleaseResponse("physical"))
					break
				case 2:
					// hold
					result += createEvent(tapDownHoldResponse("physical"))
					break
				case 3: 
					// 2 Times
					result +=createEvent(tapDown2Response("physical"))
					break
				case 4:
					// 3 times
					result=createEvent(tapDown3Response("physical"))
					break
				case 5:
					// 4 times
					result=createEvent(tapDown4Response("physical"))
					break
				case 6:
					// 5 times
					result=createEvent(tapDown5Response("physical"))
					break
				default:
					log.debug ("unexpected down press keyAttribute: $cmd.keyAttributes")
				}
				break
		case 3:
			// relay button switch
			switch (cmd.keyAttributes) {
				case 0:
					// Press Once
					result += createEvent(tapRB1Response("physical"))
					break
				case 1:
					// release after hold
					result += createEvent(tapRBReleaseResponse("physical"))
					break
				case 2:
					// hold
					result += createEvent(tapRBHoldResponse("physical"))
					break
				case 3: 
					// 2 Times
					result+=createEvent(tapRB2Response("physical"))
					break
				case 4:
					// 3 Times
					result=createEvent(tapRB3Response("physical"))
					break
				case 5:
					// 4 Times
					result=createEvent(tapRB4Response("physical"))
					break
				case 6:
					// 5 Times
					result=createEvent(tapRB5Response("physical"))
					break
				default:
					log.debug ("unexpected RB press keyAttribute: $cmd.keyAttributes")
			}
			break
		default:
			// unexpected case
			log.debug ("unexpected scene: $cmd.sceneNumber")
			log.debug ("unexpected scene: $cmd")
	}
}

def buttonEvent(button, value) {
	createEvent(name: "button", value: value, data: [buttonNumber: button], descriptionText: "$device.displayName button $button was $value", isStateChange: true)
}

def tapDown1Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "2"], descriptionText: "$device.displayName Tap-Down-1 (button 2) pressed", isStateChange: true, type: "$buttonType"]
}

def tapDownReleaseResponse(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "19"], descriptionText: "$device.displayName Tap-Down-Release (button 19) pressed", isStateChange: true, type: "$buttonType"]
}

def tapDownHoldResponse(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "18"], descriptionText: "$device.displayName Tap-Down-Hold (button 18) pressed", isStateChange: true, type: "$buttonType"]
}

def tapDown2Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "4"], descriptionText: "$device.displayName Tap-Down-2 (button 4) pressed", isStateChange: true, type: "$buttonType"]
}

def tapDown3Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "6"], descriptionText: "$device.displayName Tap-Down-3 (button 6) pressed", isStateChange: true, type: "$buttonType"]
}

def tapDown4Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "8"], descriptionText: "$device.displayName Tap-Down-4 (button 8) pressed", isStateChange: true, type: "$buttonType"]
}

def tapDown5Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "10"], descriptionText: "$device.displayName Tap-Down-5 (button 10) pressed", isStateChange: true, type: "$buttonType"]
}

def tapUp1Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "1"], descriptionText: "$device.displayName Tap-Up-1 (button 1) pressed", isStateChange: true, type: "$buttonType"]
}

def tapUpReleaseResponse(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "17"], descriptionText: "$device.displayName Tap-Up-Release (button 17) pressed", isStateChange: true, type: "$buttonType"]
}

def tapUpHoldResponse(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "16"], descriptionText: "$device.displayName Tap-Up-Hold (button 16) pressed", isStateChange: true, type: "$buttonType"]
}

def tapUp2Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "3"], descriptionText: "$device.displayName Tap-Up-2 (button 3) pressed", isStateChange: true, type: "$buttonType"]
}

def tapUp3Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "5"], descriptionText: "$device.displayName Tap-Up-3 (button 5) pressed", isStateChange: true, type: "$buttonType"]
}

def tapUp4Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "7"], descriptionText: "$device.displayName Tap-Up-4 (button 7) pressed", isStateChange: true, type: "$buttonType"]
}

def tapUp5Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "9"], descriptionText: "$device.displayName Tap-Up-5 (button 9) pressed", isStateChange: true, type: "$buttonType"]
}

def tapRB1Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "11"], descriptionText: "$device.displayName Tap-RB-1 (button 11) pressed", isStateChange: true, type: "$buttonType"]
}

def tapRBReleaseResponse(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "21"], descriptionText: "$device.displayName Tap-RB-Release (button 21) pressed", isStateChange: true, type: "$buttonType"]
}

def tapRBHoldResponse(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "20"], descriptionText: "$device.displayName Tap-RB-Hold (button 20) pressed", isStateChange: true, type: "$buttonType"]
}

def tapRB2Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "12"], descriptionText: "$device.displayName Tap-RB-2 (button 12) pressed", isStateChange: true, type: "$buttonType"]
}

def tapRB3Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "13"], descriptionText: "$device.displayName Tap-RB-3 (button 13) pressed", isStateChange: true, type: "$buttonType"]
}

def tapRB4Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "14"], descriptionText: "$device.displayName Tap-RB-4 (button 14) pressed", isStateChange: true, type: "$buttonType"]
}

def tapRB5Response(String buttonType) {
	[name: "button", value: "pushed", data: [buttonNumber: "15"], descriptionText: "$device.displayName Tap-RB-5 (button 15) pressed", isStateChange: true, type: "$buttonType"]
}

def tapDown1() {
	sendEvent(tapDown1Response("digital"))
}

def tapDownRelease() {
	sendEvent(tapDown1Response("digital"))
}

def tapDownHold() {
	sendEvent(tapDown1Response("digital"))
}

def tapDown2() {
	sendEvent(tapDown2Response("digital"))
}

def tapDown3() {
	sendEvent(tapDown3Response("digital"))
}

def tapDown4() {
	sendEvent(tapDown4Response("digital"))
}

def tapDown5() {
	sendEvent(tapDown5Response("digital"))
}

def tapUp1() {
	sendEvent(tapUp1Response("digital"))
}

def tapUpRelease() {
	sendEvent(tapUp1Response("digital"))
}

def tapUpHold() {
	sendEvent(tapUp1Response("digital"))
}

def tapUp2() {
	sendEvent(tapUp2Response("digital"))
}

def tapUp3() {
	sendEvent(tapUp3Response("digital"))
}

def tapUp4() {
	sendEvent(tapUp4Response("digital"))
}

def tapUp5() {
	sendEvent(tapUp5Response("digital"))
}

def tapRB1() {
	sendEvent(tapUp1Response("digital"))
}

def tapRBRelease() {
	sendEvent(tapRBReleaseResponse("digital"))
}

def tapRBHold() {
	sendEvent(tapRBHoldResponse("digital"))
}

def tapRB2() {
	sendEvent(tapRB2Response("digital"))
}

def tapRB3() {
	sendEvent(tapRB3Response("digital"))
}

def tapRB4() {
	sendEvent(tapRB4Response("digital"))
}

def tapRB5() {
	sendEvent(tapRB5Response("digital"))
}

def parmSet(parmnum, parmsize, parmval) {
	return zwave.configurationV1.configurationSet(scaledConfigurationValue: parmval, parameterNumber: parmnum, size: parmsize).format()
}

def parmGet(parmnum) {
	return zwave.configurationV1.configurationGet(parameterNumber: parmnum).format()
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

private parseAssocGroupList(list, group) {
	def nodes = group == 3 ? [] : [zwaveHubNodeId]
 	if (list) {
		def nodeList = list.split(',')
		def max = group == 3 ? 5 : 4
		def count = 0

		nodeList.each { node ->
			node = node.trim()
			if ( count >= max) {
				log.warn "Association Group ${group}: Number of members is greater than ${max}! The following member was discarded: ${node}"
			}
			else if (node.matches("\\p{XDigit}+")) {
				def nodeId = Integer.parseInt(node,16)
				if (nodeId == zwaveHubNodeId) {
					log.warn "Association Group ${group}: Adding the hub as an association is not allowed (it would break double-tap)."
				}
				else if ( (nodeId > 0) & (nodeId < 256) ) {
					nodes << nodeId
					count++
				}
				else {
					log.warn "Association Group ${group}: Invalid member: ${node}"
				}
			}
			else {
				log.warn "Association Group ${group}: Invalid member: ${node}"
			}
		}
	}
	return nodes
}

def zwaveEvent(physicalgraph.zwave.commands.associationv2.AssociationReport cmd) {
// DFC:needs review
	log.debug "---ASSOCIATION REPORT V2--- ${device.displayName} sent groupingIdentifier: ${cmd.groupingIdentifier} maxNodesSupported: ${cmd.maxNodesSupported} nodeId: ${cmd.nodeId} reportsToFollow: ${cmd.reportsToFollow}"
	state.group3 = "1,2"
	if (cmd.groupingIdentifier == 3) {
		if (cmd.nodeId.contains(zwaveHubNodeId)) {
			createEvent(name: "numberOfButtons", value: 21, displayed: false)
		}
		else {
			sendEvent(name: "numberOfButtons", value: 0, displayed: false)
				sendHubCommand(new physicalgraph.device.HubAction(zwave.associationV2.associationSet(groupingIdentifier: 3, nodeId: zwaveHubNodeId).format()))
				sendHubCommand(new physicalgraph.device.HubAction(zwave.associationV2.associationGet(groupingIdentifier: 3).format()))
		}
	}
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv1.ConfigurationReport cmd) {
	def name = ""
	def value = ""
	def reportValue = cmd.configurationValue[0]
	log.debug "---CONFIGURATION REPORT V1--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
	switch (cmd.parameterNumber) {
		case 24:
			name = "topcontrol"
			switch (reportValue) {
				case 0:
					value = "on"
					break
				case 1:
					value = "off"
					break
				case 2:
					value = "toggle"
					break
				default:
					value = "on"
					break
			}
			break
		case 1:
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
			name = "ledfollowRB"
			break
		case 3:
			switch (reportValue) {
				case 0:
					value = "white"
					break
				case 1:
					value = "blue"
					break
				case 2:
					value = "green"
					break
				case 3:
					value = "red"
					break
				default:
					value = "white"
					break
			}
			name = "ledLightColor"
			break
		case 4:
			switch (reportValue) {
				case 0:
					value = "white"
					break
				case 1:
					value = "blue"
					break
				case 2:
					value = "green"
					break
				case 3:
					value = "red"
					break
				default:
					value = "white"
					break
			}
			name = "ledLightColorRB"
			break
		case 5:
			switch (reportValue) {
				case 0:
					value = "bright-100%"
					break
				case 1:
					value = "medium-60%"
					break
				case 2:
					value = "low-30%"
					break
				default:
					value = "medium-60%"
					break
			}
			name = "ledLightBrightness"
			break
		case 6:
			switch (reportValue) {
				case 0:
					value = "bright-100%"
					break
				case 1:
					value = "medium-60%"
					break
				case 2:
					value = "low-30%"
					break
				default:
					value = "medium-60%"
					break
			}
			name = "ledLightBrightnessRB"
			break
		case 7:
			name = "LEDsceneIndicator"
			value = reportValue == 1 ? "true" : "false"
			break
		case 8:
			name = "autoofftimer"
			value = cmd.configurationValue[3] + (cmd.configurationValue[2] * 0x100) + (cmd.configurationValue[1] * 0x10000) + (cmd.configurationValue[0] * 0x1000000)
			break
		case 9:
			name = "autoontimer"
			value = cmd.configurationValue[3] + (cmd.configurationValue[2] * 0x100) + (cmd.configurationValue[1] * 0x10000) + (cmd.configurationValue[0] * 0x1000000)
			break
		case 10:
			name = "autoofftimerRB"
			value = cmd.configurationValue[3] + (cmd.configurationValue[2] * 0x100) + (cmd.configurationValue[1] * 0x10000) + (cmd.configurationValue[0] * 0x1000000)
			break
		case 11:
			name = "autoontimerRB"
			value = cmd.configurationValue[3] + (cmd.configurationValue[2] * 0x100) + (cmd.configurationValue[1] * 0x10000) + (cmd.configurationValue[0] * 0x1000000)
			break
		case 12:
			name = "afterfailure"
			switch (reportValue) {
				case 0:
					value = "both off"
					break
				case 1:
					value = "dimmer off, relay on"
					break
				case 2:
					value = "dimmer on, relay off"
					break
				case 3:
					value = "both remembers"
					break
				case 4:
					value = "dimmer remembers, relay on"
					break
				case 5:
					value = "dimmer remembers, relay off"
					break
				case 6:
					value = "dimmer on, relay remembers"
					break
				case 7:
					value = "dimmer off, relay remembers"
					break
				case 8:
					value = "both on"
					break
				default:
					break
			}
			break
		case 13:
			name = "rampspeed"
			value = reportValue
			break
		case 14:
			name = "minbrightness"
			value = reportValue
			break
		case 15:
			name = "maxbrightness"
			value = reportValue
			break
		case 17:
			name = "double_tap"
			value = reportValue == 1 ? "true" : "false"
			break
		case 18:
			name = "double_tap_disable"
			switch (reportValue) {
				case 0:
					value = "off"
					break
				case 1:
					value = "single_max"
					break
				case 2:
					value = "single_full"
					break
				default:
					value = "off"
					break
			}
			break
		case 19:
			name = "local_control"
			value = reportValue == 1 ? "true" : "false"
			break
		case 20:
			name = "local_controlRB"
			value = reportValue == 1 ? "true" : "false"
			break
		case 21:
			name = "man_dim_speed"
			value = reportValue
			break
		case 22:
			name = "zwave_ramp_type"
			value = reportValue == 1 ? "command" : "parm9"
			break
		case 23:
			name = "def_brightness"
			value = reportValue
			break
		default:
			break
	}
	createEvent([name: name, value: value])
}

def updated(){
	// These are needed as SmartThings is not honoring defaultValue in preferences. They are set to the device defaults
	def setPhysDefBright = 0
	if (physDefBright) {setPhysDefBright = physDefBright}
	def setPhysdimspeed = 4
	if (physDimspeed) {setPhysdimspeed = physDimspeed}
	def setZwaveontype = 1
	if (zwaveOntype!=null) {setZwaveontype = zwaveOntype == "zwcontrollog" ? 1 : 0}
	def setLocalControl = 1
	if (localControl!=null) {setLocalControl = localControl == "lcOn" ? 1 : localControl == "lcOff" ? 0 : 2}
	def setLocalControlRB = 1
	if (localControlRB!=null) {setLocalControlRB = localControlRB == "lcOnRB" ? 1 : localControlRB == "lcOffRB" ? 0 : 2}
	def setOffTimer = 0
	if (offTimer) {setOffTimer = offTimer}
	def setOnTimer = 0
	if (onTimer) {setOnTimer = onTimer}
	def setOffTimerRB = 0
	if (offTimerRB) {setOffTimerRB = offTimerRB}
	def setOnTimerRB = 0
	if (onTimerRB) {setOnTimerRB = onTimerRB}
	def setRampRate = 1
	if (rampRate!=null) {setRampRate = rampRate}
	def setMaxBright = 99
	if (maxBright) {setMaxBright = maxBright}
	def setMinBright = 1
	if (minBright) {setMinBright = minBright}

	def nodes = []
	def commands = []
	if (getDataValue("MSR") == null) {
   		def level = 99
		commands << mfrGet()
		commands << zwave.versionV1.versionGet().format()
		commands << zwave.basicV1.basicSet(value: level).format()
		commands << zwave.switchMultilevelV1.switchMultilevelGet().format()
	}
	def setLedScene = ledScene == true ? 1 : 0
	def setDoubleTap = 0
	def setDtapDisable = 0
	switch (doubleTap) {
		case "tap2full":
			setDoubleTap = 0
			setDtapDisable = 0
			break
		case "tap2max":
			setDoubleTap = 1
			setDtapDisable = 0
			break
		case "tap2max":
			setDoubleTap = 0
			setDtapDisable = 1
			break
		case "tap1last":
			setDoubleTap = 0
			setDtapDisable = 2
			break
		default:
			setDoubleTap = 0
			setDtapDisable = 0
			break
	}

	// use binary map for values, dimmer is most significant
    //		dimmer	relay
	//off		00	00
	//on		01	01
	//remember	10	10
    def setPowerRestore = 3
    def setPowerRestoreD = 2
    def setPowerRestoreRB = 2
	if (powerRestore != null) {setPowerRestoreD = powerRestore == "prremember" ? 2 : powerRestore == "proff" ? 0 : 1}
	if (powerRestoreRB != null) {setPowerRestoreRB = powerRestoreRB == "prrememberRB" ? 2 : powerRestoreRB == "proffRB" ? 0 : 1}
    def powerRestoreCalc = 4 * setPowerRestoreD + setPowerRestoreRB
    switch (powerRestoreCalc) {
    	case 0:
        	setPowerRestore = 0
            break
    	case 1:
        	setPowerRestore = 1
            break
    	case 4:
        	setPowerRestore = 2
            break
    	case 10:
        	setPowerRestore = 3
            break
    	case 9:
        	setPowerRestore = 4
            break
    	case 8:
        	setPowerRestore = 5
            break
    	case 6:
        	setPowerRestore = 6
            break
    	case 2:
        	setPowerRestore = 7
            break
    	case 5:
        	setPowerRestore = 8
            break
		case "default":
        	setPowerRestore = 3
            break
	}
    
	def setLedIndicator = 0
	def setLedIndicatorRB = 0
	def setPaddleControl = 0
	switch (paddleControl) {
		case "std":
			setPaddleControl = 0
			break
		case "invert":
			setPaddleControl = 1
			break
		case "toggle":
			setPaddleControl = 2
			break
		default:
			setPaddleControl = 0
			break
	}

	def setLedColor = 0
    switch (ledColor) {
    	case "white":
        	setLedColor = 0
            break
    	case "blue":
        	setLedColor = 1
            break
    	case "green":
        	setLedColor = 2
            break
    	case "red":
        	setLedColor = 3
            break
    	case "default":
        	setLedColor = 3
            break
	}
	def setLedColorRB = 0
    switch (ledColorRB) {
    	case "white":
        	setLedColorRB = 0
            break
    	case "blue":
        	setLedColorRB = 1
            break
    	case "green":
        	setLedColorRB = 2
            break
    	case "red":
        	setLedColorRB = 3
            break
    	case "default":
        	setLedColorRB = 3
            break
	}
    
	def setLedBrightness = 1
    switch (ledBrightness) {
    	case "high":
        	setLedBrightness = 0
            break
    	case "medium":
        	setLedBrightness = 1
            break
    	case "low":
        	setLedBrightness = 2
            break
    	case "default":
        	setLedBrightness = 1
            break
	}
	def setLedBrightnessRB = 1
    switch (ledBrightnessRB) {
    	case "high":
        	setLedBrightnessRB = 0
            break
    	case "medium":
        	setLedBrightnessRB = 1
            break
    	case "low":
        	setLedBrightnessRB = 2
            break
    	case "default":
        	setLedBrightnessRB = 1
            break
	}
	def setZwRType = zwdef
	if (zwaveramptype != null) {setZwRType = zwaveramptype}
	state.dimDuration = 255
	if (zwaverampspeed != null && setZwaveontype == 1) {
		switch (setZwRType) {
			case "zwdef":
				state.dimDuration = 255
				break
			case "zwsec":
				state.dimDuration = zwaverampspeed
				break
			case "zwmin":
				state.dimDuration = zwaverampspeed + 127
				break
			case "zwinstant":
				state.dimDuration = 0
				break
		}
	}
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
	switch (ledIndicatorRB) {
		case "off":
			setLedIndicatorRB = 0
			break
		case "on":
			setLedIndicatorRB = 1
			break
		case "never":
			setLedIndicatorRB = 2
			break
		case "always":
			setLedIndicatorRB = 3
			break
		default:
			setLedIndicatorRB = 0
			break
	}
	
	if (settings.requestedGroup2 != state.currentGroup2) {
		nodes = parseAssocGroupList(settings.requestedGroup2, 2)
		commands << zwave.associationV2.associationRemove(groupingIdentifier: 2, nodeId: []).format()
		commands << zwave.associationV2.associationSet(groupingIdentifier: 2, nodeId: nodes).format()
		commands << zwave.associationV2.associationGet(groupingIdentifier: 2).format()
		state.currentGroup2 = settings.requestedGroup2
	}
	
	if (settings.requestedGroup3 != state.currentGroup3) {
		nodes = parseAssocGroupList(settings.requestedGroup3, 3)
		commands << zwave.associationV2.associationRemove(groupingIdentifier: 3, nodeId: []).format()
		commands << zwave.associationV2.associationSet(groupingIdentifier: 3, nodeId: nodes).format()
		commands << zwave.associationV2.associationGet(groupingIdentifier: 3).format()
//        commands << associationSetCmd(3)
		commands << multiChannelAssociationSetCmd(3, 1)
		commands << multiChannelAssociationGetCmd(3)

		state.currentGroup3 = settings.requestedGroup3
	}


	//parmset takes the parameter number, it's size, and the value - in that order
	commands << parmSet(23, 1, setPhysDefBright)
	commands << parmSet(22, 1, setZwaveontype)
	commands << parmSet(21, 1, setPhysdimspeed)
	commands << parmSet(20, 1, setLocalControlRB)
	commands << parmSet(19, 1, setLocalControl)
	commands << parmSet(18, 1, setDtapDisable)
	commands << parmSet(17, 1, setDoubleTap)
	commands << parmSet(15, 1, setMaxBright)
	commands << parmSet(14, 1, setMinBright)
	commands << parmSet(13, 1, setRampRate)
	commands << parmSet(12, 1, setPowerRestore)
	commands << parmSet(11, 4, setOnTimerRB)
	commands << parmSet(10, 4, setOffTimerRB)
	commands << parmSet(9, 4, setOnTimer)
	commands << parmSet(8, 4, setOffTimer)
	commands << parmSet(7, 1, setLedScene)
	commands << parmSet(6, 1, setLedBrightnessRB)
	commands << parmSet(5, 1, setLedBrightness)
	commands << parmSet(4, 1, setLedColorRB)
	commands << parmSet(3, 1, setLedColor)
	commands << parmSet(2, 1, setLedIndicatorRB)
	commands << parmSet(1, 1, setLedIndicator)

	commands << parmGet(23)
	commands << parmGet(22)
	commands << parmGet(21)
	commands << parmGet(20)
	commands << parmGet(19)
	commands << parmGet(18)
	commands << parmGet(17)
	commands << parmGet(15)
	commands << parmGet(14)
	commands << parmGet(13)
	commands << parmGet(12)
	commands << parmGet(11)
	commands << parmGet(10)
	commands << parmGet(9)
	commands << parmGet(8)
	commands << parmGet(7)
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

def mfrGet() {
	return zwave.manufacturerSpecificV2.manufacturerSpecificGet().format()
}

private multiChannelAssociationSetCmd(group, endpoint) { 
	def cmd = zwave.multiChannelAssociationV2.multiChannelAssociationSet(groupingIdentifier:group, nodeId:[zwaveHubNodeId]).format()
	return secureRawCmd("${cmd}00${convertToHex(zwaveHubNodeId)}${convertToHex(endpoint)}")
}	

private multiChannelAssociationGetCmd(group) { 
	return secureCmd(zwave.multiChannelAssociationV2.multiChannelAssociationGet(groupingIdentifier:group))
}

private secureRawCmd(cmd) {
	if (isSecurityEnabled()) {
		return "988100${cmd}"
	}
	else {
		return cmd
	}
}

private isSecurityEnabled() {
	try {
		return zwaveInfo?.zw?.contains("s") || ("0x98" in device.rawDescription?.split(" "))
	}
	catch (e) {
		return false
	}
}

private convertToHex(num) {
	return Integer.toHexString(num).padLeft(2, "0").toUpperCase()
}

def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd) {
	def manufacturerCode = String.format("%04X", cmd.manufacturerId)
	def productTypeCode = String.format("%04X", cmd.productTypeId)
	def productCode = String.format("%04X", cmd.productId)
	def msr = manufacturerCode + "-" + productTypeCode + "-" + productCode
    log.debug "getting msr"
	updateDataValue("MSR", msr)
	updateDataValue("Manufacturer", "Zooz")
	updateDataValue("Manufacturer ID", manufacturerCode)
	updateDataValue("Product Type", productTypeCode)
	updateDataValue("Product Code", productCode)
	createEvent([descriptionText: "$device.displayName MSR: $msr", isStateChange: false])
}
