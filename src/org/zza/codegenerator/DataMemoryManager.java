package org.zza.codegenerator;

import java.util.HashMap;
import java.util.Stack;


public class DataMemoryManager {
    
    private final int MAX = 1024;
    private int current;
    private HashMap<String,String> memory; //<Variable,Address>
    
    private Stack<Frame> memoryStack;
    
    public DataMemoryManager() {
        current = 0;
        memory = new HashMap<String,String>();
        memoryStack = new Stack<Frame>();
    }

    private void release(int amount) {
        current -= amount;
    }
    
    public void addStackFrame(Frame frame) throws MemoryOutOfBoundsException {
        if ((current + frame.getSize())< MAX){
        	memoryStack.push(frame);
        	current += frame.getSize();
        } else {
        	throw new MemoryOutOfBoundsException(
        			"Not enough Data Memory available to add a new stack frame" );
        }
    }
    
    public Frame peekTopOfStack(){
    	return memoryStack.peek();
    }
    
    public Frame peekBottomOfStack(){
    	return memoryStack.lastElement();
    }
    
    
    public Frame popMemoryStack(){
    	release(memoryStack.peek().getSize());
    	return memoryStack.pop();
    }
    
    public void addNewVariable(String varName) throws MemoryOutOfBoundsException{
    	Frame frame = peekTopOfStack();
    	int address = frame.getNextTemporary();
    	memory.put(varName, "" + (address + frame.getStartAddress()));
    }

    public int getAddressOfVar(String varName){
    	return Integer.parseInt(memory.get(varName));
    }
    
}
