package org.zza.parser.parsingstack;


public abstract class SemanticEntry extends Entry{

    public SemanticEntry(String entry) {
        super(entry);
    }
    
    @Override
    public boolean isSemanticEntry() {
        return false;
    }
}
