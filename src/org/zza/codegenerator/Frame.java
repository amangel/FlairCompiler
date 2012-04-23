package org.zza.codegenerator;


public abstract class Frame {
    
    public abstract int getSize();
    public abstract int getStartAddress();
    public abstract int getLocalDataSize();
    public abstract int getTempDataSize();
    public abstract int getNextTemporary() throws MemoryOutOfBoundsException;
    public abstract int getNextLocal() throws MemoryOutOfBoundsException;
    public abstract String getName();
}
