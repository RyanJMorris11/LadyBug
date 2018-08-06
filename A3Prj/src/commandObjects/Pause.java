package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class Pause extends Command{

		private Game myG;

		public Pause(Game newG) {
			super("Pause");
			myG= newG;
		}
		@Override
		public void actionPerformed(ActionEvent evt) {
			myG.togglePause();
		}
		
}
