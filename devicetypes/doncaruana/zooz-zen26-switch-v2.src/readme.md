# Zen26 Switch v2.x

This is the latest paddle switch from thesmartesthouse with the new version 2 firmware (and an update to 2.01 firmware).
https://www.thesmartesthouse.com/products/zooz-z-wave-plus-s2-on-off-wall-switch-zen26-with-simple-direct-3-way-4-way

This device has a LOT of new parameters since version 1 and not only has association capability but also uses Central Scene control 
to enable scenes and control of other devices.

There is also a difference in version 2.0 and 2.01 of the firmware. Version 2 only has 2 taps down or 2 taps down 
for scene control. The version 2.01 firmware allows all 10 'taps' for scene control. The taps are as below:
<table>
<tr><td>Button</td><td>Direction</td><td>Presses</td></tr>
<tr><td>1</td><td>Up</td><td>1</td></tr>
<tr><td>2</td><td>Down</td><td>1</td></tr>
<tr><td>3</td><td>Up</td><td>2</td></tr>
<tr><td>4</td><td>Down</td><td>2</td></tr>
<tr><td>5</td><td>Up</td><td>3</td></tr>
<tr><td>6</td><td>Down</td><td>3</td></tr>
<tr><td>7</td><td>Up</td><td>4</td></tr>
<tr><td>8</td><td>Down</td><td>4</td></tr>
<tr><td>9</td><td>Up</td><td>5</td></tr>
<tr><td>10</td><td>Down</td><td>5</td></tr>
</table>

A very important note here is that triple tap up or down are available as scene control even though these are used
for inclusion or exclusion, repectively. If you use them, you will note the LED will begin flashing rapidly as it
enters that pair/unpair but it will time out and stop after 30 seconds. Use the triple taps with caution.

There is also a new parameter with the 2.01 firmware, 11 the table below.

You can use the SmartThings Smart Lighting smartapp to assign devices or scenes to either of these virtual buttons. 
However, the buttons will not be available for assignment unless the scene control has been turned on and they
will no longer be available if scene control is turned off.

You may run into a known bug on android with Smart Lighting in that it will not save. Various fixes have been proposed
like force stopping it, but I actually cleared cache, cleared data, uninstalled and reinstalled to get it to save
consistently for me.

You can also use webcore to do the same button assignment.

The full list of parameters are here:

<table>
<tr><td>Parm</td><td>Description</td><td>Default</td><td>Values</td></tr>
<tr><td>1</td><td>Paddle Control</td><td>0</td><td>0-Upper paddle turns light on, 1-Lower paddle turns light on, 2-either paddle toggles on/off</td></tr>
<tr><td>2</td><td>LED Indicator</td><td>0</td><td>0-LED on when light is OFF, 1-LED on when light is ON, 2-LED always off, 3-LED always on</td></tr>
<tr><td>3</td><td>Auto Turn-Off</td><td>0</td><td>0-Timer disabled, 1-Timer enabled; Set time in parameter</td></tr>
<tr><td>4</td><td>Turn-off Timer</td><td>60</td><td>60-Time in minutes after turning on to automatically turn off (1-65535 minutes)</td></tr>
<tr><td>5</td><td>Auto Turn-On</td><td>0</td><td>0-Timer disabled, 1-Timer enabled; Set time in parameter</td></tr>
<tr><td>6</td><td>Turn-on Timer</td><td>60</td><td>60-Time in minutes after turning off to automatically turn on (1-65535 minutes)</td></tr>
<tr><td>8</td><td>Power Restore</td><td>2</td><td>2-Remember state from pre-power failure, 0-Off after power restored, 1-On after power restore</td></tr>
<tr><td>10</td><td>Scene Control</td><td>0</td><td>0-Scene control disabled, 1-Scene control enabled</td></tr>
<tr><td>11</td><td>Local Control</td><td>1</td><td>0-Local control disabled, 1-Local control enabled</td></tr>
</table>

The handler is just over a subdirectory...
https://github.com/doncaruana/SmartThings-doncaruana/blob/master/devicetypes/doncaruana/zooz-zen26-switch-v2.src/zooz-zen26-switch-v2.groovy
