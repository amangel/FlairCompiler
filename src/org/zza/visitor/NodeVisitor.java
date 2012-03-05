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
        if (node instanceof ProgramNode) {
            return visit((ProgramNode) node);
            
        } else if (node instanceof VariableDeclarationNode) {
            return visit((VariableDeclarationNode) node);
            
        } else if (node instanceof FunctionNode) {
            return visit((FunctionNode) node);
            
        } else if (node instanceof ParameterNode) {
            return visit((ParameterNode) node);
            
        } else if (node instanceof AssignmentExpressionNode) {
            return visit((AssignmentExpressionNode) node);
            
        } else if (node instanceof CompoundStatementNode) {
            return visit((CompoundStatementNode) node);
            
        } else if (node instanceof DivisionExpressionNode) {
            return visit((DivisionExpressionNode) node);
            
        } else if (node instanceof IdentifierNode) {
            return visit((IdentifierNode) node);
            
        } else if (node instanceof IntegerNode) {
            return visit((IntegerNode) node);
            
        } else if (node instanceof IntegerNode) {
            return visit((IntegerNode) node);
            
        } else if (node instanceof MinusExpressionNode) {
            return visit((MinusExpressionNode) node);
            
        } else if (node instanceof MultiplicationExpressionNode) {
            return visit((MultiplicationExpressionNode) node);
            
        } else if (node instanceof PlusExpressionNode) {
            return visit((PlusExpressionNode) node);
            
        } else if (node instanceof RealNode) {
            return visit((RealNode) node);
            
        } else if (node instanceof TypeNode) {
            return visit((TypeNode) node);
            
        } else if (node instanceof AllParametersNode) {
            return visit((AllParametersNode) node);
            
        } else if (node instanceof AllVariableDeclarationsNode) {
            return visit((AllVariableDeclarationsNode) node);
            
        } else if (node instanceof ArgumentNode) {
            return visit((ArgumentNode) node);
            
        } else if (node instanceof CompareNode) {
            return visit((CompareNode) node);
            
        } else if (node instanceof ComparisonNode) {
            return visit((ComparisonNode) node);
            
        } else if (node instanceof WhileExpressionNode) {
            return visit((WhileExpressionNode) node);
            
        } else if (node instanceof NegativeExpressionNode) {
            return visit((NegativeExpressionNode) node);
            
        } else if (node instanceof ProgramHeaderNode) {
            return visit((ProgramHeaderNode) node);
            
        } else if (node instanceof DeclarationsNode) {
            return visit((DeclarationsNode) node);
            
        } else if (node instanceof PrintStatementNode) {
            return visit((PrintStatementNode) node);
            
        } else if (node instanceof FunctionCallNode) {
            return visit((FunctionCallNode) node);
            
        } else if (node instanceof FunctionHeadingNode) {
            return visit((FunctionHeadingNode) node);
            
        } else if (node instanceof AllFunctionDeclarationsNode) {
            return visit((AllFunctionDeclarationsNode) node);
            
        } else if (node instanceof FunctionBodyNode) {
            return visit((FunctionBodyNode) node);
            
        } else if (node instanceof ReturnStatementNode) {
            return visit((ReturnStatementNode) node);
            
        } else if (node instanceof IfStatementNode) {
            return visit((IfStatementNode) node);
            
        } else if (node instanceof EmptyNode) {
            return visit((EmptyNode) node);
            
        } else {
            return "Could not find type for : " + node.getStringRepresentation();
        }
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
