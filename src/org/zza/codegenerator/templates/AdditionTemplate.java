package org.zza.codegenerator.templates;
import org.zza.semanticchecker.SymbolTable;

public class AdditionTemplate implements Template {
	
	private String var1;
	private String var2;
	private String targetVar;
	
	private String var1Address;
	private String var2Address;
	private String targetVarAddress;
	
	private SymbolTable table;
	
	private int mySize;
	
	public AdditionTemplate(String var1, String var2,String targetVar, SymbolTable table){
		this.var1=var1;
		this.var2=var2;
		this.table = table;
		this.targetVar = targetVar;
		this.mySize = 4;
		
		
		var1Address = table.getAddress(var1);
		var2Address = table.getAddress(var2);
		this.targetVarAddress = table.getAddress(targetVar);
		
	}

	@Override
	public void generateTM() {
		
		System.out.println(":    LD  0," + var1Address + "(6)");//Register 6 holds a 0;
		System.out.println(":    LD  1," + var2Address + "(6)"); 
		System.out.println(":   ADD  0,0,1");//Register 5 is Return Value
		System.out.println(":    ST  0," + targetVarAddress + "(6)");
		
	}

	@Override
	public int getSize() {
		return mySize;
	}

	
}
