package org.zza.parser.semanticstack.nodes;

import java.util.Collections;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class AllVariableDeclarationsNode extends ArrayNode {
    
    @Override
    public String getName() {
        return "VariableDeclarations";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    protected void orderArray() {
        Collections.reverse(getArray());
    }
    
    @Override
    protected boolean popFromSemanticStackUntil(SemanticStack stack) {
        return stack.peek().getName().equals("VariableDeclaration");
    }
}
