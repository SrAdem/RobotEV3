import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;

/**
 * -Objectif: "Actuators" à pour objectif d'initialiser les diférents moteurs (roues et pince).
 * C'est un singleton, permettant ainsi de faire appel aux méthodes public de la classe en ayant instancié qu'une seul fois l'objet "Actuators".
 * -Cette classe est appelé par toutes les classes utilisant les moteurs du robot.
 * -La classe utilise la librairie LeJos, plus précisément les classes lié à "chassis" permettant de gérer 2 moteurs en même temps.
 * 		Il utilise aussi la classe "NXTRegulatedMorto" (le moteur).
 * 		Il implémente l'interface RobotSpec.
 * -Les attribut :
 * 		-left_Motor et right_Motor sont de type "Wheel" représentant respectivement le moteur gauche et droite. 
 * 			Ensemble, ils forment l'attibut chassis de classe "Chassis"
 * 		-plier est de type "NXTRegulatedMotor", il représent la pince du robot.
 * 		-INSTANCE de type Actuators (pour le singleton).
 * -Procédure externe:
 * 		-getChasis()
 * 		-getPlier()
 * 		-getInstance()
 *
 */
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
	
	/**
	 * Return the attribut chassis, the 2 motors of the robot.
	 * @return Chassis.
	 */
	public Chassis getChassis() {
		return this.chassis;
	}

	/**
	 * Return the attribut plier, the plier of the robot.
	 * @return RegulatedMotor.
	 */
	public RegulatedMotor getPlier() {
		return this.plier;
	}
	 
	/**
	 * Actuators is a Singleton, getInstance return the instance of this singleton 
	 * or create instance.
	 * @return Actuators.
	 */
	public static Actuators getInstance() {		
		if(INSTANCE == null)
			INSTANCE = new Actuators();
		return INSTANCE;
	}
}
