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
        if (isDigit(firstParam.charAt(0))) {
            System.out.println(lineNumber++ +":   LDC  0," + firstParam + ZERO_REGISTER);
        } else {
            System.out.println(lineNumber++ +":    LD  0," + manager.getAddressOfVar(firstParam) + OFFSET_REGISTER);//Register 6 holds a 0;
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
