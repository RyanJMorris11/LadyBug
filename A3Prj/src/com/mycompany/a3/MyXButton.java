package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;


/** These buttons are formatted with padding to the left and right up the buttons.
 * Made for buttons that are put side by side - left and right.
 * 
 * @author ryanmorris
 *
 */
public class MyXButton extends Button{

		public MyXButton() {
			
			this.getUnselectedStyle().setBgTransparency(255); 
			this.getUnselectedStyle().setBgColor(ColorUtil.BLUE); 
			this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
			this.getSelectedStyle().setBgColor(ColorUtil.rgb(255,40,40));
			this.getAllStyles().setPadding(Component.LEFT, 3); 
			this.getAllStyles().setPadding(Component.RIGHT, 3);
			this.getAllStyles().setMargin(Component.LEFT, 3); 
			this.getAllStyles().setMargin(Component.RIGHT, 3);
			
		}
		

}
