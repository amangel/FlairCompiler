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
    private String target;
    
    public FunctionCall3AC(int lineNumber, String name, String params, String target, DataMemoryManager dataManager, InstructionMemoryManager instructionManager) {
        super(lineNumber, dataManager);
        this.dataManager = dataManager;
        this.instructionManager = instructionManager;
        this.parameters = params.split(",");
        this.name = name;
        this.target = target;
    }
    
    @Override
    public void emitCode() {
        Address address = null;
        FunctionInformation info = instructionManager.getFunctionAddress(name);
        
        System.out.println("*starting function call");
        System.out.println(lineNumber++ + ":   ADD  0,3,4   get the start point of the new frame");//get start of new frame
        System.out.println(lineNumber++ + ":    ST  3,1(0)  store register 3");//store 3
        System.out.println(lineNumber++ + ":    ST  4,2(0)  store register 4");//store 4
        System.out.println("*copying parameters");
        int count = 0;
        for (String parameter : parameters) { //copy each param into the new stack frame4
            if(parameter.length() > 0) {
                if(isDigit(parameter.charAt(0))) {
                    System.out.println(lineNumber++ + ":   LDC  1,"+parameter+ZERO_REGISTER);
                } else {
//                    System.out.println("parameter is: "+parameter);
                    address = dataManager.getAddressOfVar(parameter);
                    System.out.println(lineNumber++ + ":    LD  1," + address.getOffset() + address.getRegisterValue());
                }
                System.out.println(lineNumber ++ + ":    ST  1,"+(count+4)+"(0)");
            }
            count++;
        }
        System.out.println("*updating control registers");
        System.out.println(lineNumber++ + ":   LDA  3,0(0)   update register 3");      //update register 3 to the new stack fram ebeginning
        System.out.println(lineNumber++ + ":   LDC  4,"+info.getSize()+ZERO_REGISTER + "   update register 4"); //update register 4
        System.out.println(lineNumber++ + ":   LDC  1,1(6)   load the number of instructions to jump");      //get the number of instructions to jump past
        System.out.println(lineNumber++ + ":   ADD  0,1,7    calculate the address to load into the control link");
        System.out.println("*storing control link");
        System.out.println(lineNumber++ + ":    ST  0,0(3)");
        System.out.println(lineNumber++ + ":   LDA  7,"+info.getLine()+ZERO_REGISTER + "   jump to the function");
        address = dataManager.getAddressOfVar(target);
        //get return value from register 5
        System.out.println(lineNumber++ + ":    ST  5,"+address.getOffset() + address.getRegisterValue() + "   store the return value");
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
        return 10 + (2 * getValidParameterNumber());
    }
    
}
