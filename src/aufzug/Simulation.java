package aufzug;

import java.util.Random;

public class Simulation implements Runnable {
	//Attribute der Simulation
	private Aufzug[] aufzuege;
	
	//Zufallszahlen Generator
	private Random r;
	
	//Link zur GUI �ber den Observer
	private Observer observer;
	
	//settings class
	private Settings settings;
	
	public Simulation(Settings settings, Observer o){
		this.settings=settings;
		observer = o;
		aufzuege = new Aufzug[settings.maxAufzug];
		r = new Random();
		//Initiales erzeugen der Aufz�ge in zuf�lligem Stockwerk
		for (int i = 0; i < settings.maxAufzug; i++)
			aufzuege[i] = new Aufzug(r.nextInt(settings.maxStockwerke), i);
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
			int welcher = r.nextInt(settings.maxAufzug);//SimulationGUI.ANZAHL_AUFZUEGE);
			//Zuf�lliges Stockwerk wird gesetzt
			aufzuege[welcher].setPosition(r.nextInt(settings.maxStockwerke));
			//Ver�nderung --> Update von GUI wird verlangt
			observer.update();
		}
	}
}
