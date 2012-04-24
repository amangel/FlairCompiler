package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.DataMemoryManager;


public class Assignment3AC extends ThreeAddressCode {
    
    public Assignment3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }
    
    @Override
    public void emitCode() {
        if (isDigit(firstParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  0," + firstParam  + "(6)");
        } else {
            System.out.println(lineNumber++ + ":    LD  0," + manager.getAddressOfVar(firstParam)  + "(6)");//Register 6 holds a 0;
        }
        System.out.println(lineNumber + ":    ST  0," + manager.getAddressOfVar(secondParam) + "(6)");
    }
    
    @Override
    public void emitComments() {
        
    }
    
    @Override
    public int getEmittedSize() {
        return 2;
    }
    
}
