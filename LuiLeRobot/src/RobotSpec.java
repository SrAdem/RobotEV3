import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.robotics.chassis.Chassis;

/**
 * -Objectif: "Variable global" permettant de limité les modifications juste aux attributs présent dans l'interface 
 * plutot que de modifier ou insérer des valeurs bruts.
 * -Cette classe est implémenté par toutes les classes utilisant les variables présent dans la classe (Ici : Toute les classes).
 * -La classe utilise la librairie LeJos, Motor / NXTRegulatedMotor / Chassis pour les moteurs et Port pour les port du robot.
 * -Les attribut :
 * 		-ECART_C_R de type float : la distance entre le centre du chassi et les roues.
 * 		-R_MOTOR de type NXTRegulatedMotor : le port du moteur droit.
 * 		-L_MOTOR de type NXTRegulatedMotor : le port du moteur gauche.
 * 		-PLIER_MOTOR de type NXTRegulatedMotor : le port de la pince.
 * 		-IR_SENSOR de type Port : le port du capteur ultrason.
 * 		-BUTTON_SENSOR de type Port : le port du bouton.
 * 		-COLOR_SENSOR de type Port : le port du capteur de couleur
 * 		-MAX_SPEED de type float : la vitesse maximal des moteurs.
 * 		-NRM_SPEED de type flaot : la vitesse normal des moteurs.
 * 		-MIN_SPEED de type flaot : la vitesse minumal des moteurs.
 * 		-NB_ECH de type int : nombre d'échantillonage lors du prélèvement des couleurs de la table.
 * 		-FILE_P de type String : le nom du fichier contenant les vecteurs des couleurs.
 *
 */
public interface RobotSpec{	
	/**
	 * 
	 */
	public static float ECART_C_R = (float) 61.5; // �cart entre l'�picentre et la roue.
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
	 * Nbr d'�chantillons pour la couleur.
	 */
	public static int NB_ECH = 4;
	/**
	 * Name of file Properties
	 */
	public static String FILE_P = "config.properties";
}
