package org.zza.parser.semanticstack;

import java.util.ArrayList;

import org.zza.parser.semanticstack.nodes.SemanticNode;


public class SemanticStack {
    
    private final ArrayList<SemanticNode> stack;
    
    public SemanticStack() {
        stack = new ArrayList<SemanticNode>();
    }
    
    public boolean push(final SemanticNode node) {
        return stack.add(node);
    }
    
    public SemanticNode pop() throws ArrayIndexOutOfBoundsException {
        return stack.remove(getLastIndex());
    }
    
    public SemanticNode peek() throws ArrayIndexOutOfBoundsException {
        return stack.get(getLastIndex());
    }
    
    private int getLastIndex() throws ArrayIndexOutOfBoundsException {
        if (stack.size() > 0) {
            return stack.size() - 1;
        } else {
            throw new ArrayIndexOutOfBoundsException("Stack was empty");
        }
    }
    
    public String toString() {
        return "SemanticStack: " + stack.toString();
    }
    
    public boolean notEmpty() {
        return stack.size() > 0;
    }
    
    public int getSize() {
        return stack.size();
    }

    //TODO: TAKE THIS OUT BEFORE SUBMITTING
    public ArrayList<SemanticNode> getArrayToPrintAndTest() {
        return stack;
    }
}
