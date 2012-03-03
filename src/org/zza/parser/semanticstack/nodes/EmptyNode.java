package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class EmptyNode extends SemanticNode {

    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        //TODO: throw exception if run?
    }

    @Override
    public String getStringRepresentation() {
        return "Empty node";
    }

    @Override
    public String getName() {
        return "Empty";
    }

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
 
}
