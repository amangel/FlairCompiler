package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.Address;
import org.zza.codegenerator.DataMemoryManager;


public class Assignment3AC extends ThreeAddressCode {
    
    public Assignment3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }
    
    @Override
    public void emitCode() {
        Address address = null;
        if (isDigit(firstParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  0," + firstParam  + ZERO_REGISTER + "  load constant for assignment");
        } else {
            address = manager.getAddressOfVar(firstParam);
            System.out.println(lineNumber++ + ":    LD  0," + address.getOffset() + address.getRegisterValue()+ "  load address for assignment");//Register 6 holds a 0;
        }
        address = manager.getAddressOfVar(secondParam);
        System.out.println(lineNumber + ":    ST  0," + address.getOffset() + address.getRegisterValue() + "  store value at address for assignment");
    }
    
    @Override
    public void emitComments() {
        
    }
    
    @Override
    public int getEmittedSize() {
        return 2;
    }
    
}
