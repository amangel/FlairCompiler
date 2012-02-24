package org.zza.parser;

import java.util.List;

import org.zza.scanner.CompilerToken;
import org.zza.scanner.CompilerTokenStream;

public class CompilerParser {
    
    private final String EOF = "EOF";
    private final String startSymbol = "<PROGRAM>";
    private final String startToken = "program";
    private final ParseStack parseStack;
    private final RuleTable ruleTable;
    private RecentTokensStack recentTokens;
    private final CompilerTokenStream stream;
    private Entry A;
    private CompilerToken i;
    
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
        //parseStack.push(new TerminalEntry(startSymbol));
        addToParseStack(ruleTable.find(startSymbol, startToken));
        
        A = parseStack.peek();
        getNextToken();
        while ((A != null) || !A.getType().equals(EOF)) {
           
            A = parseStack.peek();
            System.out.println("Parse stack:" +parseStack);
            System.out.println("working with: A:"+A +" i:"+i+"\n");
            
            if (A.isTerminal()) {
                System.out.println(A.getType() + " is terminal");
                System.out.println("A: "+A.getType() + " i: "+i.getId());
                if (A.getType().equalsIgnoreCase(i.getId())) {
                    System.out.println("A and i match");
                    System.out.println("i: "+i.getId());
                    parseStack.pop();
                    A = parseStack.peek();
                    getNextToken();// i.consume();
                    System.out.println("i: "+i.getId());
                    
                } else {
                    throw new ParsingException("Terminal mismatch. Expected: " + A.getType() + " Found: " + i.getId() + "");
                }
            } else {
                if (isRuleContained(A, i)) {
                    System.out.println("A is not terminal, rule was found");
                    parseStack.pop();
                    addToParseStack(ruleTable.find(A.getType(), i.getId()));
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
    
    private void getNextToken() {
        i = stream.getNext();
        if(i.getId().equals("COMMENT")) {
            getNextToken();
        }
    }

    private boolean isRuleContained(final Entry A, final CompilerToken i) {
        final List<Entry> returnValue = ruleTable.find(A.getType(), i.getId());
        return returnValue != null;
    }
    
    private void addToParseStack(final List<Entry> tableEntry) {
        System.out.println("adding to parse stack: "+tableEntry);
        for (int i = tableEntry.size() - 1; i >= 0; i--) {
            if(!tableEntry.get(i).isEpsilon()) {
                parseStack.push(tableEntry.get(i));
            }
        }
    }
}
