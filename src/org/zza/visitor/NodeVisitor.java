package org.zza.visitor;
import org.zza.parser.semanticstack.nodes.*;

public interface NodeVisitor {
    public String visit(ProgramNode node);

    public String visit(VariableDeclarationNode node);

    public String visit(FunctionNode node);

    public String visit(ParameterNode node);

    public String visit(AssignmentExpressionNode node);
	
	public String visit(CompoundStatementNode node);
	
	public String visit(DivisionExpressionNode node);
	
	public String visit(IdentifierNode node);
	
	public String visit(IntegerNode node);
	
	public String visit(MinusExpressionNode node);
	
	public String visit(MultiplicationExpressionNode node);
	
	public String visit(PlusExpressionNode node);
	
	public String visit(RealNode node);
	
	public String visit(TypeNode node);

	public String visit(AllParametersNode node);

	public String visit(AllVariableDeclarationsNode node);

	public String visit(ArgumentNode node);

	public String visit(CompareNode node);

	public String visit(ComparisonNode node);

	public String visit(WhileExpressionNode node);

	public String visit(NegativeExpressionNode node);

	public String visit(ProgramHeaderNode node);

	public String visit(DeclarationsNode node);

	public String visit(PrintStatementNode node);

	public String visit(FunctionCallNode node);

	public String visit(FunctionHeadingNode node);

	public String visit(AllFunctionDeclarationsNode node);

	public String visit(FunctionBodyNode node);

	public String visit(ReturnStatementNode node);

	public String visit(IfStatementNode node);
	
	public String visit(EmptyNode node);
}
