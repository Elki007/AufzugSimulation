package aufzug;

import java.util.Random;

public class Simulation implements Runnable {
	public final static int ANZAHL_STOCKWERKE = 10;
	
	//Attribute der Simulation
	private Aufzug[] aufzuege;
	
	//Zufallszahlen Generator
	private Random r;
	
	//Link zur GUI �ber den Observer
	private Observer observer;
	
	public Simulation(int anzahl, Observer o){
		observer = o;
		aufzuege = new Aufzug[anzahl];
		r = new Random();
		//Initiales erzeugen der Aufz�ge in zuf�lligem Stockwerk
		for (int i = 0; i < anzahl; i++)
			aufzuege[i] = new Aufzug(r.nextInt(ANZAHL_STOCKWERKE), i);
	}
	
	//getter zur Abfrage von Zust�nden
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
			//Zuf�lliger Aufzug wird ausgew�hlt
			int welcher = r.nextInt(SimulationGUI.ANZAHL_AUFZUEGE);
			//Zuf�lliges Stockwerk wird gesetzt
			aufzuege[welcher].setPosition(r.nextInt(ANZAHL_STOCKWERKE));
			//Ver�nderung --> Update von GUI wird verlangt
			observer.update();
		}
	}
}
