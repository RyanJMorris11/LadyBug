package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

/** This command class handles the actionPerformed of gui components. 
 * 
 * @author ryanmorris
 *
 */

public class About extends Command {
	private com.mycompany.a3.Game myG;

	public About(Game newG) {
		super("About");
		myG= newG;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		myG.activateAboutDialog();
	}


}
