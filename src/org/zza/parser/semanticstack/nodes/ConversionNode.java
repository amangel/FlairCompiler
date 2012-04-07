package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public abstract class ConversionNode extends SemanticNode {

    private SemanticNode nodeToConvert;
    
    public ConversionNode() {
    }
    
    public void convert(SemanticNode node) {
        nodeToConvert = node;
    }

    @Override
    public void runOnSemanticStack(SemanticStack stack) {
    }
    
    @Override
    public String getStringRepresentation() {
        return nodeToConvert.getStringRepresentation();
    }
    
    @Override
    public String getName() {
        return nodeToConvert.getName();
    }
    
    @Override
    public String accept(NodeVisitor visitor) {
        return nodeToConvert.accept(visitor);
    }
    
}
