package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;
import java.util.Collections;

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
        Collections.reverse(variableDeclarations);
        stack.push(this);
    }

    public ArrayList<SemanticNode> getDeclarations(){
        return variableDeclarations;
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getDeclarationString() + "}";
    }

    private String getDeclarationString() {
        String toReturn = "";
        for (SemanticNode declaration : variableDeclarations) {
            toReturn += declaration.getStringRepresentation() + " ";
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
