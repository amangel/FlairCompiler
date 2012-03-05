package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class MinusExpressionNode extends TwoFieldNode {
    
    // protected SemanticNode leftHandSide from TwoFieldNode
    // protected SemanticNode rightHandSide from TwoFieldNode
    // protected SemanticNode parent from SemanticNode
    // protected CompilerToken token from SemanticNode
    
    public MinusExpressionNode() {
    }
    
    @Override
    public String getName() {
        return "MinusExpression";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
}