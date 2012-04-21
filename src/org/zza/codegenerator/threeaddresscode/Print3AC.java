package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.DataMemoryManager;


public class Print3AC extends ThreeAddressCode {

    
    public Print3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }

    @Override
    public void setParameters(String first, String second, String third) {
        firstParam = first;
        secondParam = second;
        thirdParam = third;
    }
    
    @Override
    public void emitCode() {
        if ("0123456789".contains(Character.toString(firstParam.charAt(0)))) {
            System.out.println(lineNumber++ +":    LD  0," + firstParam + "(6)");
        } else {
            System.out.println(lineNumber++ +":   LDC  0," + manager.getAddressOfVar(firstParam) + "(6)");//Register 6 holds a 0;
        }
        System.out.println(lineNumber + ":   OUT  0,0,0");
    }

    @Override
    public void emitComments() {
    }

    @Override
    public int getEmittedSize() {
        return 2;
    }
    
}
