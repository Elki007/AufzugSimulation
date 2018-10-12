package aufzug;

import java.util.Vector;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimulationGUI extends Stage implements Observer{
	//Konstanten zur Konfiguration
	//public final static int ANZAHL_AUFZUEGE = 5;
	private Settings settings;
	//private final int PIXEL_HOEHE = 600;
	//private final int AUFZUG_PIXEL_HOEHE = PIXEL_HOEHE / Simulation.ANZAHL_STOCKWERKE;
	
	//Attribute für die Darstellung
	private Rectangle[] aufzuge;
	private Rectangle[] stockwerke;
	private Color[] colors;
	private Vector<Circle> humans = new Vector<Circle>();
	
	//Simulations Attribute
	private Simulation sim;
	private Thread t;

	int stockwerkHohe;
	Pane root = new Pane();
	
	public SimulationGUI(Settings settings) {
		super();
		Platform.setImplicitExit(false);
		//Titel setzen
		this.setTitle("Aufzugssimulation Demo");
		this.setWidth(settings.w);
		this.setHeight(settings.h+44);
		//Rechtecke werden auf einer leeren Pane platziert
		

		//Simulation erzeugen und starten
		//Simulation würde noch mehr Konfig Parameter übergeben bekommenz
		this.settings = settings; //? do we really need this?
		sim = new Simulation(settings, this);
		t = new Thread(sim);
		t.start();
		
		stockwerkHohe = (settings.h / settings.maxStockwerke);
		
		//color array
		colors = new Color[settings.maxStockwerke];
		for (int i=0;i<settings.maxStockwerke;i++) {
			colors[i]=Color.rgb((int)(255-Math.random()*100),(int)(255-Math.random()*100),(int)(255-Math.random()*100));
		}
		
		drawStockwerke();
		root.getChildren().addAll(stockwerke);
		
		drawElevators();
		root.getChildren().addAll(aufzuge);
		
		drawHumans(root);
		
		//Scene graph wird gesetzt
		Scene scene = new Scene(root, (settings.getMaxAufzug() + 1)*50, settings.getFensterHoehe());
		this.setScene(scene);
	}
	public void drawElevators() {
		//Die Rechtecke für die Aufzüge werden initial gezeichnet
		aufzuge = new Rectangle[settings.maxAufzug];
		//System.out.println("Etagen: "+settings.maxStockwerke+" #Aufzuge:"+settings.maxAufzug);
		for (int i=0; i<settings.maxAufzug; i++){
			int pos = sim.getAufzugPosition(i);
			int iReverted = Math.abs(pos-settings.maxStockwerke+1);
			
			//System.out.println("pos="+pos+"  Rev="+iReverted+" ");
			aufzuge[i] = new Rectangle(50 + i * 60, iReverted*stockwerkHohe, 50, stockwerkHohe);
			aufzuge[i].setFill(Color.AQUA); 
		}
	}
	
	public void drawStockwerke() {
		stockwerke = new Rectangle[settings.maxStockwerke];
		for (int i=0; i<settings.maxStockwerke;i++) { 
			stockwerke[i] = new Rectangle(0, i*stockwerkHohe, settings.w,stockwerkHohe); 
			stockwerke[i].setFill(colors[i]);//Color.rgb((int)(255-Math.random()*100),(int)(255-Math.random()*100),(int)(255-Math.random()*100)));
			//root.getChildren().add(stockwerke.get(i)); 
		}
	}
	public void drawStockwerkeRechts() {
		stockwerke = new Rectangle[settings.maxStockwerke];
		for (int i=0; i<settings.maxStockwerke;i++) { 
			stockwerke[i] = new Rectangle(settings.w/2, i*stockwerkHohe, settings.w,stockwerkHohe); 
			stockwerke[i].setFill(colors[i]);//Color.rgb((int)(255-Math.random()*100),(int)(255-Math.random()*100),(int)(255-Math.random()*100)));
			//root.getChildren().add(stockwerke.get(i)); 
		}
	}
	
	public void humanGoHome() {
		//System.out.println(humans.size());
		int overall =0;
		int iiReverted;
		for (int i=0; i<settings.maxStockwerke;i++) {
			Vector<Person> current = sim.getAnzleuteAnDerEtage(i);
			int number =0;
			for (int p=0; p <current.size() ;p++) {
				//System.out.println("Geduld: " +current.get(p).geduld);
				if (current.get(p).geduld<=4) {//frame
					//System.out.println("Person No: " +overall+"goes home" );
					TranslateTransition anpassung = new TranslateTransition(Duration.seconds(6), humans.get(overall));//frame
					anpassung.setByX(200);
					anpassung.play();
				}
				overall++;
			}
		}
	}
	
	public void drawHumans(Pane root) {
		int overall =0;
		int iiReverted;
		for (int i=0; i<settings.maxStockwerke;i++) {
			Vector<Person> current = sim.getAnzleuteAnDerEtage(i);
			int number =0;
			for (int p=0; p <current.size() ;p++) {
				int centerX;
				int centerY;
				double radius;
				if (current.get(p).getGewicht()<100) {
					radius = 5;
				}else if(current.get(p).getGewicht()<200) {
					radius = 10;
				}else {
					radius = 15;
				}
				//System.out.println("X:"+ 100+radius*number+" Y"+stockwerkHohe*(i+0.5));
				//revert i to show Etage in a right order
				 
				iiReverted = Math.abs(i-settings.maxStockwerke +1 );
				
				humans.add(new Circle(settings.w -100 - 30*number, stockwerkHohe*(iiReverted+0.5),radius));
				if (current.get(number).geduld<=4) { //frame
					humans.get(overall).setFill(Color.RED);
				}else {
					humans.get(overall).setFill(Color.BLACK);
				}
				root.getChildren().add(humans.get(overall));
				overall++;
				number ++;
			}
		}
	}
	
	//Simulation stoppen
	public void stop(){
		t.stop();
	}

	@Override
	public void update() {
		//Über alle Aufzüge iterieren und bei möglicher Positionsänderung anzeigen
		for (int i=0; i<settings.getMaxAufzug(); i++){
			int aenderungPosition = sim.getAufzugPositionAenderung(i);
			//Bei Positionsänderung, Animation
			if (aenderungPosition != 0)
				fahre(i, aenderungPosition);
		}
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				humans.clear();
				//call method for drawing
				drawStockwerkeRechts();
				root.getChildren().addAll(stockwerke);
				drawHumans(root);
				humanGoHome();
				// TODO Auto-generated method stub	
			}
		});
		
		/*
		System.out.println(humans.size());
		int overall =0;
		int iiReverted;
		for (int i=0; i<settings.maxStockwerke;i++) {
			Vector<Person> current = sim.getAnzleuteAnDerEtage(i);
			int number =0;
			for (int p=0; p <current.size() ;p++) {
				//System.out.println("Geduld: " +current.get(p).geduld);
				if (current.get(p).geduld<=0) {
					System.out.println("Person No: " +overall+"goes home" );
					TranslateTransition anpassung = new TranslateTransition(Duration.seconds(2), humans.get(overall));
					anpassung.setByX(100);
					anpassung.play();
				}
			}
		}
		*/
		
	}
	private void addperson(int number) {
		System.out.println("addperson");
		TranslateTransition anpassung = new TranslateTransition(Duration.seconds(2), humans.get(number));
		anpassung.setByX(-100);
		anpassung.play();
	}
	
	private void fahre(int aufzugNummer, int stockwerkAenderung){
		//Ausgabe auf der Konsole was passieren soll
		System.out.println("A" + aufzugNummer + " mit " + (sim.getAufzugPosition(aufzugNummer)+stockwerkAenderung) + " -> " + sim.getAufzugPosition(aufzugNummer));
		System.out.println();
		//Animation der Aufzüge
		TranslateTransition anpassung = new TranslateTransition(Duration.seconds(Aufzug.DAUER_PRO_STOCK*Math.abs(stockwerkAenderung)), aufzuge[aufzugNummer]);
		anpassung.setByY(stockwerkAenderung*stockwerkHohe);
		anpassung.play();
	}
}
