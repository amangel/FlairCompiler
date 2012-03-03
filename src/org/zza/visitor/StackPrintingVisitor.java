package org.zza.visitor;

import java.util.ArrayList;

import org.zza.parser.semanticstack.nodes.*;

public class StackPrintingVisitor extends NodeVisitor {

    private int depth;
    
    private String getTabs(int count) {
        //System.out.println("depth is: "+depth);
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
        String tabs = getTabs(depth+1);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        return "\n" + tabs + leftHand + " : "+rightHand;
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
        return tabs + "VariableDeclarations:"+declarationString;
    }
    
    @Override
    public String visit(DeclarationsNode node) {
        String variables = node.getVariableDeclarations().accept(this);
        String functions = node.getFunctionDeclarations().accept(this);
        return "\n"+variables + "\n"+functions;
    }

    @Override
    public String visit(FunctionNode node) {
        depth++;
        String header = node.getHeader().accept(this);
        String body = node.getBody().accept(this);
        depth--;
        return "Function: " + header + "\n" + body;
    }

    @Override
    public String visit(ParameterNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        depth--;
        return "\n"+tabs+"param: \n"+ tabs +leftHand + "\n"+tabs+rightHand;
    }

    @Override
    public String visit(AssignmentExpressionNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        depth--;
        return "\n"+tabs+"assignment: "+leftHand + " : "+rightHand;    }

    @Override
    public String visit(CompoundStatementNode node) {
        depth++;
        ArrayList<SemanticNode> statements = node.getStatements();
        String tabs = getTabs(depth);
        String statementString = tabs;
        for (SemanticNode statement : statements) {
            statementString += statement.accept(this);
        }
        depth--;
        return "\n" + tabs + "Compound: "+statementString;
    }

    @Override
    public String visit(DivisionExpressionNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        depth--;
        return "\n"+tabs+"division: "+leftHand + " : "+rightHand;
    }

    @Override
    public String visit(IdentifierNode node) {
        return "  Identifier: "+node.getValue();
    }

    @Override
    public String visit(IntegerNode node) {
        return "  Integer: "+ node.getValue();
    }

    @Override
    public String visit(MinusExpressionNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        depth--;
        return "\n"+tabs+"minus: "+leftHand + " : "+rightHand;
    }

    @Override
    public String visit(MultiplicationExpressionNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        depth--;
        return "\n"+tabs+"multiplication: "+leftHand + " : "+rightHand;
    }

    @Override
    public String visit(PlusExpressionNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        depth--;
        return "\n"+tabs+"plus: "+leftHand + " : "+rightHand;
    }

    @Override
    public String visit(RealNode node) {
        return "  Real: "+node.getValue();
    }

    @Override
    public String visit(TypeNode node) {
        return getTabs(depth) + node.getType();
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
        depth++;
        String tabs = getTabs(depth);
        ArrayList<SemanticNode> arguments = node.getArguments();
        String argumentString = tabs;
        for (SemanticNode argument : arguments) {
            argumentString += argument.accept(this);
        }
        depth--;
        return argumentString;
    }

    @Override
    public String visit(CompareNode node) {
        return node.getValue();
    }

    @Override
    public String visit(ComparisonNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLefthand().accept(this);
        String compare = node.getMiddle().accept(this);
        String rightHand = node.getRighthand().accept(this);
        depth--;
        return "\n" + tabs + "Comparison: "+ leftHand + " "+compare+" "+rightHand;
    }

    @Override
    public String visit(WhileExpressionNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        depth--;
        return "\n"+tabs+"While: "+leftHand + " : "+rightHand;
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        depth++;
        String tabs = getTabs(depth);
        String expression = node.getContent().accept(this);
        depth--;
        return "\n" + tabs + "Negative: " + expression;
    }

    @Override
    public String visit(PrintStatementNode node) {
        depth++;
        String tabs = getTabs(depth);
        String arguments = node.getArgument().accept(this);
        depth--;
        return "\n" + tabs + "Print: " + arguments;
    }

    @Override
    public String visit(FunctionCallNode node) {
        depth++;
        String tabs = getTabs(depth);
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        depth--;
        return "\n"+tabs+"funcCall: \n"+ tabs +leftHand + "\n"  + rightHand;
    }

    @Override
    public String visit(FunctionHeadingNode node) {
        depth++;
        String tabs = getTabs(depth);
        String identifier = node.getLefthand().accept(this);
        String parameters = node.getMiddle().accept(this);
        String type = node.getRighthand().accept(this);
        depth--;
        return "\n" + tabs + "funcHeading: \n" + tabs + identifier + "\n" + parameters + "\n" + tabs + "Returns: \n  " + type;
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
        depth++;
        String tabs = getTabs(depth);
        String variables = node.getVariables().accept(this);
        String body = node.getBody().accept(this);
        depth--;
        return "\n" + tabs + "FunctionBody:\n" + variables + "\n" + body;
    }

    @Override
    public String visit(ReturnStatementNode node) {
        depth++;
        String tabs = getTabs(depth);
        String arguments = node.getArguments().accept(this);        
        depth--;
        return "\n" + tabs + "Return:" + arguments;
    }

    @Override
    public String visit(IfStatementNode node) {
        depth++;
        String tabs = getTabs(depth);
        String test = node.getLefthand().accept(this);
        String then = node.getMiddle().accept(this);
        String elseString = node.getRighthand().accept(this);
        depth--;
        return "\n" + tabs + "If:\n" + test + "\n" + then + "\n" + elseString;
    }

    @Override
    public String visit(EmptyNode node) {
        return "empty";
    }
}
