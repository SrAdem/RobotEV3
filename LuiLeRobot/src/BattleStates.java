/**
 * Objectifs : sp�cifie les diff�rentes �tapes durant une confrontation.
 * 
 * Relations : impl�mente l'interface RobotSpec.
 * 
 * Classes utilis�es par BattleStates : RobotActions, Actuators et Sensors.
 * 
 * Classes utilisant BattleStates : Main.
 * 
 * D�finitions des attributs: 	actuators de type Actuators repr�sente tous les actionneurs du robot,
 * 								sensors de type Sensors repr�sente tous les capteurs du robot,
 * 								robotAction de type RobotActions repr�sente les actions r�alisables du robot et
 * 								rotateOrNot de type boolean est vrai si le robot doit faire demi-tour.
 * 
 * Proc�dures externes : start(), firstGoal(), secondGoal(boolean startAt), allGoal(), goToGoal() et takeItemOrdBack(float distance).
 *
 */

public class BattleStates implements RobotSpec {
	private RobotActions robotActions;
	private Actuators actuators;
	private Sensors sensors;	
	private boolean rotateOrNot;
	
	public BattleStates() {
		this.actuators = Actuators.getInstance();
		this.sensors = Sensors.getInstance();
		this.robotActions = new RobotActions();
		this.rotateOrNot = false;
	}
	
	public void start() {
		this.robotActions.run(1000);
		while(this.actuators.getChassis().isMoving()) {
			if(this.sensors.buttonIsPressed()) {
				this.robotActions.take();
			}
		}
	}

	/**
	 * Run and take the first puck in front of the robot and bring it to the "zone".
	 * Initialization of the ultrasonicSensor.
	 */
	public void firstGoal() {
		this.actuators.getChassis().setSpeed(MAX_SPEED, MAX_SPEED);
		this.robotActions.run(2600);// TODO: a opti mais d�ja pas mal
		this.robotActions.firstTake();
		
		this.sensors.initUltrasonicSensor();
	
		this.robotActions.putAndReposition();
	}
	
	// false : Left 		true : Right
	public void secondGoal(boolean startAt) throws InterruptedException {
		int rotation;
		if(startAt) {
			rotation = -90;
		}
		else {
			rotation = 90;
		}
		
		//positioner et balayer
		this.robotActions.rotate(rotation);
		this.sensors.initTouchSensor();//init button
		this.sensors.initColorSensor();//init color
		while(Actuators.getInstance().getChassis().isMoving());
		float distance = this.robotActions.detectItem(rotation, false);
		
		//aller chercher
		this.takeItemOrdBack(distance);
	}
	/**
	 * Search a puck, then take it and bring it to the delimited area.
	 * 
	 */
	public void allGoal() throws InterruptedException {
		if(this.rotateOrNot) {
			this.robotActions.rotate(180);
		}

		while(Actuators.getInstance().getChassis().isMoving());
		float distance = this.robotActions.detectItem(90, true);
		this.takeItemOrdBack(distance);
	}
	 /**
	  * Bring the puck to the delimited area.
	  */
	public void goToGoal() {
		this.actuators.getChassis().setLinearSpeed(NRM_SPEED);
		this.robotActions.rotate(180);
		while(this.actuators.getChassis().isMoving());
		this.robotActions.run(10000);
		TableColor color = this.robotActions.detectColor();
		while(color != TableColor.White) {
			color = this.robotActions.detectColor();
		}
		this.robotActions.stop();
		this.actuators.getChassis().setLinearSpeed(MAX_SPEED);
		this.robotActions.putAndReposition();
	}
	/**
	 * If the puck is taken, do goToGoal(), else back.
	 * 
	 */
	private void takeItemOrdBack(float distance) {
		boolean itemIsPuck = this.robotActions.takeItem(distance);
		if(itemIsPuck) {
			this.goToGoal();
			this.rotateOrNot = true;
		}
		else {
			this.robotActions.run(-200);
			while(this.actuators.getChassis().isMoving());
			this.rotateOrNot = false;
		}

	}
}
