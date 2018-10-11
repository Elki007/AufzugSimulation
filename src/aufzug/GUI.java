package aufzug;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{
	private SimulationGUI simStage;
	private Simulation_Einstellungen simSett;
	public Settings sett=new Settings();

	@Override
	public void start(Stage arg0) throws Exception {
		//Hier würde das erste Fenster zur Konfiguration erscheinen
		simSett = new Simulation_Einstellungen(this, sett);
		simSett.show();
		arg0.close();
		
	}
	public void simulate() {
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


