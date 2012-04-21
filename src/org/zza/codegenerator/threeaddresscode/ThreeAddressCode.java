package org.zza.codegenerator.threeaddresscode;


public abstract class ThreeAddressCode {
    
    protected String firstParam;
    protected String secondParam;
    protected String thirdParam;
    
    public ThreeAddressCode() {}
    
    public void setParameters(String first, String second, String third) {
        firstParam = first;
        secondParam = second;
        thirdParam = third;
    }
    
    public abstract void emitCode();
    public abstract void emitComments();
    public abstract void getEmittedSize();
    
}
