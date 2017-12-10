import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.filter.MeanFilter;

public class ColorSensor extends EV3ColorSensor implements RobotSpec{

	//TODO : private float[][] => factorisation + moins de lignes
	private float[] blue;
	private float[] red;
	private float[] green;
	private float[] yellow;
	private float[] black;
	private float[] grey;
	private float[] white;
	private MeanFilter average;
	private Properties properties;

	private float[] path_color;
    private final double ERROR = 0.01;
	
	public ColorSensor(Port p) {
		super(p);
		this.properties = new Properties();
		this.average = new MeanFilter(this.getRGBMode(), 1);
		this.setFloodlight(Color.WHITE);
		
		this.blue = new float[this.average.sampleSize()];
		this.red = new float[this.average.sampleSize()];
		this.green = new float[this.average.sampleSize()];
		this.yellow = new float[this.average.sampleSize()];
		this.black = new float[this.average.sampleSize()];
		this.grey = new float[this.average.sampleSize()];
		this.white = new float[this.average.sampleSize()];
				
		if(this.properties.isEmpty()) {
			try {
				this.loadProperties();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Calibrate the different colors and save them in a file (with Properties class).
	 */
	public void calibrateColor() {
		float[] cpt = {0, 0, 0};
		
		System.out.println("Press enter to calibrate " + TableColor.Blue.toString() + " :");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			this.blue = new float[this.average.sampleSize()];
			this.average.fetchSample(this.blue, 0);
			if(i == 0) {
				cpt = this.blue;
			}
			else {
				addTable(cpt, this.blue);
			}
		}
		meanTable(cpt);		
		addAttributToProperties(cpt, TableColor.Blue.toString());
		
		System.out.println("Press enter to calibrate " + TableColor.Red.toString() + " :");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			this.red = new float[this.average.sampleSize()];
			this.average.fetchSample(this.red, 0);
			if(i == 0) {
				cpt = this.red;
			}
			else {
				addTable(cpt, this.red);
			}
		}
		meanTable(cpt);		
		addAttributToProperties(cpt, TableColor.Red.toString());
		
		System.out.println("Press enter to calibrate " + TableColor.Green.toString() + " :");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			this.green = new float[this.average.sampleSize()];
			this.average.fetchSample(this.green, 0);
			if(i == 0) {
				cpt = this.green;
			}
			else {
				addTable(cpt, this.green);
			}
		}
		meanTable(cpt);		
		addAttributToProperties(cpt, TableColor.Green.toString());

		System.out.println("Press enter to calibrate " + TableColor.Yellow.toString() + " :");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			this.yellow = new float[this.average.sampleSize()];
			this.average.fetchSample(this.yellow, 0);
			if(i == 0) {
				cpt = this.yellow;
			}
			else {
				addTable(cpt, this.yellow);
			}
		}
		meanTable(cpt);		
		addAttributToProperties(cpt, TableColor.Yellow.toString());
		
		System.out.println("Press enter to calibrate " + TableColor.Black.toString() + " :");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			this.black = new float[this.average.sampleSize()];
			this.average.fetchSample(this.black, 0);
			if(i == 0) {
				cpt = this.black;
			}
			else {
				addTable(cpt, this.black);
			}
		}
		meanTable(cpt);		
		addAttributToProperties(cpt, TableColor.Black.toString());
		
		System.out.println("Press enter to calibrate " + TableColor.Grey.toString() + " :");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			this.grey = new float[this.average.sampleSize()];
			this.average.fetchSample(this.grey, 0);
			if(i == 0) {
				cpt = this.grey;
			}
			else {
				addTable(cpt, this.grey);
			}
		}
		meanTable(cpt);		
		addAttributToProperties(cpt, TableColor.Grey.toString());
		
		System.out.println("Press enter to calibrate " + TableColor.White.toString() + " :");
		for(int i = 0 ; i < NB_ECH; i++) {
			Button.ENTER.waitForPressAndRelease();
			this.white = new float[this.average.sampleSize()];
			this.average.fetchSample(this.white, 0);
			if(i == 0) {
				cpt = this.white;
			}
			else {
				addTable(cpt, this.white);
			}
		}
		meanTable(cpt);		
		addAttributToProperties(cpt, TableColor.White.toString());
		
		try {
			this.saveProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Color.
	 */
	public TableColor testColor() {
		float[] sample = new float[this.average.sampleSize()];
		this.average.fetchSample(sample, 0);
		TableColor color = null;
		
		double minScal = Double.MAX_VALUE;
		double scalar;
		
		scalar = scalar(sample, this.blue);
		if (scalar < minScal) {
			minScal = scalar;
			color = TableColor.Blue;
		}
		
		scalar = scalar(sample, this.red);
		if (scalar < minScal) {
			minScal = scalar;
			color = TableColor.Red;
		}
		
		scalar = scalar(sample, this.green);
		if (scalar < minScal) {
			minScal = scalar;
			color = TableColor.Green;
		}
		
		scalar = scalar(sample, this.yellow);
		if (scalar < minScal) {
			minScal = scalar;
			color = TableColor.Yellow;
		}
		
		scalar = scalar(sample, this.black);
		if (scalar < minScal) {
			minScal = scalar;
			color = TableColor.Black;
		}
		
		scalar = scalar(sample, this.grey);
		if (scalar < minScal) {
			minScal = scalar;
			color = TableColor.Grey;
		}
		
		scalar = scalar(sample, this.white);
		if (scalar < minScal) {
			minScal = scalar;
			color = TableColor.White;
		}
		
		return color;
	}

	private void addTable(float[] cpt, float[] color) {
		cpt[0] += color[0];
		cpt[1] += color[1];
		cpt[2] += color[2];
	}

	private void meanTable(float[] cpt) {
		cpt[0] = cpt[0] / cpt.length;
		cpt[1] = cpt[1] / cpt.length;
		cpt[2] = cpt[2] / cpt.length;
	}

	private double scalar(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}
	
	
	//TODO : Line follower
	public void pathColor() {
		this.path_color = new float[this.average.sampleSize()];
		this.average.fetchSample(this.path_color, 0);
	}
    public boolean onPath()
    {
    	float[] sample = new float[this.average.sampleSize()];
		this.average.fetchSample(sample, 0);
		
		double scalaire = this.scalar(sample, this.path_color);
		System.out.println(scalaire < this.ERROR);
		//Button.ENTER.waitForPressAndRelease();
		
		return this.scalar(sample, this.path_color) < this.ERROR;
    }
    //end of todo
    
	/**
	 * Save colors/properties in a file.
	 */
	private void saveProperties()throws IOException
    {
            FileOutputStream fr=new FileOutputStream(FILE_P);
            this.properties.store(fr,"Properties");
            fr.close();
    }
	
	/**
	 * Load colors/properties.
	 * @throws InterruptedException 
	 */
    private void loadProperties()throws IOException, InterruptedException
    {
            this.properties.load(new FileInputStream(FILE_P));
			this.loadAttributOfProperties(this.blue, TableColor.Blue.toString());
			this.loadAttributOfProperties(this.red, TableColor.Red.toString());
			this.loadAttributOfProperties(this.green, TableColor.Green.toString());
			this.loadAttributOfProperties(this.yellow, TableColor.Yellow.toString());
			this.loadAttributOfProperties(this.black, TableColor.Black.toString());
			this.loadAttributOfProperties(this.grey, TableColor.Grey.toString());
			this.loadAttributOfProperties(this.white, TableColor.White.toString());
    }
    
	/**
	 * Add the different values which the color can take.
	 */
	private void addAttributToProperties(float[] cpt, String color) {
		for (int i = 0; i < 3; i++) {
			this.properties.setProperty(color + i, "" + cpt[i]);
		}
	}
	
	private void loadAttributOfProperties(float[] color, String c) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			color[i] = Float.parseFloat(this.properties.getProperty(c+i));
		}
	}
	
}
