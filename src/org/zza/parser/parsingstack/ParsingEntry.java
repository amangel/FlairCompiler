package org.zza.parser.parsingstack;

public abstract class ParsingEntry extends Entry {
    
    public ParsingEntry(final String entry) {
        super(entry);
    }
    
    @Override
    public boolean isSemanticEntry() {
        return false;
    }
}
