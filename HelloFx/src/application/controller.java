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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

public class controller implements Initializable {

	FileChooser fileChooser = new FileChooser();
	
	private String txtF1, LtxtF1[];
	
	private String txtF2, LtxtF2[];
	
	private String[] Converge;
	
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
    
    void setLTxtF1(Object[] objects) {
    	LtxtF1 = (String[]) objects;
    }
    
    void setLTxtF2(Object[] objects) {
    	LtxtF2 = (String[]) objects;
    }
    String getTxtF1() {
    	return txtF1;
    }
    
    String getTxtF2() {
    	return txtF2;
    }
    
    String[] getLTxtF1() {
    	return LtxtF1;
    }
    
    String[] getLTxtF2() {
    	return LtxtF2;
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
    
    String[] splitt(String str) {
    	return str.trim().split("\\s+");
    }
    
    void master() {
    	setLTxtF1(splitt(getTxtF1()));
    	setLTxtF2(splitt(getTxtF2()));
    	
    	List<String> list = Arrays.asList(getLTxtF1());
    	Set<String> set = new HashSet<String>(list);
    	
    	setLTxtF1(set.toArray());
    	
    	list = Arrays.asList(getLTxtF2());
    	set = new HashSet<String>(list);
    	
    	setLTxtF2(set.toArray());
    	
    	
    			
    }
    
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		fileChooser.setInitialDirectory(new File("C:\\Users\\user\\Desktop"));
		// TODO Auto-generated method stub
		
	}
}
