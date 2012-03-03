package org.zza.parser.semanticstack.nodes;

import java.util.ArrayList;
import java.util.Collections;

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
        Collections.reverse(statements);
        stack.pop();
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " {" + getDeclarations() + "}";
    }

    public ArrayList<SemanticNode> getStatements(){
        return statements;
    }
    
    private String getDeclarations() {
        String toReturn = "";
        for (SemanticNode statement : statements) {
            toReturn += statement.getStringRepresentation() + " ";
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
