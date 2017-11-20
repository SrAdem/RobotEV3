import java.io.IOException;
import java.util.Properties;
import lejos.hardware.Button;

public class Main implements RienDuTout{

	public static void main(String[] args) throws InterruptedException {
		BattleStates battleStates = new BattleStates();
		Properties properties = new Properties();
		
//		Debut(battleStates);

		ColorSensor ColorSensor = new ColorSensor(COLOR_SENSOR);
		try {
			ColorSensor.loadProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ColorSensor.testColor();
		
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
	}

}
