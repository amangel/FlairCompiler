package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.DataMemoryManager;


public abstract class ThreeAddressCode {
    
    protected final static String DIGITS = "1234567890-";
    
    protected String firstParam;
    protected String secondParam;
    protected String thirdParam;
    protected int lineNumber;
    protected DataMemoryManager manager;
    
    protected boolean isDigit(char d) {
        return DIGITS.contains(Character.toString(d));
    }
    
    public ThreeAddressCode(int lineNumber, DataMemoryManager manager) {
        this.lineNumber = lineNumber;
        this.manager = manager;
    }
    
    public void setParameters(String first, String second, String third) {
        firstParam = first;
        secondParam = second;
        thirdParam = third;
    }
    
    public abstract void emitCode();
    public abstract void emitComments();
    public abstract int getEmittedSize();
    
}
