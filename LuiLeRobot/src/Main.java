import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ActionRobot robotAction = new ActionRobot();
		BattleStates battleStates = new BattleStates();
		Battery battery = new Battery();
		
		System.out.println(battery.getVoltage());
		
//		while (!actionneur.getCapteurs().buttonIsPressed()) {
//		System.out.println(actionneur.getCapteurs().getDistance());
//	}
		
		Capteurs.getInstance().initUltrasonicSensor();
		Capteurs.getInstance().initTouchSensor();
		float i = robotAction.detectItem(90, false);
		robotAction.takeItem(i);
		
		
//		System.out.println("Start Posi");
//		boolean doChoice = true;
//		int choice = -1;
//		while (doChoice) {
//			if(Button.ENTER.isDown()) {
//				System.out.println("pas encore mec");
//				choice = 0;
////				choice = false;
//			}
//			else if(Button.LEFT.isDown()) {
//				choice = 1;
//				doChoice = false;
//			}
//			else if(Button.RIGHT.isDown()) {
//				choice = 2;
//				doChoice = false;
//			}
//		}
//		
//		//foncer
//		battleStates.firstGoal();
//		
//		switch (choice) {
//			case 0 :
//				//pas encore
//				break;
//			case 1 :
//				battleStates.secondGoal(false);
//				break;
//			case 2 :
//				battleStates.secondGoal(true);
//				break;
//		}
		
	    Button.ENTER.waitForPressAndRelease();
	}

}
