package org.zza.parser.semanticstack;

import org.zza.scanner.CompilerToken;


public abstract class SemanticNode {
    protected SemanticNode parent;
    protected CompilerToken token;
    
    public abstract void runOnSemanticStack(SemanticStack stack);
    public abstract void printChildren();
    
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
}
