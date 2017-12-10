import lejos.hardware.Button;

public class Pinces {

	public static void main(String[] args) {
		RobotActions ra = new RobotActions();
		
		boolean doChoice = true;
		System.out.println("Droite : lacher");
		System.out.println("Gauche : attraper");
		while (doChoice) {
			if(Button.LEFT.isDown()) {
				doChoice = false;
				ra.take();
			}
			if(Button.RIGHT.isDown()) {
				doChoice = false;
				ra.release();
			}
			if(Button.ENTER.isDown()) {
				doChoice = false;
				ra.pliersRepostion();
			}
		}
	    Button.ENTER.waitForPressAndRelease();
	}

}
