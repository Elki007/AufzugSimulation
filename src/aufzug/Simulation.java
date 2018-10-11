package aufzug;

import java.util.Random;
import java.util.Vector;

public class Simulation implements Runnable {
	//settings class
	private Settings settings;
	
	//Attribute der Simulation
	Vector<Stockwerk> stockwerke = new Vector<Stockwerk>();
	int anzPersProMin = 6;
	int secBetweenSummon = (int)(60/anzPersProMin);
	int summonTimer = 0;
<<<<<<< HEAD
	int personenGarantiert = 1;
	int personenZufallInStockwerkZuBeginn = 1;  
=======
	int personenGarantiert = 5;
	int personenMaxInStockwerkZuBeginn = 3;  
>>>>>>> branch 'master' of https://github.com/Elki007/AufzugSimulation.git
	int frame = 5; //seconds

	private Aufzug[] aufzuege;
	
	//Zufallszahlen Generator
	private Random r;
	
	//Link zur GUI �ber den Observer
	private Observer observer;
	

	
	public Simulation(Settings settings, Observer o){
		this.settings=settings;
		observer = o;
		aufzuege = new Aufzug[settings.maxAufzug];
		r = new Random();
		//Initiales erzeugen der Aufz�ge in zuf�lligem Stockwerk
		for (int i = 0; i < settings.maxAufzug; i++)
			aufzuege[i] = new Aufzug(r.nextInt(settings.maxStockwerke), i);
		
<<<<<<< HEAD
		// Debug-Ausgabe f�r Fahrst�hle zu Beginn der Simulation
		for (Aufzug each : aufzuege) {
			System.out.println("Aufzug Nr. " + each.getId() + ": Stockwerk -> " + each.getPosition());
		}
		System.out.println();
		
		// Erzeugen der Stockwerke mit zuf�lligen Leuten innerhalb der Stockwerke
		for (int i=0; i< settings.maxStockwerke; i++) {
			stockwerke.add(new Stockwerk());
			System.out.print("Stockwerk: " + i + " \n");
			int startAnzVonLeuten = (int)(personenGarantiert + ((personenZufallInStockwerkZuBeginn + 1) * Math.random()));
			
			// einzelnen Stockwerken werden zuf�llig Leute zugeordnet + Debug-Ausgabe
			for (int j = 0; j < startAnzVonLeuten; j++) {
				stockwerke.get(i).leute.add(new Person(i, settings.maxStockwerke));
				System.out.print("\tPerson #" + j + " - ");
				//print out initialized humans
				stockwerke.get(i).leute.get(j).printTwo();
			}
		}
		System.out.println("\n");

=======
		for (int i=0; i< settings.maxStockwerke; i++) {
			stockwerke.add(new Stockwerk());
			System.out.print("Stockwerk: " + i + " \n");
			int startAnzVonLeuten = (int)(personenGarantiert + (personenMaxInStockwerkZuBeginn + 1) * Math.random());
			
			// einzelnen Stockwerken werden zuf�llig Leute zugeordnet + Debug-Ausgabe
			for (int j = 0; j < startAnzVonLeuten; j++) {
				stockwerke.get(i).leute.add(new Person(i, settings.maxStockwerke));
				System.out.print("\tPerson #" + j + " - ");
				//print out initialized humans
				stockwerke.get(i).leute.get(j).printTwo();
			}
		}
		System.out.println("\n");
					
		
>>>>>>> branch 'master' of https://github.com/Elki007/AufzugSimulation.git
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
				Thread.sleep(frame*1000);
			} catch (InterruptedException e) {
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
