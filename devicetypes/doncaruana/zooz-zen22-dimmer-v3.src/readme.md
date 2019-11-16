# Zen22 Dimmer Switch v3.x

This is the latest paddle switch from thesmartesthouse with the new version 3 firmware.
https://www.thesmartesthouse.com/products/zooz-z-wave-plus-dimmer-light-switch-zen22

This device has a LOT of new parameters since version 2 and not only has association capability but also uses Central Scene control 
to enable scenes and control of other devices.

There is also a difference in version 3.0 and 3.01 of the firmware. Version 3 only has 2 taps down or 4 taps down 
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

A very important note here is that triple tap up or down are available as scene control even though these are used
for inclusion or exclusion, repectively. If you use them, you will note the LED will begin flashing rapidly as it
enters that pair/unpair but it will time out and stop after 30 seconds. Use the triple taps with caution.

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
<tr><td>9</td><td>Ramp Rate</td><td>1</td><td>Ramp rate in seconds to reach full brightness or off (0-99 seconds)</td></tr>
<tr><td>10</td><td>Minimum Brightness</td><td>1</td><td>Minimum brightness that light will set (1-99%)</td></tr>
<tr><td>11</td><td>Maximum Brightness</td><td>99</td><td>Maximum brightness that light will set (1-99%)</td></tr>
<tr><td>12</td><td>Double Tap</td><td>0</td><td>0-Full brightness with double tap, 1-Double tap goes to max set in Parameter 11 with double tap</td></tr>
<tr><td>13</td><td>Scene Control</td><td>0</td><td>0-Scene control disabled, 1-Scene control enabled</td></tr>
<tr><td>14</td><td>Disable Double Tap</td><td>0</td><td>0-Double tap to full/max brightness, 1-double tap disabled-single tap to last brightness, 2-double tap disable-single tap to full/max brightness</td></tr>
<tr><td>15</td><td>Local Control</td><td>1</td><td>0-Local control disabled, 1-Local control enabled</td></tr>
<tr><td>16</td><td>Physical Dimming Speed</td><td>4</td><td>Time in seconds to go from 0 to 100%, 1-99 dimming time</td></tr>
<tr><td>17</td><td>Zwave Ramp Type</td><td>1</td><td>1-Zwave ramp speed set through command class, 0-Zwave ramp speed matches parameter 9</td></tr>
<tr><td>18</td><td>Default Brightness</td><td>0</td><td>0-Last brightness level, 1-99-custom brightness level</td></tr>
</table>

The handler code is here: https://github.com/doncaruana/SmartThings-doncaruana/blob/master/devicetypes/doncaruana/zooz-zen22-dimmer-v3.src/zooz-zen22-dimmer-v3.groovy
