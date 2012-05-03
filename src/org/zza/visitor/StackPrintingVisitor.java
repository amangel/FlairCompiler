package org.zza.visitor;

import java.util.ArrayList;

import org.zza.parser.semanticstack.nodes.*;

public class StackPrintingVisitor extends NodeVisitor {
    
    private int depth;
    
    public StackPrintingVisitor() {
        depth = 0;
    }
    
    private String getTabs(final int count) {
        String toReturn = "";
        for (int i = 0; i <= count; i++) {
            toReturn += "   ";
        }
        return toReturn;
        
    }
    
    @Override
    public String visit(final ProgramNode node) {
        final String tabs = getTabs(depth);
        final String header = node.getHeader().accept(this);
        final String declarations = node.getDeclarations().accept(this);
        final String body = node.getbody().accept(this);
        return "Program:\n" + tabs + "Header: " + header + "\n" + tabs + "Declarations: " + declarations + "\n" + tabs + "Body: " + body;
    }
    
    @Override
    public String visit(final ProgramHeaderNode node) {
        depth++;
        final String tabs = getTabs(depth);
        final String identifier = node.getIdentifier().accept(this);
        final String parameters = node.getParameters().accept(this);
        depth--;
        return "\n" + tabs + "ProgramHeader:\n" + identifier + parameters;
    }
    
    @Override
    public String visit(final VariableDeclarationNode node) {
        return handleTwoFieldNode(node, "VariableDeclaration");
    }
    
    @Override
    public String visit(final AllVariableDeclarationsNode node) {
        depth++;
        final ArrayList<SemanticNode> declarations = node.getArray();
        final String tabs = getTabs(depth);
        String declarationString = tabs;
        for (final SemanticNode declaration : declarations) {
            declarationString += declaration.accept(this);
        }
        depth--;
        return "\n" + tabs + "VariableDeclarations:\n" + declarationString;
    }
    
    @Override
    public String visit(final DeclarationsNode node) {
        final String variables = node.getVariableDeclarations().accept(this);
        final String functions = node.getFunctionDeclarations().accept(this);
        return "\n" + variables + "\n" + functions;
    }
    
    @Override
    public String visit(final FunctionNode node) {
        depth++;
        final String tabs = getTabs(depth);
        final String header = node.getHeader().accept(this);
        final String body = node.getBody().accept(this);
        depth--;
        return "\n" + tabs + "Function:" + header + "\n" + body;
    }
    
    @Override
    public String visit(final ParameterNode node) {
        return handleTwoFieldNode(node, "Parameter");
    }
    
    @Override
    public String visit(final AssignmentExpressionNode node) {
        return handleTwoFieldNode(node, "Assignment");
    }
    
    @Override
    public String visit(final CompoundStatementNode node) {
        depth++;
        final ArrayList<SemanticNode> statements = node.getStatements();
        final String tabs = getTabs(depth);
        String statementString = tabs;
        for (final SemanticNode statement : statements) {
            statementString += statement.accept(this);
        }
        depth--;
        return "\n" + tabs + "Compound: " + statementString;
    }
    
    @Override
    public String visit(final DivisionExpressionNode node) {
        return handleTwoFieldNode(node, "Division");
    }
    
    @Override
    public String visit(final IdentifierNode node) {
        return handleTerminal(node.getValue(), "Identifier");
    }
    
    @Override
    public String visit(final IntegerNode node) {
        return handleTerminal(node.getValue(), "Integer");
    }
    
    @Override
    public String visit(final MinusExpressionNode node) {
        return handleTwoFieldNode(node, "Minus");
    }
    
    @Override
    public String visit(final MultiplicationExpressionNode node) {
        return handleTwoFieldNode(node, "Multiplication");
    }
    
    @Override
    public String visit(final PlusExpressionNode node) {
        return handleTwoFieldNode(node, "Plus");
    }
    
    @Override
    public String visit(final RealNode node) {
        return handleTerminal(node.getValue(), "Real");
    }
    
    @Override
    public String visit(final TypeNode node) {
        return handleTerminal(node.getType(), "Type");
    }
    
    @Override
    public String visit(final AllParametersNode node) {
        return handleArrayNode(node, "AllParameters");
    }
    
    @Override
    public String visit(final ArgumentNode node) {
        depth++;
        final String tabs = getTabs(depth);
        final ArrayList<SemanticNode> arguments = node.getArguments();
        String argumentString = "";
        for (final SemanticNode argument : arguments) {
            argumentString += argument.accept(this) + "\n";
        }
        depth--;
        return "\n" + tabs + "Arguments:\n" + argumentString;
    }
    
    @Override
    public String visit(final CompareOperatorNode node) {
        return handleTerminal(node.getValue(), "Compare");
    }
    
    @Override
    public String visit(final ComparisonNode node) {
        return handleThreeFieldNode(node, "Comparison");
    }
    
    @Override
    public String visit(final WhileExpressionNode node) {
        return handleTwoFieldNode(node, "While");
    }
    
    @Override
    public String visit(final NegativeExpressionNode node) {
        depth++;
        final String tabs = getTabs(depth);
        final String expression = node.getContent().accept(this);
        depth--;
        return "\n" + tabs + "Negative: " + expression;
    }
    
    @Override
    public String visit(final PrintStatementNode node) {
        depth++;
        final String tabs = getTabs(depth);
        final String arguments = node.getArgument().accept(this);
        depth--;
        return "\n" + tabs + "Print:" + arguments;
    }
    
    @Override
    public String visit(final FunctionCallNode node) {
        return handleTwoFieldNode(node, "FunctionCall");
    }
    
    @Override
    public String visit(final FunctionHeadingNode node) {
        return handleThreeFieldNode(node, "FunctionHeading");
    }
    
    @Override
    public String visit(final AllFunctionDeclarationsNode node) {
        return handleArrayNode(node, "FunctionDeclarations");
    }
    
    @Override
    public String visit(final FunctionBodyNode node) {
        depth++;
        final String tabs = getTabs(depth);
        final String variables = node.getVariables().accept(this);
        final String body = node.getBody().accept(this);
        depth--;
        return "\n" + tabs + "FunctionBody:\n" + variables + "\n" + body;
    }
    
    @Override
    public String visit(final ReturnStatementNode node) {
        depth++;
        final String tabs = getTabs(depth);
        final String arguments = node.getArguments().accept(this);
        depth--;
        return "\n" + tabs + "Return:\n" + arguments;
    }
    
    @Override
    public String visit(final IfStatementNode node) {
        return handleThreeFieldNode(node, "If");
    }
    
    private String handleThreeFieldNode(final ThreeFieldNode node, final String nodeType) {
        depth++;
        final String tabs = getTabs(depth);
        final String left = node.getLefthand().accept(this);
        final String middle = node.getMiddle().accept(this);
        final String right = node.getRighthand().accept(this);
        depth--;
        return "\n" + tabs + nodeType + ":\n" + left + "\n" + middle + "\n" + right;
    }
    
    private String handleTwoFieldNode(final TwoFieldNode node, final String nodeType) {
        depth++;
        final String tabs = getTabs(depth);
        final String left = node.getLeftHand().accept(this);
        final String right = node.getRightHand().accept(this);
        depth--;
        return "\n" + tabs + nodeType + ":\n" + left + "\n" + right;
    }
    
    private String handleArrayNode(final ArrayNode node, final String string) {
        depth++;
        final String tabs = getTabs(depth);
        final ArrayList<SemanticNode> parameters = node.getArray();
        String parameterString = getTabs(depth);
        for (final SemanticNode parameter : parameters) {
            parameterString += parameter.accept(this);
        }
        depth--;
        return "\n" + tabs + string + ":" + parameterString;
    }
    
    private String handleTerminal(final String node, final String type) {
        return getTabs(depth + 1) + type + " : " + node;
    }
    
    @Override
    public String visit(final EmptyNode node) {
        return "empty";
    }
}
