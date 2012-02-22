package org.zza.parser;


public class TerminalEntry extends Entry {

    public TerminalEntry(String s) {
        super(s);
    }

    @Override
    public boolean isTerminal() {
        return true;
    }
}
