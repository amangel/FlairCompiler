package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;


public class RealNode extends SemanticNode{

    final String id = "real";
    String value;
    
    public RealNode() {
        
    }
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        if (token.getId().equals(id)) {
            stack.push(this);
            value = token.getValue();
        }
    }

    @Override
    public void printChildren() {
        
    }

    @Override
    public String getStringRepresentation() {
        return getTabIndentation(getDepth()) + getName() + " " + value;
    }

    @Override
    public String getName() {
        return "real";
    }
    
}
