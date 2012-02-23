package org.zza.parser;

public abstract class Entry {
    
    private final String entry;
    
    public Entry(final String s) {
        entry = s;
    }
    
    public String getType() {
        return entry;
    }
    
    public abstract boolean isTerminal();
    
}
