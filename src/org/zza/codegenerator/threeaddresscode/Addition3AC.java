package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.Address;
import org.zza.codegenerator.DataMemoryManager;


public class Addition3AC extends ThreeAddressCode {

    public Addition3AC(int lineNumber, DataMemoryManager manager) {
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

        if (isDigit(thirdParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  1," + thirdParam + ZERO_REGISTER); 
        } else {
            address = manager.getAddressOfVar(thirdParam);
            System.out.println(lineNumber++ + ":    LD  1," + address.getOffset() + address.getRegisterValue());
        }
        System.out.println(lineNumber++ + ":   ADD  0,0,1");//Register 5 is Return Value
        address = manager.getAddressOfVar(firstParam);
        System.out.println(lineNumber + ":    ST  0," + address.getOffset() + address.getRegisterValue());
    }

    @Override
    public void emitComments() {
        
    }

    @Override
    public int getEmittedSize() {
        return 4;
    }
    
}
