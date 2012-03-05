package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class MarkerNode extends SemanticNode {
    
    private String value;
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        value = token.getValue();
        stack.push(this);
    }
    
    @Override
    public String getStringRepresentation() {
        return "Marker: " + getName();
    }
    
    @Override
    public String getName() {
        return value;
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public boolean isMarker() {
        return true;
    }
}
