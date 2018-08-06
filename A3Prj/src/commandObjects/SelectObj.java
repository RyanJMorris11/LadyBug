package commandObjects;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.MapView;

public class SelectObj extends Command {
	private GameWorld myGw;
	private boolean moveReady;
	private Game myG;

	public SelectObj(GameWorld newGw, Game newG) {
		super("Position");
		myGw = newGw;
		myG= newG;
	}

	public void actionPerformed(ActionEvent evt) {
		if (!myG.isPaused()) {
			return;
		}
		Point loc = myG.getMapOrigin();
		int pointerX = evt.getX() - loc.getX();
		int pointerY = evt.getY() - loc.getY();
		
		System.out.println("origin x and y: " + loc.getX() + " " + loc.getY() );
		System.out.println("Pointer x and y: " + pointerX + " " + pointerY );
		System.out.println("MoveReady= "+ moveReady);
		
		if (moveReady) {
			myGw.moveSelected(pointerX, pointerY );
			moveReady = false;

		} else {
			myGw.findSelectedObj(pointerX, pointerY );
		}

	}

	public void toggleMoveReady() {
		if (myGw.hasSelectedObj()) {
			moveReady = true;
		} else
			moveReady = false;
	}

}
