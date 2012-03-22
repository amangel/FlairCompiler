package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.*;

public abstract class NodeVisitor {
    
    public String visit(final SemanticNode node) {
        return visit((ProgramNode) node);
    }
    
    public abstract String visit(ProgramNode node);
    
    public abstract String visit(VariableDeclarationNode node);
    
    public abstract String visit(FunctionNode node);
    
    public abstract String visit(ParameterNode node);
    
    public abstract String visit(AssignmentExpressionNode node);
    
    public abstract String visit(CompoundStatementNode node);
    
    public abstract String visit(DivisionExpressionNode node);
    
    public abstract String visit(IdentifierNode node);
    
    public abstract String visit(IntegerNode node);
    
    public abstract String visit(MinusExpressionNode node);
    
    public abstract String visit(MultiplicationExpressionNode node);
    
    public abstract String visit(PlusExpressionNode node);
    
    public abstract String visit(RealNode node);
    
    public abstract String visit(TypeNode node);
    
    public abstract String visit(AllParametersNode node);
    
    public abstract String visit(AllVariableDeclarationsNode node);
    
    public abstract String visit(ArgumentNode node);
    
    public abstract String visit(CompareNode node);
    
    public abstract String visit(ComparisonNode node);
    
    public abstract String visit(WhileExpressionNode node);
    
    public abstract String visit(NegativeExpressionNode node);
    
    public abstract String visit(ProgramHeaderNode node);
    
    public abstract String visit(DeclarationsNode node);
    
    public abstract String visit(PrintStatementNode node);
    
    public abstract String visit(FunctionCallNode node);
    
    public abstract String visit(FunctionHeadingNode node);
    
    public abstract String visit(AllFunctionDeclarationsNode node);
    
    public abstract String visit(FunctionBodyNode node);
    
    public abstract String visit(ReturnStatementNode node);
    
    public abstract String visit(IfStatementNode node);
    
    public abstract String visit(EmptyNode node);
}
