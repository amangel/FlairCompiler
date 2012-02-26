package org.zza.parser.semanticstack;

import org.zza.scanner.CompilerToken;


public class IdentifierNode extends SemanticNode{

    final String id = "<identifier>";
    String value;
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public IdentifierNode() {
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
    
}
