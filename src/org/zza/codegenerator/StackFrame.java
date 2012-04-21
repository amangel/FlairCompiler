package org.zza.codegenerator;


public class StackFrame {
    
    private int startOfFrame;
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
        startOfFrame = offset;
        controlLink = (offset);
        oldRegisters = (offset=offset+1);
        parameters = (offset=offset+4);
        localVariables = (offset=offset+parameterNumber);
        tempData = (offset=offset+localVariableNumber);
        
        numberOfParameters = parameterNumber;
        numberOfLocalVariables = localVariableNumber;
        maxTempData = tempDataCount;

    }

	public int getStartAddress() {
		return startOfFrame;
	}

	public int getNextTemporary() throws MemoryOutOfBoundsException {
		if (maxTempData>0){
			maxTempData--;
			return tempData++;
		} else {
			throw new MemoryOutOfBoundsException("Requesting two many temporary variables");
		}
	}
    
    
}
