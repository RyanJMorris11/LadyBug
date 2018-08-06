//package commandObjects;
//
//import com.codename1.ui.Command;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.TextField;
//import com.codename1.ui.events.ActionEvent;
//import com.mycompany.a2.Game;
//import com.mycompany.a2.GameWorld;
///** This command class handles the actionPerformed of gui components. 
// * 
// * @author ryanmorris
// *
// */
//public class FlagNumOkay extends Command {
//	private Game myG;
//	private GameWorld myWorld;
//
//	public FlagNumOkay(Game newGame, GameWorld newGw) {
//		super("Okay");
//		myG = newGame;
//		myWorld = newGw;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent evt) {
//		TextField inputFlagNum = myG.getInputFlagNum();
//		String sCommand = inputFlagNum.getText().toString();
//		char next;
//		try {
//			next = sCommand.charAt(0);
//		} catch (Exception e) {
//			System.out.println("Invalid input.");
//			return;
//		}
//
//		switch (next) {
//		case '1':
//			myWorld.hitFlag(1);
//			break;
//		case '2':
//			myWorld.hitFlag(2);
//			break;
//		case '3':
//			myWorld.hitFlag(3);
//			break;
//		case '4':
//			myWorld.hitFlag(4);
//			break;
//		case '5':
//			myWorld.hitFlag(5);
//			break;
//		case '6':
//			myWorld.hitFlag(6);
//			break;
//		case '7':
//			myWorld.hitFlag(7);
//			break;
//		case '8':
//			myWorld.hitFlag(8);
//			break;
//		case '9':
//			myWorld.hitFlag(9);
//			break;
//		default:
//			Dialog.show("Ah-oh", "Enter a number between 1 and 9", "okay", null);
//			break;
//		}
//
//		myG.deactivateFlagDialog();
//
//	}// end action performed
//}
