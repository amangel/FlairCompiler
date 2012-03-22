package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class FunctionBodyNode extends SemanticNode {
    
    private SemanticNode body;
    private SemanticNode variables;
    
    public FunctionBodyNode() {
        body = new EmptyNode();
        variables = new EmptyNode();
    }
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        body = stack.pop();
        if (stack.peek().getName().equals("VariableDeclarations")) {
            variables = stack.pop();
        }
        stack.push(this);
        body.setParent(this);
        variables.setParent(this);
    }
    
    public SemanticNode getVariables() {
        return variables;
    }
    
    public SemanticNode getBody() {
        return body;
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " {" + variables.getStringRepresentation() + body.getStringRepresentation();
    }
    
    @Override
    public String getName() {
        return "FunctionBody";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}
