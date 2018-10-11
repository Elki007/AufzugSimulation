package aufzug;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimulationGUI extends Stage implements Observer{
	//Konstanten zur Konfiguration
	public final static int ANZAHL_AUFZUEGE = 5;
	private Settings sett;
	private final int PIXEL_HOEHE = 600;
	private final int AUFZUG_PIXEL_HOEHE = PIXEL_HOEHE / Simulation.ANZAHL_STOCKWERKE;
	
	//Attribute für die Darstellung
	private Rectangle[] rechtecke;
	
	//Simulations Attribute
	private Simulation sim;
	private Thread t;

	public SimulationGUI(Settings settings) {
		super();
		//Titel setzen
		this.setTitle("Aufzugssimulation Demo");

		//Simulation erzeugen und starten
		//Simulation würde noch mehr Konfig Parameter übergeben bekommenz
		sett = settings; //? do we really need this?
		sim = new Simulation(settings, this);
		t = new Thread(sim);
		t.start();
		
		//Die Rechtecke für die Aufzüge werden initial gezeichnet
		rechtecke = new Rectangle[sett.getMaxAufzug()];
		for (int i=0; i<sett.getMaxAufzug(); i++){
			int pos = sim.getAufzugPosition(i);
			rechtecke[i] = new Rectangle(25+i*50, (sett.getFensterHoehe() - AUFZUG_PIXEL_HOEHE) - pos*AUFZUG_PIXEL_HOEHE, 50, AUFZUG_PIXEL_HOEHE);
			rechtecke[i].setFill(Color.AQUA); 
		}
		
		//Rechtecke werden auf einer leeren Pane platziert
		Pane root = new Pane();
		root.getChildren().addAll(rechtecke);
		
		//Scene graph wird gesetzt
		Scene scene = new Scene(root, (sett.getMaxAufzug() + 1)*50, sett.getFensterHoehe());
		this.setScene(scene);
	}
	
	//Simulation stoppen
	public void stop(){
		t.stop();
	}

	@Override
	public void update() {
		//Über alle Aufzüge iterieren und bei möglicher Positionsänderung anzeigen
		for (int i=0; i<sett.getMaxAufzug(); i++){
			int aenderungPosition = sim.getAufzugPositionAenderung(i);
			//Bei Positionsänderung, Animation
			if (aenderungPosition != 0)
				fahre(i, aenderungPosition);
		}
	}
	
	private void fahre(int aufzugNummer, int stockwerkAenderung){
		//Ausgabe auf der Konsole was passieren soll
		System.out.println("Aufzug " + aufzugNummer + " von " + (sim.getAufzugPosition(aufzugNummer)+stockwerkAenderung) + " in " + sim.getAufzugPosition(aufzugNummer));
		
		//Animation der Aufzüge
		TranslateTransition anpassung = new TranslateTransition(Duration.seconds(Aufzug.DAUER_PRO_STOCK), rechtecke[aufzugNummer]);
		anpassung.setByY(stockwerkAenderung*AUFZUG_PIXEL_HOEHE);
		anpassung.play();
	}
}
