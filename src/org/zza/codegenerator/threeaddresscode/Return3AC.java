package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.Address;
import org.zza.codegenerator.DataMemoryManager;


public class Return3AC extends ThreeAddressCode {

    public Return3AC(int lineNumber, DataMemoryManager dataManager) {
        super(lineNumber, dataManager);
    }

    @Override
    public void emitCode() {
        Address address = null;
        if (isDigit(firstParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  5," + firstParam  + ZERO_REGISTER);
        } else {
            address = manager.getAddressOfVar(firstParam);
            System.out.println(lineNumber++ + ":    LD  5," + address.getOffset() + address.getRegisterValue());//Register 6 holds a 0;
        }
        
        System.out.println(lineNumber++ + ":    LD  4,2(3)");
        //load old r3
        System.out.println(lineNumber++ + ":    LD  3,1(3)");
        //load control link
        System.out.println(lineNumber++ + ":   ADD  0,3,4");
        System.out.println(lineNumber++ + ":   LD  7,0(0)");
    }

    @Override
    public void emitComments() {
    }

    @Override
    public int getEmittedSize() {
        return 5;
    }
    
}
