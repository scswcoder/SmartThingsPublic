/**
 *  Zooz Zen22 Dimmer Switch v2
 *
 *  Date: 2017-8-29
 *  Supported Command Classes
 *  
 *         Association v2
 *         Association Group Information
 *         Basic
 *         Configuration
 *         Device Reset Local
 *         Manufacturer Specific v2
 *         Powerlevel
 *         Switch_all
 *         Switch_multilevel
 *         Version v2
 *         ZWavePlus Info v2
 *  
 *   Parm Size Description                                   Value
 *      1    1 Invert Switch                                 0 (Default)-Upper paddle turns light on, 1-Lower paddle turns light on
 *      2    1 LED Indicator                                 0 (Default)-LED is on when light is OFF, 1-LED is on when light is ON
 *      3    1 LED Disable                                   0 (Default)-LED is on based on parameter 2, 1-LED is off always
 */
metadata {
	definition (name: "Zooz Zen22 Dimmer Switch v2", namespace: "doncaruana", author: "Don Caruana") {
		capability "Switch Level"
		capability "Switch"
		capability "Polling"
		capability "Refresh"
		capability "Sensor"

//zw:L type:1101 mfr:027A prod:B112 model:1F1C ver:20.15 zwv:4.05 lib:06 cc:5E,85,59,70,5A,72,73,27,26,86,20 role:05 ff:9C02 ui:9C00

	fingerprint mfr:"027A", prod:"B112", model:"1F1C", deviceJoinName: "Zooz Zen22 Dimmer v2"
    fingerprint deviceId:"0x1101", inClusters: "0x5E,0x59,0x85,0x70,0x5A,0x72,0x73,0x27,0x26,0x86,0x20"
    fingerprint cc: "0x5E,0x59,0x85,0x70,0x5A,0x72,0x73,0x27,0x26,0x86,0x20", mfr:"027A", prod:"B112", model:"1F1C", deviceJoinName: "Zooz Zen22 Dimmer v2"
	}

	simulator {
		status "on":  "command: 2003, payload: FF"
		status "off": "command: 2003, payload: 00"
		status "09%": "command: 2003, payload: 09"
		status "10%": "command: 2003, payload: 0A"
		status "33%": "command: 2003, payload: 21"
		status "66%": "command: 2003, payload: 42"
		status "99%": "command: 2003, payload: 63"

		// reply messages
		reply "2001FF,delay 5000,2602": "command: 2603, payload: FF"
		reply "200100,delay 5000,2602": "command: 2603, payload: 00"
		reply "200119,delay 5000,2602": "command: 2603, payload: 19"
		reply "200132,delay 5000,2602": "command: 2603, payload: 32"
		reply "20014B,delay 5000,2602": "command: 2603, payload: 4B"
		reply "200163,delay 5000,2602": "command: 2603, payload: 63"
	}

	preferences {
		input "ledIndicator", "bool", title: "LED on when light on", description: "LED will be on when light OFF if not set", required: false, defaultValue: false
		input "invertSwitch", "bool", title: "Invert Switch", description: "Flip switch upside down", required: false, defaultValue: false
		input "ledDisable", "bool", title: "LED Diabled", description: "Turn off LED completely", required: false, defaultValue: false
  }

	tiles(scale: 2) {
		multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#79b821", nextState:"turningOff"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
				attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#79b821", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
			}
			tileAttribute ("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action:"switch level.setLevel"
			}
		}

		standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}

		valueTile("level", "device.level", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "level", label:'${currentValue} %', unit:"%", backgroundColor:"#ffffff"
		}

		main(["switch"])
		details(["switch", "level", "refresh"])

	}
}
