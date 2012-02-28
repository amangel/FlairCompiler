package org.zza.parser.semanticstack.nodes;



public class DivisionExpressionNode extends TwoFieldNode {

    //protected SemanticNode leftHandSide from TwoFieldNode
    //protected SemanticNode rightHandSide from TwoFieldNode
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public DivisionExpressionNode() {
    }

    @Override
    public void printChildren() {
        
    }

    @Override
    public String getStringRepresentation() {
        return "DivisionExpression:\n\tleft:"+leftHandSide.getStringRepresentation() 
                + "\n\tright:"+rightHandSide.getStringRepresentation();
    }
    
    @Override
    public String getName() {
        return "DivisionExpression";
    }
}
