package aufzug;
//import java.util.Vector;

public class Person {
	int gewicht, geduld, startStockwerk, zielStockwerk, currentStockwerk, einausStiegZ;
	//geduld in secondsss
	boolean gepaeck, richtungHoch;
	long warteZeit,fahrZeit;
	int maxGew = 300, minGew = 10;
	
	
	Person(int start, int maxStockwerk) {
		gewicht = (int)(minGew + (Math.random() * ((maxGew - minGew) + 1)));
		geduld = (int)(5 + 100 * Math.random());//180*
		einausStiegZ = (int)(3 + 10 * Math.random());
		startStockwerk = start;
		zielStockwerk = (int)(1 + (Math.random() * ((maxStockwerk-1) + 1)));
		
		// Bei gleichem Start und Zielstockwerk -> erneute zufällige Zielstockwerksuche
		// Abbruch nach 1000 Versuchen 
		int tmp = 0;
		while(zielStockwerk == startStockwerk) {
			zielStockwerk = (int)(1 + (Math.random() * ((maxStockwerk-1) + 1)));
			if (tmp > 1000) {
				System.out.println("Fehler: Zielstockwerk nach 1000 Versuchen nicht anders als Startstockwerk.");
				break;
			}
			tmp++;
		}
		currentStockwerk = startStockwerk;
		
		// Richtung nach oben, wenn startStockwerk kleiner als zielStockwerk 
		if (startStockwerk < zielStockwerk) richtungHoch = true;
		else richtungHoch = false;
		
		if ( (int)(1+100 * Math.random())<20 ) 
			gepaeck = true;//
		else
			gepaeck = false;
		
	}
	
	public int getGewicht() {
		return gewicht;
	}
	
	void print() {
		System.out.printf("%d %d %d %b",gewicht, geduld, einausStiegZ, gepaeck);
		System.out.print("Gewicht: "+gewicht+" Gedult: "+geduld+" Speed: "+einausStiegZ+ "Gepaeck: "+ gepaeck + " Start: "+startStockwerk + " Ziel: "+ zielStockwerk +"\n");
	}
	
	void printTwo() {
		//System.out.print("Gewicht: " + gewicht + " Geduld: " + geduld + " Speed: " + einausStiegZ + "Gepaeck: " + gepaeck + " Start: " + startStockwerk+ " Ziel: "+ zielStockwerk+ "\n");
		System.out.print("Von Start zu Ziel: " + startStockwerk + " -> " + zielStockwerk + "\tRichtung: ");
		if (richtungHoch) System.out.print("oben");
		else System.out.print("unten");
		System.out.print("\t\tSpeed: " + einausStiegZ + "  Gewicht:" + gewicht + "\n");
	}

	public void printAlleLeute() {
		// TODO Auto-generated method stub
		System.out.print(" i ");
	}
	
	/*public static void main(String[] args) {
		Vector<Person> a = new Vector<Person>();
		int m = (int)(1+100 * Math.random());
		int n = 0;
		int g = 0;

		while (n<m) {s;
			a.get(n).print();
			if (a.get(n).gepaeck == true)
				g++;
			n++;
		}
		float percent = 0;
		percent = (float)g*100/m;
		System.out.print("Total: "+m+" "+"mit gepaeck:"+g+" "+percent);
		
	}*/
	
}
