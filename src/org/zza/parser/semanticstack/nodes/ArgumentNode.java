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
            arguments.add(stack.pop());
        }
        stack.pop();
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

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }

}
