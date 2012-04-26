package org.zza.codegenerator.threeaddresscode;

import org.zza.codegenerator.DataMemoryManager;


public class WhileFooter3AC  extends ThreeAddressCode {

    private int originalLineNumber;

    public WhileFooter3AC(int lineNumber, DataMemoryManager manager) {
        super(lineNumber, manager);
    }

    public void setParameters(String first, String second, String third, int originalLineNumber) {
        super.setParameters(first, second, third);
        this.originalLineNumber = originalLineNumber;
    }
    
    @Override
    public void emitCode() {
        System.out.println(lineNumber + ":   LDA  7,"+originalLineNumber+ZERO_REGISTER);
    }

    @Override
    public void emitComments() {
        
    }

    @Override
    public int getEmittedSize() {
        return 1;
    }
    
}
