package _21010310003_Ahmet_Bektas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class _21010310003_ReaderAndSeperater {
	File dosya;
	String line;
	String[] terms;
	String[] variables; 
	String[] booleTerms;
	
	public _21010310003_ReaderAndSeperater(String file) throws IOException {
		this.dosya = new File(file);
		this.readLine(dosya);
		this.seperateExpressions();
		this.seperateVariables();
		this.seperateBooleterms();
	}
	
	public void readLine(File file) throws IOException {
		try {
			this.dosya = file;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

			this.line = br.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String[] parseExpression(String line) {
		String[] terms = line.split("\\s+"); 
        return terms;
	}
	
	public void seperateExpressions() {
		this.terms = parseExpression(line);
	}
	
	
	public void seperateVariables() {
		this.variables = parseVariables(terms);
		
	}
	

	public String[] parseVariables(String[] terms) {
        TreeSet<Character> variableSet = new TreeSet<>();

        for (char c : terms[0].toCharArray()) {
            if (Character.isLetter(c)) {
                variableSet.add(c);
            }
        }

        for (int i = 1; i < terms.length; i++) {
            for (char c : terms[i].toCharArray()) {
                if (Character.isLetter(c)) {
                    variableSet.add(c);
                }
            }
        }


        String[] variableNamesArray = new String[variableSet.size()];
        int i = 0;
        variableNamesArray[i++] = String.valueOf(terms[0].charAt(0));

        for (char c : variableSet) {
            if (c != terms[0].charAt(0)) {
                variableNamesArray[i++] = String.valueOf(c);
            }
        }

        return variableNamesArray;
    }

	
	public void seperateBooleterms() {
		this.booleTerms = parseBooleterms(line);
		
	}
	

	public static String[] parseBooleterms(String line) {
        List<String> booleTermList = new ArrayList<>();


        String[] parts = line.split("=");
        if (parts.length > 1) {

            String[] terms = parts[1].trim().split("\\s*\\+\\s*");
            for (String term : terms) {
                booleTermList.add(term);
            }
        }

        String[] mintermsArray = new String[booleTermList.size()];
        booleTermList.toArray(mintermsArray);

        return mintermsArray;
    }
	
}
