package org.zza.parser;

import java.util.ArrayList;

import org.zza.scanner.CompilerToken;
import org.zza.scanner.CompilerTokenStream;

public class CompilerParser {
    
    private final String EOF = "EOF";
    private final String startSymbol = "program";
    private final ParseStack parseStack;
    private final RuleTable ruleTable;
    private RecentTokensStack recentTokens;
    private final CompilerTokenStream stream;
    
    public CompilerParser(final CompilerTokenStream tokenStream) {
        stream = tokenStream;
        parseStack = new ParseStack();
        ruleTable = new RuleTable();
        try {
            run();
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public void run() throws Exception {
        parseStack.push(new TerminalEntry(EOF));
        parseStack.push(new TerminalEntry(startSymbol));
        Entry A = parseStack.peek();
        CompilerToken i = null;
        while ((A != null) || !A.getType().equals(EOF)) {
            i = stream.getNext();
            A = parseStack.peek();
            if (A.isTerminal()) {
                if (A.getType().equalsIgnoreCase(i.getId())) {
                    parseStack.pop();
                    A = parseStack.peek();
                    i = stream.getNext();// i.consume();
                } else {
                    throw new ParsingException("Terminal mismatch. Expected: " + A.getType() + " Found: " + i.getId() + "");
                }
            } else {
                if (isRuleContained(A, i)) {
                    addToParseStack(ruleTable.find(A.getType(), i.getId()));
                    parseStack.pop();
                    A = parseStack.peek();
                } else {
                    throw new ParsingException("Non-terminal mismatch. No entry in the table for: " + A.getType() + " , " + i.getId());
                }
            }
        }
        
        if (!stream.isEmpty()) {
            throw new ParsingException("Parser found the end of file marker but the token stream was not empty.");
        }
        
        System.out.println("Finished parser run() method.");
    }
    
    private boolean isRuleContained(final Entry A, final CompilerToken i) {
        final ArrayList<Entry> returnValue = ruleTable.find(A.getType(), i.getId());
        return returnValue != null;
    }
    
    private void addToParseStack(final ArrayList<Entry> tableEntry) {
        for (int i = tableEntry.size() - 1; i > 0; i--) {
            parseStack.push(tableEntry.get(i));
        }
    }
}
