package org.zza.codegenerator;


public class ProgramFrame extends Frame{

    private int temporaryVariableCount;
    private int localVariableCount;
    private int offset;
    private int localVariables;
    private int tempVariables;
    private int maxTempData;
    private int maxLocalData;

    public ProgramFrame(int offset, int localVariableCount, int temporaryVariableCount) {
        this.offset = offset;
        this.localVariables = offset;
        this.tempVariables = (offset = offset + localVariableCount);
        this.localVariableCount = localVariableCount;
        this.temporaryVariableCount = temporaryVariableCount;
        maxTempData = temporaryVariableCount;
        maxLocalData = localVariableCount;
    }
    
    @Override
    public int getSize() {
        return temporaryVariableCount + localVariableCount;
    }

    @Override
    public int getStartAddress() {
        return offset;
    }

    @Override
    public int getLocalDataSize() {
        return localVariableCount;
    }

    @Override
    public int getTempDataSize() {
        return temporaryVariableCount;
    }

    @Override
    public int getNextTemporary() throws MemoryOutOfBoundsException {
        if (maxTempData > 0) {
            maxTempData--;
            return tempVariables++;
        } else {
            throw new MemoryOutOfBoundsException("Requested too many temporary variables in the program frame. Max: "+temporaryVariableCount);
        }
    }

    @Override
    public int getNextLocal() throws MemoryOutOfBoundsException {
        if (maxLocalData > 0) {
            maxLocalData--;
            return localVariables++;
        } else {
            throw new MemoryOutOfBoundsException("Requested too many local variables in the program frame. Max: "+localVariableCount);
        }
    }

    @Override
    public String getName() {
        return "program";
    }
    
}
