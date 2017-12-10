import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class Sensors implements RienDuTout {
	private EV3TouchSensor button = null;
	private ColorSensor colorSensor = null;
	private EV3UltrasonicSensor usSensor = null;
	private SampleProvider distance;
	private float[] distanceSample;

	private static Sensors INSTANCE = null;

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
	 * Initialization of ultrasonicSensor..
	 */
	public void initUltrasonicSensor() {
		if (this.usSensor == null) {
			this.usSensor = new EV3UltrasonicSensor(IR_SENSOR);
			this.distance = this.usSensor.getMode("Distance");
			this.distanceSample = new float[this.distance.sampleSize()];
		}
	}
	
	/**
	 * Initialization of the button.
	 */
    public boolean buttonIsPressed() {
        float[] sample = new float[1];
        button.fetchSample(sample, 0);
        return sample[0] != 0;
    }
    
    public float getDistance() {
    	this.distance.fetchSample(this.distanceSample, 0);
    	return this.distanceSample[0];
    }
	
	public EV3TouchSensor getButton() {
		return this.button;
	}
	
	public ColorSensor getColorSensor() {
		return this.colorSensor;
	}

	public EV3UltrasonicSensor getIrSensor() {
		return this.usSensor;
	}
	
	public static Sensors getInstance() {	
		if(INSTANCE == null)
			INSTANCE = new Sensors();
		return INSTANCE;
	}
}
