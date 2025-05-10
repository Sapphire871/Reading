package com.example.demo.service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TextComparisonService {

	public String compareTexts(String text1, String text2) {
		String result = ratio(text1, text2);
		return result;
	}
	
	String[] splitt(String str) {
    	return str.trim().replaceAll("[^\\s\\p{L}0-9]", "").split("\\s+");
    }
	
	String ratio(String TxtF1, String TxtF2) {
		
		String[] ULtxtF1;
		String[] ULtxtF2;
		
    	ULtxtF1 = splitt(TxtF1);
    	ULtxtF2 = splitt(TxtF2);
    	
    	Set<String> convergedSet = new LinkedHashSet<>();
    	
    	List<String> list = Arrays.asList(ULtxtF1);
    	List<String> LtxtF1 = Arrays.asList(ULtxtF1);
    	Set<String> set1 = new HashSet<String>(list);

    	ULtxtF1 = set1.toArray(new String[0]);
    	
    	list = Arrays.asList(ULtxtF2);
    	List<String> LtxtF2 = Arrays.asList(ULtxtF2);
    	Set<String> set2 = new HashSet<String>(list);
    	
    	ULtxtF2 = set2.toArray(new String[0]);
    	
    	for (String word: set1) {
    		if (set2.contains(word)) {
    			convergedSet.add(word);
    		}
    	}
    	
    	int T1UC = 0; 
    	int T2UC = 0; 
    	
    	for (String word : convergedSet) {
    		T1UC += Collections.frequency(Arrays.asList(splitt(TxtF1)), word);
    	}
    	
    	for (String word : convergedSet) {
    		T2UC += Collections.frequency(Arrays.asList(splitt(TxtF2)), word);
    	}
    	
        Double Txt1Ratio = (((double) T1UC / LtxtF1.size() ) * 100);
        Double Txt2Ratio = (((double) T2UC / LtxtF2.size() ) * 100);
        
        String result;
        
    	DecimalFormat df = new DecimalFormat("#.#####");
    	
    	result = "Text 1: " + df.format(Txt1Ratio) + "%\n" + "\tvs\n"+ "Text 2: " + df.format(Txt2Ratio) + "%";
    	
    	return result;

    }

}
