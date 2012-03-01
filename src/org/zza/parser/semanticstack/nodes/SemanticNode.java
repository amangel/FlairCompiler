package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.scanner.CompilerToken;
import org.zza.visitor.NodeVisitor;


public abstract class SemanticNode {
    protected SemanticNode parent;
    protected CompilerToken token;
    
    public abstract void runOnSemanticStack(SemanticStack stack);
    public abstract String getStringRepresentation();
    public abstract String getName();
    public abstract String accept(NodeVisitor visitor);
    public SemanticNode() {}
    
    public void setToken(CompilerToken token) {
        this.token = token;
    }
    public void setParent(SemanticNode parent) {
        this.parent = parent;
    }
    
    public int getDepth() {
        return (parent.getDepth() + 1);
    }
    
    protected String getTabIndentation(int number) {
        String tabs = "";
        for (int i = 0; i < number; i++) {
            tabs += "   ";
        }
        return tabs;
    }
    
    public boolean isMarker() {
        return false;
    }
}
