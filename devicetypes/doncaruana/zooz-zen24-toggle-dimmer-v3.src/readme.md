This device uses Central Scene control to enable scenes and control of other devices. 
Button One is 2 toggles down and Button Two is 4 toggles down.

You can use the SmartThings Smart Lighting smartapp to assign devices or scenes to either of these virtual buttons. 
However, the buttons will not be available for assignment unless the scene control has been turned on and they
will no longer be available if scene control is turned off.

You may run into a known bug on android with Smart Lighting in that it will not save. Various fixes have been proposed
like force stopping it, but I actually cleared cache, cleared data, uninstalled and reinstalled to get it to save
consistently for me.

You can also use webcore to do the same button assignment.

The full list of parameters are here:

Parm Description           Value
*  1 - Toggle Control         0 (Default)-Toggle up turns light on, 1-Toggle down turns light on, 2-toggle either way toggles light status
*  3 - Auto Turn-Off          0 (Default)-Timer disabled, 1-Timer enabled; Set time in parameter 4
*  4 - Turn-off Timer        60 (Default)-Time in minutes after turning on to automatically turn off (1-65535 minutes)
*  5 - Auto Turn-On           0 (Default)-Timer disabled, 1-Timer enabled; Set time in parameter 6
*  6 - Turn-on Timer         60 (Default)-Time in minutes after turning off to automatically turn on (1-65535 minutes)
*  8 - Power Restore          2 (Default)-Remember state from pre-power failure, 0-Off after power restored, 1-On after power restore
*  9 - Ramp Rate Control      1 (Default)-Ramp rate in seconds to reach full brightness or off (1-99 seconds)
* 10 - Minimum Brightness     1 (Default)-Minimum brightness that light will set (1-99%)
* 11 - Maximum Brightness    99 (Default)-Maximum brightness that light will set (1-99%)
* 12 - Double Tap             0 (Default)-Light will go to full brightness with double tap, 1-light will go to max set in Parameter 11 with double tap
* 13 - Scene Control          0 (Default)-Scene control disabled, 1-Scene control enabled
