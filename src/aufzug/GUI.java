package aufzug;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{
	private SimulationGUI simStage;
	private Simulation_Einstellungen simSett;

	@Override
	public void start(Stage arg0) throws Exception {
		//Hier würde das erste Fenster zur Konfiguration erscheinen
		simSett = new Simulation_Einstellungen();
		
<<<<<<< HEAD
		int maxAufzug = 3;
		int maxStockwerk = 3;
		Settings sett = new Settings(maxAufzug, maxStockwerk,400,800);
=======
		Settings sett = new Settings(4,4,1366,780);
>>>>>>> branch 'master' of https://github.com/Elki007/AufzugSimulation.git
		//simSett.show()
		arg0.close();
		
		//Die neue Simulationsstage wird erzeugt
		//Sie würde eigentlich die nötigen Parameter aus der Konfig Ansicht übergeben bekommen
		simStage = new SimulationGUI(sett);
		simStage.show();
	}
	
	public void stop(){
		simStage.stop();
	}
	
	public static void main(String... args){
		launch(args);
	}
}


