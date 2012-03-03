package org.zza.parser.semanticstack.nodes;

import org.zza.visitor.NodeVisitor;


public class FunctionHeadingNode extends ThreeFieldNode {

    @Override
    public String getName() {
        return "FunctionHeading";
    }

    public String accept(NodeVisitor visitor) {
        return visitor.visit(this);
    }
}
