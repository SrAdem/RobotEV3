import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.robotics.chassis.Chassis;

public interface Properties{

		public static float ECART_C_R = (float) 61.5; // écart entre l'épicentre et la roue.
		/**
		 * Moteur droite.
		 */
		public static NXTRegulatedMotor R_MOTOR = Motor.C;
		/**
		 * Moteur gauche.
		 */
		public static NXTRegulatedMotor L_MOTOR = Motor.B;
		/**
		 * Moteur de la pince.
		 */
		public static NXTRegulatedMotor PLIER_MOTOR = Motor.A;
		/**
		 * Le capteur infrarouge.
		 */
		public static Port IR_SENSOR = SensorPort.S1;
		/**
		 * Le bouton avant.
		 */
		public static Port BUTTON_SENSOR = SensorPort.S2;
		/**
		 * Le detecteur de couleur.
		 */
		public static Port COLOR_SENSOR = SensorPort.S4;
		/**
		 * La vitesse Max.
		 */
		public static float MAX_SPEED = Float.MAX_VALUE;
		/**
		 * La vitesse Min.
		 */
		public static float MIN_SPEED = 60.0f;
}
