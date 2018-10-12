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
	int personenGarantiert = 1;
	int personenMaxInStockwerkZuBeginn = 1;  
	int frame = 1; //seconds

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
		
		// Debug-Ausgabe für Fahrstühle zu Beginn der Simulation
		for (Aufzug each : aufzuege) {
			System.out.println("Aufzug Nr. " + each.getId() + ": Stockwerk -> " + each.getPosition());
		}
		System.out.println();
		
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
					
		
	}
	
	//getter zur Abfrage von Zuständen
	public int getAufzugPosition(int nummer){
		return aufzuege[nummer].getPosition();
	}
	
	public int getAufzugPositionAenderung(int nummer){
		return aufzuege[nummer].getPositionVeraenderung();
	}
	
	public Vector<Person> getAnzleuteAnDerEtage(int i) {
		return stockwerke.get(i).leute;
	}
	
	private void summonPerson() {
		if (summonTimer >= secBetweenSummon) {
        	int randomStockwerk = (int)((Math.random() * (settings.maxStockwerke)));
			stockwerke.get(randomStockwerk).leute.add(new Person(randomStockwerk, settings.maxStockwerke));
        	summonTimer = summonTimer % secBetweenSummon;            	
        }
	}
	
	// Vermindert Geduld und gibt Anzahl der Personen pro Stockwerk aus
	private void updateLoyalty() {
		//System.out.print("|| ");
		for (int i = 0; i < settings.maxStockwerke; i++) {
        	//lower loyalty for each person, who isn't in an elevator
        	stockwerke.get(i).geduldsenken(frame);
        	//Show current amount for each flour
        	//System.out.print("  St-" + i + "  #P-" + stockwerke.get(i).leute.size() + "  ||");
        }
        //System.out.print("\n");
	}
	
	// Geht Aufzüge durch und schaut, ob im Stockwerk vom Aufzug Leute einsteigen
	private void leuteSteigenEin() {
		for (Aufzug aufzug : aufzuege) {
			// Wenn Aufzug nicht in Bewegung
			if (aufzug.getInBewegung() == false) {
				// Gehe Leute im Stockwerk vom Aufzug durch (von hinten beginnend)
				int zaehltEinsteiger = 0;
				for (int i = (stockwerke.get(aufzug.getPosition()).leute.size()-1); i >= 0; i--) {
					// Wenn Person nicht am Ziel
					if (stockwerke.get(aufzug.getPosition()).leute.get(i).amZiel == false) {
						// Person steigt ein, wenn es nicht Zielstockwerk ist
						aufzug.setPersonSteigtEin(stockwerke.get(aufzug.getPosition()).leute.get(i));
						// Person verlässt somit Stockwerk
						stockwerke.get(aufzug.getPosition()).leute.remove(i);
						zaehltEinsteiger++;
					}
				}
				// Debug-Ausgabe, wenn jemand einsteigt
				if (zaehltEinsteiger > 0) 
					System.out.println("Personen die in Stockwerk " + aufzug.getPosition() + " in A" + aufzug.getId() + " eingestiegen sind: " + zaehltEinsteiger);
				
			}
		}
	}
	
	private void leuteSteigenEin_2() {
		for (Aufzug aufzug : aufzuege) {
			// Wenn Aufzug nicht in Bewegung
			// Einsteigen möglich, wenn Aufzug mindestens 1 Sek. wartend und maximal 1 Sekunde vor losfahren (Türöffnungszeit)
			if (aufzug.getWartend() == true && aufzug.getDauerWartezeit() > 1 && aufzug.getDauerWartezeit() < aufzug.getWartezeitMax() - 1) {
				// Gehe Leute im Stockwerk vom Aufzug durch (von hinten beginnend)
				int zaehltEinsteiger = 0;
				for (int i = (stockwerke.get(aufzug.getPosition()).leute.size()-1); i >= 0; i--) {
					// Wenn Person nicht am Ziel
					if (stockwerke.get(aufzug.getPosition()).leute.get(i).amZiel == false) {
						// Person steigt ein, wenn es nicht Zielstockwerk ist
						aufzug.setPersonSteigtEin(stockwerke.get(aufzug.getPosition()).leute.get(i));
						// Person verlässt somit Stockwerk
						//stockwerke.get(aufzug.getPosition()).leute.remove(i);
						if (stockwerke.get(aufzug.getPosition()).leute.get(i).eingestiegen == false) {
							stockwerke.get(aufzug.getPosition()).leute.get(i).steigEin = true;
						}
						zaehltEinsteiger++;
					}
				}
				// Debug-Ausgabe, wenn jemand einsteigt
				if (zaehltEinsteiger > 0) 
					System.out.println("Personen die in Stockwerk " + aufzug.getPosition() + " in A" + aufzug.getId() + " eingestiegen sind: " + zaehltEinsteiger);
				
			}
		}
	}
	
	// Geht Aufzüge durch und schaut, ob im Stockwerk vom Aufzug Leute ihr Ziel erreicht haben
	private void leuteSteigenAus() {
		for (Aufzug aufzug : aufzuege) {
			// Wenn Aufzug nicht in Bewegung
			if (aufzug.getInBewegung() == false) {
				// Ist eine Personen im Aufzug am Ziel? (von hinten beginnend)
				int zaehltAussteiger = 0;
				for (int i = (aufzug.getAnzahlLeuteInFahrstuhl() - 1); i >= 0; i--) {
					// Wenn eine Person ihr Zielstockwerk erreicht 
					if (aufzug.getPersonAnPosition(i).zielStockwerk == aufzug.getPosition()) {
						// Person ist am Ziel
						aufzug.getPersonAnPosition(i).amZiel = true;
						// Steigt in Stockwerk ein & aus Auszug aus
						stockwerke.get(aufzug.getPosition()).leute.add(aufzug.getPersonAnPosition(i));
						aufzug.setPersonAnPositionSteigtAus(i);
						
						// Meldung, dass Person in Stockwerk x ausgestiegen ist
						zaehltAussteiger++;
					}
				}
				// Debug-Ausgabe, wenn jemand aussteigt
				if (zaehltAussteiger > 0) 
					System.out.println("Personen die in Stockwerk " + aufzug.getPosition() + " aus A" + aufzug.getId() + " ausgestiegen sind: " + zaehltAussteiger);
			}
		}	
	}

	/*
	 *  Aufzüge bleiben für die Zeit der Stockwerkdauer * Stockwerkveraenderung im Zustand Bewegung
	 *  Daraufhin beginnt die Wartezeit 
	 */
	private void aufzuegeInBewegung() {
		for (Aufzug aufzug : aufzuege) {
			if (aufzug.getInBewegung() == true) {
				if (aufzug.getDauerBewegung() > (2 + (Math.abs(aufzug.getStockwerkVeraenderung()) * aufzug.getDAUER_PRO_STOCK()))) {
					aufzug.setInBewegung(false);
					aufzug.setWartezeitStart();
					aufzug.setWartet(true);
				}
			}
		}
	}
	
	// Wartezeit beginnt und endet nach WartezeitMax vom Aufzug
	private void aufzuegeWarten() {
		for (Aufzug aufzug : aufzuege) {
			if (aufzug.getWartend() == true) {
				if (aufzug.getDauerWartezeit() > aufzug.getWartezeitMax()) {
					aufzug.setWartet(false);
				}
			}
		}
	}
	
	@Override
	public void run() {
		//Dauerschleife zur Simulation
		while (true){
			try {
				Thread.sleep(frame*1000);
				summonTimer +=4;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			summonPerson();
			updateLoyalty();
			
			/*
			 * Aufzüge werden wieder in Bereitschaft gesetzt, wenn sie in einem Stockwerk angekommen sind
			 * 
			 * Gedanke:
			 * - ab .setBewegungStart() wird gezählt
			 * - Aufzug überquert eine Anzahl von Stockwerken in einer bestimmten Zeit -> abs(Stockwerkänderung) * Stockwerkzeit
			 * - dazu werden 2 Wartesekunden addiert
			 * - inwiefern ist frame bzw. Aktualisierungsrate zu beachten? Eigentlich nicht weiter - gibt zwar den nächsten Anstoß und könnte kleiner sein, aber nicht wichtig
			 */
			
			
			aufzuegeInBewegung();
			
			aufzuegeWarten();
			
			
			
			/*
			 *  Ein- und Aussteigen von Leuten in Aufzüge
			 */
			//einUndAussteigen();
			leuteSteigenEin_2();
			leuteSteigenAus();			
			
			/*
			 *  Bewegungsänderungen für wartende Aufzüge
			 */
			// Zufälliger Aufzug wird ausgewählt (mit inBewegung -> false) - 100 Versuche
			int aufzugZufaellig;
			int stockwerkZufaellig;
			int gegenEndlosSchleife = 0;
			
			// Es wird ein Aufzug gesucht, der nicht in Bewegung ist
			// Für diesen Aufzug wird ein zufälliges Ziel gesucht und das Stockwerk als neue Position gesetzt
			while (gegenEndlosSchleife < 100) {
				// Zufälliger Aufzug der nicht in Bewegung ist - maximal 100 Versuche
				aufzugZufaellig = r.nextInt(settings.getMaxAufzug());
				if (aufzuege[aufzugZufaellig].getInBewegung() == false && aufzuege[aufzugZufaellig].getWartend() == false) {
					// Zufälliger Stockwerk
					stockwerkZufaellig = r.nextInt(settings.maxStockwerke);
					while (stockwerkZufaellig == aufzuege[aufzugZufaellig].getPosition()) {
						stockwerkZufaellig = r.nextInt(settings.maxStockwerke);
					}
					
					// Zufälliges Stockwerk wird gesetzt
					aufzuege[aufzugZufaellig].setPosition(stockwerkZufaellig);
					
					// neu (noch nicht komplett umgesetzt) - noch muss auch Stefans setPosition verwendet werden
					// reicht erst einmal, wenn es dazu benutzt werden kann, um "inBewegung" zu steuern
					aufzuege[aufzugZufaellig].setStockwerkNeu(stockwerkZufaellig);
					
					// Problem: Aufzüge müssten nach Stockwerkzeit * Positionsveränderung wieder inBewegung->false gesetzt werden
					// Aktuell: Jeder Aufzug fährt nur einmal
					aufzuege[aufzugZufaellig].setInBewegung(true);
					aufzuege[aufzugZufaellig].setBewegungStart();
					
					aufzuege[aufzugZufaellig].setStockwerkVeraenderung();
					
					// Debug-Ausgabe "A0 fährt jetzt: 1 -> 3"
					System.out.println("A" + aufzuege[aufzugZufaellig].getId() + " fährt jetzt: " + aufzuege[aufzugZufaellig].getStockwerkAlt() + " -> " + aufzuege[aufzugZufaellig].getStockwerkAktuell());
					
					/*
					System.out.println("A" + aufzuege[aufzugZufaellig].getId() + " Aktuell: " + aufzuege[aufzugZufaellig].getStockwerkAktuell());
					System.out.println("A" + aufzuege[aufzugZufaellig].getId() + " Alt: " + aufzuege[aufzugZufaellig].getStockwerkAlt());
					System.out.println("A" + aufzuege[aufzugZufaellig].getId() + " Unterschied: " + aufzuege[aufzugZufaellig].getStockwerkVeraenderung());
					System.out.println();
					*/
					
					// Ohne "break;" wird für alle Aufzüge ein neues Ziel ermittelt (100 Versuche aus der Aufzugwahl)
					//break;
				}
				gegenEndlosSchleife++;
			}
					
			
			/*
			 * Experiment mit Zeit
			 * Idee: 
			 * - Aufzug bekommt eine Startzeit bei Bewegung und wenn Startzeit
			 * - Wenn Startzeit + Stockwerkzeit*Stockwerkänderung > Jetzt -> False
			 * 
			 * Aufzug 0 wird beobachtet.
			 */
			
			/*
			// Alte Implementation von vor der Löschung - kann aktuell nicht genutzt werden (fehlende Methoden)
			if (aufzugZufaellig == 0) {
				//int alter = (int)((System.currentTimeMillis() - aufzuege[aufzugZufaellig].getDauerBewegung())/1000);
				//System.out.println("\n\nSo alt ist Aufzug 0 in Sekunden: " + alter + "\n\n");
				System.out.println("Altes Stockwerk: " + aufzuege[aufzugZufaellig].getStockwerkAlt());
				System.out.println("Aktuelles Stockwerk: " + aufzuege[aufzugZufaellig].getStockwerkAktuell());
				aufzuege[aufzugZufaellig].setStockwerkVeraenderung();
				System.out.println("Stockwerkveränderung: " + aufzuege[aufzugZufaellig].getStockwerkVeraenderung());
			}	
			*/
			
			
			/*
			 * Debug-Ausgaben
			 */
			
			// Debug-Ausgabe: "Aufzugnummer" in "Stockwerknummer" mit "Personenanzahl" - bspw. "A0 in S0 mit P-5"
			for (Aufzug each : aufzuege) {
				System.out.print("A" + each.getId() + " ist dann in S" + each.getPosition() + " mit P-" + each.getAnzahlLeuteInFahrstuhl() + "\n");
			}
			System.out.println();
			
			// Debug-Ausgabe: "Stockwerknummer" mit "Personenanzahl" - bspw. "S0 mit 3" 
			for (int i = 0; i < settings.getMaxStockwerk(); i++) {
				System.out.print("S" + i + " mit " + stockwerke.get(i).leute.size() + "\n");
			}
			System.out.println("\n##########################\n");
			
			//Veränderung --> Update von GUI wird verlangt
			observer.update(); 
		}
	}
}
