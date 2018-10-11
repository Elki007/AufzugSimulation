package aufzug;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{
	private SimulationGUI simStage;

	@Override
	public void start(Stage arg0) throws Exception {
		//Hier w�rde das erste Fenster zur Konfiguration erscheinen
		arg0.close();
		
		//Die neue Simulationsstage wird erzeugt
		//Sie w�rde eigentlich die n�tigen Parameter aus der Konfig Ansicht �bergeben bekommen
		simStage = new SimulationGUI();
		simStage.show();
	}
	
	public void stop(){
		simStage.stop();
	}
	
	public static void main(String... args){
		launch(args);
	}
}


