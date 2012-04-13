package org.zza.codegenerator;


public class DataMemoryManager {
    
    private final int MAX = 1024;
    private int current;
    
    public DataMemoryManager() {
        current = 0;
    }
    
    public int getNextAvailable() {
        current++;
        return current-1;
    }

    public void release(int amount) {
        current -= amount;
    }
    
    public void addStackFrame(StackFrame frame) {
        
    }
}
