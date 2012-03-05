package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.AllFunctionDeclarationsNode;
import org.zza.parser.semanticstack.nodes.AllParametersNode;
import org.zza.parser.semanticstack.nodes.AllVariableDeclarationsNode;
import org.zza.parser.semanticstack.nodes.ArgumentNode;
import org.zza.parser.semanticstack.nodes.AssignmentExpressionNode;
import org.zza.parser.semanticstack.nodes.CompareNode;
import org.zza.parser.semanticstack.nodes.ComparisonNode;
import org.zza.parser.semanticstack.nodes.CompoundStatementNode;
import org.zza.parser.semanticstack.nodes.DeclarationsNode;
import org.zza.parser.semanticstack.nodes.DivisionExpressionNode;
import org.zza.parser.semanticstack.nodes.EmptyNode;
import org.zza.parser.semanticstack.nodes.FunctionBodyNode;
import org.zza.parser.semanticstack.nodes.FunctionCallNode;
import org.zza.parser.semanticstack.nodes.FunctionHeadingNode;
import org.zza.parser.semanticstack.nodes.FunctionNode;
import org.zza.parser.semanticstack.nodes.IdentifierNode;
import org.zza.parser.semanticstack.nodes.IfStatementNode;
import org.zza.parser.semanticstack.nodes.IntegerNode;
import org.zza.parser.semanticstack.nodes.MinusExpressionNode;
import org.zza.parser.semanticstack.nodes.MultiplicationExpressionNode;
import org.zza.parser.semanticstack.nodes.NegativeExpressionNode;
import org.zza.parser.semanticstack.nodes.ParameterNode;
import org.zza.parser.semanticstack.nodes.PlusExpressionNode;
import org.zza.parser.semanticstack.nodes.PrintStatementNode;
import org.zza.parser.semanticstack.nodes.ProgramHeaderNode;
import org.zza.parser.semanticstack.nodes.ProgramNode;
import org.zza.parser.semanticstack.nodes.RealNode;
import org.zza.parser.semanticstack.nodes.ReturnStatementNode;
import org.zza.parser.semanticstack.nodes.SemanticNode;
import org.zza.parser.semanticstack.nodes.TypeNode;
import org.zza.parser.semanticstack.nodes.VariableDeclarationNode;
import org.zza.parser.semanticstack.nodes.WhileExpressionNode;

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
