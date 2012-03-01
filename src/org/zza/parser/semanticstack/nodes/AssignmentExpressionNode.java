package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;


public class AssignmentExpressionNode extends TwoFieldNode {

    //protected SemanticNode leftHandSide from TwoFieldNode
    //protected SemanticNode rightHandSide from TwoFieldNode
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public AssignmentExpressionNode() {
    }
    
    @Override
    public String getName() {
        return "AssignmentExpression";
    }

    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
