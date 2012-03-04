package org.zza.visitor;

import java.util.ArrayList;

import org.zza.parser.semanticstack.nodes.*;

public class StackPrintingVisitor extends NodeVisitor {

    private int depth;
    
    private String getTabs(int count) {
        String toReturn = "";
        for (int i = 0; i <= count; i++) {
            toReturn += "   ";
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
        return "Program:\n" + tabs + "Header: "+header + "\n"+ tabs + "Declarations: "+declarations + tabs + "Body: "+body;
    }
    
    @Override
    public String visit(ProgramHeaderNode node) {
        depth++;
        String tabs = getTabs(depth);
        String identifier = node.getIdentifier().accept(this);
        String parameters = node.getParameters().accept(this);
        depth--;
        return "\n"+tabs+"ProgramHeader:\n"+identifier + "\n"+tabs+parameters;
    }

    @Override
    public String visit(VariableDeclarationNode node) {
        return handleTwoFieldNode(node, "VariableDeclaration");
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
        return "\n" + tabs + "VariableDeclarations:\n"+declarationString;
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
        String tabs = getTabs(depth);
        String header = node.getHeader().accept(this);
        String body = node.getBody().accept(this);
        depth--;
        return "\n" +tabs + "Function:\n" + header + "\n" + body;
    }

    @Override
    public String visit(ParameterNode node) {
        return handleTwoFieldNode(node, "Parameter");
    }

    @Override
    public String visit(AssignmentExpressionNode node) {
        return handleTwoFieldNode(node, "Assignment");    
    }

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
        return handleTwoFieldNode(node, "Division");
    }

    @Override
    public String visit(IdentifierNode node) {
        return handleTerminal(node.getValue(), "Identifier");
    }

    @Override
    public String visit(IntegerNode node) {
        return handleTerminal(node.getValue(), "Integer");
    }

    @Override
    public String visit(MinusExpressionNode node) {
        return handleTwoFieldNode(node, "Minus");
    }

    @Override
    public String visit(MultiplicationExpressionNode node) {
        return handleTwoFieldNode(node, "Multiplication");
    }

    @Override
    public String visit(PlusExpressionNode node) {
        return handleTwoFieldNode(node, "Plus");
    }

    @Override
    public String visit(RealNode node) {
        return handleTerminal(node.getValue(), "Real");
    }

    @Override
    public String visit(TypeNode node) {
        return handleTerminal(node.getType(), "Type");
    }

    @Override
    public String visit(AllParametersNode node) {
        depth++;
        String tabs = getTabs(depth);
        ArrayList<SemanticNode> parameters = node.getParameters();
        String parameterString = getTabs(depth);
        for (SemanticNode parameter : parameters) {
            parameterString += parameter.accept(this);
        }
        depth--;
        return "\n" + tabs + "AllParameters:\n"+parameterString;
    }

    @Override
    public String visit(ArgumentNode node) {
        depth++;
        String tabs = getTabs(depth);
        ArrayList<SemanticNode> arguments = node.getArguments();
        String argumentString = "";
        for (SemanticNode argument : arguments) {
            argumentString += argument.accept(this);
        }
        depth--;
        return "\n" + tabs + "Arguments:\n"+argumentString;
    }

    @Override
    public String visit(CompareNode node) {
        return handleTerminal(node.getValue(), "Compare");
    }

    @Override
    public String visit(ComparisonNode node) {
        return handleThreeFieldNode(node, "Comparison");
    }

    @Override
    public String visit(WhileExpressionNode node) {
        return handleTwoFieldNode(node, "While");
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
        return "\n" + tabs + "Print:" + arguments;
    }

    @Override
    public String visit(FunctionCallNode node) {
        return handleTwoFieldNode(node, "FunctionCall");
    }

    @Override
    public String visit(FunctionHeadingNode node) {
        return handleThreeFieldNode(node, "FunctionHeading");
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
        return  "\n" + tabs + "FunctionDeclarations: \n"+functionsString;
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
        return "\n" + tabs + "Return:\n" + arguments;
    }

    @Override
    public String visit(IfStatementNode node) {
        return handleThreeFieldNode(node, "If");
    }

    private String handleThreeFieldNode(ThreeFieldNode node, String nodeType) {
        depth++;
        String tabs = getTabs(depth);
        String left = node.getLefthand().accept(this);
        String middle = node.getMiddle().accept(this);
        String right = node.getRighthand().accept(this);
        depth--;
        return "\n" + tabs + nodeType + ":\n" + left + "\n" + middle + "\n" + right;
    }
    
    private String handleTwoFieldNode(TwoFieldNode node, String nodeType) {
        depth++;
        String tabs = getTabs(depth);
        String left = node.getLeftHand().accept(this);
        String right= node.getRightHand().accept(this);
        depth--;
        return "\n" + tabs + nodeType + ":\n" + left + "\n" + right;
    }

    private String handleTerminal(String node, String type) {
        return getTabs(depth+1) + type + " : " + node;
    }
    
    @Override
    public String visit(EmptyNode node) {
        return "empty";
    }
}
