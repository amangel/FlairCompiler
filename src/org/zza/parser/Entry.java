package org.zza.parser;

public abstract class Entry {
    
    private String entry;
    private String type;
    private boolean isTerminal;
    
    public Entry(String s) {
        entry = s;
       // setType();
    }
    
    public String getType() {
        return type;
    }
    
    public abstract boolean isTerminal();
    
}
