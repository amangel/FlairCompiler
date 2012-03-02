package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;


public class MarkerNode extends SemanticNode {

    private String value;
    
    @Override
    public void runOnSemanticStack(SemanticStack stack) {
        value = token.getValue();
        System.out.println("######################################################################################");
        System.out.println("creating new marker with value: "+value);
        stack.push(this);
    }

    @Override
    public String getStringRepresentation() {
        return "Marker: "+getName();
    }

    @Override
    public String getName() {
        return value;
    }

    @Override
    public String accept(NodeVisitor visitor) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean isMarker() {
        return true;
    }
}
