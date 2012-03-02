package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class ProgramHeaderNode extends SemanticNode {

    private SemanticNode parameters;
    private SemanticNode name;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        if(stack.peek().getName().equals("Parameters")) {
            parameters = stack.pop();
        }
        name = stack.pop();
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " : " + name.getStringRepresentation() + " : " + parameters.getStringRepresentation();
    }

    @Override
    public String getName() {
        return "ProgramHeader";
    }

    @Override
    public String accept(NodeVisitor visitor) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
