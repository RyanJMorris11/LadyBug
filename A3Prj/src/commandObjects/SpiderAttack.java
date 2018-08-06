package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
/** This command class handles the actionPerformed of gui components. 
 * 
 * @author ryanmorris
 *
 */
public class SpiderAttack extends Command{

	private GameWorld myGw;

	public SpiderAttack(GameWorld newGw) {
		super("Spider Attack");
		myGw = newGw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		myGw.attack();
	}

}
