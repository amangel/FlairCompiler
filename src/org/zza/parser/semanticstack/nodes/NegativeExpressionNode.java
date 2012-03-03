package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class NegativeExpressionNode extends SemanticNode {

    private SemanticNode content;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        content = stack.pop();
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " " + content.getStringRepresentation();
    }

    @Override
    public String getName() {
        return "NegativeExpression";
    }

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
