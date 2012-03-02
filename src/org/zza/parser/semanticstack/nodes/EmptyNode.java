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

    @Override
    public String accept(NodeVisitor visitor) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
