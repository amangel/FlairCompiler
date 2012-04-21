package org.zza.codegenerator.templates;

import org.zza.semanticchecker.SymbolTable;

public class MultiplicationTemplate implements Template {
	
	private String var1;
	private String var2;
	private String targetVar;
	
	
	private String var1Address;
	private String var2Address;
	private String targetVarAddress;
	
	private SymbolTable table;
	
	private int mySize;
	
	
	public MultiplicationTemplate(String var1, String var2, String targetVar, SymbolTable table){
		this.var1=var1;
		this.var2=var2;
		this.targetVar = targetVar;
		this.table = table;
		
		mySize = 4;

		
		var1Address = table.getAddress(var1);
		var2Address = table.getAddress(var2);
		targetVarAddress   = table.getAddress(targetVar);
	}

	@Override
	public void generateTM() {
		
		System.out.println(":    LD  0," + var1Address + "(6)");//Register 6 holds a 0;
		System.out.println(":    LD  1," + var2Address + "(6)"); 
		System.out.println(":   MUL  0,0,1");
		System.out.println(":    ST  0," + targetVarAddress + "(6)");
		
	}

	@Override
	public int getSize() {
		return mySize;
	}

}