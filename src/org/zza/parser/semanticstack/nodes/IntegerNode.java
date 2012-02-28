package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;


public class IntegerNode extends SemanticNode{

    final String id = "integer";
    String value;
    
    public IntegerNode() {
        
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
        return value;
    }

    @Override
    public String getName() {
        return "integer";
    }
    
    
}
