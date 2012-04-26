package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.Address;
import org.zza.codegenerator.DataMemoryManager;


public class ComparisonHeader3AC extends ThreeAddressCode {

    private int thenSize;

    public ComparisonHeader3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }

    public void setParameters(String first, String second, String third, int thenSize) {
        super.setParameters(first, second, third);
        this.thenSize = thenSize;
    }
    
    @Override
    public void emitCode() {
        Address address = null;
        if (isDigit(firstParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  0," + firstParam +ZERO_REGISTER);
        } else {
            address =  manager.getAddressOfVar(firstParam);
            System.out.println(lineNumber++ + ":    LD  0," + address.getOffset() + address.getRegisterValue());//Register 6 holds a 0;
            
        }
        if (isDigit(secondParam.charAt(0))) {
            System.out.println(lineNumber++ + ":   LDC  1," + secondParam +ZERO_REGISTER);
        } else {
            address =  manager.getAddressOfVar(secondParam);
            System.out.println(lineNumber++ + ":    LD  1," + address.getOffset() + address.getRegisterValue()); 
            
        }
        
        System.out.println(lineNumber++ + ":    SUB 0,0,1   get compared values relative to 0");
        
        if(thirdParam.equals("<")){
            System.out.println(lineNumber++ + ":    JGE 0" + "," +  thenSize + "(7)");
        }
        else if (thirdParam.equals(">")) {
            System.out.println(lineNumber++ + ":    JLE 0" + "," +  thenSize + "(7)");
        }
        else if (thirdParam.equals("=")) {
            System.out.println(lineNumber++ + ":    JNE 0" + "," +  thenSize + "(7)");     
        }
        else if (thirdParam.equals("<=")){
            System.out.println(lineNumber++ + ":    JGT 0" + "," +  thenSize + "(7)");    
        }
        else if (thirdParam.equals(">=")){
            System.out.println(lineNumber++ + ":    JLT 0" + "," +  thenSize + "(7)"); 
        }
        else if (thirdParam.equals("!=")){
            System.out.println(lineNumber++ + ":    JEQ 0" + "," + thenSize+ "(7)");
        }   
    }

    @Override
    public void emitComments() {
        
    }

    @Override
    public int getEmittedSize() {
        return 4;
    }
    
}
