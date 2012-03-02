package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class AllParametersNode extends SemanticNode {
    
    private ArrayList<SemanticNode> parameters;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        parameters = new ArrayList<SemanticNode>();
        while(stack.peek().getName().equals("ParameterNode")) {
            parameters.add(stack.pop());
        }
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getParameters() + "}";
    }

    private String getParameters() {
        String toReturn = "";
        for (SemanticNode node : parameters) {
            toReturn += node.getStringRepresentation() + " ";
        }
        return toReturn;
    }
    
    @Override
    public String getName() {
        return "Parameters";
    }

    @Override
    public String accept(NodeVisitor visitor) {
        // TODO Auto-generated method stub
        return null;
    }

}
