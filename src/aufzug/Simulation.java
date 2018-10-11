package aufzug;

import java.util.Random;

public class Simulation implements Runnable {
	//Attribute der Simulation
	private Aufzug[] aufzuege;
	
	//Zufallszahlen Generator
	private Random r;
	
	//Link zur GUI über den Observer
	private Observer observer;
	
	//settings class
	private Settings settings;
	
	public Simulation(Settings settings, Observer o){
		this.settings=settings;
		observer = o;
		aufzuege = new Aufzug[settings.maxAufzug];
		r = new Random();
		//Initiales erzeugen der Aufzüge in zufälligem Stockwerk
		for (int i = 0; i < settings.maxAufzug; i++)
			aufzuege[i] = new Aufzug(r.nextInt(settings.maxStockwerke), i);
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
			int welcher = r.nextInt(settings.maxAufzug);//SimulationGUI.ANZAHL_AUFZUEGE);
			//Zufälliges Stockwerk wird gesetzt
			aufzuege[welcher].setPosition(r.nextInt(settings.maxStockwerke));
			//Veränderung --> Update von GUI wird verlangt
			observer.update();
		}
	}
}
