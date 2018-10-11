package aufzug;

public class Settings {
	int maxAufzug;
	int maxStockwerke;
	int h;
	int w;
	
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
