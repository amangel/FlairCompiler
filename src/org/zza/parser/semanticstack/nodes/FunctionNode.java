package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class FunctionNode extends SemanticNode {
    
    private SemanticNode header;
    private SemanticNode body;
    
    public FunctionNode() {
        header = new EmptyNode();
        body = new EmptyNode();
    }
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        if (stack.peek().getName().equals("FunctionBody")) {
            body = stack.pop();
        }
        if (stack.peek().getName().equals("FunctionHeading")) {
            header = stack.pop();
        }
        stack.push(this);
        body.setParent(this);
        header.setParent(this);
    }
    
    public SemanticNode getHeader() {
        return header;
    }
    
    public SemanticNode getBody() {
        return body;
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " {" + header.getStringRepresentation() + body.getStringRepresentation() + "}";
    }
    
    @Override
    public String getName() {
        return "Function";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}