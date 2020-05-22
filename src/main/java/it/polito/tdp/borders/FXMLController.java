
package it.polito.tdp.borders;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;



import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model; 
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<Country> ComboBoxStato;

    @FXML
    private Button btnVicino;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
		txtResult.clear();

		int anno;

		try {
			anno = Integer.parseInt(txtAnno.getText());
			if ((anno < 1816) || (anno > 2016)) {
				txtResult.setText("Inserire un anno nell'intervallo 1816 - 2016");
				return;
			}
			
		} catch (NumberFormatException e) {
			txtResult.setText("Inserire un anno nell'intervallo 1816 - 2016");
			return;
		}

		try {
			model.creaGrafo(anno);
			ComboBoxStato.getItems().addAll(this.model.getCountries());
			
			txtResult.appendText(String.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents()));

			Map<Country, Integer> stati = model.getCountryList();
			for (Country country : stati.keySet())
				txtResult.appendText(String.format("%s %d\n", country, stati.get(country)));

		} catch (RuntimeException e) {
			txtResult.setText("Errore: " + e.getMessage() + "\n");
			return;
		}
    	
    	
    }

    @FXML
    void doTrovaTuttiVicini(ActionEvent event) {
    	
    	txtResult.clear();

		if (ComboBoxStato.getItems().isEmpty()) {
			txtResult.setText("Il grafo è vuoto, seleziona un altro stato o anno");
		}

		Country selectedCountry = ComboBoxStato.getSelectionModel().getSelectedItem();
		if (selectedCountry == null) {
			txtResult.setText("Select a country first.");
		}

			List<Country> vicini= model.getVicini(selectedCountry);
			for (Country country : vicini) {
				txtResult.appendText(String.format("%s\n", country));
			}
		
    	
    }

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert ComboBoxStato != null : "fx:id=\"ComboBoxStato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVicino != null : "fx:id=\"btnVicino\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }


    public void setModel(Model model) {
    	this.model = model;
    }
}
