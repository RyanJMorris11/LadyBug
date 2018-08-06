package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Position extends Command{
	private GameWorld myGw;
	private SelectObj mySelectObj;

	public Position(GameWorld newGw, SelectObj mySelectObj) {
		super("Position");
		myGw= newGw;
		this.mySelectObj=mySelectObj;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if( myGw.hasSelectedObj()) {
			mySelectObj.toggleMoveReady();
		}	
	}	
}
