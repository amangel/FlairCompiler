package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.DataMemoryManager;


public class Subtraction3AC extends ThreeAddressCode {

    public Subtraction3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }

    @Override
    public void emitCode() {
        if ("0123456789".contains(Character.toString(secondParam.charAt(0)))) {
            System.out.println(lineNumber++ + ":   LDC  0," + secondParam + "(6)");//Register 6 holds a 0;
        } else {
            System.out.println(lineNumber++ + ":    LD  0," + manager.getAddressOfVar(secondParam) + "(6)");//Register 6 holds a 0;
        }
        
        if ("0123456789".contains(Character.toString(thirdParam.charAt(0)))) {
            System.out.println(lineNumber++ + ":   LDC  1," + thirdParam + "(6)");
        } else {
            System.out.println(lineNumber++ + ":    LD  1," + manager.getAddressOfVar(thirdParam) + "(6)"); 
            
        }
        System.out.println(lineNumber++ + ":   SUB  0,0,1");
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
