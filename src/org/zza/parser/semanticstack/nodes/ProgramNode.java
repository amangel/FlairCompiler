package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class ProgramNode extends SemanticNode{

    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        //TODO
    }

    @Override
    public String getStringRepresentation() {
        return null;
    }

    @Override
    public int getDepth() {
        return 0;
    }
    
    @Override
    public String getName() {
        return "Program";
    }
    
    @Override
    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
