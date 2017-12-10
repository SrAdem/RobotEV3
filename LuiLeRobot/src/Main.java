import java.io.IOException;
import java.util.Properties;

import com.sun.corba.se.impl.orb.ParserTable.TestContactInfoListFactory;

import lejos.hardware.Button;

public class Main implements RienDuTout{

	public static void main(String[] args) throws InterruptedException {
		BattleStates battleStates = new BattleStates();
//		RobotActions ra = new RobotActions();
//		ColorSensor colorSensor = new ColorSensor(COLOR_SENSOR);
		
		Debut(battleStates);
		
//		colorSensor.calibrateColor();
//		
//		boolean essai = true;
//		while(essai) {
//			System.out.println(colorSensor.testColor().toString());
//			Button.waitForAnyPress();
//			if(Button.ESCAPE.isDown()) {
//				essai = false;
//			}
//		}
	    Button.ENTER.waitForPressAndRelease();
	}

	
	
	private static void Debut(BattleStates battleStates) throws InterruptedException {
		System.out.println("Start Posi");
		boolean doChoice = true;
		int choice = -1;
		while (doChoice) {
			if(Button.LEFT.isDown()) {
				choice = 1;
				doChoice = false;
			}
			else if(Button.RIGHT.isDown()) {
				choice = 2;
				doChoice = false;
			}
		}
		
		
		//foncer
		battleStates.firstGoal();
		
		switch (choice) {
			case 1 :
				battleStates.secondGoal(false);
				break;
			case 2 :
				battleStates.secondGoal(true);
				break;
		}
		int i=0;
		
		boolean catched = false;
		while(i<50 || Button.ENTER.isDown()) {
			i++;
			battleStates.allGoal();
		}
	}

}
