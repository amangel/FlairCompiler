package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class WhileExpressionNode extends TwoFieldNode {
    
    @Override
    public String getName() {
        return "While";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
