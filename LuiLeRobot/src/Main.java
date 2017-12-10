import lejos.hardware.Button;

/**
 * Objectif : lancer le robot selon la position de d�part et enclencher le programme.
 * 
 * Relations : impl�mente RobotSpec.
 * 
 * Classes utilis�es par Main : BattleStates.
 * 
 * Classes utilisant Main : aucune.
 * 
 * D�finitions des attributs: 	battleStates de type BattleStates repr�sentant les diff�rents �tats possibles du robot lors du tournoi.
 * 
 * Proc�dures : aucune.
 *
 */

public class Main implements RobotSpec{

	public static void main(String[] args) throws InterruptedException {
		BattleStates battleStates = new BattleStates();
		
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
		
		
		//rush
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
		
	    Button.ENTER.waitForPressAndRelease();
	}

}
