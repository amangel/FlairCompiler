package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;


public class IfStatementNode extends ThreeFieldNode {

    @Override
    public String getName() {
        return "If";
    }

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }

    
}
