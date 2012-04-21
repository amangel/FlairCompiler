package org.zza.codegenerator.templates;

import org.zza.codegenerator.DataMemoryManager;
import org.zza.semanticchecker.SymbolTable;

public class AssignmentTemplate implements Template{
	private String target;
	private String value;
	
	
	private String targetAddress;
	private String valueAddress;
	
	private DataMemoryManager memoryMap;
	
	private int mySize;
	
	public AssignmentTemplate(String target, String value, DataMemoryManager memory){
		this.target=target;
		this.value=value;
		this.memoryMap = memory;
		
		
		targetAddress = "" + memory.getAddressOfVar(target);
		valueAddress =  "" + memory.getAddressOfVar(value);
		
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
