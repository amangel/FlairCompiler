package org.zza.codegenerator;

import org.zza.parser.semanticstack.nodes.*;
import org.zza.visitor.NodeVisitor;


public class CodeGeneratingVisitor extends NodeVisitor {

    @Override
    public String visit(ProgramNode node) {
        String s = node.getbody().accept(this);
        s += "HALT 0,0,0";
        String[] parts = s.split("\n");
        String toReturn = "";
        for (int i = 0; i<parts.length; i++) {
            toReturn += "  "+i+":     "+parts[i] + "\n";
        }
        return toReturn ;
    }

    @Override
    public String visit(VariableDeclarationNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ParameterNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(AssignmentExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(CompoundStatementNode node) {
        String toReturn = "";
        for (SemanticNode n : node.getStatements()) {
            toReturn += n.accept(this) + "\n";
        }
        return toReturn.substring(0, toReturn.length()-1);
    }

    @Override
    public String visit(DivisionExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(IdentifierNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(IntegerNode node) {
        return node.getValue();
    }

    @Override
    public String visit(MinusExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(MultiplicationExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(PlusExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(RealNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(TypeNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(AllParametersNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(AllVariableDeclarationsNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ArgumentNode node) {
        String toReturn = "";
        for(SemanticNode n : node.getArguments()) {
            toReturn += n.accept(this) + ",";
        }
        return toReturn;
    }

    @Override
    public String visit(CompareOperatorNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ComparisonNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(WhileExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ProgramHeaderNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(DeclarationsNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     *    0:     LDC 0,1(0)
          1:     OUT  0,1,1
          2:     HALT 0,0,0
     */
    @Override
    public String visit(PrintStatementNode node) {
        String s = node.getArgument().accept(this);
        String[] sBox = s.split(",");
        String toReturn = "";
        for (String nbao : sBox) {
            toReturn += "LDC 0,"+nbao+"(0)\n";
            toReturn += "OUT 0,1,1\n";
        }
        return toReturn;
    }

    @Override
    public String visit(FunctionCallNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionHeadingNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionBodyNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ReturnStatementNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(IfStatementNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(EmptyNode node) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
