package aufzug;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Simulation_Einstellungen extends Stage {
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new Group());
		primaryStage.setTitle("     Simulation Einstellungen     ");
		primaryStage.setWidth(500);
		primaryStage.setHeight(300);

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		//

		final TextField Stockwerke = new TextField("Stockwerk: ");
		GridPane.setConstraints(Stockwerke, 0, 0);
		grid.getChildren().add(Stockwerke);

		//
		final TextField Aufzuege = new TextField("Aufzuege: ");
		GridPane.setConstraints(Aufzuege, 0, 1);
		grid.getChildren().add(Aufzuege);

		//
		final TextField Personen = new TextField("Personen: ");
		GridPane.setConstraints(Personen, 0, 2);
		grid.getChildren().add(Personen);

		// Defining the Submit button
		Button Simulation = new Button("Simulation");
		Simulation.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		GridPane.setConstraints(Simulation, 2, 4);
		grid.getChildren().add(Simulation);

		// Defining the Clear button
		Button clear = new Button("Clear");
		GridPane.setConstraints(clear, 2, 5);
		grid.getChildren().add(clear);

		// Event Handler erzeugen
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Stockwerke.setPromptText("Stockwerke: 1-2-3-4-5");
				Aufzuege.setPromptText("Aufzug: 1-2-3");
				Personen.setPromptText("Personen: max 13");
			}
		};
		Stockwerke.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		Aufzuege.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		Personen.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		Simulation.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

		clear.setOnAction((ActionEvent e) -> {
			Stockwerke.clear();
			Aufzuege.clear();
			Personen.clear();
		});

		Simulation.setOnAction((ActionEvent e) -> {
			
		 
		        primaryStage.setTitle("Hello World");
		        Group root = new Group();
		        Button btn = new Button();
		        btn.setOnAction(new EventHandler<ActionEvent>() {

		            public void handle(ActionEvent event) {
		                System.out.println("Hello World");
		            }
		        });
		        root.getChildren().add(btn);        
		        primaryStage.setScene(scene);
		        primaryStage.close();
	

			String anzahlStockwerke = Stockwerke.getText();
			String anzahlAufzuege = Aufzuege.getText();
			String anzahlPersonen = Personen.getText();
			
			
	 
	  
	     	//statt anzahlPersonen -> screen width , screen eight
			System.out.println("Stockwerke2: "+anzahlStockwerke+"     Aufzuege2: "+anzahlAufzuege+"     Höher:  "+primaryStage.getHeight()+"    Laenge:  "+ primaryStage.getWidth());

		});

		// Szene setzen und Oberfläche anzeigen

		primaryStage.setScene(new Scene(grid));
		//primaryStage.show();

	}

};
