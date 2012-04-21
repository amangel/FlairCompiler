package org.zza.codegenerator.threeaddresscode;


public class Assignment3AC extends ThreeAddressCode {

    public Assignment3AC(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public void emitCode() {
        System.out.println(lineNumber++ + ":    LD  0," + firstParam  + "(6)");//Register 6 holds a 0;
        System.out.println(lineNumber + ":    ST  0," + secondParam + "(6)");
    }

    @Override
    public void emitComments() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getEmittedSize() {
        return 2;
    }
    
}
