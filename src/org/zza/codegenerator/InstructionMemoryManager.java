package org.zza.codegenerator;

import java.util.HashMap;

public class InstructionMemoryManager {

	private HashMap<String,Integer> functionMap;
	
	
	public InstructionMemoryManager(){
		functionMap = new HashMap<String,Integer>();
	}
	
	public void addFunction(String name, int location){
		functionMap.put(name, location);
	}
	
	public int getFunctionAddress(String name){
		return functionMap.get(name);
	}
	
}
