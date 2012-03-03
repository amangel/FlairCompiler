package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class AllFunctionDeclarationsNode extends SemanticNode {

    private ArrayList<SemanticNode> functionDeclarations;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        functionDeclarations = new ArrayList<SemanticNode>();
        while(stack.peek().getName().equals("Function")) {
            functionDeclarations.add(stack.pop());
        }
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getDeclarations() + "}";
    }

    private String getDeclarations() {
        String toReturn = "";
        for (int i = functionDeclarations.size() - 1; i >= 0; i--) {
            toReturn += functionDeclarations.get(i).getStringRepresentation() + " ";
        }
        return toReturn;
    }
    
    @Override
    public String getName() {
        return "FunctionDeclarations";
    }

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }

    public ArrayList<SemanticNode> getFunctions() {
        return functionDeclarations;
    }
    
}
