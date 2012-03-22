package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;

import org.zza.parser.semanticstack.SemanticStack;

public abstract class ArrayNode extends SemanticNode {
    
    private ArrayList<SemanticNode> nodeArray;
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        nodeArray = new ArrayList<SemanticNode>();
        while (popFromSemanticStackUntil(stack)) {
            nodeArray.add(stack.pop());
        }
        stack.push(this);
        orderArray();
    }
    
    protected abstract void orderArray();
    protected abstract boolean popFromSemanticStackUntil(SemanticStack stack);
    
    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getDeclarations() + "}";
    }
    
    private String getDeclarations() {
        String toReturn = "";
        for (int i = nodeArray.size() - 1; i >= 0; i--) {
            toReturn += nodeArray.get(i).getStringRepresentation() + " ";
        }
        return toReturn;
    }

    public ArrayList<SemanticNode> getArray() {
        return nodeArray;
    }
    
}
