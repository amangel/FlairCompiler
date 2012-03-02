package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class CompoundStatementNode extends SemanticNode {

    private ArrayList<SemanticNode> statements;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        statements = new ArrayList<SemanticNode>();
        while(!stack.peek().getName().equals("begin")) {
            statements.add(stack.pop());
        }
        stack.pop();
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getDeclarations() + "}";
    }

    private String getDeclarations() {
        String toReturn = "";
        for (int i = statements.size() - 1; i >= 0; i--) {
            toReturn += statements.get(i).getStringRepresentation() + " ";
        }
        return toReturn;
    }

    @Override
    public String getName() {
        return "CompoundStatement";
    }
    
    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
