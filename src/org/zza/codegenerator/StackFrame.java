package org.zza.codegenerator;


public class StackFrame {
    
    private int returnLocation;
    private int controlLink;
    private int oldRegisters;
    private int parameters;
    private int localVariables;
    private int tempData;
    
    private int numberOfParameters;
    private int numberOfLocalVariables;
    private int maxTempData;
    
    public int getTotalFrameSize() {
        return 1 + 1 + 7 + numberOfParameters + numberOfLocalVariables + maxTempData;
    }
    
    public StackFrame(int offset, int parameterNumber, int localVariableNumber, int tempDataCount) {
        returnLocation = offset;
        controlLink = (offset=offset+1);
        oldRegisters = (offset=offset+1);
        parameters = (offset=offset+7);
        localVariables = (offset=offset+parameterNumber);
        tempData = (offset=offset+localVariableNumber);
        
        numberOfParameters = parameterNumber;
        numberOfLocalVariables = localVariableNumber;
        maxTempData = tempDataCount;
    }
    
    
}
