package org.zza.codegenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class DataMemoryManager {
    
    private final int MAX = 1024;
    private int current;
    private HashMap<String,String> memory; //<Variable,Address>
    private HashMap<Frame,Map<String,String>> newMemory;
    private Stack<Frame> memoryStack;
    
    public DataMemoryManager() {
        current = 0;
        newMemory = new HashMap<Frame,Map<String,String>>();
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
            newMemory.put(frame, new HashMap<String,String>());
        } else {
            throw new MemoryOutOfBoundsException(
                    "Not enough Data Memory available to add a new stack frame" );
        }
    }
    
    private Frame peekTopOfStack(){
        return memoryStack.peek();
    }
    
    private Frame peekBottomOfStack(){
        return memoryStack.lastElement();
    }
    
    
    public Frame popMemoryStack(){
        release(memoryStack.peek().getSize());
        newMemory.remove(peekTopOfStack());
        return memoryStack.pop();
    }
    
    public void addNewTemporaryVariable(String varName) throws MemoryOutOfBoundsException{
        Frame frame = peekTopOfStack();
        int address = frame.getNextTemporary();
        addToMemory(varName, frame, address);
        memory.put(varName, "" + (address));
    }
    
    public void addLocalVariable(String varName) throws MemoryOutOfBoundsException {
        Frame frame = peekTopOfStack();
        if (!newMemory.get(frame).containsKey(varName)) {
            int address = frame.getNextLocal();
            addToMemory(varName, frame, address);
            memory.put(frame.getName()+varName, ""+(address));
        }
    }
    
    
    public int getAddressOfVar(String varName){
        String r = getAddress(varName, peekTopOfStack());
        
        if (r==null){
            r = getAddress(varName,peekBottomOfStack());
        }
        return Integer.parseInt(r);
    }
    
    private String getAddress(String varName, Frame frame) {
        return newMemory.get(frame).get(varName);
    }
    
    private void addToMemory(String varName, Frame frame, int address) {
        newMemory.get(frame).put(varName, "" +(address));
    }
    
    public void dump() {
        System.out.println("*newMemory: "+newMemory);
    }
    
}
