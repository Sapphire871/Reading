package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
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
						  //Unique words in text f1.
	private String txtF2, ULtxtF2[];
						  //Unique words in text f2.
	private double Txt1Ratio, Txt2Ratio;

    @FXML
    private Button myButton;
    
    @FXML
    private Label labelText1;

    @FXML
    private Label labelText2;

	public double getTxt2Ratio() {
		return Txt2Ratio;
	}

	public void setTxt2Ratio(double txt2Ratio) {
		Txt2Ratio = txt2Ratio;
	}

	public double getTxt1Ratio() {
		return Txt1Ratio;
	}

	public void setTxt1Ratio(double txt1Ratio) {
		Txt1Ratio = txt1Ratio;
	}

	void setTxtF1(String text) {
    	txtF1 = text;
    }
    
    void setTxtF2(String text) {
    	txtF2 = text;
    }
    
    void setULtxtF1(String[] strr) {
    	ULtxtF1 = strr;
    }
    
    void setULtxtF2(String[] strr) {
    	ULtxtF2 = strr;
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
    void show() {
    	File file = fileChooser.showOpenDialog(new Stage());
    	try (Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				txtF1 = scanner.useDelimiter("\\A").next();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void show2() {
    	File file2 = fileChooser.showOpenDialog(new Stage());
    	try (Scanner scanner = new Scanner(file2)) {
			while(scanner.hasNextLine()) {
				txtF2 = scanner.useDelimiter("\\A").next();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    String[] splitt(String str) {
    	return str.trim().replaceAll("[^\\s\\p{L}0-9]", "").split("\\s+");
    }
    
    void ratio() {
    	setULtxtF1(splitt(getTxtF1()));
    	setULtxtF2(splitt(getTxtF2()));
    	
    	Set<String> convergedSet = new LinkedHashSet<>();
    	
    	List<String> list = Arrays.asList(getULtxtF1());
    	List<String> LtxtF1 = Arrays.asList(getULtxtF1());
    	Set<String> set1 = new HashSet<String>(list);

    	setULtxtF1(set1.toArray(new String[0]));
    	
    	
    	list = Arrays.asList(getULtxtF2());
    	List<String> LtxtF2 = Arrays.asList(getULtxtF2());
    	Set<String> set2 = new HashSet<String>(list);
    	
    	setULtxtF2(set2.toArray(new String[0]));
    	
    	for (String word: set1) {
    		if (set2.contains(word)) {
    			convergedSet.add(word);
    		}
    	}
    	
    	int T1UC = 0; //Unique words converged in text 1
    	int T2UC = 0; //Unique words converged in text 2
    	
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
    	
        setTxt1Ratio(((double) T1UC / LtxtF1.size() ) * 100);
        setTxt2Ratio(((double) T2UC / LtxtF2.size() ) * 100);
    	    		
        
    }
    
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		String userDirectoryPath = System.getProperty("user.dir");
		fileChooser.setInitialDirectory(new File(userDirectoryPath));
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	void master(ActionEvent event) {
		show();
		show2();
		ratio();
    	DecimalFormat df = new DecimalFormat("#.#####");
		labelText1.setText(df.format(getTxt1Ratio()) + '%');
		labelText2.setText(df.format(getTxt2Ratio()) + '%');
	}
}
