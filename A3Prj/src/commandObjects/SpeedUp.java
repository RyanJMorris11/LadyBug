package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
/** This command class handles the actionPerformed of gui components. 
 * 
 * @author ryanmorris
 *
 */
public class SpeedUp extends Command{
	private GameWorld myGw;

	public SpeedUp(GameWorld newGw) {
		super("Speed Up");
		myGw = newGw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		myGw.speedUp();
	}

}
