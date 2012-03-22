package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class AssignmentExpressionNode extends TwoFieldNode {
    
    public AssignmentExpressionNode() {
    }
    
    @Override
    public String getName() {
        return "AssignmentExpression";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
