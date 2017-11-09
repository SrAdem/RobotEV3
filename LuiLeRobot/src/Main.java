import lejos.hardware.Button;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Actionneurs actionneur = new Actionneurs();
		boolean Pasgrandchose = false;
		
//		while (!actionneur.getCapteurs().buttonIsPressed()) {
//		System.out.println(actionneur.getCapteurs().getDistance());
//	}
		
		//detecter l'objectif ayant la distance la plus courte.
		float previousDistance = actionneur.detectItem();
		float sensorDistance = previousDistance;
		
		//Avancer jusqu'a l'objet compris entre 0.32 et infini
		if( previousDistance >= sensorDistance && sensorDistance > 0.32f ) {
			System.out.println("allow");
			actionneur.avancer( (previousDistance+0.2f) * 1000.0f );
			sensorDistance = actionneur.getCapteurs().getDistance();
		}
		
		while( previousDistance >= sensorDistance && sensorDistance > 0.35f ) {
			System.out.println(previousDistance);
			System.out.println(sensorDistance);
			previousDistance = sensorDistance;
			sensorDistance = actionneur.getCapteurs().getDistance();
		}
		
		if(sensorDistance < 0.35f && sensorDistance > 0.32f) {;
			boolean stop = true;
			while(stop && actionneur.getChassis().isMoving()) {
				if(actionneur.getCapteurs().buttonIsPressed()) {
					stop = false;
					Pasgrandchose = true;
					actionneur.attraper();
				}
			}
		}

		actionneur.getChassis().stop();
		actionneur.getChassis().waitComplete();
		if(Pasgrandchose) {
			actionneur.lacher();
		}
	    Button.ENTER.waitForPressAndRelease();
	   	
	}

}
