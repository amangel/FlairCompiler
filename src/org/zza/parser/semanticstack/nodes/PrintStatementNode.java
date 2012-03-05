package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class PrintStatementNode extends SemanticNode {
    
    private SemanticNode argument;
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        argument = stack.pop();
        stack.push(this);
    }
    
    public SemanticNode getArgument() {
        return argument;
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " " + argument.getStringRepresentation();
    }
    
    @Override
    public String getName() {
        return "Print";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}
