package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.Address;
import org.zza.codegenerator.DataMemoryManager;
import org.zza.codegenerator.FunctionInformation;
import org.zza.codegenerator.InstructionMemoryManager;


public class FunctionCall3AC extends ThreeAddressCode {
    
    private String[] parameters;
    private InstructionMemoryManager instructionManager;
    private DataMemoryManager dataManager;
    private String name;
    
    public FunctionCall3AC(int lineNumber, String name, String params, DataMemoryManager dataManager, InstructionMemoryManager instructionManager) {
        super(lineNumber, dataManager);
        this.dataManager = dataManager;
        this.instructionManager = instructionManager;
        this.parameters = params.split(",");
        this.name = name;
    }
    
    @Override
    public void emitCode() {
        Address address = null;
        FunctionInformation info = instructionManager.getFunctionAddress(name);
        
        System.out.println("*starting function call");
        System.out.println(lineNumber++ + ":   ADD  0,3,4");//get start of new frame
        System.out.println(lineNumber++ + ":    ST  3,1(0)");//store 3
        System.out.println(lineNumber++ + ":    ST  4,2(0)");//store 4
        System.out.println("*copying parameters");
        int count = 0;
        for (String parameter : parameters) { //copy each param into the new stack frame4
            if(parameter.length() > 0) {
                if(isDigit(parameter.charAt(0))) {
                    System.out.println(lineNumber++ + ":   LDC  1,"+parameter+ZERO_REGISTER);
                } else {
//                    System.out.println("parameter is: "+parameter);
                    address = dataManager.getAddressOfVar(parameter);
                    System.out.println(lineNumber++ + ":    LD   1," + address.getOffset() + address.getRegisterValue());
                }
                System.out.println(lineNumber ++ + ":    ST   1,"+(count+4)+"(0)");
            }
            count++;
        }
        System.out.println("*updating control registers");
        System.out.println(lineNumber++ + ":   LDA   3,0(0)");      //update register 3 to the new stack fram ebeginning
        System.out.println(lineNumber++ + ":   LDC  4,"+info.getSize()+ZERO_REGISTER); //update register 4
        System.out.println(lineNumber++ + ":   LDC   1,2(6)");      //get the number of instructions to jump past
        System.out.println(lineNumber++ + ":   ADD   0,1,7");
//        System.out.println(lineNumber++ + ":   OUT   0,0,0");
        System.out.println("*storing control link");
        System.out.println(lineNumber++ + ":    ST   0,0(3)");
        System.out.println(lineNumber++ + ":   LDA  7,"+info.getLine()+ZERO_REGISTER);
        /*
         * ADD 0,3,4
         * ST  3,1(0)
         * ST 4,2(0)
         * for all param n:
         *  LDC 1, n(3)      ld/ldc? figure it out
         *  st 1,n+3(3)
         * lda r3,0(0)
         * lda 4,framesize (6)
         * ldc 1,2(1)
         * add 0,1,7
         * st 0,0(3)
         * LDA *WHERE TO JUMP TO*
         */
    }
    
    @Override
    public void emitComments() {
        
    }
    
    private int getValidParameterNumber() {
        int toReturn = 0;
        for (String parameter : parameters) {
            if (parameter.length() > 0) {
                toReturn++;
            }
        }
        return toReturn;
    }
    
    @Override
    public int getEmittedSize() {
        return 9 + (2 * getValidParameterNumber());
    }
    
}
