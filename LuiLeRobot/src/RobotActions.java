import java.util.ArrayList;

public class RobotActions implements RobotSpec{

	private Actuators actuators;
	private Sensors sensors;
	private ArrayList<TableColor> detectedLineColor; //TODO : lineFollower

	public RobotActions() {
		this.actuators = Actuators.getInstance();
		this.sensors = Sensors.getInstance();
		this.detectedLineColor = new ArrayList<>();
	}
	
	/**
	 * Turn with a given angle.
	 */
	public void rotate(float angle) {
		this.actuators.getChassis().rotate(angle);
	}
	
	/**
	 * Run with a given distance.
	 */
	public void run(float distance) {
		this.actuators.getChassis().travel(distance);
	}
	public void stop() {
		this.actuators.getChassis().stop();
		this.actuators.getChassis().waitComplete();
	}
	
	/**
	 * Clamp the pliers for take the puck.
	 */
	public void firstTake() {
		this.actuators.getPlier().rotate(-1200);
	}
	public void take() {
		this.actuators.getPlier().rotate(-900);
	}
	public void halfTake() {
		this.actuators.getPlier().rotate(-450);
	}
	
	/**
	 * Loosen the pliers for release the puck.
	 */
	public void release() {
		this.actuators.getPlier().rotate(900);
	}
	public void halfRelease() {
		this.actuators.getPlier().rotate(450);
	}
	
	/**
	 * Reposition the pliers in the initial state.
	 */
	public void pliersRepostion() {
		this.actuators.getPlier().rotate(4000);
		this.actuators.getPlier().rotate(-2000);
	}
	
	public void putAndReposition() {
		this.run(-200);
		this.release();
		while(Actuators.getInstance().getChassis().isMoving());
	}
	
	/**
	 * Return color detected by the ColorSesor.
	 */
	public TableColor detectColor() {
		return this.sensors.getColorSensor().testColor();
	}
	
	//TODO : pour lineFollower
	public void detectedColor() {
		TableColor color = this.detectColor();
		if(color != TableColor.White && color != TableColor.Grey) {
			int last = detectedLineColor.size()-1;
			if(detectedLineColor.get(last) == color) {
				detectedLineColor.add(color);
			}
		}
	}
	public void addColorToArray(TableColor color) {
		this.detectedLineColor.add(color);
	}
	public void removeColorOfArray(TableColor color) {
		if(!this.detectedLineColor.isEmpty()) {
			this.detectedLineColor.remove(color);
		}
	}
	//fin du todo
	
	/**
	 * Detect the object which is the closest of the robot.
	 */
	public float detectItem (float angle, boolean withPosi) throws InterruptedException {
		this.take();
		if( angle < 360.0 && withPosi) {
			float rotate = - angle / 2;
			this.rotate(rotate);
			while(this.actuators.getChassis().isMoving()) {
			}
		}
		this.rotate(angle);

		float min = Float.MAX_VALUE;

		//recherche de la plus petite distance
		while(this.actuators.getChassis().isMoving()) {
			Thread.sleep(5);
			float distance = this.sensors.getDistance();
			if(min > distance && distance > 0.321) {
				min = distance;
			}
		}
		this.actuators.getChassis().waitComplete();
		
		this.actuators.getChassis().setAngularSpeed(MIN_SPEED);
		this.rotate(-angle);
		
		//positionnement face � l'objectif
		while(this.actuators.getChassis().isMoving()) {
			Thread.sleep(5);
			float distance = this.sensors.getDistance();
			if(distance >= min-0.03 && distance <= min+0.03) {
				System.out.println(distance);
				if(distance >= 0.321 && distance < 0.50) 
					Thread.sleep(10);
				else if(distance >= 0.50 && distance < 75)
						Thread.sleep(20); 
				else if(distance >= 0.75 && distance < 1)
					Thread.sleep(25); 
				else if(distance >= 1 && distance < 2)
					Thread.sleep(20);
				else if(distance >= 2)
					Thread.sleep(25);
				this.stop();
				this.release();
			}
		}
		this.actuators.getChassis().setAngularSpeed(MAX_SPEED);
		return min;
	}
	
	/**
	 * Take the object detected if it's a puck.
	 */
	public boolean takeItem(float object) {
		//detecter l'objectif ayant la distance la plus courte.
		float previousDistance = object;
		float sensorDistance = previousDistance;
		
		//Avancer jusqu'a l'objet compris entre 0.32 et infini
		if( previousDistance >= sensorDistance && sensorDistance > 0.32f ) {
			this.run( (previousDistance+0.3f) * 1000.0f );
			sensorDistance = this.sensors.getDistance();
		}
		
		while( previousDistance >= sensorDistance && sensorDistance > 0.321f ) {
			previousDistance = sensorDistance;
			sensorDistance = this.sensors.getDistance();
		}
		
		// L'objet est un palet
		boolean itemIsPuck = false;
		while(sensorDistance > 0.321f && !itemIsPuck && this.actuators.getChassis().isMoving()) {
			if(this.sensors.buttonIsPressed()) {
				itemIsPuck = true;
				this.take();
			}
		}
		this.stop();
		return itemIsPuck;
	}
	
	//TODO : a dev
	public void lineFollower() {
		this.run(10000);
		while(this.sensors.getColorSensor().onPath());
		this.stop();
		this.rotate(-100);
		while(this.actuators.getChassis().isMoving()) {
			if(this.sensors.getColorSensor().onPath()) {
				this.stop();
			}
		}
	}
	
	public ArrayList<TableColor> getDetectedLineColor(){
		return this.detectedLineColor;
	}
	
}
