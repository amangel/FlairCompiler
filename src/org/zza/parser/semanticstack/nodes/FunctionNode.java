package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class FunctionNode extends SemanticNode {

    private SemanticNode header;
    private SemanticNode declarations;
    private SemanticNode body;
    
    public FunctionNode () {
        header = new EmptyNode();
        declarations = new EmptyNode();
        body = new EmptyNode();
    }
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        if(stack.peek().getName().equals("CompoundStatement")) {
            body = stack.pop();
        }
        if(stack.peek().getName().equals("Declarations")) {
            declarations = stack.pop();
        }
        if(stack.peek().getName().equals("FunctionHeader")) {
            header = stack.pop();
        }
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " {" + header.getStringRepresentation() +
                declarations.getStringRepresentation() + 
                body.getStringRepresentation() + "}";
    }
    
    @Override
    public String getName() {
        return "Function";
    }
    
    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }

}