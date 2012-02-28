package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;

public class IdentifierNode extends SemanticNode{

    final String id = "<identifier>";
    String value;
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public IdentifierNode() {
    }
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        if (token.getStringType().equals(id)) {
            stack.push(this);
            value = token.getValue();
        } else {
            System.out.println("attempting to run "+ getName() + " on semantic stack. token did not match expected type");
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
        return "Identifier";
    }
}
