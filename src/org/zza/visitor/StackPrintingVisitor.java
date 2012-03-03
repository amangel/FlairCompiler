package org.zza.visitor;

import java.util.ArrayList;

import org.zza.parser.semanticstack.nodes.*;

public class StackPrintingVisitor extends NodeVisitor {

    private int depth;
    
    private String getTabs(int count) {
        System.out.println("depth is: "+depth);
        String toReturn = "";
        for (int i = 0; i <= count; i++) {
            toReturn += "  ";
        }
        return toReturn;
    }
    
    @Override
    public String visit(ProgramNode node) {
        
        depth = 0;
        String tabs = getTabs(depth);
        String header = node.getHeader().accept(this);
        String declarations = node.getDeclarations().accept(this);
        String body = node.getbody().accept(this);
        return "Program:\n" + tabs + "Header: "+header + "\n"+ tabs +"Declarations: "+declarations + "\n"+ tabs +"Body: "+body;
    }
    
    @Override
    public String visit(ProgramHeaderNode node) {
        depth++;
        String tabs = getTabs(depth);
        String identifier = node.getIdentifier().accept(this);
        String parameters = node.getParameters().accept(this);
        depth--;
        return "\n"+tabs+"Identifier: "+identifier + "\n"+tabs+"Parameters: "+parameters;
    }

    @Override
    public String visit(VariableDeclarationNode node) {
        
        // TODO Auto-generated method stub
        return "vardec";
    }

    @Override
    public String visit(AllVariableDeclarationsNode node) {
        depth++;
        ArrayList<SemanticNode> declarations = node.getDeclarations();
        String tabs = getTabs(depth);
        String declarationString = tabs;
        for (SemanticNode declaration : declarations) {
            declarationString += declaration.accept(this);
        }
        depth--;
        return tabs + "VariableDeclarations: \n"+declarationString;
    }
    
    @Override
    public String visit(DeclarationsNode node) {
        String variables = node.getVariableDeclarations().accept(this);
        String functions = node.getFunctionDeclarations().accept(this);
        return "\n"+variables + "\n"+functions;
    }

    @Override
    public String visit(FunctionNode node) {
        // TODO Auto-generated method stub
        return "function";
    }

    @Override
    public String visit(ParameterNode node) {
        String tabs = getTabs(depth+1);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        return "\n"+tabs+"param: "+leftHand + " : "+rightHand;
    }

    @Override
    public String visit(AssignmentExpressionNode node) {
        // TODO Auto-generated method stub
        return "assignment";
    }

    @Override
    public String visit(CompoundStatementNode node) {
        // TODO Auto-generated method stub
        return "compound";
    }

    @Override
    public String visit(DivisionExpressionNode node) {
        // TODO Auto-generated method stub
        return "division";
    }

    @Override
    public String visit(IdentifierNode node) {
        return node.getValue();
    }

    @Override
    public String visit(IntegerNode node) {
        return node.getValue();
    }

    @Override
    public String visit(MinusExpressionNode node) {
        // TODO Auto-generated method stub
        return "minus";
    }

    @Override
    public String visit(MultiplicationExpressionNode node) {
        // TODO Auto-generated method stub
        return "multiExp";
    }

    @Override
    public String visit(PlusExpressionNode node) {
        // TODO Auto-generated method stub
        return "plusexp";
    }

    @Override
    public String visit(RealNode node) {
        return node.getValue();
    }

    @Override
    public String visit(TypeNode node) {
        return node.getType();
    }

    @Override
    public String visit(AllParametersNode node) {
        depth++;
        ArrayList<SemanticNode> parameters = node.getParameters();
        String parameterString = getTabs(depth);
        for (SemanticNode parameter : parameters) {
            parameterString += parameter.accept(this);
        }
        depth--;
        return parameterString;
    }


    @Override
    public String visit(ArgumentNode node) {
        // TODO Auto-generated method stub
        return "argument";
    }

    @Override
    public String visit(CompareNode node) {
        // TODO Auto-generated method stub
        return "compare";
    }

    @Override
    public String visit(ComparisonNode node) {
        // TODO Auto-generated method stub
        return "comparison";
    }

    @Override
    public String visit(WhileExpressionNode node) {
        // TODO Auto-generated method stub
        return "while";
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        // TODO Auto-generated method stub
        return "negativeexpression";
    }

    @Override
    public String visit(PrintStatementNode node) {
        // TODO Auto-generated method stub
        return "print";
    }

    @Override
    public String visit(FunctionCallNode node) {
        // TODO Auto-generated method stub
        return "functioncall";
    }

    @Override
    public String visit(FunctionHeadingNode node) {
        // TODO Auto-generated method stub
        return "functionheading";
    }

    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        depth++;
        String tabs = getTabs(depth);
        ArrayList<SemanticNode> functions = node.getFunctions();
        String functionsString = getTabs(depth);
        for (SemanticNode function : functions) {
            functionsString += function.accept(this);
        }
        depth--;
        return  tabs + "FunctionDeclarations: \n"+functionsString;
    }

    @Override
    public String visit(FunctionBodyNode node) {
        // TODO Auto-generated method stub
        return "functionbody";
    }

    @Override
    public String visit(ReturnStatementNode node) {
        // TODO Auto-generated method stub
        return "return";
    }

    @Override
    public String visit(IfStatementNode node) {
        // TODO Auto-generated method stub
        return "if";
    }

    @Override
    public String visit(EmptyNode node) {
        return "empty";
    }
}
