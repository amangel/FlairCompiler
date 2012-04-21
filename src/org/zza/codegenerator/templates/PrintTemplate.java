package org.zza.codegenerator.templates;

import org.zza.semanticchecker.SymbolTable;

public class PrintTemplate implements Template {

	private String myVar;
	private int mySize;
	private SymbolTable table;
	private String myVarAddress;

	
	
	public PrintTemplate(String myVar, SymbolTable table){
		this.myVar = myVar;
		this.mySize = 2;
		
		this.table = table;
		
		myVarAddress = table.getAddress(myVar);
	}
	
	@Override
	public void generateTM() {
		System.out.println(":    LD  0," + myVarAddress + "(6)");//Register 6 holds a 0;
		System.out.println(":   OUT  0,0,0");
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return mySize;
	}

}
