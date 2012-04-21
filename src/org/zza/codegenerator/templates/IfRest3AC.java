package org.zza.codegenerator.templates;

import org.zza.codegenerator.DataMemoryManager;
import org.zza.codegenerator.threeaddresscode.ThreeAddressCode;


public class IfRest3AC extends ThreeAddressCode {

    private int elseSize;

    public IfRest3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }

    public void setParameters(String first, String second, String third, int thenSize) {
        super.setParameters(first, second, third);
        this.elseSize = thenSize;
    }
    
    @Override
    public void emitCode() {
        System.out.println(lineNumber++ + ":    LD  7, "+  elseSize + "(7)"); 
    }

    @Override
    public void emitComments() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getEmittedSize() {
        return 1;
    }
    
}
