package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class FunctionHeadingNode extends ThreeFieldNode {
    
    @Override
    public String getName() {
        return "FunctionHeading";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
