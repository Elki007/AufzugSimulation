package aufzug;

import java.util.Random;

public class Simulation implements Runnable {
	public final static int ANZAHL_STOCKWERKE = 10;
	
	//Attribute der Simulation
	private Aufzug[] aufzuege;
	
	//Zufallszahlen Generator
	private Random r;
	
	//Link zur GUI über den Observer
	private Observer observer;
	
	public Simulation(int anzahl, Observer o){
		observer = o;
		aufzuege = new Aufzug[anzahl];
		r = new Random();
		//Initiales erzeugen der Aufzüge in zufälligem Stockwerk
		for (int i = 0; i < anzahl; i++)
			aufzuege[i] = new Aufzug(r.nextInt(ANZAHL_STOCKWERKE), i);
	}
	
	//getter zur Abfrage von Zuständen
	public int getAufzugPosition(int nummer){
		return aufzuege[nummer].getPosition();
	}
	
	public int getAufzugPositionAenderung(int nummer){
		return aufzuege[nummer].getPositionVeraenderung();
	}

	@Override
	public void run() {
		//Dauerschleife zur Simulation
		while (true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Zufälliger Aufzug wird ausgewählt
			int welcher = r.nextInt(SimulationGUI.ANZAHL_AUFZUEGE);
			//Zufälliges Stockwerk wird gesetzt
			aufzuege[welcher].setPosition(r.nextInt(ANZAHL_STOCKWERKE));
			//Veränderung --> Update von GUI wird verlangt
			observer.update();
		}
	}
}
