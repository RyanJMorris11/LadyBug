package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
/** This command class handles the actionPerformed of gui components. 
 * 
 * @author ryanmorris
 *
 */
public class CancelExit extends Command {
	private Game myG;

	public CancelExit(Game newG) {
		super("Keep playing");
		myG = newG;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		myG.deactivateExitDialog();
	}


}