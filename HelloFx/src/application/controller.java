package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class controller {
	@FXML
	private Label MyLabel;
	@FXML
	private Button MyButton;
	
	int deger;
	
	public void show(ActionEvent event) {
		try {
			deger = 17;
			MyLabel.setText(deger +"%");
		}
		catch (Exception e) {
			System.out.println(e);		
		}
		
	}
}
