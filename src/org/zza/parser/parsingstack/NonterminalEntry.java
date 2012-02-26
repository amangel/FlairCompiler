package org.zza.parser.parsingstack;

public class NonterminalEntry extends ParsingEntry {
    
    public NonterminalEntry(final String s) {
        super(s);
    }
    
    @Override
    public boolean isTerminal() {
        return false;
    }
    
}
