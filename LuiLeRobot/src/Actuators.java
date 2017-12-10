import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;

public class Actuators implements RobotSpec{
	private Wheel left_Motor;
	private Wheel right_Motor;
	private NXTRegulatedMotor plier;
	private Chassis chassis;

	private static Actuators INSTANCE = null;

	/**
	 * Initialization of all actuators.
	 */
	public Actuators() {
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
	 
	public static Actuators getInstance() {		
		if(INSTANCE == null)
			INSTANCE = new Actuators();
		return INSTANCE;
	}
}
