
public class ActionRobot implements Properties{

	private Actionneurs actionneurs;
	private Capteurs capteurs;

	public ActionRobot() {
		this.actionneurs = Actionneurs.getInstance();
		this.capteurs = Capteurs.getInstance();
	}
	
	public void tourne(float angle) {
		this.actionneurs.getChassis().rotate(angle);
	}
	
	public void avancer(float distance) {
		this.actionneurs.getChassis().travel(distance);
//		timer(2000);
	}
	
	public void attraper() {
		this.actionneurs.getPlier().rotate(-900);
	}
	public void lacher() {
		this.actionneurs.getPlier().rotate(900);
	}
	
	public void repositionerLesPinces() {
		this.actionneurs.getPlier().rotate(4000);
		this.actionneurs.getPlier().rotate(-2000);
	}
		
	public void timer(int x){
		try{
			Thread.sleep(x); //timer de x millisec
		}
		catch(InterruptedException e1){
			e1.printStackTrace();
		}
	}
		
	public float detectItem (float angle, boolean withPosi) throws InterruptedException {
		if( angle < 360.0 && withPosi) {
			float rotate = - angle / 2;
			this.tourne(rotate);
			while(this.actionneurs.getChassis().isMoving()) {}
		}
		this.tourne(angle);

		float min = Float.MAX_VALUE;

		//recherche de la plus petite distance
		while(this.actionneurs.getChassis().isMoving()) {
//			Thread.sleep(5);
			float distance = this.capteurs.getDistance();
			if(min > distance && distance > 0.321) {
				min = distance;
			}
		}
		this.actionneurs.getChassis().waitComplete();
		
		this.actionneurs.getChassis().setAngularSpeed(MIN_SPEED);
		this.tourne(-angle);
		
		//positionnement face à l'objectif
		while(this.actionneurs.getChassis().isMoving()) {
//			Thread.sleep(5);
			float distance = this.capteurs.getDistance();
			if(distance >= min-0.03 && distance <= min+0.03) {
				this.timer(50);
				this.actionneurs.getChassis().stop();
				this.actionneurs.getChassis().waitComplete();
			}
		}
		this.actionneurs.getChassis().setAngularSpeed(MAX_SPEED);
		return min;
	}
	
	public void takeItem(float object) {

		boolean Pasgrandchose = false;
		
		//detecter l'objectif ayant la distance la plus courte.
		float previousDistance = object;
		float sensorDistance = previousDistance;
		
		//Avancer jusqu'a l'objet compris entre 0.32 et infini
		if( previousDistance >= sensorDistance && sensorDistance > 0.32f ) {
			this.avancer( (previousDistance+0.2f) * 1000.0f );
			sensorDistance = this.capteurs.getDistance();
		}
		
		while( previousDistance >= sensorDistance && sensorDistance > 0.321f ) {
			previousDistance = sensorDistance;
			sensorDistance = this.capteurs.getDistance();
		}
		
		// L'objet est un palet
		boolean palet = true;
		while(sensorDistance > 0.321f && palet) {
			if(this.capteurs.buttonIsPressed()) {
				Pasgrandchose = true;
				palet = false;
				this.attraper();
			}
		}
		
		actionneurs.getChassis().stop();
		actionneurs.getChassis().waitComplete();
		
		if(Pasgrandchose) {
			this.lacher();
		}
	}
	
}
