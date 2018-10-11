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
	
	//Link zur GUI über den Observer
	private Observer observer;
	

	
	public Simulation(Settings settings, Observer o){
		this.settings=settings;
		observer = o;
		aufzuege = new Aufzug[settings.maxAufzug];
		r = new Random();
		//Initiales erzeugen der Aufzüge in zufälligem Stockwerk
		for (int i = 0; i < settings.maxAufzug; i++)
			aufzuege[i] = new Aufzug(r.nextInt(settings.maxStockwerke), i);
		
<<<<<<< HEAD
		// Debug-Ausgabe für Fahrstühle zu Beginn der Simulation
		for (Aufzug each : aufzuege) {
			System.out.println("Aufzug Nr. " + each.getId() + ": Stockwerk -> " + each.getPosition());
		}
		System.out.println();
		
		// Erzeugen der Stockwerke mit zufälligen Leuten innerhalb der Stockwerke
		for (int i=0; i< settings.maxStockwerke; i++) {
			stockwerke.add(new Stockwerk());
			System.out.print("Stockwerk: " + i + " \n");
			int startAnzVonLeuten = (int)(personenGarantiert + ((personenZufallInStockwerkZuBeginn + 1) * Math.random()));
			
			// einzelnen Stockwerken werden zufällig Leute zugeordnet + Debug-Ausgabe
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
			
			// einzelnen Stockwerken werden zufällig Leute zugeordnet + Debug-Ausgabe
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
				Thread.sleep(frame*1000);
			} catch (InterruptedException e) {
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
