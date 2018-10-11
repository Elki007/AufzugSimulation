package aufzug;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{
	private SimulationGUI simStage;
	private Simulation_Einstellungen simSett;

	@Override
	public void start(Stage arg0) throws Exception {
		//Hier w�rde das erste Fenster zur Konfiguration erscheinen
		simSett = new Simulation_Einstellungen();
		
		Settings sett = new Settings(4,5,1366,768);
		//simSett.show()
		arg0.close();
		
		//Die neue Simulationsstage wird erzeugt
		//Sie w�rde eigentlich die n�tigen Parameter aus der Konfig Ansicht �bergeben bekommen
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


