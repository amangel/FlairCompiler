package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.DataMemoryManager;


public class IfHeader3AC extends ThreeAddressCode {

    private int thenSize;

    public IfHeader3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }

    public void setParameters(String first, String second, String third, int thenSize) {
        super.setParameters(first, second, third);
        this.thenSize = thenSize;
    }
    
    @Override
    public void emitCode() {
        if ("0123456789".contains(Character.toString(firstParam.charAt(0)))) {
            System.out.println(lineNumber++ + ":   LDC  0," + firstParam +"(6)");
        } else {
            System.out.println(lineNumber++ + ":    LD  0," + manager.getAddressOfVar(firstParam) + "(6)");//Register 6 holds a 0;
            
        }
        if ("0123456789".contains(Character.toString(secondParam.charAt(0)))) {
            System.out.println(lineNumber++ + ":   LDC  1," + secondParam +"(6)");
        } else {
            System.out.println(lineNumber++ + ":    LD  1," + manager.getAddressOfVar(secondParam) + "(6)"); 
            
        }
        
//        if(operator == "<"){
            System.out.println(lineNumber++ + ":    SUB 0, 0, 1");
            System.out.println(lineNumber++ + ":    JGE 0" + ", " +  (thenSize) + "(7)");
//            thenCodeBlock.emitCode();
//            System.out.println(":    LD  7, "+  elseSize + "(7)");
//            elseCodeBlock.emitCode();
    }

    @Override
    public void emitComments() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getEmittedSize() {
        return 4;
    }
    
}
