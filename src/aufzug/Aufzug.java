package aufzug;

import java.util.Vector;

public class Aufzug {
	public static final int DAUER_PRO_STOCK = 3;
	//Attribute eines Aufzugs
	private int aufzugId;
	private int altePosition;
	private int position;
	private int stockwerkAlt; // ersetzt später altePosition
	private int stockwerkAktuell; // ersetzt später Position
	private int gewichtMax;
	private int gewichtAktuell;
	private int groesseMax;
	private int groesseAktuell;
	private int stockwerkAenderung;
	private boolean inBewegung;
	private long startBewegung;
	private boolean wartend;
	private long startWartezeit;
	private Vector<Person> leuteImFahrstuhl = new Vector<Person>();
	
	//Initialisierung im Konstruktor
	public Aufzug(int startpos, int aufzugId){
		this.position = startpos;
		this.altePosition = startpos;
		
		this.stockwerkAktuell = startpos;
		this.stockwerkAlt = startpos;
		
		this.aufzugId = aufzugId;
		this.gewichtMax = 900;
		this.gewichtAktuell = 0;
		this.groesseMax = 10;
		this.groesseAktuell = 0;
		this.inBewegung = false;
		this.wartend = true;
	}
	
	//Weitere getter und setter - Ordnung kommt später (wahrscheinlich nicht :( )
	public void setStockwerkVeraenderung() {
		// Warum invertiert? -> von 0 zu 2 = -2?
		stockwerkAenderung = stockwerkAlt - stockwerkAktuell;
	}
	
	public int getStockwerkAktuell() {
		return stockwerkAktuell;
	}
	
	public int getStockwerkAlt() {
		return stockwerkAlt;
	}
	
	public int getStockwerkVeraenderung() {
		return stockwerkAenderung;
	}
	
	public long getDauerBewegung() {
		return (System.currentTimeMillis() - startBewegung) / 1000;
	}
	
	public void setBewegungStart() {
		startBewegung = System.currentTimeMillis();
	}
	
	public void setWartet(boolean wartet) {
		this.wartend = wartet;
	}
	
	public boolean getWartend() {
		return this.wartend;
	}
	
	public void setWartezeitStart() {
		startWartezeit = System.currentTimeMillis();
	}
	
	public long getDauerWartezeit() {
		return (System.currentTimeMillis() - startWartezeit) / 1000;
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
	
	public void setStockwerkNeu(int stockwerkNeu) {
		// Abfrage sollte nicht notwendig sein, aber schaden kann es auch nicht - noch übernommen von Stefan
		if (stockwerkNeu != this.stockwerkAktuell){
			this.stockwerkAlt = this.stockwerkAktuell;
			this.stockwerkAktuell = stockwerkNeu;
		}
	}	
}
