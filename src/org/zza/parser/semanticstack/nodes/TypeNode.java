package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class TypeNode extends SemanticNode {
    
    final String integer = "integer";
    final String real = "real";
    String value;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        if(token.getValue().equals(integer) || token.getValue().equals(real)) {
            value = token.getValue();
            stack.push(this);
        } else {
            System.out.println("attempting to run "+ getName() + " on semantic stack. token did not match expected type");
        }
    }
    
    public String getType() {
        return value;
    }
    
    @Override
    public String getName() {
        return "Type";
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " " +value;
    }
    
    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}
