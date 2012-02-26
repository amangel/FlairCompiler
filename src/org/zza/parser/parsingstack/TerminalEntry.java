package org.zza.parser.parsingstack;

public class TerminalEntry extends ParsingEntry {
    
    public TerminalEntry(final String s) {
        super(s);
    }
    
    @Override
    public boolean isTerminal() {
        return true;
    }
}
