package org.zza.parser;

public class NonterminalEntry extends Entry {
    
    public NonterminalEntry(final String s) {
        super(s);
    }
    
    @Override
    public boolean isTerminal() {
        return false;
    }
    
}
