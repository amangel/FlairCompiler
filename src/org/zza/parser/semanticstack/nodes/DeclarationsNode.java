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
    public void runOnSemanticStack(SemanticStack stack) {
        if(stack.peek().getName().equals("FuncionDeclarations")) {
            functionDeclarations = stack.pop();
        }
        if(stack.peek().getName().equals("VariableDeclarations")) {
            variableDeclarations = stack.pop();
        }
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        // TODO Auto-generated method stub
        return "\n"+getName() + " { variables " + variableDeclarations.getStringRepresentation() 
                + ": func " + functionDeclarations.getStringRepresentation() + "}\n";
    }

    @Override
    public String getName() {
        return "Declarations";
    }

    @Override
    public String accept(NodeVisitor visitor) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
