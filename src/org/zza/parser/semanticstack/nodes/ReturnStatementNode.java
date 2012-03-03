package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class ReturnStatementNode extends SemanticNode {

    private SemanticNode arguments;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        arguments = stack.pop();
        stack.push(this);
    }

    public SemanticNode getArguments() {
        return arguments;
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " {" + arguments.getStringRepresentation() + " }";
    }

    @Override
    public String getName() {
        return "Return";
    }

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }

}
