package org.zza.visitor;
import org.zza.parser.semanticstack.nodes.*;

public interface NodeVisitor {
	public void visitAssignment(AssignmentExpressionNode node);
	
	public void visitCompoundStatement(CompoundStatementNode node);
	
	public void visitDivisionExp(DivisionExpressionNode node);
	
	public void visitFunction(FunctionNode node);
	
	public void visitIdentifier(IdentifierNode node);
	
	public void visitInteger(IntegerNode node);
	
	public void vistMinusExp(MinusExpressionNode node);
	
	public void visitMultiplicationExp(MultiplicationExpressionNode node);
	
	public void visitParameters(ParametersNode node);
	
	public void visitPlusExp(PlusExpressionNode node);
	
	public void visitProgram(ProgramNode node);
	
	public void visitReal(RealNode node);
	
	public void visitSemantic(SemanticNode node);
	
	public void visitTwoField(TwoFieldNode node);
	
	public void visitType(TypeNode node);
	
	public void visitVarDeclaration(VariableDeclarationNode node);
}
