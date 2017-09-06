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
	}
}
