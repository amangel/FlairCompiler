package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class CompareNode extends SemanticNode {
    
    private String value;
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        value = token.getValue();
        stack.push(this);
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " " + value;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String getName() {
        return "Compare";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}
