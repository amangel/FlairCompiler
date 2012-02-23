package org.zza.parser;

public class TerminalEntry extends Entry {
    
    public TerminalEntry(final String s) {
        super(s);
    }
    
    public boolean isTerminal() {
        return true;
    }
}
