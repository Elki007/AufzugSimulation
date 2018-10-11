package aufzug;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Simulation_Einstellungen extends Stage {
	Settings sett;
	public Simulation_Einstellungen() {
		//System.out.println("empty constructor");
	}
	public Simulation_Einstellungen(GUI gui, Settings sett2) {
		super();
		sett=sett2;
		//System.out.println("NON-empty constructor");
		Platform.setImplicitExit(false);
		this.setTitle("Setting");
		this.setWidth(300);
		this.setHeight(400);
		
		final TextField Stockwerke = new TextField("# Stockwerk: ");
		final TextField Aufzuege = new TextField("# Aufzuege: ");
		final TextField Breite = new TextField("Breite: ");
		final TextField Hoehe = new TextField("Hoehe: ");
		//***********************************************************************
		Button Simulation = new Button("Simulation");
		Simulation.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		
		// Defining the Clear button
		Button clear = new Button("Default");
		
		VBox vbox = new VBox(Stockwerke,Aufzuege,Breite,Hoehe,Simulation, clear);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		
		HBox hbox = new HBox(vbox);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		
		clear.setOnAction((ActionEvent e) -> {
			Stockwerke.setText("5");
			Aufzuege.setText("2");
			Breite.setText("1366");
			Hoehe.setText("768");
		});

		Simulation.setOnAction((ActionEvent e) -> {         
	        sett.maxStockwerke = Integer.parseInt(Stockwerke.getText());
	        sett.maxAufzug = Integer.parseInt(Aufzuege.getText());
			sett.h = Integer.parseInt(Hoehe.getText());
			sett.w = Integer.parseInt(Breite.getText());
			
			System.out.println("Stockwerke: "+sett.maxStockwerke+"     Aufzuege: "+sett.maxAufzug+"     Aufloesung:  "+ sett.w+"x"+sett.h);
			this.close();
			gui.simulate();
		});

		this.setScene(new Scene(hbox));
	}
};
