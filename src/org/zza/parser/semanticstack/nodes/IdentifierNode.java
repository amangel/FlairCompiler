package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

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

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " " +value;
    }
    
    @Override
    public String getName() {
        return "Identifier";
    }
}
