package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
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
    
    String read(String t) {
    	File file = fileChooser.showOpenDialog(new Stage());
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    	    StringBuilder sb = new StringBuilder();
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	        sb.append(line).append(System.lineSeparator());
    	    }
    	    t = sb.toString();
    	    return t;
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
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
    		T1UC += Collections.frequency(Arrays.asList(splitt(getTxtF1())), word);
    	}
    	
    	for (String word : convergedSet) {
    		T2UC += Collections.frequency(Arrays.asList(splitt(getTxtF2())), word);
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
		setTxtF1(read((getTxtF1())));
		setTxtF2(read((getTxtF1())));
		ratio();
    	DecimalFormat df = new DecimalFormat("#.#####");
		labelText1.setText(df.format(getTxt1Ratio()) + '%');
		labelText2.setText(df.format(getTxt2Ratio()) + '%');
	}
}
