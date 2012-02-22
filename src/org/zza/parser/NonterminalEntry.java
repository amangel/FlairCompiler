package org.zza.parser;


public class NonterminalEntry extends Entry {

    public NonterminalEntry(String s) {
        super(s);
    }

    @Override
    public boolean isTerminal() {
        return false;
    }
    
}
