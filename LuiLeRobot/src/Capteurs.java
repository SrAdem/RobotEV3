import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;

public class Capteurs implements Properties {
	private EV3TouchSensor button;
	private EV3ColorSensor colorSensor;
	private EV3UltrasonicSensor usSensor;
	private SampleProvider distance;
	private float[] distanceSample;

	public Capteurs() {
		this.button = new EV3TouchSensor(BUTTON_SENSOR);
//		this.colorSensor = new EV3ColorSensor(COLOR_SENSOR);
		this.usSensor = new EV3UltrasonicSensor(IR_SENSOR);
		
		this.distance = this.usSensor.getMode("Distance");
		this.distanceSample = new float[this.distance.sampleSize()];
	}

    public boolean buttonIsPressed() {
        float[] sample = new float[1];
        button.fetchSample(sample, 0);

        return sample[0] != 0;
    }
    
    public float getDistance() {
    	this.distance.fetchSample(this.distanceSample, 0);
    	return this.distanceSample[0];
    }
    
    public void getColor() {} //get color a faire
	
	public EV3TouchSensor getButton() {
		return this.button;
	}
	
	public EV3ColorSensor getColorSensor() {
		return this.colorSensor;
	}

	public EV3UltrasonicSensor getIrSensor() {
		return this.usSensor;
	}
}