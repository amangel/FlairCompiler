package org.zza.visitor;
import org.zza.parser.semanticstack.nodes.*;

public interface NodeVisitor {
    public String visit(ProgramNode node);

    public String visit(SemanticNode node);

    public String visit(TwoFieldNode node);

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
	
}
