package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.Address;
import org.zza.codegenerator.DataMemoryManager;


public class Negative3AC extends ThreeAddressCode {
    
    public Negative3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }
    
    @Override
    public void emitCode() {
        Address address = null;
        if (isDigit(secondParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  0," + secondParam + ZERO_REGISTER);//Register 6 holds a 0;
        } else {
            address = manager.getAddressOfVar(secondParam);
            System.out.println(lineNumber++ + ":    LD  0," + address.getOffset() + address.getRegisterValue());//Register 6 holds a 0;
        }
        System.out.println(lineNumber++ + ":   SUB 0,6,0");
        address = manager.getAddressOfVar(firstParam);
        System.out.println(lineNumber + ":    ST  0,"+address.getOffset() + address.getRegisterValue());
    }
    
    @Override
    public void emitComments() {
        
    }
    
    @Override
    public int getEmittedSize() {
        return 3;
    }
    
}
