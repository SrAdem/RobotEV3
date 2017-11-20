import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.filter.MeanFilter;

public class ColorSensor extends EV3ColorSensor implements RienDuTout{

	private String file;
	private float[] blue;
	private float[] red;
	private float[] green;
	private float[] yellow;
	private float[] black;
	private MeanFilter average;
	private Properties properties;
	
	public ColorSensor(Port p) {
		super(p);
		this.properties = new Properties();
		this.average = new MeanFilter(this.getRGBMode(), 1);
		this.setFloodlight(Color.WHITE);
		
		this.blue = null;
		this.red = null;
		this.green = null;
		this.yellow = null;
		this.black = null;
	}
	
	/**
	 * Calibrate different colors and save it in file (with Properties class)
	 */
	public void calibrateColor() {
		float[] cpt = {0, 0, 0};
		
		float[] blue = null;
		System.out.println("Press enter to calibrate blue...");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			blue = new float[this.average.sampleSize()];
			this.average.fetchSample(blue, 0);
			if(i == 0) {
				cpt = blue;
			}
			else {
				addTable(cpt, blue);
			}
		}
		System.out.println("hey");
		moyTable(cpt);		
		addToProperties(this.properties, cpt, "blue");
		System.out.println("yep");
		
		float[] red = null;
		System.out.println("Press enter to calibrate red...");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			red = new float[this.average.sampleSize()];
			this.average.fetchSample(red, 0);
			if(i == 0) {
				cpt = red;
			}
			else {
				addTable(cpt, red);
			}
		}
		moyTable(cpt);		
		addToProperties(this.properties, cpt, "red");
		
		float[] green = null;
		System.out.println("Press enter to calibrate green...");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			green = new float[this.average.sampleSize()];
			this.average.fetchSample(green, 0);
			if(i == 0) {
				cpt = green;
			}
			else {
				addTable(cpt, green);
			}
		}
		moyTable(cpt);		
		addToProperties(this.properties, cpt, "green");

		float[] yellow = null;
		System.out.println("Press enter to calibrate yellow...");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			yellow = new float[this.average.sampleSize()];
			this.average.fetchSample(yellow, 0);
			if(i == 0) {
				cpt = yellow;
			}
			else {
				addTable(cpt, yellow);
			}
		}
		moyTable(cpt);		
		addToProperties(this.properties, cpt, "yellow");
		
		float[] black = null;
		System.out.println("Press enter to calibrate black...");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			black = new float[this.average.sampleSize()];
			this.average.fetchSample(black, 0);
			if(i == 0) {
				cpt = black;
			}
			else {
				addTable(cpt, black);
			}
		}
		moyTable(cpt);		
		addToProperties(this.properties, cpt, "black");
		
		File file = null;
		FileOutputStream sv = null;
		
		try {
			this.saveProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Color.
	 */
	public void testColor() {
		boolean again = true;
		while (again) {
			float[] sample = new float[this.average.sampleSize()];
			System.out.println("\nPress enter to detect a color...");
			Button.ENTER.waitForPressAndRelease();
			this.average.fetchSample(sample, 0);
			double minscal = Double.MAX_VALUE;
			String color = "";
			
			double scalaire = scalaire(sample, this.blue);
			//Button.ENTER.waitForPressAndRelease();
			//System.out.println(scalaire);
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "blue";
			}
			
			scalaire = scalaire(sample, this.red);
			//System.out.println(scalaire);
			//Button.ENTER.waitForPressAndRelease();
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "red";
			}
			
			scalaire = scalaire(sample, this.green);
			//System.out.println(scalaire);
			//Button.ENTER.waitForPressAndRelease();
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "green";
			}
			
			scalaire = scalaire(sample, this.yellow);
			//System.out.println(scalaire);
			//Button.ENTER.waitForPressAndRelease();
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "yellow";
			}
			
			scalaire = scalaire(sample, this.black);
			//System.out.println(scalaire);
			//Button.ENTER.waitForPressAndRelease();
			if (scalaire < minscal) {
				minscal = scalaire;
				color = "black";
			}
			
			System.out.println("The color is " + color + " \n");
			System.out.println("Press ENTER to continue \n");
			System.out.println("ESCAPE to exit");
			Button.waitForAnyPress();
			if(Button.ESCAPE.isDown()) {
				this.setFloodlight(false);
				again = false;
			}
		}
	}
	
	/**
	 * Add the different values which the color can take.
	 */
	private void addToProperties(Properties properties, float[] cpt, String color) {
		for (int i = 0; i < 3; i++) {
			properties.setProperty(color + i, "" + cpt[i]);
		}
	}

	private void addTable(float[] cpt, float[] blue) {
		cpt[0] += blue[0];
		cpt[1] += blue[1];
		cpt[2] += blue[2];
	}

	/**
	 * Mean
	 */
	private void moyTable(float[] cpt) {
		cpt[0] = cpt[0] / cpt.length;
		cpt[1] = cpt[1] / cpt.length;
		cpt[2] = cpt[2] / cpt.length;
	}

	public double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}
	
	private void saveProperties()throws IOException
    {
            FileOutputStream fr=new FileOutputStream(FILE_P);
            this.properties.store(fr,"Properties");
            fr.close();
            System.out.println("After saving properties:"+this.properties);
    }
	
    public void loadProperties()throws IOException
    {
            this.properties.load(new FileInputStream(FILE_P));
            System.out.println("After Loading properties:" + this.properties);
    }
	
}
