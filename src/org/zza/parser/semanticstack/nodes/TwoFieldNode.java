package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;


public abstract class TwoFieldNode extends SemanticNode {

    protected SemanticNode leftHandSide;
    protected SemanticNode rightHandSide;
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public TwoFieldNode() {
    }
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        rightHandSide = stack.pop();
        leftHandSide = stack.pop();
        leftHandSide.setParent(this);
        rightHandSide.setParent(this);
        stack.push(this);
    }
    
    public String getStringRepresentation() {
        int depth = getDepth();
        String tabs = getTabIndentation(depth);
        String toReturn =  tabs + getName() + "\n"+leftHandSide.getStringRepresentation() 
                + "\n"+rightHandSide.getStringRepresentation();
        return toReturn;
    }
}
