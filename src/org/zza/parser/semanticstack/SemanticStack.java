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
        if (getSize() > 0) {
            return getSize() - 1;
        } else {
            throw new ArrayIndexOutOfBoundsException("Stack was empty");
        }
    }
    
    @Override
    public String toString() {
        return "SemanticStack: " + stack.toString();
    }
    
    public boolean notEmpty() {
        return stack.size() > 0;
    }
    
    private int getSize() {
        return stack.size();
    }
    
    public void printOutSemanticStack() {
        System.out.println("\n\nSemantic stack: ");
        for (final SemanticNode node : stack) {
            System.out.println(node.getStringRepresentation());
        }
        System.out.println("SemanticStack contains " + getSize() + " items");
    }
}
