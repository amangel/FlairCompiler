package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class AllVariableDeclarationsNode extends SemanticNode {

    private ArrayList<SemanticNode> variableDeclarations;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        variableDeclarations = new ArrayList<SemanticNode>();
        while(stack.peek().getName().equals("VariableDeclaration")) {
            variableDeclarations.add(stack.pop());
        }
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getDeclarations() + "}";
    }

    private String getDeclarations() {
        String toReturn = "";
        for (int i = variableDeclarations.size() - 1; i >= 0; i--) {
            toReturn += variableDeclarations.get(i).getStringRepresentation() + " ";
        }
        return toReturn;
    }
    
    @Override
    public String getName() {
        return "VariableDeclarations";
    }

    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
