package org.zza.parser;

import java.util.ArrayList;

import org.zza.scanner.CompilerToken;
import org.zza.scanner.CompilerTokenStream;

public class CompilerParser {
    
    private final String EOF = "EOF";
    private final String startSymbol = "program";
    private ParseStack parseStack;
    private Table ruleTable;
    private RecentTokensStack recentTokens;
    private CompilerTokenStream stream;
    
    public CompilerParser(final CompilerTokenStream tokenStream) {
        stream = tokenStream;
        parseStack = new ParseStack();
    }
    
    public void run() throws Exception {
        parseStack.push(new TerminalEntry(EOF));
        parseStack.push(new TerminalEntry(startSymbol));
        Entry A = parseStack.peek();
        CompilerToken i = null;
        while (A != null || !A.getType().equals(EOF)) {
            i = stream.getNext();
            A = parseStack.peek();
            if (A.isTerminal()) { //parseStack.peek();
                if (A.getType().equalsIgnoreCase(i.getId())) {
                    parseStack.pop();
                    A = parseStack.peek();
                    i = stream.getNext();// i.consume();
                } else {
                    throw new ParsingException("Terminal mismatch. Expected: "+A.getType() + " Found: "+i.getId()+"");
                }
            } else {
                if (ruleTable.find(A.getType(), i.getId()).contains("")) {//TODO: add code to check if a rule is contained

                    parseStack.pop();
                    A = parseStack.peek();
                    addToStack(ruleTable.find(A.getType(), i.getId()));
                } else {
                    throw new ParsingException("Non-terminal mismatch. No entry in the table for: " + A.getType() + " : "+i.getId()); // TODO give message for parsing exception
                }
            }
        }
    }
    
    private void addToStack(final ArrayList<Entry> find) {
        // TODO Auto-generated method stub
    }
}
