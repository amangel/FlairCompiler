package org.zza.codegenerator.templates;

import org.zza.semanticchecker.SymbolTable;

public class SubtractionTemplate implements Template {
	
	private String var1;
	private String var2;
	private String targetVariable;	
	
	private String var1Address;
	private String var2Address;
	private String targetVarAddress;

	private SymbolTable table;
	
	private int mySize;

	
	public SubtractionTemplate(String var1, String var2, SymbolTable table, String targetVariable){
		this.var1=var1;
		this.var2=var2;
		this.targetVariable = targetVariable;
		this.table = table;
		this.mySize = 4;
		
		var1Address = table.getAddress(var1);
		var2Address = table.getAddress(var2);
		this.targetVarAddress = table.getAddress(targetVariable);
		
	}

	@Override
	public void generateTM() {
		
		System.out.println(":    LD  0," + var1Address + "(6)");//Register 6 holds a 0;
		System.out.println(":    LD  1," + var2Address + "(6)"); 
		System.out.println(":   SUB  0,0,1");
		System.out.println(":    ST  0," + targetVarAddress + "(6)");
		
	}

	@Override
	public int getSize() {
		return mySize;
	}

}