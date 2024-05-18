package _21010310003_Ahmet_Bektas;

import java.io.IOException;

public class _21010310003_TruthTable {
	
	int boyut;
	int digit;
	String[][] table;
	_21010310003_ReaderAndSeperater reader;
	
	public _21010310003_TruthTable() throws IOException  {
		this.reader = new _21010310003_ReaderAndSeperater("Boole.txt");
		this.CreateTruthTable();
	}
	
	public void CreateTruthTable() throws IOException {
		
		this.digit = reader.variables.length-1;
		this.boyut = (int) Math.pow(2, digit);
		this.table= new String[boyut][digit+1];
		
		System.out.println("Dogruluk Tablosu: ");
		
		for (int i = 1; i < reader.variables.length; i++) {
			System.out.print(reader.variables[i]+" ");
		}
		System.out.println(reader.variables[0]);
		
		
		for (int i = 0; i < boyut; i++) {	
				this.table[i][digit] = String.valueOf(1);
			
		}
		
		
		for (int i = 0; i < boyut; i++) {
			String binary = decimalToBinary(i,digit);	
			for (int j = 0; j < binary.length(); j++) {
				this.table[i][j] = String.valueOf(binary.charAt(j));
			}

			int fValue = calculateResultValue(reader.variables, table, i, reader.booleTerms);
	        this.table[i][digit] = String.valueOf(fValue);
			
		}
		
		
		
		
		for (int z = 0; z < table.length; z++) {
		    for (int j = 0; j < table[z].length; j++) {
		        System.out.print(table[z][j] + " ");
		    }
		    System.out.println(); 
		}
		
		
	}
	

	

	
	public String decimalToBinary(int ondalik, int basamak) {
		StringBuilder binary = new StringBuilder();
		
		if(ondalik == 0) {
			for (int i = 0; i < basamak; i++) {
				binary.insert(0,"0");
			}
			return binary.toString();
		}
		
		while (ondalik > 0) {
            int bit = ondalik % 2;
            binary.insert(0, bit);
            ondalik /= 2;
        }
		
		while (binary.length() < basamak) {
            binary.insert(0, "0");
        }
		
		return binary.toString();
		
	}
	
	public int calculateResultValue(String[] variables, String[][] table, int row, String[] terms) {
		int fValue = 0;

	    for (String term : terms) {
	        int termValue = 1;

	        for (int i = 0; i < term.length(); i++) {
	            char c = term.charAt(i);

	            if (Character.isLetter(c) ) {
	                if(i + 1 < term.length() && !Character.isLetter(term.charAt(i+1))) {
	                	int variableIndex = getIndexFromVariables(variables, c);
	                	int variableValue = Integer.parseInt(table[row][variableIndex]); 
	                	variableValue = (variableValue + 1) % 2; 
	                	termValue *= variableValue; 
	                } else {
	                	int variableIndex = getIndexFromVariables(variables, c); 
	                	int variableValue = Integer.parseInt(table[row][variableIndex]); 
	                	termValue *= variableValue;
	                }
	            } else {
	            	
	            }

	        }

	        fValue += termValue;
	    }
	    

	    fValue = fValue > 0 ? 1 : 0;

	    return fValue;
	}



	
	private int getIndexFromVariables(String[] variables, char c) {
	    for (int i = 1; i < variables.length; i++) {
	        if (variables[i].charAt(0) == c) {
	            return i-1;
	        }
	    }
	    return -1; 
	}
}
