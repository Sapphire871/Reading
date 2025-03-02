package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class controller implements Initializable {

	
	FileChooser fileChooser = new FileChooser();
	
    @FXML
    private Button MyButton;

    @FXML
    private TextArea Txet;

    @FXML
    void show(ActionEvent event) {
    	File file = fileChooser.showOpenDialog(new Stage());
    	try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				Txet.appendText(scanner.nextLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		fileChooser.setInitialDirectory(new File("C:\\Users\\user\\Desktop"));
		// TODO Auto-generated method stub
		
	}

}
