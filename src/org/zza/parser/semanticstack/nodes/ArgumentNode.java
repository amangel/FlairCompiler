package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class ArgumentNode extends SemanticNode {

    private ArrayList<SemanticNode> arguments;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        arguments = new ArrayList<SemanticNode>();
        while(!stack.peek().getName().equals("(")) {
            System.out.println("removing "+stack.peek().getName() + " from stack");
            arguments.add(stack.pop());
        }
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getArguments() + "}";
    }

    private String getArguments() {
        String toReturn = "";
        for (SemanticNode node : arguments) {
            toReturn += node.getStringRepresentation() + " ";
        }
        return toReturn;
    }
    
    @Override
    public String getName() {
        return "Argument";
    }

    @Override
    public String accept(NodeVisitor visitor) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
