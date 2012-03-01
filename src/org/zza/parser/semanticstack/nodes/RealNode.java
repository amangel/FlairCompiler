package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class RealNode extends SemanticNode {

    final String id = "real";
    String value;
    
    public RealNode() {
        
    }
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        if (token.getStringType().equals(id)) {
            stack.push(this);
            value = token.getValue();
        }
    }

    @Override
    public String getStringRepresentation() {
        return getTabIndentation(getDepth()) + getName() + " " + value;
    }

    @Override
    public String getName() {
        return "real";
    }
    
    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
