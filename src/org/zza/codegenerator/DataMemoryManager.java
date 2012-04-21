package org.zza.codegenerator;

import java.util.HashMap;
import java.util.Stack;


public class DataMemoryManager {
    
    private final int MAX = 1024;
    private int current;
    private HashMap<String,String> memory; //<Variable,Address>
    
    private Stack<StackFrame> memoryStack;
    
    public DataMemoryManager() {
        current = 0;
        memory = new HashMap<String,String>();
        memoryStack = new Stack<StackFrame>();
    }

    private void release(int amount) {
        current -= amount;
    }
    
    public void addStackFrame(StackFrame frame) throws MemoryOutOfBoundsException {
        if ((current + frame.getTotalFrameSize())< MAX){
        	memoryStack.push(frame);
        	current += frame.getTotalFrameSize();
        } else {
        	throw new MemoryOutOfBoundsException(
        			"Not enough Data Memory available to add a new stack frame" );
        }
    }
    
    public StackFrame peekTopOfStack(){
    	return memoryStack.peek();
    }
    
    public StackFrame peekBottomOfStack(){
    	return memoryStack.lastElement();
    }
    
    
    public StackFrame popMemoryStack(){
    	release(memoryStack.peek().getTotalFrameSize());
    	return memoryStack.pop();
    }
    
    public void addNewVariable(String varName) throws MemoryOutOfBoundsException{
    	StackFrame frame = peekTopOfStack();
    	int address = frame.getNextTemporary();
    	memory.put(varName, "" + (address + frame.getStartAddress()));
    }

    public int getAddressOfVar(String varName){
    	return Integer.parseInt(memory.get(varName));
    }
    
}
