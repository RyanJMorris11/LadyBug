package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

/** This command class handles the actionPerformed of gui components. 
 * 
 * @author ryanmorris
 *
 */ public class Exit extends Command {
	private Game myG;

	public Exit(Game newG) {
		super("Exit Game");
		myG= newG;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
		myG.activateExitDialog();
	}


}
