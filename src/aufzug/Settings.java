package aufzug;

public class Settings {
	int maxAufzug=0;
	int maxStockwerke=0;
	int h=0;
	int w=0;
	
	Settings(){
		
	}
	Settings(int maxAufzug, int maxStockwerke, int w, int h){
		this.maxAufzug = maxAufzug; 
		this.maxStockwerke = maxStockwerke;
		this.h = h;
		this.w = w;
		
	}
	
	public int getMaxAufzug() {
		return maxAufzug;
	}
	
	public int getMaxStockwerk() {
		return maxStockwerke;
	}
	
	public int getFensterHoehe() {
		return h;
	}
	
	public int getFensterWeite() {
		return w;
	}
	
}
