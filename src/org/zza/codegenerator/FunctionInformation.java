package org.zza.codegenerator;


public class FunctionInformation {
    
    private int size;
    private int lineNumber;

    public FunctionInformation(int lineNumber, int size) {
        this.lineNumber = lineNumber;
        this.size = size;
    }
    
    public int getLine() {
        return lineNumber;
    }
    
    public int getSize() {
        return size;
    }
    
}
