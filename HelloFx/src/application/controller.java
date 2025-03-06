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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.Scanner;

public class controller implements Initializable {

	
	FileChooser fileChooser = new FileChooser();
	
	private String txtF1;
	
	private String txtF2;
	
    @FXML
    private Button myButton2;
    
    @FXML
    private Button MyButton;

    @FXML
    private TextArea Txet;
    
    void setTxtF1(String text) {
    	txtF1 = text;
    }
    
    void setTxtF2(String text) {
    	txtF2 = text;
    }

    @FXML
    void show(ActionEvent event) {
    	File file = fileChooser.showOpenDialog(new Stage());
    	try (Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				//Txet.appendText(scanner.nextLine() + "\n");
				txtF1 = scanner.useDelimiter("\\A").next();
				//System.out.println(txtF1);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void show2(ActionEvent event) {
    	File file2 = fileChooser.showOpenDialog(new Stage());
    	try (Scanner scanner = new Scanner(file2)) {
			while(scanner.hasNextLine()) {
				//Txet.appendText(scanner.nextLine() + "\n");
				txtF2 = scanner.useDelimiter("\\A").next();
				//System.out.println(txtF1)
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
