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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

public class controller implements Initializable {

	FileChooser fileChooser = new FileChooser();
	
	private String txtF1, ULtxtF1[];
	
	private String txtF2, ULtxtF2[];

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
    
    void setULtxtF1(Object[] objects) {
    	ULtxtF1 = (String[]) objects;
    }
    
    void setULtxtF2(Object[] objects) {
    	ULtxtF2 = (String[]) objects;
    }
    String getTxtF1() {
    	return txtF1;
    }
    
    String getTxtF2() {
    	return txtF2;
    }
    
    String[] getULtxtF1() {
    	return ULtxtF1;
    }
    
    String[] getULtxtF2() {
    	return ULtxtF2;
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
    
    void ratio() {
    	setULtxtF1(splitt(getTxtF1()));
    	setULtxtF2(splitt(getTxtF2()));
    	
    	Set<String> convergedSet = new LinkedHashSet<>();
    	
    	List<String> list = Arrays.asList(getULtxtF1());
    	List<String> LtxtF1 = Arrays.asList(getULtxtF1());
    	Set<String> set = new HashSet<String>(list);

    	setULtxtF1(set.toArray());
    	
    	convergedSet.addAll(set);
    	
    	list = Arrays.asList(getULtxtF2());
    	List<String> LtxtF2 = Arrays.asList(getULtxtF1());
    	set = new HashSet<String>(list);
    	
    	setULtxtF2(set.toArray());
    	
    	convergedSet.addAll(set);
    	
    	int T1UC = 0; //Unique words union in text 1
    	int T2UC = 0; //Unique words union in text 2
    	
    	for (String word : convergedSet) {
    		if (getTxtF1().contains(word)) {
    			T1UC++;
    		}
    	}
    	
    	for (String word : convergedSet) {
    		if (getTxtF2().contains(word)) {
    			T2UC++;
    		}
    	}
    	
        double Txt1Ratio = ((double) T1UC / LtxtF1.size() ) * 100;
        double Txt2Ratio = ((double) T2UC / LtxtF2.size() ) * 100;
    	    			
    }
    
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		fileChooser.setInitialDirectory(new File("C:\\Users\\user\\Desktop"));
		// TODO Auto-generated method stub
		
	}
	
	void master() {
		// TODO this will take an action and call for all the relevant functions in order and will display the ratios.
	}
}
