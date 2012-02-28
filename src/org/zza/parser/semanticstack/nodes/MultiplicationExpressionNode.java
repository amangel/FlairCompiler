package org.zza.parser.semanticstack.nodes;



public class MultiplicationExpressionNode extends TwoFieldNode {

    //protected SemanticNode leftHandSide from TwoFieldNode
    //protected SemanticNode rightHandSide from TwoFieldNode
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public MultiplicationExpressionNode() {
    }

    @Override
    public void printChildren() {
        
    }
    
    @Override
    public String getName() {
        return "MultiplicationExpression";
    }
}
