package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
/** This command class handles the actionPerformed of gui components. 
 * 
 * @author ryanmorris
 *
 */
public class Help extends Command {
		private Game myG;

		public Help(Game newG) {
			super("Help");
			myG= newG;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			myG.activateHelpDialog();
		}


}
