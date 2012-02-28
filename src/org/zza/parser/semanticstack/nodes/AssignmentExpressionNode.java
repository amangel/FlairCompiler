package org.zza.parser.semanticstack.nodes;


public class AssignmentExpressionNode extends TwoFieldNode {

    //protected SemanticNode leftHandSide from TwoFieldNode
    //protected SemanticNode rightHandSide from TwoFieldNode
    //protected SemanticNode parent from SemanticNode
    //protected CompilerToken token from SemanticNode
    
    public AssignmentExpressionNode() {
    }

    @Override
    public void printChildren() {
        
    }
    
    @Override
    public String getName() {
        return "AssignmentExpression";
    }
}
