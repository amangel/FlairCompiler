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
            System.out.println(lineNumber++ + ":   LDC  5," + firstParam  + ZERO_REGISTER + "   load constant into return register");
        } else {
            address = manager.getAddressOfVar(firstParam);
            System.out.println(lineNumber++ + ":    LD  5," + address.getOffset() + address.getRegisterValue() + "   load value from address into return register");//Register 6 holds a 0;
        }
        
        System.out.println(lineNumber++ + ":    LD  4,1(3)");
        //load old r3
        System.out.println(lineNumber++ + ":    LD  3,2(3)");
        //load control link
        System.out.println(lineNumber++ + ":   ADD  0,3,4");
//        System.out.println(lineNumber++ + ":   OUT  5,0,0  JUMPING FROM RETURN VALUE");
        System.out.println(lineNumber++ + ":   LDA  7,0(0)   jump using the restored control link from return");
    }

    @Override
    public void emitComments() {
    }

    @Override
    public int getEmittedSize() {
        return 5;
    }
    
}
