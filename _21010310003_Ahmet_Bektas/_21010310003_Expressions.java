package _21010310003_Ahmet_Bektas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class _21010310003_Expressions {
	
	_21010310003_TruthTable tt;
	_21010310003_ReaderAndSeperater reader;

	public _21010310003_Expressions() throws IOException{
		this.tt = new _21010310003_TruthTable();
		this.reader = new _21010310003_ReaderAndSeperater("Boole.txt");
		System.out.println("Fonksiyon Ifadeleri");
		this.printMinterms();
		System.out.println();
		this.printMintermsShort();
		System.out.println();
		this.printMaxterms();
		System.out.println();
		this.printMaxtermsShort();
		
	}
	
	
	public void printMinterms() {
		 List<String> minterms = findMinterms(tt.table, reader.variables);
		 System.out.print(reader.variables[0] + " = ");
		 for (int i = 0; i < minterms.size(); i++) {
			System.out.print(minterms.get(i));
			
			if (i < minterms.size() - 1) {
	            System.out.print(" + ");
	        }
		}
	}
	
	public void printMaxterms() {
		 List<String> maxterms = findMaxterms(tt.table, reader.variables);
		 System.out.print(reader.variables[0] + " = ");
		 for (int i = 0; i < maxterms.size(); i++) {
				System.out.print(maxterms.get(i));
				
				if (i < maxterms.size() - 1) {
		            System.out.print(" . ");
		        }
			}
		 
	}
	
	public void printMintermsShort() {
		List<String> minterms = mintermShortExp(tt.table);
		System.out.print(reader.variables[0] + " = Σ(");
		 for (int i = 0; i < minterms.size(); i++) {
				System.out.print(minterms.get(i));
				
				if (i < minterms.size() - 1) {
		            System.out.print(",");
		        }
			}
		 System.out.print(")");
	}
	
	public void printMaxtermsShort() {
		List<String> maxterms = maxtermShortExp(tt.table);
		System.out.print(reader.variables[0] + " = ∏(");
		 for (int i = 0; i < maxterms.size(); i++) {
				System.out.print(maxterms.get(i));
				
				if (i < maxterms.size() - 1) {
		            System.out.print(",");
		        }
			}
		 System.out.print(")");
	}
	
	
	
	public List<String> mintermShortExp(String[][] table) {
		List<String> minterms = new ArrayList<>();
		for (int row = 0; row < table.length; row++) {
			if ("1".equals(table[row][4])) {
				StringBuilder minterm = new StringBuilder();
				minterm.append(row);
				minterms.add(minterm.toString());
			}
			
		}
		return minterms;
		
	}
	
	
	public List<String> maxtermShortExp(String[][] table) {
		List<String> minterms = new ArrayList<>();
		for (int row = 0; row < table.length; row++) {
			if ("0".equals(table[row][4])) {
				StringBuilder minterm = new StringBuilder();
				minterm.append(row);
				minterms.add(minterm.toString());
			}
			
		}
		return minterms;
		
	}
	
	public List<String> findMinterms(String[][] table, String[] variables){
		List<String> minterms = new ArrayList<>();
		
		for (int satir = 0; satir < table.length; satir++) {
            if ("1".equals(table[satir][4])) { 
                StringBuilder minterm = new StringBuilder();
                for (int sutun = 0; sutun < 4; sutun++) { 
                    if ("0".equals(table[satir][sutun])) { 
                        minterm.append(variables[sutun+1]).append("'"); 
                    } else { 
                        minterm.append(variables[sutun+1]); 
                    }
                }
                minterms.add(minterm.toString());
            }
        }
		
		return minterms;
	}
	
	public List<String> findMaxterms(String[][] table, String[] variables) {
	    List<String> maxterms = new ArrayList<>();
	    
	    for (int satir = 0; satir < table.length; satir++) {
	        if ("0".equals(table[satir][4])) { 
	            StringBuilder maxterm = new StringBuilder();
	            maxterm.append("(");
	            for (int sutun = 0; sutun < 4; sutun++) {
	                if ("1".equals(table[satir][sutun])) { 
	                    maxterm.append(variables[sutun+1]).append("'").append("+"); 
	                } else { 
	                    maxterm.append(variables[sutun+1]).append("+"); 
	                }
	            }
	            maxterm.setLength(maxterm.length() - 1);
	            maxterm.append(")");
	            
	            maxterms.add(maxterm.toString());
	        }
	    }
	    
	    return maxterms;
	}


}
