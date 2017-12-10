import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.robotics.chassis.Chassis;

public interface RienDuTout{

	// initColor entre 4 et 5sec
	// initBouton moins de 0.2 sec
	
	/**
	 * 
	 */
	public static float ECART_C_R = (float) 61.5; // écart entre l'épicentre et la roue.
	/**
	 * Right motor.
	 */
	public static NXTRegulatedMotor R_MOTOR = Motor.C;
	/**
	 * Left motor.
	 */
	public static NXTRegulatedMotor L_MOTOR = Motor.B;
	/**
	 * Plier's motor.
	 */
	public static NXTRegulatedMotor PLIER_MOTOR = Motor.A;
	/**
	 * Ultrasonic sensor.
	 */
	public static Port IR_SENSOR = SensorPort.S1;
	/**
	 * Button.
	 */
	public static Port BUTTON_SENSOR = SensorPort.S2;
	/**
	 * Color sensor.
	 */
	public static Port COLOR_SENSOR = SensorPort.S4;
	/**
	 * Max speed.
	 */
	public static float MAX_SPEED = Float.MAX_VALUE;
	/**
	 * Normal speed
	 */
	public static float NRM_SPEED = 200.0f;
	/**
	 * Min speed.
	 */
	public static float MIN_SPEED = 60.0f;
	/**
	 * Nbr d'échantillons pour la couleur.
	 */
	public static int NB_ECH = 4;
	/**
	 * Name of file Properties
	 */
	public static String FILE_P = "config.properties";
}
