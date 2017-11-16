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

	private static Actionneurs INSTANCE = new Actionneurs();

	public Actionneurs() {
		this.left_Motor= WheeledChassis.modelWheel(L_MOTOR, 56).offset(-ECART_C_R);
		this.right_Motor = WheeledChassis.modelWheel(R_MOTOR, 56).offset(ECART_C_R);
		this.plier = PLIER_MOTOR;
		this.chassis = new WheeledChassis(new Wheel[] {this.left_Motor, this.right_Motor}, WheeledChassis.TYPE_DIFFERENTIAL);
	}
	
	public Chassis getChassis() {
		return this.chassis;
	}

	public RegulatedMotor getPlier() {
		return this.plier;
	}
	 
	public static Actionneurs getInstance() {	
		return INSTANCE;
	}
}
