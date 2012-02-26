package org.zza.parser.parsingstack;


public abstract class ParsingEntry extends Entry {

    public ParsingEntry(String entry) {
        super(entry);
    }
    
    @Override
    public boolean isSemanticEntry() {
        return false;
    }
}
