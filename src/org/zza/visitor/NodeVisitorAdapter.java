package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.*;


public class NodeVisitorAdapter extends NodeVisitor {

    private static String EMPTY = "";
    
    @Override
    public String visit(ProgramNode node) {
        return EMPTY;
    }

    @Override
    public String visit(VariableDeclarationNode node) {
        return EMPTY;
    }

    @Override
    public String visit(FunctionNode node) {
        return EMPTY;
    }

    @Override
    public String visit(ParameterNode node) {
        return EMPTY;
    }

    @Override
    public String visit(AssignmentExpressionNode node) {
        return EMPTY;
    }

    @Override
    public String visit(CompoundStatementNode node) {
        return EMPTY;
    }

    @Override
    public String visit(DivisionExpressionNode node) {
        return EMPTY;
    }

    @Override
    public String visit(IdentifierNode node) {
        return EMPTY;
    }

    @Override
    public String visit(IntegerNode node) {
        return EMPTY;
    }

    @Override
    public String visit(MinusExpressionNode node) {
        return EMPTY;
    }

    @Override
    public String visit(MultiplicationExpressionNode node) {
        return EMPTY;
    }

    @Override
    public String visit(PlusExpressionNode node) {
        return EMPTY;
    }

    @Override
    public String visit(RealNode node) {
        return EMPTY;
    }

    @Override
    public String visit(TypeNode node) {
        return EMPTY;
    }

    @Override
    public String visit(AllParametersNode node) {
        return EMPTY;
    }

    @Override
    public String visit(AllVariableDeclarationsNode node) {
        return EMPTY;
    }

    @Override
    public String visit(ArgumentNode node) {
        return EMPTY;
    }

    @Override
    public String visit(CompareOperatorNode node) {
        return EMPTY;
    }

    @Override
    public String visit(ComparisonNode node) {
        return EMPTY;
    }

    @Override
    public String visit(WhileExpressionNode node) {
        return EMPTY;
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        return EMPTY;
    }

    @Override
    public String visit(ProgramHeaderNode node) {
        return EMPTY;
    }

    @Override
    public String visit(DeclarationsNode node) {
        return EMPTY;
    }

    @Override
    public String visit(PrintStatementNode node) {
        return EMPTY;
    }

    @Override
    public String visit(FunctionCallNode node) {
        return EMPTY;
    }

    @Override
    public String visit(FunctionHeadingNode node) {
        return EMPTY;
    }

    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        return EMPTY;
    }

    @Override
    public String visit(FunctionBodyNode node) {
        return EMPTY;
    }

    @Override
    public String visit(ReturnStatementNode node) {
        return EMPTY;
    }

    @Override
    public String visit(IfStatementNode node) {
        return EMPTY;
    }

    @Override
    public String visit(EmptyNode node) {
        return EMPTY;
    }
    
}
