package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.DataMemoryManager;


public class Division3AC extends ThreeAddressCode {

    public Division3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }

    @Override
    public void emitCode() {
        if (isDigit(secondParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  0," + secondParam + "(6)");//Register 6 holds a 0;
        } else {
            System.out.println(lineNumber++ + ":    LD  0," + manager.getAddressOfVar(secondParam) + "(6)");//Register 6 holds a 0;
        }
        if (isDigit(thirdParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  1," + thirdParam + "(6)"); 
        } else {
            System.out.println(lineNumber++ + ":    LD  1," + manager.getAddressOfVar(thirdParam) + "(6)"); 
        }
        System.out.println(lineNumber++ + ":   DIV  0,0,1");
        System.out.println(lineNumber + ":    ST  0," + manager.getAddressOfVar(firstParam) + "(6)");        
    }

    @Override
    public void emitComments() {
    }

    @Override
    public int getEmittedSize() {
        return 4;
    }
    
}
