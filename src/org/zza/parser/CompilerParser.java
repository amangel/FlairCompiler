package org.zza.parser;

import java.util.List;

import org.zza.parser.parsingstack.Entry;
import org.zza.parser.parsingstack.ParseStack;
import org.zza.parser.parsingstack.TerminalEntry;
import org.zza.parser.semanticstack.SemanticNodeFactory;
import org.zza.parser.semanticstack.SemanticStack;
import org.zza.parser.semanticstack.nodes.SemanticNode;
import org.zza.scanner.CompilerToken;
import org.zza.scanner.CompilerTokenStream;

public class CompilerParser {
    
    private final String EOF = "EOF";
    private final String startSymbol = "<PROGRAM>";
    private final String startToken = "program";
    private final ParseStack parseStack;
    private final SemanticStack semanticStack;
    private final SemanticNodeFactory nodeFactory;
    private final RuleTable ruleTable;
    private static RecentTokensStack recentTokens;
    private final CompilerTokenStream stream;
    private Entry A;
    private CompilerToken i;
    
    public CompilerParser(final CompilerTokenStream tokenStream) {
        stream = tokenStream;
        recentTokens = new RecentTokensStack();
        parseStack = new ParseStack();
        semanticStack = new SemanticStack();
        ruleTable = new RuleTable();
        nodeFactory = new SemanticNodeFactory(recentTokens);
    }
    
    public SemanticNode parseProgram() {
        try {
            run();
        } catch (final Exception e) {
            parsingErrorEncountered(e);
        }
        return semanticStack.pop();
    }
    
    public void run() throws ParsingException {
        parseStack.push(new TerminalEntry(EOF));
        parseStack.addToParseStack(ruleTable.find(startSymbol, startToken));
        A = parseStack.peek();
        getNextToken();
        while ((A != null) && !A.isEof()) {
            A = parseStack.peek();
            if (A.isTerminal()) {
                if (A.matches(i)) {
                    parseStack.pop();
                    if (parseStack.notEmpty()) {
                        A = parseStack.peek();
                        getNextToken();
                    }
                } else {
                    throw new ParsingException("Terminal mismatch. Expected: " + A + " Found: " + i + "");
                }
            } else if (A.isSemanticEntry()) {
                final SemanticNode node = nodeFactory.getNewNode(A);
                node.runOnSemanticStack(semanticStack);
                parseStack.pop();
            } else {
                if (isRuleContained(A, i)) {
                    parseStack.pop();
                    parseStack.addToParseStack(ruleTable.find(A, i));
                    A = parseStack.peek();
                } else {
                    throw new ParsingException("Non-terminal mismatch. No entry in the table for: " + A + " , " + i);
                }
            }
        }
        
        if (!stream.isEmpty()) {
            throw new ParsingException("Parser found the end of file marker but the token stream was not empty.");
        }
        
    }
    
    private void getNextToken() {
        recentTokens.push(i);
        i = stream.getNext();
        if (i.isComment()) {
            getNextToken();
        }
    }
    
    private boolean isRuleContained(final Entry A, final CompilerToken i) throws ParsingException {
        List<Entry> returnValue = null;
        try {
            returnValue = ruleTable.find(A, i);
        } catch (final NullPointerException e) {
            throw new ParsingException("Program contains a grammatical error: Looking for: " + A + ", found: " + i);
        }
        return returnValue != null;
    }
    
    public static void parsingErrorEncountered(final Exception e) {
        System.out.println("An error was encountered while parsing the program. Code before the error is:\n");
        System.out.println(recentTokens.getStackDump());
        System.out.println(e.getMessage());
        e.printStackTrace();
        System.exit(-1);
    }
}
