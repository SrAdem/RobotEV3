
public class BattleStates implements Properties {
	private ActionRobot robotActions;
	private Actionneurs actionneurs;
	private Capteurs capteurs;
	
	public BattleStates() {
		this.actionneurs = Actionneurs.getInstance();
		this.capteurs = Capteurs.getInstance();
		this.robotActions = new ActionRobot();
	}
	
	public void start() {
		this.robotActions.avancer(1000);
		while(this.actionneurs.getChassis().isMoving()) {
			if(this.capteurs.buttonIsPressed()) {
				this.robotActions.attraper();
			}
		}
	}
	
	public void firstGoal() {
		this.actionneurs.getChassis().setSpeed(MAX_SPEED, MAX_SPEED);
		this.robotActions.avancer(2500);
		this.robotActions.attraper();
		
		Capteurs.getInstance().initUltrasonicSensor();
	
		//lacher
		this.robotActions.avancer(-70);
		this.robotActions.lacher();
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
		this.robotActions.tourne(rotation);
		while(Actionneurs.getInstance().getChassis().isMoving());
		float distance = this.robotActions.detectItem(rotation, false);
		
		//aller chercher
		Capteurs.getInstance().initTouchSensor();
		this.robotActions.takeItem(distance);
	}
	
	
	
}
