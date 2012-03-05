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
import org.zza.visitor.StackPrintingVisitor;

public class CompilerParser {
    
    private final String COMMENT = "COMMENT";
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
    private String submissionOutput;
    
    public CompilerParser(final CompilerTokenStream tokenStream) {
        stream = tokenStream;
        recentTokens = new RecentTokensStack();
        parseStack = new ParseStack();
        semanticStack = new SemanticStack();
        ruleTable = new RuleTable();
        nodeFactory = new SemanticNodeFactory(recentTokens);
        try {
            run();
        } catch (final Exception e) {
            parsingErrorEncountered(e);
        }
    }
    
    public void run() throws ParsingException {
        parseStack.push(new TerminalEntry(EOF));
        addToParseStack(ruleTable.find(startSymbol, startToken));
        submissionOutput = "";
        A = parseStack.peek();
        getNextToken();
        while ((A != null) && !A.getType().equals(EOF)) {
            A = parseStack.peek();
            if (A.isTerminal()) {
                if (A.getType().equalsIgnoreCase(i.getStringType())) {
                    parseStack.pop();
                    if (parseStack.notEmpty()) {
                        A = parseStack.peek();
                        getNextToken();
                    }
                } else {
                    throw new ParsingException("Terminal mismatch. Expected: " + A.getType() + " Found: " + i.getStringType() + "");
                }
            } else if (A.isSemanticEntry()) {
                final SemanticNode node = nodeFactory.getNewNode(A.getType());
                node.runOnSemanticStack(semanticStack);
                parseStack.pop();
            } else {
                if (isRuleContained(A, i)) {
                    parseStack.pop();
                    addToParseStack(ruleTable.find(A.getType(), i.getStringType()));
                    A = parseStack.peek();
                } else {
                    throw new ParsingException("Non-terminal mismatch. No entry in the table for: " + A.getType() + " , " + i.getStringType());
                }
            }
        }
        printOutSemanticStack();
        
        if (!stream.isEmpty()) {
            throw new ParsingException("Parser found the end of file marker but the token stream was not empty.");
        }
        
        if (submissionOutput.length() > 0) {
            System.out.println(submissionOutput);
        }
        final StackPrintingVisitor printer = new StackPrintingVisitor();
        System.out.println(printer.visit(semanticStack.pop()));
    }
    
    // TODO: remove this before submitting
    private void printOutSemanticStack() {
        System.out.println("\n\nSemantic stack: ");
        for (final SemanticNode node : semanticStack.getArrayToPrintAndTest()) {
            System.out.println(node.getStringRepresentation());
        }
        System.out.println("SemanticStack contains " + semanticStack.getSize() + " items");
    }
    
    private void getNextToken() {
        recentTokens.push(i);
        i = stream.getNext();
        if (i.getStringType().equals(COMMENT)) {
            getNextToken();
        }
    }
    
    private boolean isRuleContained(final Entry A, final CompilerToken i) throws ParsingException {
        // System.out.println(A + " " + i);
        List<Entry> returnValue = null;
        try {
            returnValue = ruleTable.find(A.getType(), i.getStringType());
        } catch (final NullPointerException e) {
            throw new ParsingException("Program contains a grammatical error: Looking for: " + A.getType() + ", found: " + i.getStringType());
        }
        return returnValue != null;
    }
    
    private void addToParseStack(final List<Entry> tableEntry) {
        for (int i = tableEntry.size() - 1; i >= 0; i--) {
            if (!tableEntry.get(i).isEpsilon()) {
                parseStack.push(tableEntry.get(i));
            }
        }
    }
    
    public static void parsingErrorEncountered(final Exception e) {
        System.out.println("An error was encountered while parsing the program. Code before the error is:\n");
        System.out.println(recentTokens.getStackDump());
        System.out.println(e.getMessage());
        System.exit(-1);
    }
}
