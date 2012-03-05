package org.zza.parser.parsingstack;

public abstract class Entry {
    
    private final String entry;
    
    public Entry(final String s) {
        entry = s;
    }
    
    public String getType() {
        return entry;
    }
    
    @Override
    public String toString() {
        return entry;
    }
    
    public boolean isEpsilon() {
        return entry.equals("<epsilon>");
    }
    
    public abstract boolean isSemanticEntry();
    
    public abstract boolean isTerminal();
    
}
