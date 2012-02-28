package org.zza.parser;

import java.util.ArrayList;

import org.zza.parser.parsingstack.Entry;
import org.zza.scanner.CompilerToken;

public class RecentTokensStack {
    
    private final int MAX_SIZE = 5;
    private final ArrayList<CompilerToken> stack;
    
    public RecentTokensStack() {
        stack = new ArrayList<CompilerToken>();
    }
    
    public void push(final CompilerToken token) {
        if (stack.size() == MAX_SIZE) {
            stack.remove(0);
        }
        stack.add(token);
    }

    public CompilerToken getMostRecent() throws ParsingException{
        if(stack.size() > 0) {
            return stack.get(stack.size()-1);
        } else {
            throw new ParsingException("Tried to retrieve a token from the recent token stream when it was empty.");
        }
    }
    
    public String getStackDump() {
        String output = "";
        for (CompilerToken token : stack) {
            output += token.getValue() + " ";
        }
        return output;
    }
}
