package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public abstract class TwoFieldNode extends SemanticNode {
    
    protected SemanticNode leftHandSide;
    protected SemanticNode rightHandSide;
    
    public TwoFieldNode() {
    }
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        rightHandSide = stack.pop();
        leftHandSide = stack.pop();
        leftHandSide.setParent(this);
        rightHandSide.setParent(this);
        stack.push(this);
    }
    
    public void convertLeftHandTo(ConversionNode node) {
        node.convert(leftHandSide);
        leftHandSide = node;
    }
    
    public SemanticNode getLeftHand() {
        return leftHandSide;
    }
    
    public SemanticNode getRightHand() {
        return rightHandSide;
    }
    
    @Override
    public String getStringRepresentation() {
        return "\n" + getName() + " {" + leftHandSide.getStringRepresentation() + " : " + rightHandSide.getStringRepresentation() + "} \n";
    }
    
    public String acceptVisitorLeftHand(NodeVisitor visitor) {
        return leftHandSide.accept(visitor);
    }
    
    public String acceptVisitorRightHand(NodeVisitor visitor) {
        return rightHandSide.accept(visitor);
    }
}
