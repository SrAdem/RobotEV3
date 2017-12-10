import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * -Objectif: "Sensor" à pour objectif d'initialiser les diférents capteurs (bouton, couleur, ultrason).
 * C'est un singleton, permettant ainsi de faire appel aux méthodes public de la classe en ayant instancié qu'une seul fois l'objet "Sensors".
 * -Cette classe est appelé par toutes les classes utilisant les capteurs du robot.
 * -La classe utilise la librairie LeJos, plus précisément les classes EV3ToucheSensor pour le bouton et la classe EV3UltrasonicSensor pour le capteur ultrason.
 * 		Il utilise aussi la classe "ColorSensor" (le capteur couleur)
 * -Les attribut :
 * 		-button, de type EV3TouchSensor est le bouton du robot.
 * 		-colorSensor de type ColorSensor est le capteur de couleur.
 * 		-usSensor de type EV3UltrasonicSensor est le capteur ultrason.
 * 		-distance de type SampleProvider.
 * 		-distanceSample de type float est un tableau en lien avec l'attribut distance.
 * 			A eux deux, ils permettent de retourner une distance à un objet.
 * 		-INSTANCE de type Sensors (pour le singleton).
 * -Procédure externe:
 * 		-initSensors()
 * 		-initTouchSensor()
 * 		-initColorSensor()
 * 		-initUltrasonicSensor()
 * 		-buttonIsPressed()
 * 		-getDistance()
 * 		-getButton()
 * 		-getColorSensor()
 * 		-getIrSensor()
 * 		-getInstance()
 *
 */

public class Sensors implements RobotSpec {
	private EV3TouchSensor button = null;
	private ColorSensor colorSensor = null;
	private EV3UltrasonicSensor usSensor = null;
	private SampleProvider distance;
	private float[] distanceSample;

	private static Sensors INSTANCE = null;

	/**
	 * Constructor.
	 */
	public Sensors() {
	}
	
	/**
	 * Initialization of all sensors.
	 */
	public void initSensors() {
		if (this.button == null && this.colorSensor == null && this.usSensor == null) {
			this.button = new EV3TouchSensor(BUTTON_SENSOR);
			this.colorSensor = new ColorSensor(COLOR_SENSOR);
			this.usSensor = new EV3UltrasonicSensor(IR_SENSOR);
			
			this.distance = this.usSensor.getMode("Distance");
			this.distanceSample = new float[this.distance.sampleSize()];
		}
	}
	
	/**
	 * Initialization of touchSensor.
	 */
	public void initTouchSensor() {
		if (this.button == null) {
			this.button = new EV3TouchSensor(BUTTON_SENSOR);
		}
	}
	
	/**
	 * Initialization of colorSensor.
	 */
	public void initColorSensor() {
		if (this.colorSensor == null) {
			this.colorSensor = new ColorSensor(COLOR_SENSOR);
		}
	}
	
	/**
	 * Initialization of ultrasonicSensor.
	 */
	public void initUltrasonicSensor() {
		if (this.usSensor == null) {
			this.usSensor = new EV3UltrasonicSensor(IR_SENSOR);
			this.distance = this.usSensor.getMode("Distance");
			this.distanceSample = new float[this.distance.sampleSize()];
		}
	}
	
	/**
	 * @return true if button is pressed false else.
	 */
    public boolean buttonIsPressed() {
        float[] sample = new float[1];
        button.fetchSample(sample, 0);
        return sample[0] != 0;
    }
    
    /**
     * return the distance between robot and an object in meter.
     * @return float.
     */
    public float getDistance() {
    	this.distance.fetchSample(this.distanceSample, 0);
    	return this.distanceSample[0];
    }
	
    /**
     * Return the attribut button.
     * @return EV3TouchSensor.
     */
	public EV3TouchSensor getButton() {
		return this.button;
	}
	
	/**
	 * Return the attribut colorSensor.
	 * @return ColorSensor.
	 */ 
	public ColorSensor getColorSensor() {
		return this.colorSensor;
	}

	/**
	 * Return the attribut usSensor.
	 * @return EV3UltrasonicSensor.
	 */
	public EV3UltrasonicSensor getIrSensor() {
		return this.usSensor;
	}
	
	/**
	 * Sensors is a Singleton, getInstance return the instance of this singleton 
	 * or create instance.
	 * @return Sensors.
	 */
	public static Sensors getInstance() {	
		if(INSTANCE == null)
			INSTANCE = new Sensors();
		return INSTANCE;
	}
}
