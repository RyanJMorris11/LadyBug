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
public class SoundToggle extends Command{
	GameWorld myGw;
	Game myG;
	
	public SoundToggle( GameWorld newGw, Game newGame) {
		super("Turn Off Sound" );
		myGw = newGw;
		myG = newGame;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		myGw.soundToggle();
		myG.updateSoundPlay();
		super.actionPerformed(evt);
	}


}
