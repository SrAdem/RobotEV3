import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;

public class Actionneurs implements Properties{
	private Wheel left_Motor;
	private Wheel right_Motor;
	private NXTRegulatedMotor plier;
	private Chassis chassis;
	private Capteurs capteurs;

	public Actionneurs() {
		this.capteurs = new Capteurs();
		this.left_Motor= WheeledChassis.modelWheel(L_MOTOR, 56).offset(-ECART_C_R);
		this.right_Motor = WheeledChassis.modelWheel(R_MOTOR, 56).offset(ECART_C_R);
		this.plier = PLIER_MOTOR;
		this.chassis = new WheeledChassis(new Wheel[] {this.left_Motor, this.right_Motor}, WheeledChassis.TYPE_DIFFERENTIAL);
	}
	
	public Chassis getChassis() {
		return this.chassis;
	}

	public RegulatedMotor getPlier() {
		return plier;
	}
	
	public void tourne(float angle) {
		this.chassis.rotate(angle);
	}
	
	public void attraper() {
		plier.rotate(-800);
	}
	public void lacher() {
		plier.rotate(800);
	}
	
	public void repositionerLesPinces() {
		plier.rotate(4000);
		plier.rotate(-2000);
	}
	
	public float detectItem() throws InterruptedException {
		this.tourne(360);
		float min = Float.MAX_VALUE;
		
		//recherche de la plus petite distance
		while(this.chassis.isMoving()) {
			Thread.sleep(5);
			float distance = capteurs.getDistance();
			if(min > distance && distance > 0.32) {
				min = distance;
			}
		}
		this.chassis.waitComplete();
		
		this.chassis.setAngularSpeed(MIN_SPEED);
		this.tourne(360);
		
		//positionnement face à l'objectif
		while(this.chassis.isMoving()) {
			Thread.sleep(5);
			float distance = capteurs.getDistance();
			if(distance >= min-0.03 && distance <= min+0.03) {
				timer(50);
				this.chassis.stop();
				this.chassis.waitComplete();
			}
		}
		this.chassis.setAngularSpeed(MAX_SPEED);
		return min;
	}
	
	public void avancer(float distance) {
		this.chassis.travel(distance);
		timer(2000);
		/*
		while ( X || Y || Z) {
			this.chassis.stop();
			switch X 
			case x = 1 
		}
		return x;
		*/
	}
	
	public void avancerLongTemps() {
		this.chassis.travel(90000);
	}
	
	public static void timer(int x){
		try{
			Thread.sleep(x); //timer de x millisec
		}
		catch(InterruptedException e1){
			e1.printStackTrace();
		}
	}
	
	public Capteurs getCapteurs() {
		return capteurs;
	}

}
