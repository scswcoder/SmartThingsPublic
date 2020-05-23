# Zen23 Toggle Switch v3.0

This is the latest toggle switch from thesmartesthouse with the new version 3 firmware
https://www.thesmartesthouse.com/products/zooz-z-wave-on-off-toggle-switch-zen23

This device has a LOT of new parameters and not only has association capability but also uses Central Scene control 
to enable scenes and control of other devices.
Note that on their new dimmer devices, the scene control is 2 down or 4 down. On the regular switches, it's 2 down or 2 up.

There is also a difference in version 3.0 and 3.01 of the firmware. Version 2 only has 2 taps down or 2 taps down 
for scene control. The version 3.01 firmware allows all 10 'taps' for scene control. The taps are as below:
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
<tr><td>1</td><td>Toggle Control</td><td>0</td><td>0-Toggle up turns light on, 1-Toggle down turns light on, 2-toggle either way toggles light status</td></tr>
<tr><td>3</td><td>Auto Turn-Off</td><td>0</td><td>0-Timer disabled, 1-Timer enabled; Set time in parameter</td></tr>
<tr><td>4</td><td>Turn-off Timer</td><td>60</td><td>60-Time in minutes after turning on to automatically turn off (1-65535 minutes)</td></tr>
<tr><td>5</td><td>Auto Turn-On</td><td>0</td><td>0-Timer disabled, 1-Timer enabled; Set time in parameter</td></tr>
<tr><td>6</td><td>Turn-on Timer</td><td>60</td><td>60-Time in minutes after turning off to automatically turn on (1-65535 minutes)</td></tr>
<tr><td>8</td><td>Power Restore</td><td>2</td><td>2-Remember state from pre-power failure, 0-Off after power restored, 1-On after power restore</td></tr>
<tr><td>9</td><td>Scene Control</td><td>0</td><td>0-Scene control disabled, 1-Scene control enabled</td></tr>
<tr><td>10</td><td>Local Control</td><td>1</td><td>0-Local control disabled, 1-Local control enabled</td></tr>
</table>

