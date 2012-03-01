package org.zza.visitor;
import org.zza.parser.semanticstack.nodes.*;

public interface NodeVisitor {
	public void visit(AssignmentExpressionNode node);
	
	public void visit(CompoundStatementNode node);
	
	public void visit(DivisionExpressionNode node);
	
	public void visit(FunctionNode node);
	
	public void visit(IdentifierNode node);
	
	public void visit(IntegerNode node);
	
	public void visit(MinusExpressionNode node);
	
	public void visit(MultiplicationExpressionNode node);
	
	public void visit(ParametersNode node);
	
	public void visit(PlusExpressionNode node);
	
	public void visit(ProgramNode node);
	
	public void visit(RealNode node);
	
	public void visit(SemanticNode node);
	
	public void visit(TwoFieldNode node);
	
	public void visit(TypeNode node);
	
	public void visit(VariableDeclarationNode node);
}
