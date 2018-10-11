package aufzug;

public class Aufzug {
	public static final int DAUER_PRO_STOCK = 3;
	//Attribute eines Aufzugs
	private int altePosition;
	private int position;
	
	//Initialisierung im Konstruktor
	public Aufzug(int startpos){
		this.position = startpos;
		this.altePosition = startpos;
	}
	
	//Berechnet die Stockwerksver�nderung und gibt sie zur�ck
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
