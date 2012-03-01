package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;



public class DivisionExpressionNode extends TwoFieldNode {

    //protected SemanticNode leftHandSide from TwoFieldNode
    //protected SemanticNode rightHandSide from TwoFieldNode
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public DivisionExpressionNode() {
    }
    
    @Override
    public String getName() {
        return "DivisionExpression";
    }
    
    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
