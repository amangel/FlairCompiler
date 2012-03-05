package org.zza.parser.semanticstack.nodes;

import org.zza.parser.CompilerParser;
import org.zza.parser.ParsingException;
import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class EmptyNode extends SemanticNode {
    
    @Override
    public void runOnSemanticStack(final SemanticStack stack) {
        CompilerParser.parsingErrorEncountered(new ParsingException("Empty node cannot be run on the stack."));
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
    public String accept(final NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
