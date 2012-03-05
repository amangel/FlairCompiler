package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class ParameterNode extends TwoFieldNode {
    
    @Override
    public String getName() {
        return "ParameterNode";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
