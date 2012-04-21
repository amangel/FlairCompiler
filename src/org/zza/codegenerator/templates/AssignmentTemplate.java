package org.zza.codegenerator.templates;

import org.zza.semanticchecker.SymbolTable;

public class AssignmentTemplate implements Template{
	private String target;
	private String value;
	
	
	private String targetAddress;
	private String valueAddress;
	
	private SymbolTable table;
	
	private int mySize;
	
	public AssignmentTemplate(String target, String value, SymbolTable table){
		this.target=target;
		this.value=value;
		this.table = table;
		
		
		targetAddress = table.getAddress(target);
		valueAddress = table.getAddress(value);
		
		mySize = 2;
		
	}

	@Override
	public void generateTM() {
		

		System.out.println(":    LD  0," + valueAddress  + "(6)");//Register 6 holds a 0;
		System.out.println(":    ST  0," + targetAddress + "(6)");

		
	}

	@Override
	public int getSize() {
		return mySize;
	}

}
