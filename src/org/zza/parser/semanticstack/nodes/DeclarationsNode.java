package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class DeclarationsNode extends SemanticNode {
    
    private SemanticNode variableDeclarations;
    private SemanticNode functionDeclarations;
    
    public DeclarationsNode() {
        variableDeclarations = new EmptyNode();
        functionDeclarations = new EmptyNode();
    }
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        if (stack.peek().getName().equals("FunctionDeclarations")) {
            functionDeclarations = stack.pop();
        } else {
            System.out.println("Tried to get functions for declaration, instead found: " + stack.peek().getName());
        }
        if (stack.peek().getName().equals("VariableDeclarations")) {
            variableDeclarations = stack.pop();
        } else {
            System.out.println("Tried to get variables for declaration, instead found: " + stack.peek().getName());
        }
        stack.push(this);
    }
    
    public SemanticNode getVariableDeclarations() {
        return variableDeclarations;
    }
    
    public SemanticNode getFunctionDeclarations() {
        return functionDeclarations;
    }
    
    @Override
    public String getStringRepresentation() {
        return "\n" + getName() + " { variables " + variableDeclarations.getStringRepresentation() + ": func " + functionDeclarations.getStringRepresentation() + "}\n";
    }
    
    @Override
    public String getName() {
        return "Declarations";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}
