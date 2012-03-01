package org.zza.parser.parsingstack;


public class SemanticEntry extends Entry{

    public SemanticEntry(String entry) {
        super(entry);
    }
    
    @Override
    public boolean isSemanticEntry() {
        return true;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }
}
