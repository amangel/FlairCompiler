package org.zza.codegenerator.threeaddresscode;


public abstract class ThreeAddressCode {
    
    protected String firstParam;
    protected String secondParam;
    protected String thirdParam;
    protected int lineNumber;
    
    public ThreeAddressCode(int lineNumber) {this.lineNumber = lineNumber;}
    
    public void setParameters(String first, String second, String third) {
        firstParam = first;
        secondParam = second;
        thirdParam = third;
    }
    
    public abstract void emitCode();
    public abstract void emitComments();
    public abstract int getEmittedSize();
    
}
