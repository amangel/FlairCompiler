package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class ProgramHeaderNode extends SemanticNode {

    private SemanticNode parameters;
    private SemanticNode name;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        if(stack.peek().getName().equals("AllParameters")) {
            parameters = stack.pop();
        }
        name = stack.pop();
        stack.push(this);
    }

    public SemanticNode getIdentifier() {
        return name;
    }
    
    public SemanticNode getParameters() {
        return parameters;
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " : " + name.getStringRepresentation() + " : " + parameters.getStringRepresentation();
    }

    @Override
    public String getName() {
        return "ProgramHeader";
    }

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }

}
