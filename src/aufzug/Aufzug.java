package aufzug;

public class Aufzug {
<<<<<<< HEAD
	public static final int DAUER_PRO_STOCK = 1;
	
=======
	public static final int DAUER_PRO_STOCK = 3;
>>>>>>> branch 'master' of https://github.com/Elki007/AufzugSimulation.git
	//Attribute eines Aufzugs
	private int aufzugId;
	private int altePosition;
	private int position;
	
	//Initialisierung im Konstruktor
	public Aufzug(int startpos, int aufzugId){
		this.position = startpos;
		this.altePosition = startpos;
		this.aufzugId = aufzugId;
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
	
	public int getDAUER_PRO_STOCK() {
		return DAUER_PRO_STOCK;
	}

	//Setzen einer neuen Position und speichern der alten Position
	public void setPosition(int position) {
		if (position != this.position){
			this.altePosition = this.position;
			this.position = position;
		}
	}	
}
