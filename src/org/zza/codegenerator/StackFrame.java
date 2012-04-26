package org.zza.codegenerator;


public class StackFrame extends Frame {
    
    private int startOfFrame;
    private int controlLink;
    private int oldRegisters;
    private int parameters;
    private int localVariables;
    private int tempData;
    

    
    private int numberOfParameters;
    private int numberOfLocalVariables;
    private int numberOfTempData;
    private int maxTempData;
    private int maxLocalData;
    private String name;
    
    public int getSize() {
        return 3 + numberOfParameters + numberOfLocalVariables + maxTempData;
    }
    
    public StackFrame(int parameterNumber, int localVariableNumber, int tempDataCount, String name) {
        int offset = 0;
    	this.name= name;
        startOfFrame = offset;
        controlLink = (offset);
        oldRegisters = (offset=offset+1);
        parameters = (offset=offset+2);
        localVariables = (offset=offset+parameterNumber);
        tempData = (offset=offset+localVariableNumber);
        numberOfTempData = tempDataCount;
        numberOfParameters = parameterNumber;
        numberOfLocalVariables = localVariableNumber;
        maxTempData = tempDataCount;
        maxLocalData = localVariableNumber;

    }

	public int getStartAddress() {
		return startOfFrame;
	}

	public int getNextTemporary() throws MemoryOutOfBoundsException {
		if (maxTempData>0){
			maxTempData--;
			return tempData++;
		} else {
			throw new MemoryOutOfBoundsException("Requesting too many temporary variables in a stack frame");
		}
	}

    @Override
    public int getLocalDataSize() {
        return numberOfLocalVariables;
    }

    @Override
    public int getTempDataSize() {
        return numberOfTempData;
    }

    @Override
    public int getNextLocal() throws MemoryOutOfBoundsException {
        if (maxLocalData>0){
            maxLocalData--;
            return localVariables++;
        } else {
            throw new MemoryOutOfBoundsException("Requesting too many local variables in a stack frame");
        }
    }

    @Override
    public String getName() {
        return name;
    }
    
    
}
