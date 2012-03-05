package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class ComparisonNode extends ThreeFieldNode {
    
    @Override
    public String getName() {
        return "Comparison";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}
