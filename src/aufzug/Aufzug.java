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
	private int stockwerkAenderung;
	private boolean inBewegung;
	private long dauerBewegung;
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
		this.inBewegung = false;
		this.dauerBewegung = 0;
	}
	
	//Weitere getter und setter - Ordnung kommt später (wahrscheinlich nicht :( )
	public void setStockwerkVeraenderung() {
		// Warum invertiert? -> von 0 zu 2 = -2?
		stockwerkAenderung = altePosition-position;
	}
	
	public int getStockwerkAktuell() {
		return position;
	}
	
	public int getStockwerkAlt() {
		return altePosition;
	}
	
	public int getStockwerkVeraenderung() {
		return stockwerkAenderung;
	}
	
	public long getDauerBewegung() {
		return (System.currentTimeMillis() - dauerBewegung);
	}
	
	public void setBewegungStart() {
		dauerBewegung = System.currentTimeMillis();
	}
	
	public boolean getInBewegung() {
		return inBewegung;
	}
	
	public void setInBewegung(boolean bewegung) {
		inBewegung = bewegung;
	}
		
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
	
	public int getAnzahlLeuteInFahrstuhl() {
		return leuteImFahrstuhl.size();
	}

	public void setPersonSteigtEin(Person mustermann) {
		leuteImFahrstuhl.addElement(mustermann);
	}
	
	public boolean setPersonSteigtAus(Person mustermann) {
		return leuteImFahrstuhl.remove(mustermann);
	}
	
	public Person getPersonAnPosition(int pos) {
		return leuteImFahrstuhl.get(pos);
	}
	
	public void setPersonAnPositionSteigtAus(int pos) {
		leuteImFahrstuhl.remove(pos);
	}

	
	//Setzen einer neuen Position und speichern der alten Position
	public void setPosition(int position) {
		if (position != this.position){
			this.altePosition = this.position;
			this.position = position;
		}
	}	
}
