package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;
import java.util.Collections;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class ArgumentNode extends SemanticNode {
    
    private ArrayList<SemanticNode> arguments;
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        arguments = new ArrayList<SemanticNode>();
        while (!stack.peek().getName().equals("(")) {
            arguments.add(stack.pop());
        }
        Collections.reverse(arguments);
        stack.pop();
        stack.push(this);
    }
    
    public ArrayList<SemanticNode> getArguments() {
        return arguments;
    }
    
    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getArgumentString() + "}";
    }
    
    private String getArgumentString() {
        String toReturn = "";
        for (final SemanticNode node : arguments) {
            toReturn += node.getStringRepresentation() + " ";
        }
        return toReturn;
    }
    
    @Override
    public String getName() {
        return "Argument";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
}
