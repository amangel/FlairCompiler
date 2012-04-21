package org.zza.codegenerator.threeaddresscode;


public class PrintLiteral3AC extends ThreeAddressCode {
    
    public PrintLiteral3AC(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public void emitCode() {
        System.out.println(lineNumber++ +":   LDC  0," + firstParam + "(6)");//Register 6 holds a 0;
        System.out.println(lineNumber + ":   OUT  0,0,0");         
    }
    
    @Override
    public void emitComments() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public int getEmittedSize() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
