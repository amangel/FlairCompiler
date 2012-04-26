package org.zza.codegenerator;

import java.util.HashMap;

public class InstructionMemoryManager {

	private HashMap<String,FunctionInformation> functionMap;
	
	
	
	public InstructionMemoryManager(){
		functionMap = new HashMap<String,FunctionInformation>();
	}
	
	public void addFunction(String name, int location, int size){
		functionMap.put(name, new FunctionInformation(location, size));
	}
	
	public FunctionInformation getFunctionAddress(String name){
		return functionMap.get(name);
	}
	
}
