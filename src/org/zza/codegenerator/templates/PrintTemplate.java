package org.zza.codegenerator.templates;

import org.zza.codegenerator.DataMemoryManager;
import org.zza.semanticchecker.SymbolTable;

public class PrintTemplate implements Template {

	private String myVar;
	private int mySize;
	private DataMemoryManager memoryManager;
	private String myVarAddress;

	
	
	public PrintTemplate(String myVar, DataMemoryManager memory){
		this.myVar = myVar;
		this.mySize = 2;
		
		this.memoryManager = memory;
		
		myVarAddress = "" + memory.getAddressOfVar(myVar);
	}
	
	@Override
	public void generateTM() {
		System.out.println(":    LD  0," + myVarAddress + "(6)");//Register 6 holds a 0;
		System.out.println(":   OUT  0,0,0");
	}

	@Override
	public int getSize() {
		return mySize;
	}

}
