package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;



public class PlusExpressionNode extends TwoFieldNode{

    //protected SemanticNode leftHandSide from TwoFieldNode
    //protected SemanticNode rightHandSide from TwoFieldNode
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public PlusExpressionNode() {
    }

    @Override
    public String getName() {
        return "PlusExpression";
    }
    
    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
//    @Override
//    public String getStringRepresentation() {
//        return getName() + " " + leftHandSide.getStringRepresentation() + " + " + rightHandSide.getStringRepresentation();
//    }
}
