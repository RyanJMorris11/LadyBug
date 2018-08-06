package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnRight extends Command{
	private GameWorld myGw;
	/** This command class handles the actionPerformed of gui components. 
	 * Turns Left
	 * @author ryanmorris
	 *
	 */
	public TurnRight(GameWorld newGw) {
		super("Turn Right");
		myGw = newGw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		myGw.turnRight();
	}

}
