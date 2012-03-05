package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class FunctionCallNode extends TwoFieldNode {
    
    @Override
    public String getName() {
        return "FunctionCall";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}
