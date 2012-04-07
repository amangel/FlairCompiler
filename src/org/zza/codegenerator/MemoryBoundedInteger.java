package org.zza.codegenerator;


public class MemoryBoundedInteger {
    
    private static final int max = 10000;//TODO find max
    private static int min = 0;
    
    private int value;
    
    public MemoryBoundedInteger(int num) throws MemoryOutOfBoundsException {
        checkBounds(num);
        value = num;
    }
    
    private void checkBounds(int num) throws MemoryOutOfBoundsException {
        if (num < min || num > max) {
            throw new MemoryOutOfBoundsException("Attempting to allocate a value that is out of bounds in memory.");
        }
    }

    public int getValue() {
        return value;
    }
    
    public void add(int val) throws MemoryOutOfBoundsException {
        checkBounds(val + value);
        value += val;
    }
    
    public void setMin(int min) throws MemoryOutOfBoundsException {
        this.min = min;
        checkBounds(value);
    }
    
}
