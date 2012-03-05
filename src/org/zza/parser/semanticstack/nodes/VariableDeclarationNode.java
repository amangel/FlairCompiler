package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;

public class VariableDeclarationNode extends TwoFieldNode {
    
    @Override
    public String getName() {
        return "VariableDeclaration";
    }
    
    @Override
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
