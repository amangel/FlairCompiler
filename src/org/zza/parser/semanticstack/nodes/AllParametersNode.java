package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class AllParametersNode extends ArrayNode {
    
    @Override
    protected void orderArray() {
        //do nothing
    }
    
    @Override
    protected boolean popFromSemanticStackUntil(SemanticStack stack) {
        return stack.peek().getName().equals("ParameterNode");
    }
    
    @Override
    public String getName() {
        return "AllParameters";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    } 
}
