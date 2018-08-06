package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
/** This command class handles the actionPerformed of gui components. 
 * 
 * @author ryanmorris
 *
 */
public class ConfirmExit extends Command {

	public ConfirmExit() {
		super("Confirm Exit");
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		System.exit(0);
	}


}
