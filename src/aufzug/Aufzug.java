package aufzug;

import java.util.Vector;

public class Aufzug {
	public static final int DAUER_PRO_STOCK = 3;
	//Attribute eines Aufzugs
	private int aufzugId;
	private int altePosition;
	private int position;
	private int gewichtMax;
	private int gewichtAktuell;
	private int groesseMax;
	private int groesseAktuell;
	private Vector<Person> leuteImFahrstuhl = new Vector<Person>();
	
	//Initialisierung im Konstruktor
	public Aufzug(int startpos, int aufzugId){
		this.position = startpos;
		this.altePosition = startpos;
		this.aufzugId = aufzugId;
		this.gewichtMax = 900;
		this.gewichtAktuell = 0;
		this.groesseMax = 10;
		this.groesseAktuell = 0;
		
	}
	
	//Berechnet die Stockwerksveränderung und gibt sie zurück
	public int getPositionVeraenderung() {
		int veraenderung = altePosition-position;
		altePosition = position;
		return veraenderung;
	}
	
	//Weitere getter
	public int getPosition() {
		return position;
	}
	
	public int getGewichtAktuell() {
		return gewichtAktuell;
	}
	
	public int getGewichtMax() {
		return gewichtMax;
	}
	
	public int getGroesseAktuell() {
		return groesseAktuell;
	}
	
	public int getGroesseMax() {
		return groesseMax;
	}
	
	public int getId() {
		return aufzugId;
	}
	
	public int getDAUER_PRO_STOCK() {
		return DAUER_PRO_STOCK;
	}

	public void setNeuePersonInAufzug() {
		
	}
	
	//Setzen einer neuen Position und speichern der alten Position
	public void setPosition(int position) {
		if (position != this.position){
			this.altePosition = this.position;
			this.position = position;
		}
	}	
}
