package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


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
    
    public SemanticNode getLeftHand() {
        return leftHandSide;
    }
    
    public SemanticNode getRightHand() {
        return rightHandSide;
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " {" + leftHandSide.getStringRepresentation() + " : " + rightHandSide.getStringRepresentation() +"} ";
    }
}
