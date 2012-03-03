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

    @Override
    public String getStringRepresentation() {
        return getName() + " {" + arguments.getStringRepresentation() + " }";
    }

    @Override
    public String getName() {
        return "Return";
    }

    @Override
    public String accept(NodeVisitor visitor) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
