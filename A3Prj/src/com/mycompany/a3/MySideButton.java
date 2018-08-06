package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;

/** These buttons are formatted with padding on top and button of the buttons.
 * Made for buttons that are put above and below each other.
 * 
 * @author ryanmorris
 *
 */
public class MySideButton extends Button{
	
	public MySideButton() {
		
		this.getUnselectedStyle().setBgTransparency(255); 
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE); 
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getSelectedStyle().setBgColor(ColorUtil.rgb(255,40,40));
		
		//set disabled style
		this.getDisabledStyle().setBgColor(ColorUtil.rgb(40,40,40));

		this.getAllStyles().setPadding(Component.TOP, 3); 
		this.getAllStyles().setPadding(Component.BOTTOM, 3);
		this.getAllStyles().setMargin(Component.TOP, 3); 
		this.getAllStyles().setMargin(Component.BOTTOM, 3);
		
	}
	
}
