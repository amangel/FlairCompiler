package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class DivisionExpressionNode extends TwoFieldNode {
    
    public DivisionExpressionNode() {
    }
    
    @Override
    public String getName() {
        return "Division";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
